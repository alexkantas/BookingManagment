package alexkantas.bookingmanagment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandros Kantas
 */
public class RoomPanel extends JPanel {

//    DatabaseConnect bd = new DatabaseConnect();
    BorderLayout borderLayout = new BorderLayout();

    GridLayout grid = new GridLayout(4, 6);
    JPanel mainpanel = new JPanel(grid);

    JPanel bottom = new JPanel();
    Color[] colorelements = new Color[]{Color.GREEN, Color.RED};
    String[] stingelements = new String[]{"Διαθέσιμο", "Μη Διαθέσιμο"};

    public RoomPanel() {
        for (int i = 0; i < stingelements.length; i++) {
            JPanel colorpanel = new JPanel();
            colorpanel.setBackground(colorelements[i]);
            bottom.add(colorpanel);
            bottom.add(new JLabel(stingelements[i]));
        }

        initializeRooms();

        setLayout(borderLayout);
        add(mainpanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);

    }

    private void initializeRooms() {
        for (int i = 0; i < 24; i++) {
            Room room = HotelFrame.db.getRoom(i + 1);
            mainpanel.add(room);
        }
    }
    
    public void updateRooms(){
        for (int i = 0; i < 24; i++) {
           Room room = (Room)mainpanel.getComponent(i);
           room.setAvailable(HotelFrame.db.getRoomAvailability(i+1));
        }
    }
}
