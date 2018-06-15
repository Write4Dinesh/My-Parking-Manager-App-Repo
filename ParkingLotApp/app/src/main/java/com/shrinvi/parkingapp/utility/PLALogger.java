package com.shrinvi.parkingapp.utility;

import android.util.Log;

public class PLALogger {
    public static final String LOG_TAG = "PLA_LOG";

    public static void logD(String message) {
        Log.d(LOG_TAG, message);
    }
}
