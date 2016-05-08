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
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Alexandros Kantas
 */
public class BookingPanel extends JPanel{
    
    //
    BorderLayout borderLayout = new BorderLayout();
    
    //
    JPanel bottom = new JPanel();
    
    //
    JButton settings = new JButton(Internationalization.settings);
    JButton showPassed = new JButton(Internationalization.oldbooks);
    JButton addnew = new JButton(Internationalization.addbook);

    public BookingPanel() {
        
        setLayout(borderLayout);
        
        bottom.add(settings);
        bottom.add(showPassed);
        bottom.add(addnew);
        
        add(bottom,BorderLayout.PAGE_END);
        
    }
}
