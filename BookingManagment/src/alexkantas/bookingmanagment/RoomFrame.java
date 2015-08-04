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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Alexandros Kantas
 */
public class RoomFrame extends JFrame {

    public RoomFrame(Room room) {
        String title = "Δωμάτιο " + room.getText();

        //
        JLabel top = new JLabel(title, SwingConstants.CENTER);
        top.setFont(new Font("Arial", Font.BOLD, 30));

        //
        GridLayout grid = new GridLayout(3,1);
        JPanel mainpanel = new JPanel(grid);

        JButton available = new JButton("Διαθέσιμο");
        available.setBackground(room.getBackground());
        available.setFont(new Font("Arial", Font.PLAIN,20));
        available.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
         room.setAvailable(!room.isAvailable());
         //JOptionPane.showMessageDialog(null, "Έγινε αλλαγή της Διαθεσιμότητας!");
         available.setBackground(room.getBackground());
            }
        });
        
        mainpanel.add(available);

        JButton type = new JButton("Δίκλινο");
        type.setBackground(Color.gray);
        type.setFont(new Font("Arial", Font.PLAIN,20));
        mainpanel.add(type);

        JButton cost = new JButton(room.getCost() + "€");
        cost.setBackground(Color.lightGray);
        cost.setFont(new Font("Arial", Font.PLAIN,20));
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

}
