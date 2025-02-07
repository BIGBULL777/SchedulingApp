package com.bigbull.schedulingApp.service;

import com.bigbull.schedulingApp.Dto.ResponseDto;
import com.bigbull.schedulingApp.handler.GoogleCalDevHandler;
import com.google.api.services.calendar.model.Events;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class BaseService {

    @Autowired
    GoogleCalDevHandler googleCalDevHandler;

    public ResponseDto<Events> getEvents(String email) {
        Events events = googleCalDevHandler.getEvents();
        log.info("Events: {}", events);
        return ResponseDto.<Events>builder()
                .message("Events fetched successfully")
                .status("Success")
                .email(email)
                .data(events)
                .build();
    }
}
