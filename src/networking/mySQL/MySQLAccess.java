package networking.mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import projectStark.Entity;
import ship.sections.ShipHull;

/*      
 *      @author Viorel Iliescu      *
                                    */

public class MySQLAccess {
    private Connection connection   = null;
    private Statement statement     = null;
    private ResultSet result = null; 
    
    private String url      = "jdbc:mysql://localhost:3306/starktest";
    private String userName = "root";
    private String password = "";
    
    // Notes:
    //private String url = "jdbc:mysql://localhost:3306/DBName";
    //private  = null;

    public MySQLAccess() {

    }
        
    public void connectToDB() throws Exception {
        // Load Driver
        try {
            System.out.println("Loading driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } 
        catch (Exception ex) {
            // handle the error
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Cannot find the driver in the classpath!", ex);
        }

        // Connect to DataBase
        try {
            System.out.println("Connecting database...");
            //connection = DriverManager.getConnection(url, username, password);
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connected!");
        } 
        catch (SQLException ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
            // If things explode, handle error and lets make sure things are Closed!
            System.out.println("Cannot Connect to the Database, Closing the connection.");
            if (connection != null) {
                try { 
                    connection.close(); 
                } 
                catch (SQLException ignore) { 
                }
            } 
            throw new RuntimeException("Cannot connect to the database!", ex); 
        }
    }
        
    public void executeStatement(String statementToExecute) throws SQLException {
        try {
            // Create a new Statement
            try {
                this.statement = connection.createStatement();
            } 
            catch (SQLException ex) {
                Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
            }

            // send the statement to the database!
            try {
                this.statement.executeUpdate(statementToExecute);
                System.out.println("Statement Sent!");
            } 
            catch (SQLException ex) {
                Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (Exception ex) {
            connection.rollback();
            System.out.println("Statement Failed to Send...");
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            close();
        }

    }

    public void readEntityFromDB() throws SQLException {

        try {
            // Create a new Statement
            try {
                statement = connection.createStatement();
            } 
            catch (SQLException ex) {
                Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Read Entity From Database!
            // TODO: 1 - NEED TO DO THIS PART!
            try {
                statement.executeUpdate("CREATE TABLE pet (name VARCHAR(20), owner VARCHAR(20), species VARCHAR(20), sex CHAR(1), birth DATE, death DATE)");
                System.out.println("Statement Sent!");
            } 
            catch (SQLException ex) {
                Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (Exception ex) {
            connection.rollback();
            System.out.println("Failed to Send Anything!");
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            close();
        }

    }

    public void writeEntityToDB(Entity entity) throws SQLException {

        try {
            // Create a new Statement
            // TODO: 1 - THIS GOES HAND IN HAND WITH READENTITYFROMDB
            try {
                statement = connection.createStatement();
            } 
            catch (SQLException ex) {
                Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Create a new Table in the Database called Entity, with the following columns!
            try {
                statement.executeUpdate("CREATE TABLE entity (name VARCHAR(20), owner VARCHAR(20), species VARCHAR(20), sex CHAR(1), birth DATE, death DATE)");
                System.out.println("Statement Sent!");
            } 
            catch (SQLException ex) {
                Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (Exception ex) {
            connection.rollback();
            System.out.println("Failed to Write Anything!");
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            close();
        }

    }

    // Close handling
    private void close() {
        try {
                if (statement != null) {
                        statement.close();
                        statement = null;
                }
                if (result != null) {
                        result.close();
                        result = null;
                }
                if (connection != null) {
                        connection.close();
                        connection = null;
                }
                
        } catch (Exception ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readHullFromDB() throws SQLException { //ShipHull hullToRead

        try {
            // Create a new Statement
            try {
                statement = connection.createStatement();
            } 
            catch (SQLException ex) {
                Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Read Entity From Database!
            // TODO: 1 - NEED TO DO THIS PART!
            try {
                System.out.println("Reading from DataBase!");
                if (statement.execute("SELECT * FROM shiphullsection")) {
                     result = statement.getResultSet();
                }
                else {
                    System.out.println("Select Failed");
                } 
                
                // print what we found? while result has next, itll print that line, or something, GREAT!
                while (result.next()) {
                   String entry = result.getString(1);
                   System.out.println("Hull Catagory Type = "+entry);
                   System.out.println("Section Type = "+result.getString(2));
                   System.out.println("Section Name = "+result.getString(3));
                   System.out.println("Section Desc = "+result.getString(4));
                   System.out.println("Offensive Module Slots = "+result.getString(5));
                   System.out.println("Deffensive Module Slots = "+result.getString(6));
                   System.out.println("Support Module Slots = "+result.getString(7));
                   System.out.println("Critical Module Slots = "+result.getString(8));
                   System.out.println("Total Module Slots = "+result.getString(9));
                   System.out.println("Section Weight = "+result.getString(10));
                   System.out.println("Support Section Health Boost = "+result.getString(11));
                   System.out.println("Support Section Armour Boost = "+result.getString(12));
               }
                System.out.println("ShipHull Read!");
            } 
            catch (SQLException ex) {
                Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (Exception ex) {
            connection.rollback();
            System.out.println("Failed to Read Hull Statistics!");
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            close();
        }

    }
}
