package cars;

public class Minivan extends Car{

    private int capacity;

    public Minivan(String model, int  price, double fuelConsumption, int maxSpeed, int capacity){

        super(model, price, fuelConsumption, maxSpeed);

        if((capacity <= 0)) {
            throw new IllegalArgumentException("Capacity rating must be positive");
        }

        this.capacity=capacity;
    }


    public int getCapacity() {
        return capacity;
    }
}
