package com.company;

import com.company.dbPack.DBHandler;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Session extends Thread{
    private Socket socket;

    private ObjectOutputStream objectOutputStream = null;
    private ObjectInputStream objectInputStream = null;


    public Session(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        int sw = 1;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            sw =Integer.parseInt((String)objectInputStream.readObject());
            switch (sw){
                case 0:{
                    DBHandler dbHandler = new DBHandler();
                    String login = (String)objectInputStream.readObject();
                    String password = (String)objectInputStream.readObject();
                    System.out.println("Login: " + login + "\nPassword: " + password );
                    ResultSet resultSet = dbHandler.getUser(login,password);
                    int counter = 0;

                    while(resultSet.next()){
                        counter++;
                    }
                    if(counter!=0) objectOutputStream.writeObject("true");
                    else objectOutputStream.writeObject("false");
                }
                break;
                case 1:{
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    String name = (String)objectInputStream.readObject();
                    String surname = (String)objectInputStream.readObject();
                    String login = (String)objectInputStream.readObject();
                    String password = (String)objectInputStream.readObject();
                    String email = (String)objectInputStream.readObject();
                    String country = (String)objectInputStream.readObject();

                    DBHandler dbHandler =new DBHandler();

                    dbHandler.signUpUser(name, surname, login, password, email, country);
                }
                    break;
                case 2:{
                    String prNumb = (String)objectInputStream.readObject();
                    String prDen = (String)objectInputStream.readObject();
                    String prCond = (String)objectInputStream.readObject();
                    String prCount = (String)objectInputStream.readObject();
                    String prType = (String)objectInputStream.readObject();

                    DBHandler dbHandler =new DBHandler();

                    int result = dbHandler.addProduct(prNumb, prDen, prCond, prCount, prType);
                    objectOutputStream.writeObject(result);
                }
                break;
                case 3:{
                    DBHandler dbHandler = new DBHandler();
                    ArrayList<String> list = dbHandler.getProductNumb();
                    objectOutputStream.writeObject(list);
                }
                break;
                case 4:{
                    DBHandler dbHandler = new DBHandler();
                    String prType = (String)objectInputStream.readObject();
                    ArrayList<String> list = dbHandler.getProductArr(prType);
                    objectOutputStream.writeObject(list);
                }
                break;
                case 5:{
                    DBHandler dbHandler = new DBHandler();
                    String productNumb = (String)objectInputStream.readObject();
                    int count = dbHandler.deleteProduct(productNumb);
                    objectOutputStream.writeObject(count);
                }
                break;
                case 6:{
                    String prNumb = (String)objectInputStream.readObject();
                    String prType = (String)objectInputStream.readObject();
                    String prDen = (String)objectInputStream.readObject();
                    String prCond = (String)objectInputStream.readObject();
                    String prCount = (String)objectInputStream.readObject();
                    String newPrNumb = (String)objectInputStream.readObject();

                    DBHandler dbHandler =new DBHandler();

                    int result = dbHandler.editProduct(prNumb, prType, prDen, prCond, prCount,newPrNumb);
                    objectOutputStream.writeObject(result);
                }
                break;
                case 7:{
                    DBHandler dbHandler = new DBHandler();
                    ArrayList<String> list = dbHandler.getProductFullArr();
                    objectOutputStream.writeObject(list);
                }
                break;
                case 8:{
                    DBHandler dbHandler = new DBHandler();
                    ArrayList<String> list = dbHandler.getProductFullArrByNumb();
                    objectOutputStream.writeObject(list);
                }
                break;
                case 9:{
                    DBHandler dbHandler = new DBHandler();
                    ArrayList<String> list = dbHandler.getProductFullArrByDen();
                    objectOutputStream.writeObject(list);
                }
                break;
                case 10:{
                    DBHandler dbHandler = new DBHandler();
                    ArrayList<String> list = dbHandler.getProductFullArrByCount();
                    objectOutputStream.writeObject(list);
                }
                break;
                case 11:{
                    String prDen = (String)objectInputStream.readObject();
                    DBHandler dbHandler = new DBHandler();
                    ArrayList<String> list = dbHandler.getProductByDen(prDen);
                    objectOutputStream.writeObject(list);
                }
                break;
                case 12:{
                    String prType = (String)objectInputStream.readObject();
                    DBHandler dbHandler = new DBHandler();
                    ArrayList<String> list = dbHandler.getProductByType(prType);
                    objectOutputStream.writeObject(list);
                }
                break;
                case 13:{
                    String prNumb = (String)objectInputStream.readObject();
                    DBHandler dbHandler = new DBHandler();
                    ArrayList<String> list = dbHandler.getProductByNumb(prNumb);
                    objectOutputStream.writeObject(list);
                }
                break;
                case 14:{
                    DBHandler dbHandler = new DBHandler();
                    String productParam = (String)objectInputStream.readObject();
                    int count = dbHandler.filtrProductDes(productParam);
                    objectOutputStream.writeObject(count);
                }
                break;
                case 15:{
                    DBHandler dbHandler = new DBHandler();
                    String productParam = (String)objectInputStream.readObject();
                    int count = dbHandler.filtrProductType(productParam);
                    objectOutputStream.writeObject(count);
                }
                break;
                case 16:{
                    DBHandler dbHandler = new DBHandler();
                    String productParam = (String)objectInputStream.readObject();
                    int count = dbHandler.filtrProductNumb(productParam);
                    objectOutputStream.writeObject(count);
                }
                break;
                case 17:{
                    DBHandler dbHandler = new DBHandler();
                    ArrayList<String> list = dbHandler.getProductType();
                    objectOutputStream.writeObject(list);
                }
                break;


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
