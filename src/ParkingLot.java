import java.util.HashMap;

public class ParkingLot {
    private int numOfEmptySlots;
    private HashMap<ParkingTicket, Object> ticketAndCarMap;

    public ParkingLot(int numOfEmptySlots) {
        this.numOfEmptySlots = numOfEmptySlots;
       ticketAndCarMap = new HashMap<>();
    }

    public ParkingTicket parkAndGetTicket(Object car) throws CarAlreadyParkedException, NoEmptySpotAvailableException {
        if(!isCarParkedAlready(car)) {
            if (numOfEmptySlots > 0) {
                numOfEmptySlots--;
                ParkingTicket ticket = new ParkingTicket(car.hashCode());
                ticketAndCarMap.put(ticket, car);
                return ticket;
            }
            throw new NoEmptySpotAvailableException();
        }
        throw new CarAlreadyParkedException();
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
                return ticketAndCarMap.remove(ticketToUnpark);
            }
        }
        throw new CarNotFoundException();
    }
}
