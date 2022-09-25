package com.company.rest;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.sql.*;
public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws SQLException {
        dbConnection=DriverManager.getConnection(url, user, password);
        return dbConnection;
    }
    public void SignUpData(String Month,String Year,String Size) throws SQLException, ClassNotFoundException {
        if(!getDbConnection().getMetaData().getTables(null,null,"rentdata",null).next()){
        getDbConnection().createStatement().executeUpdate(Const.Create);
        }
        String Insert = "INSERT INTO "+Const.Table + "("+
                Const.SIZETable + "," + Const.MONTHTable + "," +
                Const.YEARSTable + "," + Const.MONEYTable + ")" +
                "VALUE(?,?,?,?)";
        PreparedStatement PrSt = getDbConnection().prepareStatement(Insert);
        PrSt.setInt(1,Integer.parseInt(Size));
        PrSt.setInt(2,Integer.parseInt(Month));
        PrSt.setInt(3,Integer.parseInt(Year));
        PrSt.setInt(4,Integer.parseInt(Size)*150);

        PrSt.executeUpdate();
    }
    public String Calcul(String Month, String Year, String Size){
        return "Расчетная кварплата: "+ Integer.parseInt(Size) * 150 +"р";
    }
    public ResultSet getData(String month,String year,String Sort) throws SQLException, ClassNotFoundException {
        ResultSet resSet;
        String select;
        PreparedStatement PrSt;
        if(!getDbConnection().getMetaData().getTables(null,null,"rentdata",null).next()){
            getDbConnection().createStatement().executeUpdate(Const.Create);
        }
        if(!month.equals("") && !year.equals("")) {
            select = "SELECT * FROM " + Const.Table + " WHERE " + Const.MONTHTable + "=? AND " + Const.YEARSTable + "=?" + " ORDER BY "+Const.IDTable+" "+Sort;
            PrSt = getDbConnection().prepareStatement(select);
            PrSt.setInt(1,Integer.parseInt(month));
            PrSt.setInt(2,Integer.parseInt(year));
        }
        else if(!month.equals("")){
            select="SELECT * FROM "+Const.Table+" WHERE "+Const.MONTHTable+"=?" + " ORDER BY "+Const.IDTable+" "+Sort;
            PrSt = getDbConnection().prepareStatement(select);
            PrSt.setInt(1,Integer.parseInt(month));
        }
        else if(!year.equals("")) {
            select = "SELECT * FROM " + Const.Table + " WHERE " + Const.YEARSTable + "=?" + " ORDER BY "+Const.IDTable+" "+Sort;
            PrSt = getDbConnection().prepareStatement(select);
            PrSt.setInt(2,Integer.parseInt(year));
        }
        else {
            select = "SELECT * FROM " + Const.Table + " ORDER BY "+Const.IDTable+" "+Sort;
            PrSt = getDbConnection().prepareStatement(select);
        }
        resSet=PrSt.executeQuery();
        return resSet;
    }
    public void deleteData() throws SQLException {
       String select = "TRUNCATE " + Const.Table;
       PreparedStatement PrSt = getDbConnection().prepareStatement(select);
       PrSt.executeQuery();
    }
}
