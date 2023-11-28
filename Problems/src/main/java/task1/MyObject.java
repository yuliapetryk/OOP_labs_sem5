package task1;

import java.io.Serializable;

public class MyObject implements Serializable {

    private String name;
    private String message;
    private int number;

    public MyObject() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public   String toString() {
        return  getName()  + getMessage()  + getNumber();
    }
}
