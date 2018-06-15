package com.shrinvi.parkingapp;

import android.content.Context;

import com.shrinvi.parkingapp.data.PLABackendless;
import com.shrinvi.parkingapp.data.PLASharedPrefs;
import com.shrinvi.parkingapp.model.ParkingSpace;
import com.shrinvi.parkingapp.ui.PLAHomeActivity;
import com.shrinvi.parkingapp.utility.PLALogger;

import java.util.ArrayList;
import java.util.List;

public class PLAAdmin {
    private static PLAAdmin sAdmin;
    private static Context mAppContext;

    public static PLAAdmin getInstance(Context context) {
        mAppContext = context;
        if (sAdmin == null) {
            sAdmin = new PLAAdmin();
        }
        return sAdmin;
    }

    private PLAAdmin() {

    }

    public boolean isDataAvailableInServer() {
        return PLASharedPrefs.getInstance(mAppContext).isParkingSystemCreated();
    }

    public void createParkingDataInServer() {
        ParkingSpace tempSpace;
        List<ParkingSpace> mFreeSpaces = new ArrayList<>();
        for (int i = 0; i < PLAHomeActivity.PARKING_CAPACITY; i++) {
            tempSpace = new ParkingSpace(i);
            mFreeSpaces.add(tempSpace);
            PLABackendless.getInstance().createAllParkingSpaces(mFreeSpaces, (response) -> {
                if (response != null && response.success) {
                    PLALogger.logD("createAllParkingSpaces():success,response=" + response.response);
                    PLASharedPrefs.getInstance(mAppContext).setParkingSystemCreated(true);
                } else {
                    PLALogger.logD("createAllParkingSpaces():fail,response error=" + response.error);
                    PLASharedPrefs.getInstance(mAppContext).setParkingSystemCreated(false);
                }
            });
        }

    }

}
