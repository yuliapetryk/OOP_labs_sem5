package data;

import com.sun.jdi.Value;

public class Gem {
    private String name;
    private int id;
    private String preciousness;
    private String origin;
    private VisualParameters visualParameters;
    private double value;

    public Gem(){}

    public Gem (String name, int id, String preciousness, String origin, double value, VisualParameters visualParameters ){
        this.id = id;
        this.name = name;
        this.preciousness = preciousness;
        this.origin = origin;
        this.value= value;
        this.visualParameters = visualParameters;
    }

    @Override
    public String toString() {
        return "Gem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preciousness='" + preciousness + '\'' +
                ", origin='" + origin + '\'' +
                ", value=" + value +
                 visualParameters.toString() +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPreciousness() {
        return preciousness;
    }

    public void setPreciousness(String preciousness) {
        this.preciousness = preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
