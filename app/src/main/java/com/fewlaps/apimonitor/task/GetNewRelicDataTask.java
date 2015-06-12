package com.fewlaps.apimonitor.task;

import android.app.IntentService;
import android.content.Intent;

import com.fewlaps.apimonitor.BuildConfig;
import com.fewlaps.apimonitor.bean.NewRelicApiBean;
import com.fewlaps.apimonitor.event.ApiUnreachableEvent;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;

/**
 * Created by Roc Boronat (roc@fewlaps.com) on 12/06/2015.
 */
public class GetNewRelicDataTask extends IntentService {

    public GetNewRelicDataTask() {
        super("GetNewRelicDataTask");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.newrelic.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        NewRelicApi service = restAdapter.create(NewRelicApi.class);
        try {
            NewRelicApiBean bean = service.listApplications(BuildConfig.NEW_RELIC_API_KEY_MONITOR);
            EventBus.getDefault().post(bean);
        } catch (Exception e) {
            EventBus.getDefault().post(new ApiUnreachableEvent());
        }
    }
}
