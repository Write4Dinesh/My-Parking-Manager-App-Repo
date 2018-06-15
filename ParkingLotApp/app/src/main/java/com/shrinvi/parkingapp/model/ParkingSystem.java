package com.shrinvi.parkingapp.model;

import android.content.Context;

import com.shrinvi.parkingapp.data.PLABackendless;
import com.shrinvi.parkingapp.ui.PLAHomeActivity;
import com.shrinvi.parkingapp.utility.PLALogger;

import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {
    public static final String LOG_TAG = "ParkingSystem:";
    private List<ParkingSpace> mFreeSpaces;
    private List<ParkingSpace> mTotalSpaces;
    private int mParkingLotCapacity;
    private static ParkingSystem sInstance;
    private Context mContext;

    private ParkingSystem(int capacity, Context context) {
        mContext = context;
        mParkingLotCapacity = capacity;
        init();
    }

    public void init() {
        mFreeSpaces = new ArrayList<>(mParkingLotCapacity);
        mTotalSpaces = new ArrayList<>(mParkingLotCapacity);
        PLABackendless.getInstance().getAllSpacesFromServer((response) -> {
            if (response != null && response.success) {
                PLALogger.logD("fetched all the spaces..");
                List<ParkingSpace> datResponse = (List<ParkingSpace>) response.response;
                for (ParkingSpace space : datResponse) {
                    mFreeSpaces.add(space);
                    mTotalSpaces.add(space);
                }
            }
        });


    }


    public synchronized static ParkingSystem getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new ParkingSystem(PLAHomeActivity.PARKING_CAPACITY, context);
        }
        return sInstance;
    }

    public boolean park(Vehicle vehicle, int spaceIndex) {
        if (vehicle != null) {
            System.out.println(LOG_TAG + "invalid regi.no");
            return false;
        }
        ParkingSpace space = mTotalSpaces.get(spaceIndex);
        if (!space.getIsEmpty()) {
            System.out.println(LOG_TAG + "No space available");
            return false;
        }
        space.park(vehicle);
        //mParkedSpaceMap.put(vehicle, space);
        return true;
    }

    public void unPark(Vehicle vehicle, int spaceIndex) {
        //ParkingSpace space = mParkedSpaceMap.remove(vehicle);
        ParkingSpace space = mTotalSpaces.get(spaceIndex);
        if (space != null) {
            space.unPark();
            System.out.println("UnParked the vehicle");
        } else {
            System.out.println("Vehicle with entered regino. not found");
            return;
        }

    }


    public List<ParkingSpace> getTotalSpaces() {
        return mTotalSpaces;
    }
}
