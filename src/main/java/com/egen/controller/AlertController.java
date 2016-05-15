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

        return new ResponseEntity<List<Alert>>(alertList, HttpStatus.OK);
    }

    @RequestMapping(value = "/readRange")
    public ResponseEntity<List<Alert>> readByTimeRange(Long startRime, Long endTime) {

        if (startRime == null || endTime == null)
            return new ResponseEntity<List<Alert>>((List<Alert>) new ArrayList<Alert>(), HttpStatus.BAD_REQUEST);

        List<Alert> alertList = alertService.readByRange(startRime, endTime);

        return new ResponseEntity<List<Alert>>(alertList, HttpStatus.OK);
    }

}
