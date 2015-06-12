package com.fewlaps.apimonitor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.fewlaps.apimonitor.bean.NewRelicApiBean;
import com.fewlaps.apimonitor.bean.NewRelicApplication;
import com.fewlaps.apimonitor.event.ApiUnreachableEvent;
import com.fewlaps.apimonitor.task.GetNewRelicDataTask;
import com.fewlaps.apimonitor.validator.NewRelicApplicationSummaryValidator;

import de.greenrobot.event.EventBus;

public class MainActivity extends ActionBarActivity {

    private Handler updateTask = new Handler();
    private static final int DELAY_BETWEEN_CALLS_TO_NEW_RELIC = 15000;
    private boolean isScreenFocused = true; //The common onResume/onPause flag

    private View okLayout;
    private View koLayout;
    private View imworkingTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okLayout = findViewById(R.id.l_ok);
        koLayout = findViewById(R.id.l_ko);
        imworkingTV = findViewById(R.id.tv_imworking);
    }

    private void activateInmersiveMode() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activateInmersiveMode();
        EventBus.getDefault().register(this);
        isScreenFocused = true;
        startService(new Intent(MainActivity.this, GetNewRelicDataTask.class));
        updateTask.postDelayed(runnable, DELAY_BETWEEN_CALLS_TO_NEW_RELIC);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        isScreenFocused = false;
    }

    private final Runnable runnable = new Runnable() {
        public void run() {
            if (isScreenFocused) {
                startService(new Intent(MainActivity.this, GetNewRelicDataTask.class));
                updateTask.postDelayed(runnable, DELAY_BETWEEN_CALLS_TO_NEW_RELIC);
            }
        }
    };

    public void onEventMainThread(NewRelicApiBean event) {
        boolean thereAreErrors = false;
        for (NewRelicApplication application : event.getApplications()) {
            boolean valid = NewRelicApplicationSummaryValidator.isOk(application);
            if (!valid) {
                thereAreErrors = true;
            }
        }
        if (thereAreErrors) {
            showErrorScreen();
        } else {
            showOkScreen();
        }
    }

    public void onEventMainThread(ApiUnreachableEvent event) {
        showErrorScreen();
    }

    public void showErrorScreen() {
        activateInmersiveMode();
        okLayout.setVisibility(View.INVISIBLE);
        koLayout.setVisibility(View.VISIBLE);
        koLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.killsomething));
    }

    public void showOkScreen() {
        activateInmersiveMode();
        okLayout.setVisibility(View.VISIBLE);
        koLayout.setVisibility(View.INVISIBLE);
        koLayout.clearAnimation();
        imworkingTV.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
