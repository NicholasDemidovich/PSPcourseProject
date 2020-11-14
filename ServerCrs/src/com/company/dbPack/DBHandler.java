package com.company.dbPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBHandler extends DBConfig{
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(String name, String surname, String login,
                           String password, String email, String country){
        String insert = "INSERT INTO " + DBConst.USER_TABLE + " ("
                + DBConst.USERS_FIRSTNAME +"," + DBConst.USERS_SURNAME + ","
                + DBConst.USERS_LOGIN + "," + DBConst.USERS_PASSWORD + ","
                + DBConst.USERS_EMAIL + "," + DBConst.USERS_COUNTRY + ")"
                + " VALUES (?,?,?,?,?,?)";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, name);
            prSt.setString(2, surname);
            prSt.setString(3, login);
            prSt.setString(4, password);
            prSt.setString(5, email);
            prSt.setString(6, country);

            prSt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public ResultSet getUser(String login, String password){
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + DBConst.USER_TABLE + " WHERE " +
                DBConst.USERS_LOGIN + "=? AND " +  DBConst.USERS_PASSWORD + "=?";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, login);
            prSt.setString(2, password);

            resultSet = prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public int addProduct(String prNumb, String prDen,
                           String prCond, String prCount, String prType){
        String addpr = "INSERT INTO product_wh (product_number,product_type,product_density,product_conditions,product_count)"
                + " VALUES (?,?,?,?,?)";
        int count =0;
        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(addpr);

            prSt.setString(1, prNumb);
            prSt.setString(2, prType);
            prSt.setString(3, prDen);
            prSt.setString(4, prCond);
            prSt.setString(5, prCount);

            count = prSt.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public ArrayList<String> getProductNumb(){
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        String select = "SELECT product_number FROM product_wh";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();

            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getProductArr(String product_number){
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        String select = "SELECT * FROM product_wh WHERE product_number=?";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,product_number);
            resultSet = prSt.executeQuery();
            int columns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()){
                for (int i = 1; i <= columns; i++) {
                    list.add(resultSet.getString(i));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deleteProduct(String productNumb){
        int count = 0;

        String select = "DELETE FROM product_wh WHERE product_number=?";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, productNumb);

            count = prSt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int editProduct(String prNumb, String prType,
                          String prDen, String prCond,
                           String prCount, String newPrNumb){
        String addpr = "UPDATE product_wh SET " +
                "product_number =?, product_type =? , product_density=? , " +
                "product_conditions =? , product_count=? WHERE product_number=?";
        int count =0;
        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(addpr);
            prSt.setString(1, newPrNumb);
            prSt.setString(2, prType);
            prSt.setString(3, prDen);
            prSt.setString(4, prCond);
            prSt.setString(5, prCount);
            prSt.setString(6, prNumb);

            count = prSt.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public ArrayList<String> getProductFullArr(){
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        String select = "SELECT * FROM product_wh";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
            int columns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()){
                for (int i = 1; i <= columns; i++) {
                    list.add(resultSet.getString(i));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<String> getProductFullArrByNumb(){
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        String select = "SELECT * FROM product_wh ORDER BY product_number";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
            int columns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()){
                for (int i = 1; i <= columns; i++) {
                    list.add(resultSet.getString(i));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getProductFullArrByDen(){
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        String select = "SELECT * FROM product_wh ORDER BY product_density";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
            int columns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()){
                for (int i = 1; i <= columns; i++) {
                    list.add(resultSet.getString(i));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getProductFullArrByCount(){
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        String select = "SELECT * FROM product_wh ORDER BY product_count";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
            int columns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()){
                for (int i = 1; i <= columns; i++) {
                    list.add(resultSet.getString(i));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getProductByDen(String prDen){
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        String select = "SELECT * FROM product_wh WHERE product_density =?";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, prDen);
            resultSet = prSt.executeQuery();
            int columns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()){
                for (int i = 1; i <= columns; i++) {
                    list.add(resultSet.getString(i));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getProductByType(String prType){
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        String select = "SELECT * FROM product_wh WHERE product_type =?";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, prType);
            resultSet = prSt.executeQuery();
            int columns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()){
                for (int i = 1; i <= columns; i++) {
                    list.add(resultSet.getString(i));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getProductByNumb(String prNumb){
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        String select = "SELECT * FROM product_wh WHERE product_number =?";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, prNumb);
            resultSet = prSt.executeQuery();
            int columns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()){
                for (int i = 1; i <= columns; i++) {
                    list.add(resultSet.getString(i));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int filtrProductDes(String productParam){
        int count = 0;

        String select = "DELETE FROM product_wh WHERE product_density=?";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, productParam);

            count = prSt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int filtrProductType(String productParam){
        int count = 0;

        String select = "DELETE FROM product_wh WHERE product_type=?";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, productParam);

            count = prSt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int filtrProductNumb(String productParam){
        int count = 0;

        String select = "DELETE FROM product_wh WHERE product_number=?";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, productParam);

            count = prSt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public ArrayList<String> getProductType(){
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        String select = "SELECT product_type FROM product_tp";

        PreparedStatement prSt = null;

        try {

            prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();

            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
