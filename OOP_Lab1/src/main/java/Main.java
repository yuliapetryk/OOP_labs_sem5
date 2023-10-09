import cars.Car;
import cars.Minivan;
import cars.SportUtilityVehicle;
import cars.SportsCar;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = List.of(
                new Minivan("Honda Odyssey", 13000, 20, 180, 8),
                new SportsCar("Chevrolet Camaro ZL1", 46000, 50, 280, 300),
                new SportUtilityVehicle("Mazda CX-5", 30000, 30, 180, 6),
                new Minivan("Toyota Sienna", 15000, 22, 220, 6),
                new SportsCar("Ford Mustang", 89000, 49, 300, 320),
                new SportsCar("Porsche 718 Boxster", 50000, 33, 270, 290),
                new SportUtilityVehicle("Hyundai Tucson", 22000, 34, 180, 8)
        );

        TaxiFleet taxiFleet = new TaxiFleet(cars);
        Scanner scanner = new Scanner(System.in);
        boolean greenLight = true;

        while (greenLight) {
            System.out.print("Enter the command: \n" +
                    "[1] get total price  \n" +
                    "[2] sort by fuel consumption \n" +
                    "[3] get cars whose maximum speed is in the range \n" +
                    "[4] stop");
            System.out.print("\n");

            int number = scanner.nextInt();
            List<String> result;

            switch (number) {
                case 1:
                    System.out.println("Total price = " + taxiFleet.getTotalPrice());
                    break;
                case 2:
                    result = taxiFleet.sortByFuelConsumption();
                    if (!result.isEmpty()) {
                        System.out.println("Sorted list: " + result);
                    } else {
                        System.out.println("There are no cars matching the condition");
                    }
                    break;
                case 3:
                    System.out.println("Input range (min, max)");
                    int min = scanner.nextInt();
                    int max = scanner.nextInt();

                    if (min > max) {
                        int temp = max;
                        max = min;
                        min = temp;
                    }

                    result = taxiFleet.getFilteredBySpeed(min, max);

                    if (!result.isEmpty()) {
                        System.out.println("Filtered list: " + result);
                    } else {
                        System.out.println("There are no cars matching the condition");
                    }
                    break;
                case 4:
                    greenLight = false;
                    break;
                default:
                    System.out.println("You entered an incorrect command");
                    break;
            }
            System.out.print("\n");
        }
    }
}
