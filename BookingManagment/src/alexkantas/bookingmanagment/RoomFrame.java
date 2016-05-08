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
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Alexandros Kantas
 */
public class RoomFrame extends JFrame {

    private static final Font titlefont = new Font("Arial", Font.BOLD, 30);
    private static final Font font = new Font("Arial", Font.BOLD, 15);
    private static final Font compfont = new Font("Arial", Font.PLAIN, 20);

    JButton available = new JButton();
    Room room;
    JButton cost;

    public RoomFrame(Room hotelRoom) {
        room = hotelRoom;
        String title = Internationalization.room + room.getText();

        //
        JLabel top = new JLabel(title, SwingConstants.CENTER);
        top.setFont(titlefont);

        //
        GridLayout grid = new GridLayout(4, 1);
        JPanel mainpanel = new JPanel(grid);

        JButton booking = new JButton(Internationalization.book);
        booking.setBackground(Color.lightGray);
        booking.setFont(compfont);
        mainpanel.add(booking);

        updateAvailableButton();
        available.setFont(titlefont);
        available.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                room.setAvailable(!room.isAvailable());
//         JOptionPane.showMessageDialog(null, "Έγινε αλλαγή της Διαθεσιμότητας!");
                updateAvailableButton();
            }
        });

        mainpanel.add(available);

        JButton type = new JButton(Internationalization.doubleroom);
        type.setBackground(Color.gray);
        type.setFont(compfont);
        mainpanel.add(type);

        cost = new JButton(room.getCost() + "€");
        cost.setBackground(Color.lightGray);
        cost.setFont(compfont);

        cost.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CostFrame c = new CostFrame();
            }
        });

        mainpanel.add(cost);

        //
        add(top, BorderLayout.PAGE_START);
        add(mainpanel, BorderLayout.CENTER);

        //
        setTitle(title);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void updateAvailableButton() {
        available.setBackground(room.getBackground());
        if (room.isAvailable()) {
            available.setText(Internationalization.available);
        } else {
            available.setText(Internationalization.notavailable);
        }
    }

    class CostFrame extends JFrame {

        public CostFrame() {

            String title = Internationalization.roomcost;
            JPanel top = new JPanel();
            JPanel bottom = new JPanel(new BorderLayout());
            JLabel costtxt = new JLabel(Internationalization.roomcost+":");
            costtxt.setFont(font);
            JLabel pricetxt = new JLabel("€ "+Internationalization.pernight);
            pricetxt.setFont(font);

            int roomcost = room.getCost();
            SpinnerModel smodel = new SpinnerNumberModel(roomcost, 0, 50000, 50);
            JSpinner spinner = new JSpinner(smodel);
            spinner.setFont(font);

            JButton okbutton = new JButton("OK");
            okbutton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                   int c = (int)spinner.getValue();
                   room.setCost(c);
                   cost.setText(room.getCost()+"€");
                   dispose();
                }
            });

            top.add(costtxt);
            top.add(spinner);
            top.add(pricetxt);

            bottom.add(okbutton, BorderLayout.LINE_END);

            add(top, BorderLayout.PAGE_START);
            add(bottom, BorderLayout.PAGE_END);

            setTitle(title);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

    }

}
