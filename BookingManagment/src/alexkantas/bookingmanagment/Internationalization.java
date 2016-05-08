/*
 * Copyright (C) 2016 Alexandros Kantas 
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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandros Kantas
 */
public class Internationalization {

    public static ResourceBundle rb;

    public static String error1;
    public static String rooms;
    public static String room;
    public static String doubleroom;
    public static String books;
    public static String oldbooks;
    public static String book;
    public static String addbook;
    public static String available;
    public static String notavailable;
    public static String roomcost;
    public static String cost;
    public static String pernight;
    public static String settings;

    public Internationalization() {
        try {
            File file = new File("Strings.properties");
            URL[] urls = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            Locale locale = Locale.getDefault();
            //locale = new Locale("el","GR");
            //locale = Locale.ENGLISH;
            rb = ResourceBundle.getBundle("Strings", locale, loader);
            
            /* String values */
            error1 = rb.getString("error1");
            rooms = rb.getString("rooms");
            room = rb.getString("room");
            doubleroom = rb.getString("doubleroom");
            roomcost = rb.getString("roomcost");
            cost = rb.getString("cost");
            books = rb.getString("books");
            oldbooks = rb.getString("oldbooks");
            book = rb.getString("book");
            addbook = rb.getString("addbook");
            available = rb.getString("available");
            notavailable = rb.getString("notavailable");
            pernight = rb.getString("pernight");
            settings = rb.getString("settings");
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Internationalization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args)  {
        new Internationalization();
    }
}
