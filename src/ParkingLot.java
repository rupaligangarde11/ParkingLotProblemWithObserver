import java.util.HashMap;
import java.util.List;

public class ParkingLot {
    private final List<Observer> observers;
    private int numOfEmptySlots;
    private HashMap<ParkingTicket, Object> ticketAndCarMap;
    private int capacity;


    public ParkingLot(int capacity, List<Observer> observers) {
        this.capacity = capacity;
        this.observers=observers;
        this.numOfEmptySlots = capacity;
        ticketAndCarMap = new HashMap<>();
    }

    public ParkingTicket parkAndGetTicket(Object car) throws NoSlotsAvailableException, CarAlreadyPresentException {
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
                throw new NoSlotsAvailableException("No empty slots available");
            }
        } else {
            throw new CarAlreadyPresentException("car already parked");
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


    public Object unparkCar(ParkingTicket ticketToUnpark) throws NoCarFoundException {
        for (ParkingTicket parkingTicket : ticketAndCarMap.keySet()) {
            if (parkingTicket.equals(ticketToUnpark)) {
                numOfEmptySlots++;
                return ticketAndCarMap.remove(ticketToUnpark);
            }
        }
        throw new NoCarFoundException("Car not found");
    }
}
