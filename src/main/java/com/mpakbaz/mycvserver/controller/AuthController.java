package com.mpakbaz.mycvserver.controller;

import com.mpakbaz.mycvserver.domain.enums.SocialStatus;
import com.mpakbaz.mycvserver.security.JwtAuthenticationRequest;
import com.mpakbaz.mycvserver.security.JwtTokenUtil;
import com.mpakbaz.mycvserver.security.service.JwtAuthenticationResponse;
import com.mpakbaz.mycvserver.tools.Constants;
import com.mpakbaz.mycvserver.DTO.UserDOT;
import com.mpakbaz.mycvserver.common.respons.ResponseSingle;
import com.mpakbaz.mycvserver.domain.Authority;
import com.mpakbaz.mycvserver.domain.User;
import com.mpakbaz.mycvserver.domain.enums.AuthorityName;
import com.mpakbaz.mycvserver.domain.enums.UserType;
import com.mpakbaz.mycvserver.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.implementation.bytecode.Throw;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.logging.Logger;

@Api(tags = {"AUTH"})

@RestController
@RequestMapping(value = "/api/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    Logger logger = Logger.getGlobal();

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserRepository userRepository;


    @PersistenceContext
    private EntityManager em;

    @Autowired
    MessageSource messageSource;


    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private AuthenticationManager authenticationManager;



    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    @ApiOperation(value = "Login user", nickname = "login", notes = "Before use replace {TOKEN} with this token 'Bearer {TOKEN}'")
    public ResponseEntity<ResponseSingle<JwtAuthenticationResponse>> login
            (@Valid @RequestBody JwtAuthenticationRequest authenticationRequest, Device device
                    , HttpServletRequest httpreq)
            throws RuntimeException {


        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken namePassToken = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername().toLowerCase(),
                    authenticationRequest.getPassword()
            );

            // Perform the security
            authentication = authenticationManager.authenticate(namePassToken);


            SecurityContextHolder.getContext().setAuthentication(authentication);

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final User user = userRepository.findByEmail(userDetails.getUsername().toLowerCase());
            final String token = jwtTokenUtil.generateToken(userDetails, device);


            JwtAuthenticationResponse jwtAuthenticationResponse = JwtAuthenticationResponse
                    .builder()
                    .authorities(user.getAuthorities())
                    .id(user.getId())
                    .fullName(user.getFullName())
                    .status(SocialStatus.LOGINED)
                    .token(token)
                    .build();

            // Return the token
            return new ResponseEntity<>(new ResponseSingle<>(jwtAuthenticationResponse), HttpStatus.OK);

        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Auth.NotValid");
        }

    }

    private String getTokenAuth(User user, Device device) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        if (userDetails.isEnabled()) {
            return jwtTokenUtil.generateToken(userDetails, device);

        } else
            throw new IllegalArgumentException("Auth.NotEnabled");

    }

}
