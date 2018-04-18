package effective_java;

import com.epam.cdp.effective.java.creating.objects.Car;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarTest {

    @Test
    public void builderTest(){
        Car car = new Car.Builder().name("Ferrari").color("red").cabriolet(true).build();
        assertEquals("Ferrari", car.getName());
        assertEquals("red", car.getColor());
        assertTrue(car.isCabriolet());
    }
}