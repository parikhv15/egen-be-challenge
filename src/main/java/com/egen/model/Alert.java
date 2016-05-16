package com.egen.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.springframework.data.annotation.Id;

/**
 * Created by parikhv on 5/14/16.
 */

@Entity("alerts")
public class Alert {

    @Id
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Property("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Property("timeStamp")
    private long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Property("weight")
    private int weight;

    public Alert(String type, long timeStamp, int weight) {
        this.timeStamp = timeStamp;
        this.type = type;
        this.weight = weight;
    }

    public Alert() {
    }
}
