package com.bigbull.schedulingApp.handler;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import org.springframework.beans.factory.annotation.Value;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


@Slf4j
@NoArgsConstructor
@Component
@AllArgsConstructor
public class GoogleCalDevHandler {

    @Autowired
    private CalendarQuickstart calendarQuickstart;
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();


    @Value("${google.calendar.appName}")
    String appName;

    @Value("${max.results}")
    int maxResults;
    public  com.google.api.services.calendar.model.Calendar getEvents(String email) {
        try {
            log.info(maxResults+" appName "+appName);
            DateTime now = new DateTime(System.currentTimeMillis());
            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Credential credentials = calendarQuickstart.getCredentials(HTTP_TRANSPORT);
            Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY,credentials)
                .setApplicationName(appName)
                .build();
            com.google.api.services.calendar.model.Calendar calendar = service.calendars().get(email).execute();
            return calendar;
        } catch (GeneralSecurityException | IOException e) {
            log.error("Error in getting events", e);
            return null;
        }
    }
}

