package com.fewlaps.apimonitor.bean;

import java.util.List;

/**
 * Created by Roc Boronat (roc@fewlaps.com) on 12/06/2015.
 */
public class NewRelicApiBean {
    List<NewRelicApplication> applications;

    public List<NewRelicApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<NewRelicApplication> applications) {
        this.applications = applications;
    }
}
