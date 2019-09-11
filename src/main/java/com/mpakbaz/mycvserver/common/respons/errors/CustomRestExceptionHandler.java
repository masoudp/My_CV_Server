package com.mpakbaz.mycvserver.common.respons.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String UNEXPECTED_ERROR = "Exception.unexpected";

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRestExceptionHandler.class);


    @Autowired
    MessageSource messageSource;

// 400

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            ex.printStackTrace();
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);

        LOGGER.error("[Bind Exception] {} {} {}", ex.getClass(), ex.getMessage(), errors);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, Locale locale) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getMessage());


        LOGGER.error("[Bad Request] {} {}", ex.getClass().getName(), ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getMessage());

        LOGGER.error("[HttpMessage Not Readable] {} {}", ex.getClass().getName(), ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error);

        LOGGER.error("[Type Mismatch] {} {} - error : {}", ex.getClass().getName(), ex.getMessage(), error);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String error = ex.getRequestPartName() + " part is missing";
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error);

        LOGGER.error("[MissingServletRequestPart] {} {} - error : {}", ex.getClass().getName(), ex.getMessage(), error);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error);
        LOGGER.error("[MissingServletRequestParameter] {} {} - error : {}", ex.getClass().getName(), ex.getMessage(), error);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        String errorMessage = messageSource.getMessage("ValidationEerror", null, request.getLocale());

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());

            errors.add(messageSource.getMessage(error.getDefaultMessage(), new Object[0], request.getLocale()));
        }
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errorMessage, errors);

        LOGGER.error("[MethodArgumentNotValid] {} {} - errors : {}", ex.getClass().getName(), ex.getMessage(), errors);
//        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        //todo : fix errorMessage
//        String errorMessage = messageSource.getMessage("ValidationEerror", null, request.getLocale());

        final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error);

        LOGGER.error("[MethodArgumentTypeMismatch] {} {} - error : {}", ex.getClass().getName(), ex.getMessage(), error);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        //todo : fix errorMessage
//        String errorMessage = messageSource.getMessage("ValidationEerror", null, request.getLocale());

        final List<String> errors = new ArrayList<>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        }
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);

        LOGGER.error("[ConstraintViolation] {} {} - errors : {}", ex.getClass().getName(), ex.getMessage(), errors);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    // 403
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(final Exception ex, final WebRequest request) {

        String errorMessage = messageSource.getMessage("AbstractAccessDecisionManager.accessDenied", null, request.getLocale());

        LOGGER.error("[AccessDeniedException] {} {} - error : {}", ex.getClass().getName(), ex.getMessage(), "request" + request.getUserPrincipal());
        ex.printStackTrace();
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }


    //database
    @ExceptionHandler({org.springframework.dao.DataAccessResourceFailureException.class})
    public ResponseEntity<Object> handleAccessDatabase(final Exception ex, final WebRequest request) {

        String errorMessage = messageSource.getMessage("Database.Concoction", null, request.getLocale());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errorMessage, new ArrayList<>());

        LOGGER.error("[handle AccessDatabase] - error : {}", ex.getMessage());
        //ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }


    // 404

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), error);

        LOGGER.error("[NoHandlerFoundException] {} {} - error : {}", ex.getClass().getName(), ex.getMessage(), error);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

// 405

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        final ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(), builder.toString());

        LOGGER.error("[HttpRequestMethodNotSupported] {} {} - errors : {}", ex.getClass().getName(), ex.getMessage(), builder);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

// 415

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

        final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage(), builder.substring(0, builder.length() - 2));

        LOGGER.error("[HttpMediaTypeNotSupported] {} {} - errors : {}", ex.getClass().getName(), ex.getMessage(), builder);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

// 500

    @ExceptionHandler({org.springframework.web.multipart.MultipartException.class})
    public ResponseEntity<Object> multipartException(final Exception ex, Locale locale) {
        String errorMessage = messageSource.getMessage("Multipart.FileSize", null, locale);

        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, new ArrayList<>());


        LOGGER.error("[FileUploadError] {} {}", ex.getClass().getName(), ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, Locale locale) {
        String errorMessage;
        try {
            errorMessage = messageSource.getMessage(ex.getMessage(), null, locale);
        } catch (NoSuchMessageException e) {
            errorMessage = ex.getMessage();
        }
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, new ArrayList<>());


        LOGGER.error("[NoHandlerFoundException] {} {}", ex.getClass().getName(), ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
