package com.bigbull.schedulingApp.Controller;

import com.bigbull.schedulingApp.Dto.ResponseDto;
import com.bigbull.schedulingApp.service.BaseService;
import com.google.api.services.calendar.model.Events;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BaseController {

    @Autowired
    BaseService baseService;
    @GetMapping(value = "/get-events")
    public ResponseEntity getEvents(@RequestParam String email) {
        log.info("BaseController_getEvents Params {} ",email);
        ResponseDto<Events> responseDto =  baseService.getEvents(email);
        return ResponseEntity.ok(responseDto);
    }
}
