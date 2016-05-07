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

/**
 *
 * @author Alexandros Kantas
 */
public abstract class DatabaseConnection {

    public abstract void initializeRoomTable();
    public abstract Room getRoom(int id);
    public abstract void updateRoomAvailability(int id, boolean available);
    public abstract void updateRoomCost(int id, int cost);
    public abstract boolean getRoomAvailability(int id);
}
