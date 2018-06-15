package com.shrinvi.parkingapp.viewmodel;

import android.databinding.BaseObservable;

import com.shrinvi.parkingapp.data.PLABackendless;
import com.shrinvi.parkingapp.model.ParkingSpace;
import com.shrinvi.parkingapp.model.Vehicle;
import com.shrinvi.parkingapp.utility.PLALogger;

public class SpaceItemViewModel extends BaseObservable {
    private ParkingSpace mParkingSpace;

    public void setParkingSpace(ParkingSpace parkingSpace) {
        mParkingSpace = parkingSpace;
    }

    public ParkingSpace getParkingSpace() {
        return mParkingSpace;
    }

    public void onClick() {
        if (mParkingSpace == null) {
            return;
        }
        if (mParkingSpace.getIsEmpty()) {
            Vehicle vehicle = new Vehicle(mParkingSpace.getCurrentPosition() + "");
            mParkingSpace.park(vehicle);
        } else {
            mParkingSpace.unPark();
        }
        PLABackendless.getInstance().updateParkingSpace(mParkingSpace, (response) -> {
            if (response != null && response.success) {
                PLALogger.logD("updateParkingSpace():success,response=" + response.response);
            } else {
                PLALogger.logD("updateParkingSpace():fail,response error=" + response.error);
            }
        });

    }
}
