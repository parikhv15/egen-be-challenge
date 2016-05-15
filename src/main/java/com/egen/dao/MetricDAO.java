package com.egen.dao;

import com.egen.MorphiaConfig;
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
public class MetricDAO {

    Datastore datastore;

    MetricDAO() {
        datastore = MorphiaConfig.getInstance().getDatastore();
    }

    public ObjectId createMetric(Metric metric) {
        datastore.save(metric);

        return metric.getId();
    }

    public List<Metric> read () {
        Query<Metric> query = datastore.createQuery(Metric.class);

        return query.asList();
    }

    public List<Metric> readByRange(long startTime, long endTime) {
        Query<Metric> query = datastore.createQuery(Metric.class)
                .filter("timeStamp >=", startTime).filter("timeStamp <=", endTime);

        return query.asList();
    }
}
