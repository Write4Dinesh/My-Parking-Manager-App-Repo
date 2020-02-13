package com.shrinvi.parkingapp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Its the model of the core system.This class implements the core functionality of parking system.
 * It's a pure java code & no android framework code is mixed. so, it can be run on any java platform
 */
public class ParkingSystem {
    public static final String LOG_TAG = "ParkingSystem:";
    private List<IParkingSpace> mFreeSpaces;
    private List<IParkingSpace> mTotalSpaces;
    private Map<Vehicle, IParkingSpace> mParkedSpaceMap;
    private int mParkingLotCapacity;
    private static ParkingSystem sInstance;

    private ParkingSystem(int capacity) {
        mParkingLotCapacity = capacity;

        mParkedSpaceMap = new HashMap<>(10);
        if (!ParkingSpaceFactory.isParkingSpaceCrated()) {
            if (ParkingSpaceFactory.createSpaces(mParkingLotCapacity)) {
                mTotalSpaces = ParkingSpaceFactory.getTotalSpaces();
                mFreeSpaces = ParkingSpaceFactory.getFreeSpaces();
            }
        }
    }

    public synchronized static ParkingSystem getInstance(int capacity) {
        if (sInstance == null) {
            sInstance = new ParkingSystem(capacity);
        }
        return sInstance;
    }

    public boolean blockSpace(String regNo, int spaceIndex) {
        if (!isRegiNoValid(regNo)) {
            System.out.println(LOG_TAG + "invalid regi.no");
            return false;
        }
        IParkingSpace space = mTotalSpaces.get(spaceIndex);
        if (!space.isEmpty()) {
            System.out.println(LOG_TAG + "No space available");
            return false;
        }
        Vehicle vehicle = new Vehicle(regNo);
        space.block(vehicle);
        //mParkedSpaceMap.put(vehicle, space);
        return true;
    }

    public void releaseSpace(String regiNo, int spaceIndex) {
        Vehicle vehicle = new Vehicle(regiNo);
        //IParkingSpace space = mParkedSpaceMap.remove(vehicle);
        IParkingSpace space = mTotalSpaces.get(spaceIndex);
        if (space != null) {
            space.release();
            System.out.println("UnParked the vehicle");
        } else {
            System.out.println("Vehicle with entered regino. not found");
            return;
        }
        //mFreeSpaces.add(space);

    }

    public List<IParkingSpace> getFreeSpaces() {
        return mFreeSpaces;
    }

    public List<IParkingSpace> getTotalSpaces() {
        return mTotalSpaces;
    }

    public Map<Vehicle, IParkingSpace> getParkedSpaceMap() {
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
