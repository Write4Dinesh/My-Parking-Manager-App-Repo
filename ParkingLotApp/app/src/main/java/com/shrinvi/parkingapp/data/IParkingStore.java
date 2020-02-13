package com.shrinvi.parkingapp.data;

import com.shrinvi.parkingapp.model.ParkingSpace;
import com.shrinvi.parkingapp.model.Vehicle;

import java.util.List;

public interface IParkingStore {
    void storeVehicle(Vehicle vehicle);

    void storeSpace(ParkingSpace space);

    void storeAllSpaces(List<ParkingSpace> spaces);

    Vehicle getVehicleByRegiNo(String regiNo);

    ParkingSpace getSpaceById(String spaceId);

    List<ParkingSpace> getAllSpaces();
}
