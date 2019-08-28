package com.rotonmeta.cs;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Entity
public class Event implements Serializable{
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LogManager.getLogger(Event.class);

    public Event(){}

    public Event(String id, String type, String host, int duration, boolean alert){
        super();
        this.id = id;
        this.type = type;
        this.host = host;
        this.duration = duration;
        this.alert = alert;

        LOG.debug(this::toString);
    }

    @Override
    public String toString(){
        return "id: " + id + ", type: " + type + ", host: " + host + ", duration: " + duration + ", alert: " + alert;
    }

    @Id
    @Column(unique=true, nullable=false)
    private String id;

    @Column(nullable = false)
    private int duration;

    @Column()
    private String type;

    @Column()
    private String host;

    @Column(nullable = false)
    private boolean alert;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }






}
