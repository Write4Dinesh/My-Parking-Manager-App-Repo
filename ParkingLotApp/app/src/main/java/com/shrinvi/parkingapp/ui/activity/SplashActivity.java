package com.shrinvi.parkingapp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.shrinvi.parkingapp.R;

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_TIME_OUT = 5000;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;
        new Handler().postDelayed(() -> {
            goToNextScreen(HomeActivity.class);
            finish();
        }, SPLASH_TIME_OUT);

    }
}
