package com.epam.cdp.effective.java.creating.objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Car {

    private final String name;
    private final String color;
    private final boolean cabriolet;

    /**
     * Entity
     *
     * @param builder the builder
     */
    public Car(Builder builder) {
        this.name = builder.name;
        this.color = builder.color;
        this.cabriolet = builder.cabriolet;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public boolean isCabriolet() {
        return cabriolet;
    }

    public static class Builder {

        private String name;
        private String color;
        private boolean cabriolet;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder cabriolet(boolean cabriolet) {
            this.cabriolet = cabriolet;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Car car = (Car) obj;

        return new EqualsBuilder()
                .append(cabriolet, car.cabriolet)
                .append(name, car.name)
                .append(color, car.color)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(color)
                .append(cabriolet)
                .toHashCode();
    }

}
