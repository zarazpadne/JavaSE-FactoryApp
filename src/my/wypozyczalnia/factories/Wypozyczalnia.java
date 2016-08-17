package my.wypozyczalnia.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import my.wypozyczalnia.model.Cars;
import my.wypozyczalnia.model.Motors;
import my.wypozyczalnia.model.Quads;
import my.wypozyczalnia.model.Wehicle;

public class Wypozyczalnia {
    private static String dbURL = "jdbc:derby://localhost:1527/factorymethodDB";
    private static Wypozyczalnia instance = null;
    private static ArrayList<Wehicle> wehiclesList = new ArrayList<Wehicle>();
    private static ArrayList<Wehicle> carsList = new ArrayList<Wehicle>();
    private static ArrayList<Wehicle> motorsList = new ArrayList<Wehicle>();
    private static ArrayList<Wehicle> quadsList = new ArrayList<Wehicle>();
    
    private Wypozyczalnia() {
        addCarsToList();
        addMotorsToList();
        addQuadsToList();
    }
    
    public static void addCarsToList() {
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "SELECT id_Car FROM CARS";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                Cars car = new Cars(id);
                wehiclesList.add(car);
                carsList.add(car);
            } 
            s .close();
        } catch (Exception except) {
            except.printStackTrace();
        }
    }
    
    public static void addMotorsToList() {
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "SELECT id_motor FROM MOTORS";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                Motors motor = new Motors(id);
                wehiclesList.add(motor);
                motorsList.add(motor);
                        
            } 
            s .close();
        } catch (Exception except) {
            except.printStackTrace();
        }
    }
    
    public static void addQuadsToList() {
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "SELECT id_quad FROM QUADS";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                Quads quad = new Quads(id);
                wehiclesList.add(quad);
                quadsList.add(quad);
            } 
            s .close();
        } catch (Exception except) {
            except.printStackTrace();
        }
    }           
    
    public synchronized static Wypozyczalnia getInstance() {
        if (instance == null) instance = new Wypozyczalnia();
        return instance;
    }
    
    public static ArrayList getwehiclesList() {
        return wehiclesList;
    }
    
    public static ArrayList getCarsList() {
        return carsList;
    }
    
    public static ArrayList getMotorsList() {
        return motorsList;
    }
    
    public static ArrayList getQuadsList() {
        return quadsList;
    }
}
