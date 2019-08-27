package com.rotonmeta.cs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFileManagerTest {

    @Test
    public void constructorTest(){
        LogFileManager lfm = new LogFileManager("test.log");
        assertEquals("test.log", lfm.getFilePath());
    }

    @Test
    public void getAllIdsTest() throws IOException {
        LogFileManager lfm = new LogFileManager("src/test/resources/test.log");
        assertEquals("scsmbstgrb", lfm.getAllIds().get(0));
    }


    @Test
    public void getEventTest() throws IOException{
        LogFileManager lfm = new LogFileManager("src/test/resources/test.log");
        Event actual = lfm.getEvent("scsmbstgrb");
        Event expected = new Event("scsmbstgrb", null, null, 5, true);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getHost(), actual.getHost());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getDuration(), actual.getDuration());
        assertEquals(expected.isAlert(), actual.isAlert());
    }

    @Test
    public void getAllEventsTest()throws IOException{
        LogFileManager lfm = new LogFileManager("src/test/resources/test.log");
        List<Event> actual = lfm.getAllEvents(lfm.getAllIds());

        Event exp = new Event("scsmbstgrb", null, null, 5, true);
        List<Event> expected = new ArrayList<>();
        expected.add(exp);

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getHost(), actual.get(0).getHost());
        assertEquals(expected.get(0).getType(), actual.get(0).getType());
        assertEquals(expected.get(0).getDuration(), actual.get(0).getDuration());
        assertEquals(expected.get(0).isAlert(), actual.get(0).isAlert());
    }


}
