package task1;

import java.io.*;
import java.net.*;

public class Server {

    static String fileName;

    public static void main(String[] args) throws IOException {
        fileName = "src/main/resources/result.txt";
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
                try (Socket clientSocket = serverSocket.accept()) {
                    processClient(clientSocket);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
        }
    }

    private static void processClient(Socket clientSocket) throws IOException {

        ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
        try {
            Object object = inputStream.readObject();
            MyObject myObject = (MyObject) object;
            save(myObject, fileName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        clientSocket.close();
    }

    private static String getInfo(MyObject myObject) {
        return "Object  " + myObject.getName() + " sent you the message '" +
                myObject.getMessage()  + "' " + myObject.getNumber() + " times\n";
    }

    public static synchronized void save(MyObject myObject, String fileName) {
        String str = getInfo(myObject);

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(fileName, false)) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

