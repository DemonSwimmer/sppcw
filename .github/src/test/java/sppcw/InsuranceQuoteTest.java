package sppcw;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InsuranceQuoteTest {

    @Test
    public void testCalculatePremiumLowRisk() {
        Car car = new Car("Ford", "Focus", 1, 4000);
        car.addDriver(new Driver("Alice", 30, 0)); // risk 1
        InsuranceQuote quote = new InsuranceQuote(car, 500);
        // total risk = carRating + driverRisk = 1 + 1 = 2 < 5
        assertEquals(500.0, quote.calculatePremium());
    }

    @Test
    public void testCalculatePremiumMediumRisk() {
        Car car = new Car("Ford", "Focus", 3, 10000);
        car.addDriver(new Driver("Bob", 20, 1)); // risk 9
        InsuranceQuote quote = new InsuranceQuote(car, 500);
        // carRating = 3 + (10000 - 5000)/3000 = 3 + 1 = 4
        // totalRisk = 9 + 4 = 13 -> between 10 and 15, premium = base * 2.0
        assertEquals(1000.0, quote.calculatePremium());
    }

    @Test
    public void testCalculatePremiumHighRisk() {
        Car car = new Car("Ford", "Focus", 5, 21000); // mileage > 20000 -> carRating=100
        car.addDriver(new Driver("Charlie", 20, 2)); // risk 12 (6 + 6)
        InsuranceQuote quote = new InsuranceQuote(car, 500);
        // totalRisk = 100 + 12 = 112 (car uninsurable -> premium = 0.0)
        assertEquals(0.0, quote.calculatePremium());
    }

    @Test
    public void testCalculatePremiumBetween5and10() {
        Car car = new Car("Ford", "Focus", 2, 8000);
        car.addDriver(new Driver("Dave", 24, 0)); // risk 6
        InsuranceQuote quote = new InsuranceQuote(car, 500);
        // carRating = 2 + (8000-5000)/3000 = 2 + 1 = 3
        // totalRisk = 6 + 3 = 9 (>=5 and <10) premium = base * 1.5
        assertEquals(750.0, quote.calculatePremium());
    }

    @Test
    public void testCalculatePremiumBetween10and15() {
        Car car = new Car("Ford", "Focus", 3, 14000);
        car.addDriver(new Driver("Eve", 22, 1)); // risk 9 + 3 = 12
        InsuranceQuote quote = new InsuranceQuote(car, 500);
        // carRating = 3 + (14000-5000)/3000 = 3 + 3 = 6
        // totalRisk = 12 + 6 = 18 (>=15) premium = base * 3.0
        assertEquals(1500.0, quote.calculatePremium());
    }
}
