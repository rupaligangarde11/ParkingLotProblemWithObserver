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
            carsParked.add(car);
            return ticket;
        }
        return null;
    }

    private boolean isCarParkedAlready(Object carToBeParked) {
        for(Object car: carsParked){
            if(car.equals(carToBeParked))
                return true;
        }
        return false;
    }


    public Object unparkCar(ParkingTicket ticketToUnpark) {
        for(int ticketIndex = 0; ticketIndex<ticketList.size();ticketIndex++) {
            ParkingTicket parkingTicket = ticketList.get(ticketIndex);
            if(parkingTicket.equals(ticketToUnpark)) {
                ticketList.remove(ticketIndex);
                numOfEmptySlots++;
                return carsParked.remove(ticketIndex);
            }
        }
        return null;
    }
}
