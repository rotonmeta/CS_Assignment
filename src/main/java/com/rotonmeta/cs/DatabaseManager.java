package com.rotonmeta.cs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DatabaseManager {

    private static final Logger LOG = LogManager.getLogger(DatabaseManager.class);

    private static DatabaseManager instance = null;
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Session session ;

    private DatabaseManager(){
        this.session = sessionFactory.openSession();
    }

    public static DatabaseManager getInstance(){
        if (instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void deleteAllEvents(){
        session.beginTransaction();
        session.<String>createQuery("Delete from Event").executeUpdate();
        session.getTransaction().commit();

        LOG.info("Deleted all Events from Database");
    }

    public void addEvents(List<Event> eventList){
        session.beginTransaction();
        for (Event ev: eventList) {
            session.save(ev);
        }
        session.getTransaction().commit();

        LOG.info("Added all Events in the database");
    }

    public void close(){
        session.close();
        sessionFactory.close();
    }




}
