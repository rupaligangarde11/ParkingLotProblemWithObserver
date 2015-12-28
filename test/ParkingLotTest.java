 import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {

    @org.junit.Test
    public void isTicketReturnedWhenCarIsParked() throws Exception {
        ParkingLot parkingLot = new ParkingLot(10);
        Object car = new Object();
        ParkingTicket ticket = new ParkingTicket(car.hashCode());
        assertEquals(ticket, parkingLot.parkAndGetTicket(car));
    }

    @Test(expected = ParkingLotFullException.class)
    public void shouldNotReturnATicketWhenTheLotIsFull() throws CarAlreadyParkedException, NoEmptySpotAvailableException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object car1 = new Object();
        parkingLot.parkAndGetTicket(car1);
        Object car2 = new Object();
        parkingLot.parkAndGetTicket(car2);
    }

    @Test(expected = CarAlreadyParkedException.class)
    public void shouldNotReturnATicketWhenThisCarIsParkedAlready() throws CarAlreadyParkedException, NoEmptySpotAvailableException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object car1 = new Object();
        parkingLot.parkAndGetTicket(car1);
        assertNull(parkingLot.parkAndGetTicket(car1));
    }

    @Test
    public void shouldUnparkTheRightCarGivenTheTicket() throws CarAlreadyParkedException, NoEmptySpotAvailableException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object car1 = new Object();
        ParkingTicket ticket = parkingLot.parkAndGetTicket(car1);
        assertEquals(car1, parkingLot.unparkCar(ticket));
    }

    @Test(expected = ParkingLotFullException.class)
    public void shouldThrowExceptionWhenParkingLotIsFull() throws CarAlreadyParkedException, NoEmptySpotAvailableException, CarNotFoundException, ParkingLotFullException{
        ParkingLot parkingLot = new ParkingLot(1);
        Object car = new Object();
        ParkingTicket ticket = parkingLot.parkAndGetTicket(car);

    }

    @Test(expected = ParkingLotFullException.class)
    public void shouldReturnTrueWhenParkingLotIsFull() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        Object car1 = new Object();
        parkingLot.parkAndGetTicket(car1);
        Object car2 = new Object();
        parkingLot.parkAndGetTicket(car2);
    }

    @Test(expected = CarNotFoundException.class)
    public void shouldThrowExceptionWhenTryingToUnparkAnAlreadyUnparkedCar() throws CarAlreadyParkedException, NoEmptySpotAvailableException, CarNotFoundException, ParkingLotFullException{
        ParkingLot parkingLot = new ParkingLot(2);
        Object car = new Object();
        ParkingTicket ticket = parkingLot.parkAndGetTicket(car);
        parkingLot.unparkCar(ticket);
        parkingLot.unparkCar(ticket);
    }

}
