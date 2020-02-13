package com.shrinvi.parkingapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shrinvi.parkingapp.R;
import com.shrinvi.parkingapp.model.ParkingSystem;
import com.shrinvi.parkingapp.ui.adapter.ParkingAdapter;


public class MainActivity extends AppCompatActivity {
    public static final int PARKING_CAPACITY = 100;
    public static final int COLUMN_COUNT = 5;
    private ParkingSystem mParkingSystem;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initModel();
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUMN_COUNT));
        mRecyclerView.setAdapter(new ParkingAdapter(this, mParkingSystem));
    }

    private void initModel() {
        mParkingSystem = new ParkingSystem(PARKING_CAPACITY);
    }


}

