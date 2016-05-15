package com.egen.service;

import com.egen.dao.AlertDAO;
import com.egen.dao.MetricDAO;
import com.egen.model.Alert;
import com.egen.model.Metric;
import com.egen.rules.MetricsRule;
import com.egen.rules.RulesFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

/**
 * Created by vrajp on 5/15/2016.
 */
public class AlertService {


    @Autowired
    private AlertDAO alertDAO;

    AlertService() {
    }

    public List<Alert> read () {
        return alertDAO.read();
    }

    public List<Alert> readByRange(long startTime, long endTime) {
        return alertDAO.readByRange(startTime, endTime);
    }

}
