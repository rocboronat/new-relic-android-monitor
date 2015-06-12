package com.fewlaps.apimonitor.bean;

/**
 * Created by Roc Boronat (roc@fewlaps.com) on 12/06/2015.
 */
public class NewRelicApplicationSummary {
    private float response_time;
    private float throughput;
    private float error_rate;
    private float apdex_target;
    private float apdex_score;
    private float host_count;
    private float instance_count;

    public float getResponse_time() {
        return response_time;
    }

    public void setResponse_time(float response_time) {
        this.response_time = response_time;
    }

    public float getThroughput() {
        return throughput;
    }

    public void setThroughput(float throughput) {
        this.throughput = throughput;
    }

    public float getError_rate() {
        return error_rate;
    }

    public void setError_rate(float error_rate) {
        this.error_rate = error_rate;
    }

    public float getApdex_target() {
        return apdex_target;
    }

    public void setApdex_target(float apdex_target) {
        this.apdex_target = apdex_target;
    }

    public float getApdex_score() {
        return apdex_score;
    }

    public void setApdex_score(float apdex_score) {
        this.apdex_score = apdex_score;
    }

    public float getHost_count() {
        return host_count;
    }

    public void setHost_count(float host_count) {
        this.host_count = host_count;
    }

    public float getInstance_count() {
        return instance_count;
    }

    public void setInstance_count(float instance_count) {
        this.instance_count = instance_count;
    }
}
