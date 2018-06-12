package com.shrinvi.parkingapp.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

import com.shrinvi.parkingapp.model.ParkingSpace;
import com.shrinvi.parkingapp.model.Vehicle;

public class SpaceItemViewModel extends BaseObservable {
    private ParkingSpace mParkingSpace;

    public void setParkingSpace(ParkingSpace parkingSpace) {
        mParkingSpace = parkingSpace;
    }

    public ParkingSpace getParkingSpace() {
        return mParkingSpace;
    }

    public  void onClick() {
        if (mParkingSpace != null && mParkingSpace.getIsEmpty()) {
            Vehicle vehicle = new Vehicle(mParkingSpace.getCurrentPosition() + "");
            mParkingSpace.park(vehicle);
        } else {
            mParkingSpace.unPark();
        }

    }
}
