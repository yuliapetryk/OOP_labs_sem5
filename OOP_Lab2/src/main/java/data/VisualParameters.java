package data;

public class VisualParameters {

    private String color;
    private double transparency;
    private int numberOfFaces;

    public VisualParameters(){}

    public VisualParameters(String color, double transparency, int numberOfFaces ){
        this.color = color;
        this.transparency = transparency;
        this.numberOfFaces = numberOfFaces;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "color='" + color + '\'' +
                ", transparency=" + transparency +
                ", numberOfFaces=" + numberOfFaces +
                '}';
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getTransparency() {
        return transparency;
    }

    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    public int getNumberOfFaces() {
        return numberOfFaces;
    }

    public void setNumberOfFaces(int numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

}
