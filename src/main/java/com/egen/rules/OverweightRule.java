package com.egen.rules;

import com.egen.MorphiaConfig;
import com.egen.dao.AlertDAO;
import com.egen.model.Alert;
import com.egen.model.Metric;
import com.egen.service.AlertService;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by parikhv on 5/14/16.
 */

@Rule(name = "Over-Weight")
public class OverweightRule implements MetricsRule{

    private AlertDAO alertDAO = new AlertDAO();

    private Metric metric;

    public OverweightRule(Metric metric) {
        this.metric = metric;
    }

    @Override
    @Condition
    public boolean when() {
        double percent = ((double) metric.getValue()) / baseWeight;

        return percent > 1.1;

    }

    @Override
    @Action
    public void then() {
        Alert alert = new Alert(MetricsRule.RuleType.OVER_WEIGHT.toString(), metric.getTimeStamp(), metric.getValue());

        alertDAO.create(alert);
    }
}
