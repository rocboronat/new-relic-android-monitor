package com.fewlaps.apimonitor.validator;

import android.util.Log;

import com.fewlaps.apimonitor.bean.NewRelicApplication;
import com.fewlaps.apimonitor.bean.NewRelicApplicationSummary;

/**
 * Created by Roc Boronat (roc@fewlaps.com) on 12/06/2015.
 */
public class NewRelicApplicationSummaryValidator {
    public static boolean isOk(NewRelicApplication application) {
        if (application.getApplication_summary() != null) {
            NewRelicApplicationSummary summary = application.getApplication_summary();
            try {
                if (summary.getThroughput() > 0) {
                    if (summary.getApdex_score() < summary.getApdex_target()) {
                        Log.d("VALIDATOR", application.getName() + " - Appdex: " + summary.getApdex_score());
                        return false;
                    }
                }
            } catch (Exception e) {
                Log.d("VALIDATOR", "isOk()", e);
                return false;
            }
        } else {
            //In case that the Application don't have summary, it's an OK for us
            Log.d("VALIDATOR", application.getName() + " don't have a summary :(");
        }
        return true;
    }
}
