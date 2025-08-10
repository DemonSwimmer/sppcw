package sppcw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DriverTest {

    // D1: Test age boundary for valid/invalid driver
    @Test
    public void testDriverNameInvalidIfUnder17() {
        Driver d1 = new Driver("Youngster", 16, 0);
        assertEquals("invalid driver", d1.getName());

        Driver d2 = new Driver("Adult", 17, 0);
        assertEquals("Adult", d2.getName());
    }

    // D2: Risk rating for driver 25+, no accidents
    @Test
    public void testRiskRating_25PlusNoAccidents() {
        Driver d = new Driver("John", 25, 0);
        assertEquals(1, d.riskRating());
    }

    // D3: Risk rating for driver under 25, no accidents
    @Test
    public void testRiskRating_Under25NoAccidents() {
        Driver d = new Driver("Jane", 24, 0);
        assertEquals(6, d.riskRating()); // 1 * 6
    }

    // D4: Risk rating for driver 75+, no accidents
    @Test
    public void testRiskRating_75PlusNoAccidents() {
        Driver d = new Driver("Elder", 75, 0);
        assertEquals(3, d.riskRating()); // 1 * 3
    }

    // D5: Risk rating with accidents for driver 25+
    @Test
    public void testRiskRating_25PlusWithAccidents() {
        Driver d = new Driver("AccidentProne", 30, 2);
        assertEquals(7, d.riskRating()); // 1 + (2 * 3)
    }

    // D6: Risk rating with accidents under 25
    @Test
    public void testRiskRating_Under25WithAccidents() {
        Driver d = new Driver("YoungAccident", 24, 2);
        assertEquals(12, d.riskRating()); // (1*6) + (2*3) = 6 + 6
    }
}
