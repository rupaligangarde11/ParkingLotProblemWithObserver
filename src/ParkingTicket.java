public class ParkingTicket {
    private int carIdentifier;

    public ParkingTicket(int carIdentifier) {

        this.carIdentifier = carIdentifier;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingTicket that = (ParkingTicket) o;

        return carIdentifier == that.carIdentifier;

    }

    @Override
    public int hashCode() {
        return carIdentifier;
    }
}
