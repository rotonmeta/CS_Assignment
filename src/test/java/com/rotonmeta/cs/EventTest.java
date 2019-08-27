package com.rotonmeta.cs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class EventTest {

    @Test
    public void constructorTest() {
        Event event = new Event("roton", "APPLICATION_LOG", "123.456", 8, true);
        assertEquals("id: roton, type: APPLICATION_LOG, host: 123.456, duration: 8, alert: true" , event.toString());
    }

    @Test
    public void getterAndSetterTest(){
        Event event = new Event();
        event.setId("roton");
        event.setType("SERVER_LOG");
        event.setHost("123");
        event.setDuration(8);
        event.setAlert(true);

        assertEquals("roton", event.getId());
        assertEquals("SERVER_LOG", event.getType());
        assertEquals("123", event.getHost());
        assertEquals(8, event.getDuration());
        assertEquals(true, event.isAlert());
    }
}
