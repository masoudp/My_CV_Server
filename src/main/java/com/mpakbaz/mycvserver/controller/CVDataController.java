package com.mpakbaz.mycvserver.controller;


import com.mpakbaz.mycvserver.DTO.CVDataDTO;
import com.mpakbaz.mycvserver.common.respons.ResponseSingle;
import com.mpakbaz.mycvserver.common.util.DotUtils;
import com.mpakbaz.mycvserver.common.util.UserUtil;
import com.mpakbaz.mycvserver.domain.CVData;
import com.mpakbaz.mycvserver.domain.User;
import com.mpakbaz.mycvserver.repository.CVDataRepository;
import com.mpakbaz.mycvserver.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Api(tags = {"CVDataController"})
@RequestMapping(value = "/api/cvData", produces = MediaType.APPLICATION_JSON_VALUE)
public class CVDataController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    CVDataRepository cvDataRepository;


    DotUtils<CVData, CVDataDTO> toolsDOT = new DotUtils<>(CVDataDTO.class, CVData.class);


    @ApiOperation(value = "Save My CV", nickname = "saveMyCv")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ResponseSingle<CVDataDTO>> saveMyCv(@Valid @RequestBody CVDataDTO entity, HttpServletRequest request) {

        String email = UserUtil.getUser().getEmail();
        CVData oldData = cvDataRepository.findByEmail(email);
        if (oldData != null) {
            cvDataRepository.delete(oldData);
        }

        CVData dataEntity = toolsDOT.convertFromDto(entity);

        User user = UserUtil.getUser();

        dataEntity.setEmail(user.getEmail());
        CVData created = this.cvDataRepository.save(dataEntity);

        CVDataDTO createdDto = toolsDOT.convertToDto(created);
        return new ResponseEntity<>(new ResponseSingle<>(createdDto), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get My CV", nickname = "getMyCv")
    @RequestMapping(value = "/getMyCv", method = RequestMethod.GET)
    public ResponseEntity<ResponseSingle<CVDataDTO>> getMyCv(
            HttpServletRequest req
    ) {

        String email = UserUtil.getUser().getEmail();
        CVData cvData = cvDataRepository.findByEmail(email);
        CVDataDTO cvDataDTO = toolsDOT.convertToDto(cvData);

        return new ResponseEntity<>(new ResponseSingle<>(cvDataDTO), HttpStatus.OK);

    }

}