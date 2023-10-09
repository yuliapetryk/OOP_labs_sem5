import cars.Car;
import cars.Minivan;
import cars.SportUtilityVehicle;
import cars.SportsCar;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxiFleetTest {

    @Test
    public void testGetTotalPrice() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Minivan("Model1", 10000, 10.0, 200,7));
        cars.add(new SportsCar("Model2", 15000, 12.0, 180,300));
        cars.add(new SportUtilityVehicle("Model3", 15000, 13.0, 180,7));

        TaxiFleet taxiFleet = new TaxiFleet(cars);

        assertEquals(40000, taxiFleet.getTotalPrice());
    }

    @Test
    public void testSortByFuelConsumption() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Minivan("Model1", 10000, 10.0, 200,7));
        cars.add(new SportsCar("Model2", 15000, 12.0, 180,300));
        cars.add(new SportUtilityVehicle("Model3", 15000, 13.0, 180,7));

        TaxiFleet taxiFleet = new TaxiFleet(cars);

        List<String> sortedModels = taxiFleet.sortByFuelConsumption();

        assertEquals("Model1", sortedModels.get(0));
        assertEquals("Model2", sortedModels.get(1));
        assertEquals("Model3", sortedModels.get(2));
    }

    @Test
    public void testGetFilteredBySpeed() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Minivan("Model1", 10000, 10.0, 200,7));
        cars.add(new SportsCar("Model2", 15000, 12.0, 180,300));
        cars.add(new SportUtilityVehicle("Model3", 15000, 13.0, 180,7));

        TaxiFleet taxiFleet = new TaxiFleet(cars);

        List<String> filteredModels = taxiFleet.getFilteredBySpeed(190, 210);

        assertEquals(1, filteredModels.size());
        assertEquals("Model1", filteredModels.get(0));
    }
}