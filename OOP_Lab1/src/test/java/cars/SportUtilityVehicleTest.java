package cars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SportUtilityVehicleTest {

    private SportUtilityVehicle suv = null;

    @BeforeEach
    public void initSportUtilityVehicle() {
        suv = getSportUtilityVehicle("Model", 10000, 10.0, 200, 7);
    }

    @Test
    public void testConstructor() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                getSportUtilityVehicle("Model", 10000, 10.0, 200, 0)
        );
        Assertions.assertEquals(exception.getMessage(), "Passability rating must be in the range from 1 to 10");

        exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                getSportUtilityVehicle("Model", 10000, 10.0, 200, 11)
        );
        Assertions.assertEquals(exception.getMessage(), "Passability rating must be in the range from 1 to 10");
    }

    @Test
    public void testGetModel() {
        Assertions.assertEquals(suv.getModel(), "Model");
    }

    @Test
    public void testGetPrice() {
        Assertions.assertEquals(suv.getPrice(), 10000);
    }

    @Test
    public void testGetFuelConsumption() {
        Assertions.assertEquals(suv.getFuelConsumption(), 10.0, 0.01);
    }

    @Test
    public void testGetMaxSpeed() {
        Assertions.assertEquals(suv.getMaxSpeed(), 200);
    }

    @Test
    public void testGetPassability() {
        Assertions.assertEquals(suv.getPassability(), 7);
    }

    private SportUtilityVehicle getSportUtilityVehicle(String model, int price, double fuelConsumption, int maxSpeed, int passability) {
        return new SportUtilityVehicle(model, price, fuelConsumption, maxSpeed, passability) {
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
            public int getPassability() {
                return super.getPassability();
            }
        };
    }
}
