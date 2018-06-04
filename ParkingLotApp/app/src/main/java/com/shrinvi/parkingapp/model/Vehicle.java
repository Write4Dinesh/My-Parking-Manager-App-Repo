package com.shrinvi.parkingapp.model;

public class Vehicle {
    private String mRegiNo;

    public Vehicle(String regNo) {
        mRegiNo = regNo;
    }


    @Override
    public int hashCode() {
        return Integer.valueOf(mRegiNo);
    }

    @Override
    public String toString() {
        return mRegiNo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (((Vehicle) o).mRegiNo.equalsIgnoreCase(mRegiNo)) {
            return true;
        }
        return false;
    }
}
