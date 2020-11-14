package sample.ClientData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataSending {
    private static String ipAddress = "localhost";
    private static int port = 5678;
    Socket socket;

    private static ObjectInputStream objectInputStream = null;
    private static ObjectOutputStream objectOutputStream = null;

    public String loginFormSending(String login, String password){
        String result="";
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject("0");

            objectOutputStream.writeObject(login);
            objectOutputStream.writeObject(password);

            objectInputStream = new ObjectInputStream(socket.getInputStream());

            result = (String)objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void signUpFormSending(String name, String surname, String login,
                                  String password, String email, String country){
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject("1");

            objectOutputStream.writeObject(name);
            objectOutputStream.writeObject(surname);
            objectOutputStream.writeObject(login);
            objectOutputStream.writeObject(password);
            objectOutputStream.writeObject(email);
            objectOutputStream.writeObject(country);
        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        }
    }

    public int registerProduct(String prNumb, String prDen,
                                String prCond, String prCount, String prType){
        int result =0;
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("2");

            objectOutputStream.writeObject(prNumb);
            objectOutputStream.writeObject(prDen);
            objectOutputStream.writeObject(prCond);
            objectOutputStream.writeObject(prCount);
            objectOutputStream.writeObject(prType);

            result = (int)objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> selectProductNumber(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("3");
            list = (ArrayList<String>) objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> selectProductArr(String selectedValue){
        ArrayList<String> list = new ArrayList<String>();
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("4");
            objectOutputStream.writeObject(selectedValue);
            list = (ArrayList<String>) objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deleteProductSend(String productNumb){
        int result=0;
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("5");

            objectOutputStream.writeObject(productNumb);

            result = (int)objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int editProductSend(String product_type , String product_density,
                               String product_conditions, String product_count,
                               String productNumb,String newProductNumb){
        int result=0;
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("6");

            objectOutputStream.writeObject(productNumb);
            objectOutputStream.writeObject(product_type);
            objectOutputStream.writeObject(product_density);
            objectOutputStream.writeObject(product_conditions);
            objectOutputStream.writeObject(product_count);
            objectOutputStream.writeObject(newProductNumb);

            result = (int)objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> selectProductFullArr(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("7");
            list = (ArrayList<String>) objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> selectProductFullArrByNumb(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("8");
            list = (ArrayList<String>) objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> selectProductFullArrByDen(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("9");
            list = (ArrayList<String>) objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> selectProductFullArrByCount(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("10");
            list = (ArrayList<String>) objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> findProductByDen(String prDen){
        ArrayList<String> list = new ArrayList<String>();
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("11");
            objectOutputStream.writeObject(prDen);
            list = (ArrayList<String>) objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> findProductByType(String prTYpe){
        ArrayList<String> list = new ArrayList<String>();
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("12");
            objectOutputStream.writeObject(prTYpe);
            list = (ArrayList<String>) objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> findProductByNumb(String prNumb){
        ArrayList<String> list = new ArrayList<String>();
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("13");
            objectOutputStream.writeObject(prNumb);
            list = (ArrayList<String>) objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int filtrProductDes(String productParam){
        int result=0;
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("14");

            objectOutputStream.writeObject(productParam);

            result = (int)objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int filtrProductType(String productParam){
        int result=0;
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("15");

            objectOutputStream.writeObject(productParam);

            result = (int)objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int filtrProductNumb(String productParam){
        int result=0;
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("16");

            objectOutputStream.writeObject(productParam);

            result = (int)objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> selectProductType(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            socket = new Socket(ipAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("17");
            list = (ArrayList<String>) objectInputStream.readObject();

        }
        catch (UnknownHostException ex) {
            System.out.print("Server not found: " + ex.getMessage() + "\n");
        }
        catch (IOException ex) {
            System.out.print("I/O error: " + ex.getMessage() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}
