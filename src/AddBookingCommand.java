class AddBookingCommand implements BookingCommand {
    private BookingManager bookingManager;
    private BookingDetails booking;

    public AddBookingCommand(BookingManager bookingManager, BookingDetails booking) {
        this.bookingManager = bookingManager;
        this.booking = booking;
    }

    public void execute() {
        this.bookingManager.addBooking(this.booking);
        System.out.println("Booking added: " + String.valueOf(this.booking));
    }
}