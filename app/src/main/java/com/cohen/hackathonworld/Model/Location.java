package com.cohen.hackathonworld.Model;

public class Location {
    public double lat;
    public double log;

    public Location() {
        this.lat = 0;
        this.log = 0;
    }

    public Location(double lat, double log) {
        super();
        this.lat = lat;
        this.log = log;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

}
