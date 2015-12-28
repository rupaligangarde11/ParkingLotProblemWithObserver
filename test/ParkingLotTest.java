import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ParkingLotTest {

    @org.junit.Test
    public void isTicketReturnedWhenCarIsParked() throws NoSlotsAvailableException,CarAlreadyPresentException {
        Object car = new Object();
        List<Observer> observers = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(10,observers);
        ParkingTicket ticket = new ParkingTicket(car.hashCode());
        assertEquals(ticket, parkingLot.parkAndGetTicket(car));
    }

    @Test(expected = NoSlotsAvailableException.class)
    public void shouldNotReturnATicketWhenTheLotIsFull() throws NoSlotsAvailableException, CarAlreadyPresentException {
        Object car1 = new Object();
        Object car2 = new Object();
        Observer parkingOwner = Mockito.mock(ParkingLotOwner.class);
        Observer airportSecurity = Mockito.mock(AirportSecurity.class);
        List<Observer> observers = new ArrayList<>();
        observers.add(parkingOwner);
        observers.add(airportSecurity);
        ParkingLot parkingLot = new ParkingLot(1,observers);
        parkingLot.parkAndGetTicket(car1);
        parkingLot.parkAndGetTicket(car2);
        verify(parkingOwner).notifyToPerson();
        verify(airportSecurity).notifyToPerson();
    }

    @Test(expected = CarAlreadyPresentException.class)
    public void shouldNotReturnATicketWhenThisCarIsParkedAlready() throws CarAlreadyPresentException, NoSlotsAvailableException {
        Object car1 = new Object();
        List<Observer> observers = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(2,observers);
        parkingLot.parkAndGetTicket(car1);
        assertNull(parkingLot.parkAndGetTicket(car1));
    }

    @Test
    public void shouldUnparkTheRightCarGivenTheTicket() throws NoSlotsAvailableException, CarAlreadyPresentException, NoCarFoundException {
        Object car1 = new Object();
        List<Observer> observers = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(2,observers);
        ParkingTicket ticket = parkingLot.parkAndGetTicket(car1);
        assertEquals(car1, parkingLot.unparkCar(ticket));
    }

//    @Test(expected = NoSlotsAvailableException.class)
//    public void shouldThrowExceptionWhenParkingLotIsFull() throws NoSlotsAvailableException, CarAlreadyPresentException {
//        List<Observer> observers = new ArrayList<>();
//        ParkingLot parkingLot = new ParkingLot(1,observers);
//        Object car = new Object();
//        ParkingTicket ticket = parkingLot.parkAndGetTicket(car);
//
//    }
//
//    @Test(expected = ParkingLotException.class)
//    public void shouldReturnTrueWhenParkingLotIsFull() throws ParkingLotException {
//        ParkingLotOwner owner = Mockito.mock(ParkingLotOwner.class);
//        AirportSecurity airportSecurity = Mockito.mock(AirportSecurity.class);
//        Observer parkingOwner = Mockito.mock(ParkingLotOwner.class);
//        ParkingLot parkingLot = new ParkingLot(2,parkingOwner);
//        Object car1 = new Object();
//        parkingLot.parkAndGetTicket(car1);
//        Object car2 = new Object();
//        parkingLot.parkAndGetTicket(car2);
//    }
//
    @Test(expected = CarAlreadyPresentException.class)
    public void shouldThrowExceptionWhenTryingToUnparkAnAlreadyUnparkedCar() throws NoSlotsAvailableException, CarAlreadyPresentException, NoCarFoundException {
        Object car = new Object();
        List<Observer> observers = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(2,observers);
        ParkingTicket ticket = parkingLot.parkAndGetTicket(car);
        parkingLot.unparkCar(ticket);
        parkingLot.unparkCar(ticket);
    }

    @Test
    public void shouldVerifyThatOwnerAndSecurityPersonnelIsNotifiedOnParkingLotIsFull() throws NoSlotsAvailableException, CarAlreadyPresentException {
        Object car = new Object();
        Observer parkingOwner = Mockito.mock(ParkingLotOwner.class);
        Observer airportSecurity = Mockito.mock(AirportSecurity.class);
        List<Observer> observers = new ArrayList<>();
        observers.add(parkingOwner);
        observers.add(airportSecurity);
        ParkingLot parkingLot = new ParkingLot(1,observers);
        parkingLot.parkAndGetTicket(car);
        verify(parkingOwner).notifyToPerson();
        verify(airportSecurity).notifyToPerson();
    }

}
