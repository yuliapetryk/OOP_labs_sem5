package cars;

public class SportsCar extends Car{

    private int horsepower;

    public SportsCar(String model, int  price, double fuelConsumption, int maxSpeed, int horsepower){

        super(model, price, fuelConsumption, maxSpeed);

        if(horsepower <= 0) {
            throw new IllegalArgumentException("Horsepower distance should be positive");
        }

        this.horsepower = horsepower;
    }

    public int getHorsepower() {
        return horsepower;
    }

    @Override
    public void getSpecialInfo(){
        System.out.println("Horsepower: "+ this.horsepower);
    }
}
