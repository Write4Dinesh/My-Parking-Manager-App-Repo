package com.shrinvi.parkingapp.model;

import com.shrinvi.parkingapp.ui.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Control the creation of ParkingSpace object centrally. don't allow the creation of Space outside
 * of this class
 */
public class ParkingSpaceFactory {
    private static boolean parkingSpaceCrated = false;
    private static List<IParkingSpace> mFreeSpaces = new ArrayList<>();
    private static List<IParkingSpace> mTotalSpaces = new ArrayList<>();

    public static boolean createSpaces(int count) {
        if (parkingSpaceCrated) {
            return false;
        }
        ParkingSpace tempSpace;
        int rows = count / HomeActivity.COLUMN_COUNT;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < HomeActivity.COLUMN_COUNT; j++) {
                tempSpace = new ParkingSpace(i + "x" + j);
                mFreeSpaces.add(tempSpace);
                mTotalSpaces.add(tempSpace);
            }
        }
        parkingSpaceCrated = true;
        return true;
    }

    public static boolean isParkingSpaceCrated() {
        return parkingSpaceCrated;
    }

    public static List<IParkingSpace> getFreeSpaces() {
        return mFreeSpaces;
    }

    public static List<IParkingSpace> getTotalSpaces() {
        return mTotalSpaces;
    }
}

class ParkingSpace implements IParkingSpace {
    private String mId;
    private Vehicle mVehicle;

    ParkingSpace(String id) {
        mId = id;
        mVehicle = null;
    }

    /**
     * Make sure there won't be race condition when multiple threads execute this space
     *
     * @param vehicle
     * @return
     */
    @Override
    public synchronized boolean block(Vehicle vehicle) {
        if (vehicle != null) {
            mVehicle = vehicle;
            return true;
        }
        return false;
    }

    /**
     * Make sure there won't be race condition when multiple threads execute this space
     *
     * @return
     */
    @Override
    public synchronized void release() {
        mVehicle = null;
    }

    @Override
    public boolean isEmpty() {
        return mVehicle == null;
    }

    @Override
    public Vehicle getVehicle() {
        return mVehicle;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(mId);
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public String toString() {
        return mId + "";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        ParkingSpace space = (ParkingSpace) o;
        if ((((ParkingSpace) o).getId() == getId())) {
            return true;
        }
        return false;
    }
}

