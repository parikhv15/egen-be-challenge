package com.egen.rules;

/**
 * Created by parikhv on 5/14/16.
 */
public interface MetricsRule {

    enum RuleType {OVER_WEIGHT, UNDER_WEIGHT};

    int baseWeight = Integer.parseInt(System.getProperty("base.value"));

    boolean when();
    void then();
}
