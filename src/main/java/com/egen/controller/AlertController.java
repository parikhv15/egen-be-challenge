package com.egen.controller;

import com.egen.MorphiaConfig;
import com.egen.model.Alert;
import com.egen.model.Metric;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parikhv on 5/14/16.
 */

@RequestMapping("/alerts")
@RestController
public class AlertController {

    @RequestMapping(value = "/read")
    public ResponseEntity<List<Alert>> read() {

        Datastore datastore = MorphiaConfig.getInstance().getDatastore();

        Query<Alert> query = datastore.createQuery(Alert.class);

        return new ResponseEntity<List<Alert>>(query.asList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/readRange")
    public ResponseEntity<List<Alert>> readByTimeRange(Long timeStamp1, Long timeStamp2) {

        if (timeStamp1 == null || timeStamp2 == null)
            return new ResponseEntity<List<Alert>>((List<Alert>) new ArrayList<Alert>(), HttpStatus.BAD_REQUEST);

        Datastore datastore = MorphiaConfig.getInstance().getDatastore();

        Query<Alert> query = datastore.createQuery(Alert.class)
                .filter("timeStamp >=", timeStamp1).filter("timeStamp <=", timeStamp2);

        return new ResponseEntity<List<Alert>>(query.asList(), HttpStatus.OK);
    }

}
