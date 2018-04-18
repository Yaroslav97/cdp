package effective_java;

import com.epam.cdp.effective.java.methods.Circle;
import org.junit.Test;

public class CircleTest {

    private Circle circle;

    @Test
    public void positiveValidateTest() {
        circle = new Circle(5);
        circle.getCircleArea();
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeValidateTest() {
        circle = new Circle(-5);
        circle.getCircleArea();
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeValidateTest() {
        circle = new Circle(0);
        circle.setRadius(-1);
        circle.getCircleArea();
    }
}