package alexkantas.bookingmanagment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Alexandros Kantas
 */
public class HotelFrame extends JFrame {

    public HotelFrame() {

        //
        JLabel top = new JLabel("Δωμάτια", SwingConstants.CENTER);
        top.setFont(new Font("Arial", Font.BOLD, 30));

        //
        JPanel bottom = new JPanel();
        Color[] colorelements = new Color[]{Color.GREEN, Color.RED};
        String[] stingelements = new String[]{"Διαθέσιμο", "Μη Διαθέσιμο"};
        for (int i = 0; i < stingelements.length; i++) {
            JPanel colorpanel = new JPanel();
            colorpanel.setBackground(colorelements[i]);
            bottom.add(colorpanel);
            bottom.add(new JLabel(stingelements[i]));
        }

        //
        GridLayout grid = new GridLayout(4, 6);
        JPanel mainpanel = new JPanel(grid);

        for (int i = 0; i < 24; i++) {
            Room room = new Room(i + 1, 2, 5);
            mainpanel.add(room);
        }

        //
        add(top, BorderLayout.PAGE_START);
        add(mainpanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);

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
