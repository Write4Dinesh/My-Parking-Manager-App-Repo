package com.shrinvi.rxj.lib.com.shrinvi.rxj.lib.model;

public class ParkingSpace {
    private int mId;
    private Vehicle mVehicle;

    public ParkingSpace(int id) {
        mId = id;
        mVehicle = null;
    }

    public boolean park(Vehicle vehicle) {
        if (vehicle != null) {
            mVehicle = vehicle;
            return true;
        }
        return false;
    }

    public void unPark() {
        mVehicle = null;
    }

    public boolean isEmpty() {
        return mVehicle == null;
    }

    public Vehicle getVehicle() {
        return mVehicle;
    }

    @Override
    public int hashCode() {
        return mId;
    }

    public int getId() {
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
