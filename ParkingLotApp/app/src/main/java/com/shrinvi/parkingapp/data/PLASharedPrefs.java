package com.shrinvi.parkingapp.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PLASharedPrefs {
    public static final String NAME = "PLA_SHARED_PREFS_NAME";
    private static PLASharedPrefs sPLASharedPref;
    private SharedPreferences mPrefs;
    private static final String PARKING_SYSTEM_CREATED = "parking_system_created";

    private PLASharedPrefs(Context context) {
        mPrefs = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static PLASharedPrefs getInstance(Context context) {
        if (sPLASharedPref == null) {
            sPLASharedPref = new PLASharedPrefs(context);
        }
        return sPLASharedPref;
    }

    public void setParkingSystemCreated(boolean isCreated) {
        mPrefs.edit().putBoolean(PARKING_SYSTEM_CREATED, isCreated);
        mPrefs.edit().commit();
    }

    public boolean isParkingSystemCreated() {
        return mPrefs.getBoolean(PARKING_SYSTEM_CREATED, false);
    }
}

