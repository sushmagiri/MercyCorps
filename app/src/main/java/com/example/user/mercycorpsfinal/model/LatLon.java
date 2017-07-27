package com.example.user.mercycorpsfinal.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by root on 7/25/17.
 */
public class LatLon implements Serializable{
    String name,location;
    Double lat,lon;
    ArrayList<Contact> contacts;

   public LatLon(){}

    public LatLon(String name, String location, Double lat, Double lon, ArrayList<Contact> contacts) {
        this.name = name;
        this.location = location;
        this.lat = lat;
        this.lon = lon;
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
