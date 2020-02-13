package com.shrinvi.parkingapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void goToNextScreen(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

}
