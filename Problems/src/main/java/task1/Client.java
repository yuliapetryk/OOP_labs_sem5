package task1;

import java.io.*;
import java.net.*;

public class Client {


    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
             objectOutputStream.writeObject(getMyObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static MyObject getMyObject(){
        MyObject dataObject = new MyObject();
        dataObject.setName("Yulia");
        dataObject.setMessage("Hi");
        dataObject.setNumber(7);
        return dataObject;
    }

}
