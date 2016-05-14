package com.egen.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.springframework.data.annotation.Id;

/**
 * Created by parikhv on 5/14/16.
 */

@Entity("metrics")
public class Metric {

    @Id
    private ObjectId id;

    @Property("timeStamp")
    private long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Property("value")
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {

        return "Metric [TimeStamp = " + timeStamp + ", " + "Value = " + value + "]";
    }
}
