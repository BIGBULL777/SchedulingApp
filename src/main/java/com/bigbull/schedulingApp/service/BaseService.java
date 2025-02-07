package com.bigbull.schedulingApp.service;

import com.bigbull.schedulingApp.handler.GoogleCalDevHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class BaseService {
    @Autowired
    GoogleCalDevHandler googleCalDevHandler;

    public void getEvents() {
        googleCalDevHandler.getEvents();
    }
}
