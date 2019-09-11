package com.mpakbaz.mycvserver.controller;


import com.mpakbaz.mycvserver.common.respons.ResponseSingle;
import com.mpakbaz.mycvserver.domain.Authority;
import com.mpakbaz.mycvserver.domain.User;
import com.mpakbaz.mycvserver.domain.enums.AuthorityName;
import com.mpakbaz.mycvserver.repository.AuthorityRepository;
import com.mpakbaz.mycvserver.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Api(tags = {"TestFillDB"})
@RequestMapping(value = "/api/testFillDB", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    private final UserRepository userRepository;

    private final AuthorityRepository roleRepository;

    @Autowired
    public TestController(UserRepository userRepository,AuthorityRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @ApiOperation(value = "", nickname = "fillAll")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<ResponseSingle<String>> fillAll() throws Exception {
        fakeFillDatabase();
        return new ResponseEntity<>(new ResponseSingle<>("SUCCESS"), HttpStatus.OK);
    }

    private void fakeFillDatabase() throws Exception {



        //User
        List<User> users = getUsers();


    }


    public static void fillAuthoritiesTable(AuthorityRepository roleRepository) {
        for (AuthorityName authorityName : AuthorityName.values()) {
            Authority role = new Authority();
            role.setName(authorityName);
            roleRepository.save(role);
        }
    }

    private List<User> getUsers() {
        if (roleRepository.count() == 0)
            fillAuthoritiesTable(roleRepository);


        //user
        Authority adminAuth = roleRepository.findByName(AuthorityName.ROLE_ADMIN);
//        Authority userAuth = roleRepository.findByName(AuthorityName.ROLE_USER);
        String mainPassword = new BCryptPasswordEncoder().encode("123456");


        List<User> users;
        if (userRepository.count() == 0) {
            users = new ArrayList<>();

            User user_masoud = new User();
            user_masoud.setEnabled(true);
            user_masoud.setFullName("masoud pakbaz");
            user_masoud.setEmail("masoud.pakbaz80@gmail.com");
            user_masoud.setPassword(mainPassword);
            user_masoud.setAuthorities(Arrays.asList(adminAuth));

            users.add(user_masoud);


            userRepository.save(users);
        }
        return userRepository.findAll();
    }
}