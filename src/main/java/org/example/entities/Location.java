package org.example.entities;

import org.example.repository.HasID;

public class Location implements HasID {
    private String id;

    public Location(String id) {
        setID(id);
    }

    public String getID(){
        return this.id;
    }

    public void setID(String id) {
        this.id = id;
    }
}
