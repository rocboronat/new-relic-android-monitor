package com.fewlaps.apimonitor.bean;

/**
 * Created by Roc Boronat (roc@fewlaps.com) on 12/06/2015.
 */
public class NewRelicApplication {
    private NewRelicApplicationSummary application_summary;
    private String name;

    public NewRelicApplicationSummary getApplication_summary() {
        return application_summary;
    }

    public void setApplication_summary(NewRelicApplicationSummary application_summary) {
        this.application_summary = application_summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
