package com.epam.cdp.effective.java.methods;

public class Circle {

    private int radius;

    public Circle(int radius) {
        validateParameter(radius);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        validateParameter(radius);
        this.radius = radius;
    }

    private void validateParameter(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number <= 0" + number);
        }
    }

    public double getCircleArea() {
        return Math.PI * (radius * radius);
    }
    
}
