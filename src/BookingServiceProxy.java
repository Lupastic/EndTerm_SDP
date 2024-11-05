class BookingServiceProxy implements BookingService {
private RealBookingService realBookingService = new RealBookingService();

    BookingServiceProxy() {
    }

    public void bookRoom(String details) {
        System.out.println("Checking access permissions for booking...");
        this.realBookingService.bookRoom(details);
    }
}
