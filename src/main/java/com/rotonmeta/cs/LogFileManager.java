package com.rotonmeta.cs;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogFileManager {
    private static final Logger LOG = LogManager.getLogger(LogFileManager.class);

    private JsonParser parser;
    private String filePath;

    public LogFileManager(String fileName){
        this.parser = new JsonParser();
        this.filePath = fileName;
    }

    public List<String> getAllIds() throws IOException{
        LOG.info("Getting all event IDs from log file");

        List<String> idList;
        try (Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
            idList = fileStream
                    .map(el -> el.split("\"", 5)[3])
                    .distinct()
                    .collect(Collectors.toList());
        }

        LOG.debug("EventIDs: {}", idList.toString());
        LOG.info("Finished getting event Ids");
        return idList;
    }

    public Event getEvent(String id) throws IOException {
        List<JsonObject> eventLog;
        Event event;

        try (Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
            eventLog = fileStream
                    .map(el -> parser.parse(el).getAsJsonObject())
                    .filter(el -> el.get("id").getAsString().equals(id))
                    .collect(Collectors.toList());

            String type = null;
            if (eventLog.get(0).has("type")) {
                type = eventLog.get(0).get("type").getAsString();
            }

            String host = null;
            if (eventLog.get(0).has("host")) {
                host = eventLog.get(0).get("host").getAsString();
            }

            int ts1 = eventLog.get(0).get("timestamp").getAsInt();
            int ts2 = eventLog.get(1).get("timestamp").getAsInt();

            int duration = Math.abs(ts1 - ts2);

            boolean alert = false;
            if (duration > 4) {
                alert = true;
            }

            event = new Event(id, type, host, duration, alert);
        }

        return event;
    }

    public List<Event> getAllEvents(List<String> idList) throws IOException {
        LOG.info("Getting all event data as Event.class instances");

        List<Event> eventList = new ArrayList<>();
        for(String id: idList){
            Event ev = this.getEvent(id);
            eventList.add(ev);
        }

        LOG.info("Finished getting Event instances");
        return eventList;
    }

    public String getFilePath() {
        return filePath;
    }



}
