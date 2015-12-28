import org.omg.CORBA.Object;

import java.util.ArrayList;

public class ParkingLot {
    private int numOfEmptySlots;
    private ArrayList<ParkingTicket> ticketList;
    private ArrayList<Object> carsParked;

    public ParkingLot(int numOfEmptySlots) {
        this.numOfEmptySlots = numOfEmptySlots;
        ticketList = new ArrayList<>();
        carsParked = new ArrayList<>();
    }

    public ParkingTicket parkAndGetTicket(Object car) {
        if(!isCarParkedAlready(car) && numOfEmptySlots > 0) {
            numOfEmptySlots--;
            ParkingTicket ticket = new ParkingTicket(car.hashCode());
            ticketList.add(ticket);
            return ticket;
        }
        return null;
    }

    private boolean isCarParkedAlready(Object car) {
        ParkingTicket ticket = new ParkingTicket(car.hashCode());
         for(ParkingTicket parkingTicket: ticketList){
             if(parkingTicket.equals(ticket))
                 return true;
         }
        return false;
    }


    public int unparkCar(ParkingTicket ticket) {
        for(ParkingTicket parkingTicket: ticketList){
            if(parkingTicket.equals(ticket))
                return ticket.getCarIdentifier();
        }
        return -1;
    }
}
