package com.rotonmeta.cs;

import org.hibernate.Session;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatabaseManagerTest {

    @Test
    public void getInstanceTest(){
        DatabaseManager dbm = DatabaseManager.getInstance();
        assertNotNull(dbm);
    }

    @Test
    public void addEventsTest(){
        DatabaseManager dbm = DatabaseManager.getInstance();
        Event test = new Event("test1", "null", "null", 7, true);
        List<Event> testList = new ArrayList<>();
        testList.add(test);

        Session session = dbm.getSession();

        session.delete(test);

        dbm.addEvents(testList);

        Event dbEvent = session.get(Event.class, "test1");

        assertEquals(test.getId(), dbEvent.getId());

        session.beginTransaction();
        session.delete(test);
        session.getTransaction().commit();

    }

    @Test
    public void deleteAllEventsTest(){
        DatabaseManager dbm = DatabaseManager.getInstance();
        Event test = new Event("test2", "null", "null", 7, true);
        List<Event> testList = new ArrayList<>();
        testList.add(test);

        dbm.addEvents(testList);
        dbm.deleteAllEvents();

        Session session = dbm.getSession();
        Event dbEvent = session.get(Event.class, "test1");

        assertEquals(null, dbEvent);

    }

    @Test
    public void closeTest(){
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.close();

        assertEquals(false, dbm.getSession().isOpen());
    }
}
