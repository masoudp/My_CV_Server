package com.mpakbaz.mycvserver.config;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.schema.ResolvedTypes;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.contexts.ModelContext;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.spi.schema.contexts.ModelContext.inputParam;

@Component
@Order
public class OperationPageableParameterReader implements OperationBuilderPlugin {
    private final TypeNameExtractor nameExtractor;
    private final TypeResolver resolver;
    private final ResolvedType pageableType;

    @Autowired
    public OperationPageableParameterReader(TypeNameExtractor nameExtractor, TypeResolver resolver) {
        this.nameExtractor = nameExtractor;
        this.resolver = resolver;
        this.pageableType = resolver.resolve(Pageable.class);
    }

    @Override
    public void apply(OperationContext context) {
        List<ResolvedMethodParameter> methodParameters = context.getParameters();
        List<Parameter> parameters = newArrayList();

        for (ResolvedMethodParameter methodParameter : methodParameters) {
            ResolvedType resolvedType = methodParameter.getParameterType();

            if (pageableType.equals(resolvedType)) {
                ParameterContext parameterContext = new ParameterContext(methodParameter,
                        new ParameterBuilder(),
                        context.getDocumentationContext(),
                        context.getGenericsNamingStrategy(),
                        context);
                Function<ResolvedType, ? extends ModelReference> factory = createModelRefFactory(parameterContext);

                ModelReference intModel = factory.apply(resolver.resolve(Integer.TYPE));
                ModelReference strModel = factory.apply(resolver.resolve(String.class));

                parameters.add(new ParameterBuilder()
                        .parameterType("query")
                        .name("q")
                        .modelRef(strModel)
                        .allowMultiple(true)
                        .description("searching/filtering Data by very simple fields.   " +
                                "for example: firstName:ali,age>25   " +
                                "you Can use Search Operation : , ! , > , < , ~ , * , OR, AND, ) (.   "+
                                "Use , for AND -> Sample AND: (created:1),(created>3).   "+
                                "Use ,| for OR -> Sample OR: (created:1),|(created>3) "+
                                "Use for (Like %Dawson%) -> title:*Dawson*"
                        )
                        .build());

                parameters.add(new ParameterBuilder()
                        .parameterType("query")
                        .name("page")
                        .modelRef(intModel)
                        .description("Results page you want to retrieve (0..N)").build());
                parameters.add(new ParameterBuilder()
                        .parameterType("query")
                        .name("size")
                        .modelRef(intModel)
                        .description("Number of records per page").build());
                parameters.add(new ParameterBuilder()
                        .parameterType("query")
                        .name("sort")
                        .modelRef(strModel)
                        .allowMultiple(true)
                        .description("Sorting criteria in the format: property(,asc|desc). "
                                + "Default sort order is ascending. "
                                + "Multiple sort criteria are supported.")
                        .build());
                context.operationBuilder().parameters(parameters);
            }
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    private Function<ResolvedType, ? extends ModelReference> createModelRefFactory(ParameterContext context) {
        ModelContext modelContext = inputParam(
                context.getGroupName(),
                context.resolvedMethodParameter().getParameterType(),
                context.getDocumentationType(),
                context.getAlternateTypeProvider(),
                context.getGenericNamingStrategy(),
                context.getIgnorableParameterTypes());
        return ResolvedTypes.modelRefFactory(modelContext, nameExtractor);
    }
}