import java.util.HashMap;

public class ParkingLot {
    private int numOfEmptySlots;
    private HashMap<ParkingTicket, Object> ticketAndCarMap;
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.numOfEmptySlots = capacity;
       ticketAndCarMap = new HashMap<>();
    }

    public ParkingTicket parkAndGetTicket(Object car) throws CarAlreadyParkedException, NoEmptySpotAvailableException, ParkingLotFullException {
        if (!isCarParkedAlready(car)) {
            if (numOfEmptySlots>0) {
                numOfEmptySlots--;
                ParkingTicket ticket = new ParkingTicket(car.hashCode());
                ticketAndCarMap.put(ticket, car);
                if(numOfEmptySlots==0)
                    throw new ParkingLotFullException();
                return ticket;
            } else { throw new NoEmptySpotAvailableException(); }
        }
        else { throw new CarAlreadyParkedException(); }
    }

    private boolean isCarParkedAlready(Object carToBeParked) {
        for(Object car: ticketAndCarMap.values()){
            if(car.equals(carToBeParked))
                return true;
        }
        return false;
    }


    public Object unparkCar(ParkingTicket ticketToUnpark) throws CarNotFoundException {
        for(ParkingTicket parkingTicket: ticketAndCarMap.keySet()){
            if(parkingTicket.equals(ticketToUnpark)){
                numOfEmptySlots++;
                return ticketAndCarMap.remove(ticketToUnpark);
            }
        }
        throw new CarNotFoundException();
    }


}
