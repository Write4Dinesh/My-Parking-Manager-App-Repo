package com.shrinvi.parkingapp.model;

public interface IParkingSpace {
    boolean block(Vehicle vehicle);

    void release();

    boolean isEmpty();

    Vehicle getVehicle();

    String getId();


}
