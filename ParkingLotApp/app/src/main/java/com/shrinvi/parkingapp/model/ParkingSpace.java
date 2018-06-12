package com.shrinvi.parkingapp.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ParkingSpace extends BaseObservable {
    private int mId;
    private Vehicle mVehicle;
    private int mCurrentPosition;
    @Bindable
    private boolean isEmpty;

    public ParkingSpace(int id) {
        mId = id;
        mVehicle = null;
    }

    public boolean park(Vehicle vehicle) {
        if (vehicle != null) {
            mVehicle = vehicle;
            setIsEmpty(false);
            return true;
        }
        return false;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
        notifyPropertyChanged(1);
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public void setCurrentPosition(int position) {
        mCurrentPosition = position;
    }

    public void unPark() {
        mVehicle = null;
        setIsEmpty(true);
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
