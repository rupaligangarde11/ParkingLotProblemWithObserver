 import org.junit.Test;
 import org.mockito.Mockito;

 import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
 import static org.mockito.Mockito.verify;

 public class ParkingLotTest {

    @org.junit.Test
    public void isTicketReturnedWhenCarIsParked() throws ParkingLotException {
        ParkingLotOwner owner = Mockito.mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(10, owner);
        Object car = new Object();
        ParkingTicket ticket = new ParkingTicket(car.hashCode());
        assertEquals(ticket, parkingLot.parkAndGetTicket(car));
    }

    @Test(expected = ParkingLotException.class)
    public void shouldNotReturnATicketWhenTheLotIsFull() throws ParkingLotException{
        ParkingLotOwner owner = Mockito.mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object car1 = new Object();
        parkingLot.parkAndGetTicket(car1);
        Object car2 = new Object();
        parkingLot.parkAndGetTicket(car2);
    }

    @Test(expected = ParkingLotException.class)
    public void shouldNotReturnATicketWhenThisCarIsParkedAlready() throws ParkingLotException {
        ParkingLotOwner owner = Mockito.mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object car1 = new Object();
        parkingLot.parkAndGetTicket(car1);
        assertNull(parkingLot.parkAndGetTicket(car1));
    }

    @Test
    public void shouldUnparkTheRightCarGivenTheTicket() throws ParkingLotException {
        ParkingLotOwner owner = Mockito.mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object car1 = new Object();
        ParkingTicket ticket = parkingLot.parkAndGetTicket(car1);
        assertEquals(car1, parkingLot.unparkCar(ticket));
    }

    @Test(expected = ParkingLotException.class)
    public void shouldThrowExceptionWhenParkingLotIsFull() throws ParkingLotException{
        ParkingLotOwner owner = Mockito.mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object car = new Object();
        ParkingTicket ticket = parkingLot.parkAndGetTicket(car);

    }

    @Test(expected = ParkingLotException.class)
    public void shouldReturnTrueWhenParkingLotIsFull() throws ParkingLotException {
        ParkingLotOwner owner = Mockito.mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object car1 = new Object();
        parkingLot.parkAndGetTicket(car1);
        Object car2 = new Object();
        parkingLot.parkAndGetTicket(car2);
    }

    @Test(expected = ParkingLotException.class)
    public void shouldThrowExceptionWhenTryingToUnparkAnAlreadyUnparkedCar() throws ParkingLotException {
        ParkingLotOwner owner = Mockito.mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object car = new Object();
        ParkingTicket ticket = parkingLot.parkAndGetTicket(car);
        parkingLot.unparkCar(ticket);
        parkingLot.unparkCar(ticket);
    }

    @Test
    public void shouldVerifyThatOwnerIsNotifiedOnParkingLotIsFull() throws ParkingLotException {
        ParkingLotOwner owner = Mockito.mock(ParkingLotOwner.class);
        ParkingLot parkingLot= new ParkingLot(1,owner);
        Object car = new Object();
        parkingLot.parkAndGetTicket(car);
        verify(owner).putFullSign();

    }
}
