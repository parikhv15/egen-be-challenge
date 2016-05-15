package com.egen.dao;

import com.egen.MorphiaConfig;
import com.egen.model.Alert;
import com.egen.model.Metric;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vrajp on 5/15/2016.
 */

@Component
public class AlertDAO {


    Datastore datastore;

    AlertDAO() {
        datastore = MorphiaConfig.getInstance().getDatastore();
    }

    public List<Alert> read () {
        Query<Alert> query = datastore.createQuery(Alert.class);

        return query.asList();
    }

    public List<Alert> readByRange(long startTime, long endTime) {
        Query<Alert> query = datastore.createQuery(Alert.class)
                .filter("timeStamp >=", startTime).filter("timeStamp <=", endTime);

        return query.asList();
    }

}
