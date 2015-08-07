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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandros Kantas
 */
public class DatabaseConnect {

    private static final String dbms = "derby";
    private static final String host = "localhost";
    private static final String port = "1527";
    private static final String dbname = "Hotel";
    private static final String properties = "";
    private static final String userName = "alex";
    private static final String password = "alex";
    private Connection conn = null;
    private Statement statement = null;
    private String sql;

    public DatabaseConnect() {
        String jdbcurl = "jdbc:" + dbms + "://" + host + ":" + port + "/" + dbname;
        System.out.print("Connecting...");
        try {
            conn = DriverManager.getConnection(jdbcurl, userName, password);
        } catch (SQLException e) {
            System.out.println("Error:\n");
            System.err.println(e.getMessage());
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Πρόβλημα με τη σύνδεση στην βάση δεδομένων!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        System.out.println("done!");
    }
    
    public void initializeRoomTable(){
        try {
            statement = conn.createStatement();
//            sql = "CREATE TABLE ROOMS("
//                    + "id INT,"
//                    + "beds INT,"
//                    + "maxbeds INT,"
//                    + "cost INT,"
//                    + "available BOOLEAN,"
//                    + "PRIMARY KEY(id))";
//            statement.executeUpdate(sql);
//            int j;
//            for (int i = 0; i < 24; i++) {
//                j=i+1;
//                sql="INSERT INTO ROOMS VALUES ("+j+",3,4,50,true)";
//                statement.executeUpdate(sql);
//            }
            
        } catch (SQLException ex) {
            System.out.println("Error:\n");
            System.err.println(ex.getMessage());
            System.exit(0);;
        }
        
    }

    public static void main(String[] args) {
        DatabaseConnect db = new DatabaseConnect();
        db.initializeRoomTable();
    }
}
