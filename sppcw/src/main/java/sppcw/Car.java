package sppcw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Car {
    private String make;
    private String model;
    private int insuranceGroup;
    private int annualMileage;
    private double value;
    private List<Driver> drivers;

    public Car(String make, String model, int insuranceGroup, int annualMileage) {
        this.make = make;
        this.model = model;
        this.insuranceGroup = insuranceGroup <= 0 || insuranceGroup  > 10 ? 100 : insuranceGroup;
        this.annualMileage = annualMileage;
        this.drivers = new ArrayList<>();
    }

    public int addDriver(Driver driver){
        if (!driver.getName().equals("invalid driver")){
            drivers.add(driver);
        }
        return drivers.size();
    }

    public boolean removeDriverByName(String name) {
        int initialSize = drivers.size();
        drivers = drivers.stream()
                .filter(driver -> !driver.getName().equals(name))
                .collect(Collectors.toList());
        return drivers.size() < initialSize;
    }

    public int driverRisk(){
        if (drivers.isEmpty()) {
            return 0; // return 0 risk if theres no drivers
        }
        int totalRisk = 0;
        for(Driver d : drivers) {
            totalRisk += d.riskRating(); // sum of all drivers risk ratings
        }
        return totalRisk / drivers.size(); // average risk rounded down
    }


    public int carRating(){
        int rating = 1;
        rating *= insuranceGroup;
        if(annualMileage > 20000){
            rating = 100;
        } else {
            rating += (annualMileage - 5000) / 3000;
        }
        return rating;
    }

}
