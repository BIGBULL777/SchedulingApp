package com.bigbull.schedulingApp.handler;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
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
    private static String appName;

    @Value("${max.results}")
    private static int maxResults;
    public Events getEvents() {
        try {
            DateTime now = new DateTime(System.currentTimeMillis());
            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, calendarQuickstart.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(appName)
                .build();
            return service.events().list("primary")
                .setMaxResults(maxResults)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

