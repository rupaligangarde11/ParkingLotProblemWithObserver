import java.util.HashMap;

public class ParkingLot {
    private int numOfEmptySlots;
    private HashMap<ParkingTicket, Object> ticketAndCarMap;
    private int capacity;
    private ParkingLotOwner owner;

    public ParkingLot(int capacity, ParkingLotOwner owner) {
        this.capacity = capacity;
        this.numOfEmptySlots = capacity;
       ticketAndCarMap = new HashMap<>();
        this.owner = owner;
    }

    public ParkingTicket parkAndGetTicket(Object car) throws ParkingLotException {
        if (!isCarParkedAlready(car)) {
            if (numOfEmptySlots>0) {
                numOfEmptySlots--;
                ParkingTicket ticket = new ParkingTicket(car.hashCode());
                ticketAndCarMap.put(ticket, car);
                if(numOfEmptySlots==0)
                    owner.putFullSign();
                return ticket;
            } else { throw new ParkingLotException("No empty slots available"); }
        }
        else { throw new ParkingLotException("car already parked"); }
    }

    private boolean isCarParkedAlready(Object carToBeParked) {
        for(Object car: ticketAndCarMap.values()){
            if(car.equals(carToBeParked))
                return true;
        }
        return false;
    }


    public Object unparkCar(ParkingTicket ticketToUnpark) throws ParkingLotException {
        for(ParkingTicket parkingTicket: ticketAndCarMap.keySet()){
            if(parkingTicket.equals(ticketToUnpark)){
                numOfEmptySlots++;
                return ticketAndCarMap.remove(ticketToUnpark);
            }
        }
        throw new ParkingLotException("Car not found");
    }


}
