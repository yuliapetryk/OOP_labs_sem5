package cars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinivanTest {

    private Minivan minivan = null;

    @BeforeEach
    public void initMinivan() {
        minivan = getMinivan("Model", 10000, 10.0, 200, 7);
    }

    @Test
    public void testConstructor() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                getMinivan("Model", 10000, 10.0, 200, 0)
        );
        Assertions.assertEquals(exception.getMessage(), "Capacity rating must be positive");

        exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                getMinivan("Model", 10000, 10.0, 200, -7)
        );
        Assertions.assertEquals(exception.getMessage(), "Capacity rating must be positive");
    }

    @Test
    public void testGetModel() {
        Assertions.assertEquals(minivan.getModel(), "Model");
    }

    @Test
    public void testGetPrice() {
        Assertions.assertEquals(minivan.getPrice(), 10000);
    }

    @Test
    public void testGetFuelConsumption() {
        Assertions.assertEquals(minivan.getFuelConsumption(), 10.0, 0.01);
    }

    @Test
    public void testGetMaxSpeed() {
        Assertions.assertEquals(minivan.getMaxSpeed(), 200);
    }

    @Test
    public void testGetCapacity() {
        Assertions.assertEquals(minivan.getCapacity(), 7);
    }

    private Minivan getMinivan(String model, int price, double fuelConsumption, int maxSpeed, int capacity) {
        return new Minivan(model, price, fuelConsumption, maxSpeed, capacity) {
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

            @Override
            public int getCapacity() {
                return super.getCapacity();
            }
        };
    }
}
