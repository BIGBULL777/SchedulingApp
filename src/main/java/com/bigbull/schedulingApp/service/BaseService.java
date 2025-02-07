package com.bigbull.schedulingApp.service;

import com.bigbull.schedulingApp.Dto.ResponseDto;
import com.bigbull.schedulingApp.handler.GoogleCalDevHandler;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class BaseService {

    @Autowired
    GoogleCalDevHandler googleCalDevHandler;

    public ResponseDto<com.google.api.services.calendar.model.Calendar> getEvents(String email) {
        com.google.api.services.calendar.model.Calendar calendar = googleCalDevHandler.getEvents(email);
        if (calendar == null) {
            return ResponseDto.<com.google.api.services.calendar.model.Calendar>builder()
                .message("Error in getting events")
                .status("Failed")
                .email(email)
                .data(null)
                .build();
        }
        log.info("Events: {}", calendar);
        return ResponseDto.<com.google.api.services.calendar.model.Calendar>builder()
            .message("Events fetched successfully")
            .status("Success")
            .email(email)
            .data(calendar)
            .build();
    }
}
