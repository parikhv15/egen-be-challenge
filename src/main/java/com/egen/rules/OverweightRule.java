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

@Rule(name = "Over-Weight")
public class OverweightRule implements MetricsRule{

    private Metric metric;

    public OverweightRule(Metric metric) {
        this.metric = metric;
    }

    @Override
    @Condition
    public boolean when() {
        double percent = ((double) metric.getValue()) / baseWeight;

        if (percent > 1.1)
            return true;

        return false;
    }

    @Override
    @Action
    public void then() {
        Datastore datastore = MorphiaConfig.getInstance().getDatastore();

        datastore.save(new Alert(RuleType.OVER_WEIGHT.toString(), metric.getTimeStamp(), metric.getValue()));
    }
}
