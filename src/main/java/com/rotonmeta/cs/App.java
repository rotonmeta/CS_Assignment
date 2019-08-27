package com.rotonmeta.cs;

import java.io.IOException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class App {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main( String[] args ) throws IOException{

        /*
        Checks if user has passed the path of te log file.
        If not, it uses the "server.log" file contained in the application directory.
        */
        String filePath;
        if ((args == null) || (args.length == 0)){
            filePath = "src/main/resources/server.log";
        } else {
            filePath = args[0];
        }

        /*
        Analyzes log file and puts in a list all the Event instances found.
        The list is ready to be saved in the database.
        */
        LogFileManager logManager = new LogFileManager(filePath);
        List<String> idList = logManager.getAllIds();
        List<Event> eventList = logManager.getAllEvents(idList);

        /*
        Deletes all existing events saved in the database.
        Saves the list of events created before in the database.
        Closes the connection to the database.
        */
        DatabaseManager dbManager = DatabaseManager.getInstance();
        dbManager.deleteAllEvents();
        dbManager.addEvents(eventList);
        dbManager.close();
    }
}
