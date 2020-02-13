package com.shrinvi.parkingapp.data;

import com.shrinvi.parkingapp.model.IParkingSpace;
import com.shrinvi.parkingapp.model.Vehicle;

import java.util.List;

public interface IParkingStore {
    void storeVehicle(Vehicle vehicle);

    void storeSpace(IParkingSpace space);

    void storeAllSpaces(List<IParkingSpace> spaces);

    Vehicle getVehicleByRegiNo(String regiNo);

    IParkingSpace getSpaceById(String spaceId);

    List<IParkingSpace> getAllSpaces();
}
