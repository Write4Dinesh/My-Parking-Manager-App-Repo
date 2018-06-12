package com.shrinvi.parkingapp.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shrinvi.parkingapp.BR;

public class ParkingSpace extends BaseObservable {
    private int mId;
    private Vehicle mVehicle;
    @Bindable
    private String currentPosition;
    @Bindable
    private boolean isEmpty;

    public ParkingSpace(int id) {
        mId = id;
        mVehicle = null;
        setIsEmpty(true);
    }

    public boolean park(Vehicle vehicle) {
        if (vehicle != null) {
            mVehicle = vehicle;
            setIsEmpty(false);
            return true;
        }
        return false;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition + "";
        notifyPropertyChanged(BR.currentPosition);
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
        notifyPropertyChanged(BR.isEmpty);
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
