package cars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SportsCarTest {

    private SportsCar sportsCar = null;

    @BeforeEach
    public void initSportsCar() {
        sportsCar = getSportsCar("Model", 10000, 10.0, 200, 500);
    }

    @Test
    public void testConstructor() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                getSportsCar("Model", 10000, 10.0, 200, 0)
        );
        Assertions.assertEquals(exception.getMessage(), "Horsepower distance should be positive");

        exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                getSportsCar("Model", 10000, 10.0, 200, -500)
        );
        Assertions.assertEquals(exception.getMessage(), "Horsepower distance should be positive");
    }

    @Test
    public void testGetModel() {
        Assertions.assertEquals(sportsCar.getModel(), "Model");
    }

    @Test
    public void testGetPrice() {
        Assertions.assertEquals(sportsCar.getPrice(), 10000);
    }

    @Test
    public void testGetFuelConsumption() {
        Assertions.assertEquals(sportsCar.getFuelConsumption(), 10.0, 0.01);
    }

    @Test
    public void testGetMaxSpeed() {
        Assertions.assertEquals(sportsCar.getMaxSpeed(), 200);
    }

    @Test
    public void testGetHorsepower() {
        Assertions.assertEquals(sportsCar.getHorsepower(), 500);
    }

    private SportsCar getSportsCar(String model, int price, double fuelConsumption, int maxSpeed, int horsepower) {
        return new SportsCar(model, price, fuelConsumption, maxSpeed, horsepower) {
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
            public int getHorsepower() {
                return super.getHorsepower();
            }
        };
    }
}
