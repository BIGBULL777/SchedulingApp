package com.bigbull.schedulingApp.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BaseController {
    @GetMapping(value = "/get-events")
    public ResponseEntity<?> getEvents(@RequestParam String email) {
        log.info("BaseController_getEvents Params {} ",email);
        return ResponseEntity.ok("Events");
    }
}
