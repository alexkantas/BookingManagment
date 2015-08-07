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

    private static final Font font = new Font("Arial", Font.BOLD, 30);

    public HotelFrame() {
        //
        RoomPanel roomPanel = new RoomPanel();
        BookingPanel bookingPanel = new BookingPanel();

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
                    System.out.println("It's here!!!");
                    roomPanel.setVisible(false);
                    bookingPanel.setVisible(true);
                    add(bookingPanel,BorderLayout.CENTER);
                    pack();
                    validate();
                } else {
                    System.out.println("Not Here !!!");
                    bookingPanel.setVisible(false);
                    roomPanel.setVisible(true);
                    add(roomPanel,BorderLayout.CENTER);
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
