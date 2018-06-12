package com.shrinvi.parkingapp.model;

import com.shrinvi.parkingapp.ui.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingSystem {
    public static final String LOG_TAG = "ParkingSystem:";
    private List<ParkingSpace> mFreeSpaces;
    private List<ParkingSpace> mTotalSpaces;
    private Map<Vehicle, ParkingSpace> mParkedSpaceMap;
    private int mParkingLotCapacity;
    private static ParkingSystem sInstance;

    private ParkingSystem(int capacity) {
        mParkingLotCapacity = capacity;
        mFreeSpaces = new ArrayList<>(mParkingLotCapacity);
        mTotalSpaces = new ArrayList<>(mParkingLotCapacity);
        mParkedSpaceMap = new HashMap<>(10);
        ParkingSpace tempSpace;
        for (int i = 0; i < mParkingLotCapacity; i++) {
            tempSpace = new ParkingSpace(i);
            mFreeSpaces.add(tempSpace);
            mTotalSpaces.add(tempSpace);
        }
    }

    public synchronized static ParkingSystem getInstance() {

        if (sInstance == null) {
            sInstance = new ParkingSystem(MainActivity.PARKING_CAPACITY);
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
        //mFreeSpaces.add(space);

    }

    public List<ParkingSpace> getFreeSpaces() {
        return mFreeSpaces;
    }

    public List<ParkingSpace> getTotalSpaces() {
        return mTotalSpaces;
    }

    public Map<Vehicle, ParkingSpace> getParkedSpaceMap() {
        return mParkedSpaceMap;
    }

    private boolean isRegiNoValid(String regNo) {
        return (regNo != null && !regNo.isEmpty());
    }

    public void printParkingStatus() {
        System.out.println("total parking capacity:" + mParkingLotCapacity);
        System.out.println("total vehicles parked now:" + mParkedSpaceMap.size());
        System.out.println("total free space available:" + mFreeSpaces.size());
    }
}
