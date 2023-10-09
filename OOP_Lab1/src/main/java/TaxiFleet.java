import cars.Car;

import java.util.List;
import java.util.stream.Collectors;

public class TaxiFleet {

    private List<Car> cars;

    public TaxiFleet( List<Car> cars) {
        this.cars = cars;
    }

    public int getTotalPrice() {
        if (cars.isEmpty()) {
            return 0;
        }

        return cars
                .stream()
                .filter(Car.class::isInstance)
                .map(Car.class::cast)
                .map(Car::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public List<String> sortByFuelConsumption() {
        return  cars.stream()
                .sorted((car1, car2) -> Double.compare(car1.getFuelConsumption(), car2.getFuelConsumption()))
                .map(Car::getModel)
                .collect(Collectors.toList());
    }

    public List<String> getFilteredBySpeed(int min, int max) {
        return  cars.stream()
                .filter(cars-> cars.getMaxSpeed()>min && cars.getMaxSpeed()<max)
                .map(Car::getModel)
                .collect(Collectors.toList());
    }
}