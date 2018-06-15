package com.shrinvi.parkingapp;

import android.app.Application;

public class PLAApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PLAAdmin admin = PLAAdmin.getInstance(getApplicationContext());
        if(!admin.isDataAvailableInServer()){
            admin.createParkingDataInServer();
        }
    }
}
