package cars;

public abstract class Car {

    private String model;

    private int  price;

    private double fuelConsumption;

    private int maxSpeed;

    public Car(String model, int price, double fuelConsumption, int maxSpeed) {
        if( model=="") {
            throw new IllegalArgumentException("Model should not be empty");
        }
        if(price<=0) {
            throw new IllegalArgumentException("The price must be a positive number");
        }
        if(maxSpeed < 0) {
            throw new IllegalArgumentException("Speed should not be negative");
        }
        if(fuelConsumption < 0) {
            throw new IllegalArgumentException("Fuel consumption distance should not be negative");
        }
        this.model=model;
        this.price = price;
        this.maxSpeed = maxSpeed;
        this.fuelConsumption = fuelConsumption;

    }
    public int getPrice() {
        return this.price;
    }

    public String getModel() {
        return this.model;
    }

    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }
}
