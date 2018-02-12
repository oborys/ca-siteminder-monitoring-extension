/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 */

package com.appdynamics.extensions.siteminder.metrics;

import java.util.Map;

import static com.appdynamics.extensions.siteminder.Util.split;


public class MetricProperties {
    static final double DEFAULT_MULTIPLIER = 1d;

    private String metricName;
    private String keyPrefix;
    private String aggregationType;
    private String timeRollupType;
    private String clusterRollupType;
    private double multiplier = DEFAULT_MULTIPLIER;
    private boolean aggregation;
    private boolean delta;
    private Map<Object,Object> conversionValues;

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getAggregationType() {
        return aggregationType;
    }

    public void setAggregationType(String aggregationType) {
        this.aggregationType = aggregationType;
    }

    public String getTimeRollupType() {
        return timeRollupType;
    }

    public void setTimeRollupType(String timeRollupType) {
        this.timeRollupType = timeRollupType;
    }

    public String getClusterRollupType() {
        return clusterRollupType;
    }

    public void setClusterRollupType(String clusterRollupType) {
        this.clusterRollupType = clusterRollupType;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public boolean isAggregation() {
        return aggregation;
    }

    public void setAggregation(boolean aggregation) {
        this.aggregation = aggregation;
    }

    public Map<Object, Object> getConversionValues() {
        return conversionValues;
    }

    public void setConversionValues(Map<Object, Object> conversionValues) {
        this.conversionValues = conversionValues;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public boolean isDelta() {
        return delta;
    }

    public void setDelta(boolean delta) {
        this.delta = delta;
    }

    public void setAggregationFields(String metricType) {
        String[] metricTypes = split(metricType," ");
        this.setAggregationType(metricTypes[0]);
        this.setTimeRollupType(metricTypes[1]);
        this.setClusterRollupType(metricTypes[2]);
    }


}


