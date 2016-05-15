package com.egen.controller;

import com.egen.MorphiaConfig;
import com.egen.model.Alert;
import com.egen.model.Metric;
import com.egen.service.AlertService;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private AlertService alertService;

    @RequestMapping(value = "/read")
    public ResponseEntity<List<Alert>> read() {

        List<Alert> alertList = alertService.read();

        if (alertList.size() == 0)
            return new ResponseEntity<List<Alert>>(alertList, HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Alert>>(alertList, HttpStatus.OK);
    }

    @RequestMapping(value = "/readRange/{startTime}/{endTime}")
    public ResponseEntity<List<Alert>> readByTimeRange(@PathVariable Long startTime, @PathVariable Long endTime) {

        if (startTime == null || endTime == null)
            return new ResponseEntity<List<Alert>>((List<Alert>) new ArrayList<Alert>(), HttpStatus.BAD_REQUEST);

        List<Alert> alertList = alertService.readByRange(startTime, endTime);

        if (alertList.size() == 0)
            return new ResponseEntity<List<Alert>>(alertList, HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Alert>>(alertList, HttpStatus.OK);
    }

}
