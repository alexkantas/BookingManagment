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
