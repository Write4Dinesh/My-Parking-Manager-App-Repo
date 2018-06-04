package com.shrinvi.rxj.lib;

import com.shrinvi.rxj.lib.com.shrinvi.rxj.lib.model.ParkingSpace;
import com.shrinvi.rxj.lib.com.shrinvi.rxj.lib.model.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class MyClass {
    public static void main(String[] args) {
        //mainMethod();
        //testMethod();
   testHashing();
    }

    public static void testHashing() {
        Vehicle vehicle = null;
        ParkingSpace space = null;
        Map<Vehicle, ParkingSpace> vehicleMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            vehicle = new Vehicle("" + (i + 100));
            space = new ParkingSpace(i + 100);
            vehicleMap.put(vehicle,space);
        }

        vehicle = new Vehicle("" + (500));
        long startTime = System.nanoTime();
        space = vehicleMap.get(vehicle);
        long endTime = System.nanoTime();
        long elapsed = (endTime - startTime)/1000;
     System.out.println("elapsed:" + elapsed);
     if(space==null){
         System.out.println("The item not found");
     }else {
         System.out.println("Found......");
     }
        System.out.println("value:" + Integer.valueOf("123"));
    }
   /* public static void mainMethod(){
        ParkingSystem parkingSystem = new ParkingSystem(5);
        DataInputStream dataInputStream = new DataInputStream(System.in);
        System.out.println(ParkingSystem.LOG_TAG + "Enter the command below");
        boolean quit = false;
        try {
            while (!quit) {
                System.out.println("1:park , 2:unPark, 3:print parking status, 4:quit");
                String line = dataInputStream.readLine();
                switch (line) {
                    case "1":
                        System.out.println("Enter vehicle registration no.");
                        line = dataInputStream.readLine();
                        parkingSystem.park(line);
                        break;
                    case "2":
                        System.out.println("Enter vehicle registration no.");
                        line = dataInputStream.readLine();
                        parkingSystem.unPark(line);
                        break;
                    case "3":
                        parkingSystem.printParkingStatus();
                        break;
                    case "4":
                        quit = true;
                        break;
                    default:
                        quit = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void testMethod(){
        Vehicle v = new Vehicle("123");
        Vehicle v2 = new Vehicle("123");
        if(v.equals(v2)){
            System.out.println("equals");
        }
    }*/
}
