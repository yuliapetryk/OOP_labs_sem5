package cars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarTest {

    private Car car = null;

    @BeforeEach
    public void initCar() {
        car = getCar("Model", 10000, 10.0, 200);
    }

    @Test
    public void testConstructor() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                getCar("", 1, 10.0, 200)
        );
        Assertions.assertEquals(exception.getMessage(), "Model should not be empty");

        exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                getCar("Model", -1, 10.0, 200)
        );
        Assertions.assertEquals(exception.getMessage(), "The price must be a positive number");

        exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                getCar("Model", 10000, -10.0, 200)
        );
        Assertions.assertEquals(exception.getMessage(), "Fuel consumption distance should not be negative");

        exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                getCar("Model", 10000, 10.0, -200)
        );
        Assertions.assertEquals(exception.getMessage(), "Speed should not be negative");
    }

    @Test
    public void testGetModel() {
        Assertions.assertEquals(car.getModel(), "Model");
    }

    @Test
    public void testGetPrice() {
        Assertions.assertEquals(car.getPrice(), 10000);
    }

    @Test
    public void testGetFuelConsumption() {
        Assertions.assertEquals(car.getFuelConsumption(), 10.0, 0.01);
    }

    @Test
    public void testGetMaxSpeed() {
        Assertions.assertEquals(car.getMaxSpeed(), 200);
    }

    private Car getCar(String model, int price, double fuelConsumption, int maxSpeed) {
        return new Car(model, price, fuelConsumption, maxSpeed) {
            @Override
            public String getModel() {
                return super.getModel();
            }

            @Override
            public int getPrice() {
                return super.getPrice();
            }

            @Override
            public double getFuelConsumption() {
                return super.getFuelConsumption();
            }

            @Override
            public double getMaxSpeed() {
                return super.getMaxSpeed();
            }
        };
    }
}


