package com.egen.rules;

import com.egen.MorphiaConfig;
import com.egen.model.Alert;
import com.egen.model.Metric;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.mongodb.morphia.Datastore;

/**
 * Created by parikhv on 5/14/16.
 */

@Rule (name = "Under-Weight")
public class UnderweightRule implements MetricsRule {

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
        Datastore datastore = MorphiaConfig.getInstance().getDatastore();

        datastore.save(new Alert(RuleType.UNDER_WEIGHT.toString(), metric.getTimeStamp(), metric.getValue()));
    }
}
