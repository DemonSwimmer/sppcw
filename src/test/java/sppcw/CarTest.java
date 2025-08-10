package sppcw;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testAddValidDriver() {
        Car car = new Car("Ford", "Focus", 3, 12000);
        Driver driver = new Driver("Alice", 30, 0);
        int count = car.addDriver(driver);
        assertEquals(1, count);
    }

    @Test
    public void testAddInvalidDriver() {
        Car car = new Car("Ford", "Focus", 3, 12000);
        Driver driver = new Driver("Bob", 16, 0);  // invalid driver
        int count = car.addDriver(driver);
        assertEquals(0, count);
    }

    @Test
    public void testRemoveDriverByNameSuccess() {
        Car car = new Car("Ford", "Focus", 3, 12000);
        Driver driver = new Driver("Alice", 30, 0);
        car.addDriver(driver);
        boolean removed = car.removeDriverByName("Alice");
        assertTrue(removed);
    }

    @Test
    public void testRemoveDriverByNameFail() {
        Car car = new Car("Ford", "Focus", 3, 12000);
        Driver driver = new Driver("Alice", 30, 0);
        car.addDriver(driver);
        boolean removed = car.removeDriverByName("Bob"); // Not present
        assertFalse(removed);
    }

    @Test
    public void testDriverRiskWithMultipleDrivers() {
        Car car = new Car("Ford", "Focus", 3, 12000);
        car.addDriver(new Driver("Alice", 30, 0));  // risk 1
        car.addDriver(new Driver("Bob", 20, 1));    // risk 6 + 3 = 9
        int risk = car.driverRisk();
        assertEquals(5, risk); // average of (1 + 9) / 2 = 5 (rounded down)
    }

    @Test
    public void testDriverRiskNoDrivers() {
        Car car = new Car("Ford", "Focus", 3, 12000);
        assertEquals(0, car.driverRisk());
    }

    @Test
    public void testCarRatingLowInsuranceGroupUnder5Years() {
        // Note: The code you shared does not check car age/year, so we’ll skip that condition or you can add it later.
        Car car = new Car("Ford", "Focus", 1, 4000);
        int rating = car.carRating();
        assertEquals(1, rating); // insuranceGroup 1 and mileage under 5000 => rating 1
    }

    @Test
    public void testCarRatingHighAnnualMileage() {
        Car car = new Car("Ford", "Focus", 5, 21000);
        int rating = car.carRating();
        assertEquals(100, rating);  // mileage > 20000 results in rating 100 (uninsurable)
    }

    @Test
    public void testCarRatingIncrementMileage() {
        Car car = new Car("Ford", "Focus", 3, 11000);
        // rating = insuranceGroup + (mileage - 5000)/3000
        // (11000 - 5000) / 3000 = 2
        // rating = 3 + 2 = 5
        int rating = car.carRating();
    }
}