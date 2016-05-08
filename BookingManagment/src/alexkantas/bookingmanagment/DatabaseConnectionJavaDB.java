/*
 * Copyright (C) 2015 Alexandros Kantas 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package alexkantas.bookingmanagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandros Kantas
 */
public class DatabaseConnectionJavaDB extends DatabaseConnection {

    private static final String dbms = "derby";
    private static final String host = "localhost";
    private static final String port = "1527";
    private static final String dbname = "Hotel";
    private static final String userName = "alex";
    private static final String password = "alex";
    private Properties properties = new Properties();
    private Connection conn = null;
    private Statement statement = null;
    private String sql;

    public DatabaseConnectionJavaDB() {
        String jdbcurl = "jdbc:" + dbms + "://" + host + ":" + port + "/" + dbname;
        properties.put("user", userName);
        properties.put("password", password);
        properties.put("user", userName);
        System.out.print("Connecting...");
        try {
            conn = DriverManager.getConnection(jdbcurl, properties);
        } catch (SQLException e) {
            System.out.println("Error:\n");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, Internationalization.error1, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        System.out.println("done!");
    }

    public void initializeRoomTable() {
        try {
            System.out.print("Initializing...");
            statement = conn.createStatement();
            sql = sql
                    + "CREATE TABLE ROOMS("
                    + "id INT,"
                    + "beds INT,"
                    + "maxbeds INT,"
                    + "cost INT,"
                    + "available BIT,"
                    + "PRIMARY KEY(id))";
            statement.executeUpdate(sql);
            int j;
            for (int i = 0; i < 24; i++) {
                j = i + 1;
                sql = "INSERT INTO ROOMS VALUES (" + j + ",3,4,50,1)";
                statement.executeUpdate(sql);
            }

        } catch (SQLException ex) {
            System.out.println("Error:\n");
            System.err.println(ex.getMessage());
            System.exit(0);;
        }
        System.out.println("done!");
    }

    public Room getRoom(int id) {
        try {
            statement = conn.createStatement();
            sql = "SELECT * from ROOMS WHERE id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            rs.next();

            int beds = rs.getInt("beds");
            int maxbeds = rs.getInt("maxbeds");
            int cost = rs.getInt("cost");
            boolean available = rs.getBoolean("available");

            //System.out.println("Data: " +id+" beds "+beds+" max "+maxbeds+" cost "+cost+" status "+available);
            return new Room(id, beds, maxbeds, available, cost);
        } catch (SQLException e) {
            System.out.println("Error:\n");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Πρόβλημα με την ανάκτηση στοιχείων των"
                    + " δωματίων από την ΒΔ!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return null;
    }

    public void updateRoomAvailability(int id, boolean available) {
        try {
            statement = conn.createStatement();
            sql = "UPDATE ROOMS SET available=" + available + " WHERE id=" + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error:\n");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Πρόβλημα με την τροποποίηση στοιχείων στη ΒΔ!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateRoomCost(int id, int cost) {
        try {
            statement = conn.createStatement();
            sql = "UPDATE ROOMS SET cost=" + cost + " WHERE id=" + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error:\n");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Πρόβλημα με την τροποποίηση στοιχείων στη ΒΔ!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean getRoomAvailability(int id) {
        try {
            statement = conn.createStatement();
            sql = "SELECT available from ROOMS WHERE id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            rs.next();

            boolean available = rs.getBoolean("available");

            //System.out.println("Data: " +id+" beds "+beds+" max "+maxbeds+" cost "+cost+" status "+available);
            return available;
        } catch (SQLException e) {
            System.out.println("Error:\n");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Πρόβλημα με την ανάκτηση διαθεσιμότητας"
                    + "του δωματίου από την ΒΔ!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return false;
    }

    public static void main(String[] args) {
        new Internationalization();
        DatabaseConnection db = new DatabaseConnectionJavaDB();
        // db.initializeRoomTable();
        //db.updateRoomAvailability(6, false);
    }
}
