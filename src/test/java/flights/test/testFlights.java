package flights.test;

import org.junit.Test;
import flights.driver.Base;
import flights.pages.HomePage;


public class testFlights extends Base {

    @Test
    public void testScenario() {
        HomePage homePage = new HomePage();

        homePage.performBugCheck("Paris", "Istanbul");

    }
}









