import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParkingLotTest {

    @org.junit.Test
    public void isTicketReturnedWhenCarIsParked() throws Exception {
        ParkingLot parkingLot = new ParkingLot(10);
        Object car = new Object();
        ParkingTicket ticket = new ParkingTicket(car.hashCode());
        assertEquals(ticket, parkingLot.parkAndGetTicket(car));
    }

    @Test
    public void shouldNotReturnATicketWhenTheLotIsFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        Object car1 = new Object();
        parkingLot.parkAndGetTicket(car1);
        Object car2 = new Object();
        assertNull(parkingLot.parkAndGetTicket(car2));
    }

    @Test
    public void shouldNotReturnATicketWhenThisCarIsParkedAlready() {
        ParkingLot parkingLot = new ParkingLot(2);
        Object car1 = new Object();
        parkingLot.parkAndGetTicket(car1);
        assertNull(parkingLot.parkAndGetTicket(car1));
    }

    @Test
    public void shouldUnparkTheRightCarGivenTheTicket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Object car1 = new Object();
        ParkingTicket ticket = parkingLot.parkAndGetTicket(car1);
        assertEquals(car1, parkingLot.unparkCar(ticket));
    }


}
