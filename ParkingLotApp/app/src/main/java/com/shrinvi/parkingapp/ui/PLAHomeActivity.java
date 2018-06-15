package com.shrinvi.parkingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.backendless.Backendless;
import com.shrinvi.parkingapp.Defaults;
import com.shrinvi.parkingapp.R;
import com.shrinvi.parkingapp.model.ParkingSystem;


public class PLAHomeActivity extends AppCompatActivity {
    public static final int PARKING_CAPACITY = 100;
    public static final int COLUMN_COUNT = 5;
    private ParkingSystem mParkingSystem;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY);
        initModel();
        initView();

    }

    private void initModel() {
        mParkingSystem = ParkingSystem.getInstance(this);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUMN_COUNT));
        mRecyclerView.setAdapter(new ParkingAdapter(this, mParkingSystem));
    }
}

