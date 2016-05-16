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

/**
 * Created by parikhv on 5/14/16.
 */

@Rule (name = "Under-Weight")
public class UnderweightRule implements MetricsRule {

    private AlertDAO alertDAO = new AlertDAO();

    private Metric metric;

    public UnderweightRule(Metric metric) {
        this.metric = metric;
    }

    @Override
    @Condition
    public boolean when() {
        double percent = ((double) metric.getValue()) / baseWeight;

        return percent < 0.9;

    }

    @Override
    @Action
    public void then() {
        Alert alert = new Alert(MetricsRule.RuleType.UNDER_WEIGHT.toString(), metric.getTimeStamp(), metric.getValue());

        alertDAO.create(alert);
    }
}
