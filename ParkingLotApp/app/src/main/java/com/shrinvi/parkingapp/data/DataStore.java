package com.shrinvi.parkingapp.data;

import com.shrinvi.parkingapp.model.ParkingSpace;
import com.shrinvi.parkingapp.model.Vehicle;

public abstract class DataStore {
    public abstract void storeVehicle(Vehicle vehicle);
    public abstract void storeSpace(ParkingSpace space);

}
