package sppcw;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        CarTest.class,
        DriverTest.class,
        InsuranceQuoteTest.class
})
public class TestSuite {
}