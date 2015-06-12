package com.fewlaps.apimonitor.task;

import com.fewlaps.apimonitor.bean.NewRelicApiBean;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Header;

public interface NewRelicApi {
    @GET("/v2/applications.json")
    NewRelicApiBean listApplications(@Header("X-Api-Key") String apiKey);
}