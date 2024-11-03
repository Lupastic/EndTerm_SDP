// BookingService.java
interface BookingService {
    void bookRoom(String details);
}

class RealBookingService implements BookingService {
    @Override
    public void bookRoom(String details) {
        System.out.println("Booking room with details: " + details);
    }
}

class BookingServiceProxy implements BookingService {
    private RealBookingService realBookingService = new RealBookingService();

    @Override
    public void bookRoom(String details) {
        System.out.println("Checking access permissions for booking...");
        realBookingService.bookRoom(details);
    }
}
