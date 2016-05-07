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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Alexandros Kantas
 */
public class Room extends JButton implements ActionListener {

    //
    private int id;
    private int beds;  //number of beds in room
    private int maxbeds; //potential higher nymber of beds
    private boolean available;
    private int cost;
    RoomFrame roomFrame;

    //
    public Room(int id, int beds, int maxbeds) {
        this(id, beds, maxbeds, true, 0);
    }

    public Room(int id, int beds, int maxbeds, boolean availble, int cost) {
        this.id = id;
        this.beds = beds;
        this.maxbeds = maxbeds;
        this.available = availble;
        this.cost = cost;
        setText("" + id);
        setFont(new Font("Agency FB", Font.BOLD, 19));
        addActionListener(this);
        updateStyle();
    }

    //
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        HotelFrame.db.updateRoomAvailability(id, available);
        this.available = available;
        updateStyle();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        HotelFrame.db.updateRoomCost(id, cost);
        this.cost = cost;
    }

    //
    private void updateStyle() {

        if (available) {
            setBackground(Color.green);
        } else {
            setBackground(Color.red);
        }

        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //setAvailable(!available);
        if (roomFrame == null || !roomFrame.isShowing()) {
            roomFrame = new RoomFrame(this);
        } else {
            roomFrame.toFront();
        }

    }

}
