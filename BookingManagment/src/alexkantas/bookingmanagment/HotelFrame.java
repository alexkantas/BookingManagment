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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Alexandros Kantas
 */
public class HotelFrame extends JFrame {

    public static DatabaseConnection db;
    RoomPanel roomPanel;
    BookingPanel bookingPanel;
    private static final Font font = new Font("Arial", Font.BOLD, 30);

    public HotelFrame() {
        db = new DatabaseConnectionJavaDB();

        //
        roomPanel = new RoomPanel();
        bookingPanel = new BookingPanel();

        //
        GridLayout topgrid = new GridLayout(2, 1);
        JPanel top = new JPanel(topgrid);
        GridLayout selectiongrid = new GridLayout(1, 2);

        //
        JLabel panelTitle = new JLabel("Δωμάτια", SwingConstants.CENTER);
        panelTitle.setFont(font);

        //
        JPanel selectPanel = new JPanel(selectiongrid);
        JButton roomsbutton = new JButton("Δωμάτια");
        roomsbutton.setBackground(Color.lightGray);
        JButton bookbutton = new JButton("Κρατήσεις");
        bookbutton.setBackground(Color.lightGray);
        selectPanel.add(roomsbutton);
        selectPanel.add(bookbutton);

        //
        roomsbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (roomPanel.isVisible()) {
                    roomPanel.updateRooms();
                } else {
                    bookingPanel.setVisible(false);
                    roomPanel.setVisible(true);
                    add(roomPanel, BorderLayout.CENTER);
                    pack();
                    validate();
                }
            }
        });

        //
        bookbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (bookingPanel.isVisible()) {
                    
                } else {
                    roomPanel.setVisible(false);
                    bookingPanel.setVisible(true);
                    add(bookingPanel, BorderLayout.CENTER);
                    pack();
                    validate();
                }
            }
        });

        //
        top.add(panelTitle);
        top.add(selectPanel);

        //
        add(top, BorderLayout.PAGE_START);
        bookingPanel.setVisible(false);
        add(roomPanel, BorderLayout.CENTER);

        //
        setTitle("Hotel Booking Managment");
        pack();
        //setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        HotelFrame hotelframe = new HotelFrame();
    }

}
