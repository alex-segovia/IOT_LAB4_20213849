package com.example.lab4_20213849.Dtos;

import java.io.Serializable;

public class EventDto implements Serializable {
    private Event[] events;

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
