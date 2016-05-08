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
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandros Kantas
 */
public class DatabaseConnectionMSSQL extends DatabaseConnection {

    private Connection conn = null;
    private Statement statement = null;
    private String sql;

    public DatabaseConnectionMSSQL() {
        String jdbcurl = "jdbc:sqlserver://0.database.windows.net:1433;"
                + "database=super;user=0@0;"
                + "password= !;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;"
                + "loginTimeout=30";

        System.out.print("Connecting...");
        try {
            conn = DriverManager.getConnection(jdbcurl);
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
            sql = "DROP TABLE IF EXISTS ROOMS;";
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
        byte av = 0;
        if (available) {
            av = 1;
        }
        try {
            statement = conn.createStatement();
            sql = "UPDATE ROOMS SET available=" + av + " WHERE id=" + id;
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
        DatabaseConnection db = new DatabaseConnectionMSSQL();
        // db.initializeRoomTable();
        db.updateRoomAvailability(6, false);
    }
}
