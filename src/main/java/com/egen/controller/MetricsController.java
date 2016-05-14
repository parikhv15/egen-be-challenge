package com.egen.controller;

import com.egen.MorphiaConfig;
import com.egen.model.Metric;
import com.egen.rules.MetricsRule;
import com.egen.rules.RulesFactory;
import org.easyrules.api.RulesEngine;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import java.util.ArrayList;
import java.util.List;
import com.egen.rules.MetricsRule.RuleType;

/**
 * Created by parikhv on 5/14/16.
 */

@RequestMapping("/metrics")
@RestController
public class MetricsController {
    private RulesEngine rulesEngine;
    private MetricsRule rule;

    MetricsController() {
        rulesEngine = aNewRulesEngine().build();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Metric> create(@RequestBody Metric metric) {
        if (metric == null)
            return new ResponseEntity<Metric>(metric, HttpStatus.BAD_REQUEST);

        Datastore datastore = MorphiaConfig.getInstance().getDatastore();

        datastore.save(metric);

        rule = RulesFactory.getRule(RuleType.UNDER_WEIGHT, metric);
        rulesEngine.registerRule(rule);
        rule = RulesFactory.getRule(RuleType.OVER_WEIGHT, metric);
        rulesEngine.registerRule(rule);

        rulesEngine.fireRules();
        rulesEngine.clearRules();

        return new ResponseEntity<Metric>(metric, HttpStatus.OK);
    }

    @RequestMapping(value = "/read")
    public ResponseEntity<List<Metric>> read() {

        Datastore datastore = MorphiaConfig.getInstance().getDatastore();

        Query<Metric> query = datastore.createQuery(Metric.class);

        return new ResponseEntity<List<Metric>>(query.asList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/readRange")
    public ResponseEntity<List<Metric>> readByTimeRange(Long timeStamp1, Long timeStamp2) {

        if (timeStamp1 == null || timeStamp2 == null)
            return new ResponseEntity<List<Metric>>((List<Metric>) new ArrayList<Metric>(), HttpStatus.BAD_REQUEST);

        Datastore datastore = MorphiaConfig.getInstance().getDatastore();

        Query<Metric> query = datastore.createQuery(Metric.class)
                .filter("timeStamp >=", timeStamp1).filter("timeStamp <=", timeStamp2);

        return new ResponseEntity<List<Metric>>(query.asList(), HttpStatus.OK);
    }
}
