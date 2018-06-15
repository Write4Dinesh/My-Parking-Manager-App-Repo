package com.shrinvi.parkingapp.utility;

import android.content.Context;
import android.widget.Toast;

public class UIUtility {
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
