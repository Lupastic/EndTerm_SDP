class RealBookingService implements BookingService {
    RealBookingService() {
    }

    public void bookRoom(String details) {
        System.out.println("Booking room with details: " + details);
    }
}