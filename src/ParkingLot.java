import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLot {
    private int numOfEmptySlots;
    private HashMap<ParkingTicket, Object> ticketAndCarMap;
    private int capacity;
    private List<Observer> observers;

    public ParkingLot(int capacity, Observer observer) {
        this.capacity = capacity;
        this.numOfEmptySlots = capacity;
        ticketAndCarMap = new HashMap<>();
        observers = new ArrayList<>();
    }

    public ParkingTicket parkAndGetTicket(Object car) throws ParkingLotException {
        if (!isCarParkedAlready(car)) {
            if (numOfEmptySlots > 0) {
                numOfEmptySlots--;
                ParkingTicket ticket = new ParkingTicket(car.hashCode());
                ticketAndCarMap.put(ticket, car);
                if (numOfEmptySlots == 0){
                    notifyAllObservers();
                }
                return ticket;
//                parkingLotNotifier.notifyAboutFullParkingLot();
            } else {
                throw new ParkingLotException("No empty slots available");
            }
        } else {
            throw new ParkingLotException("car already parked");
        }
    }

    public void notifyAllObservers(){
        for(Observer observer:observers){
            observer.notifyToPerson();
        }
    }

    private boolean isCarParkedAlready(Object carToBeParked) {
        for (Object car : ticketAndCarMap.values()) {
            if (car.equals(carToBeParked))
                return true;
        }
        return false;
    }


    public Object unparkCar(ParkingTicket ticketToUnpark) throws ParkingLotException {
        for (ParkingTicket parkingTicket : ticketAndCarMap.keySet()) {
            if (parkingTicket.equals(ticketToUnpark)) {
                numOfEmptySlots++;
                return ticketAndCarMap.remove(ticketToUnpark);
            }
        }
        throw new ParkingLotException("Car not found");
    }


}
