import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private static BookingManager instance;
    private List<BookingDetails> bookings;

    private BookingManager() {
        bookings = new ArrayList<>();
    }

    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    public void addBooking(BookingDetails booking) {
        bookings.add(booking);
    }

    public List<BookingDetails> getBookings() {
        return bookings;
    }

    // Метод для получения деталей бронирования по ID
    public BookingDetails getBookingDetails(int bookingId) {
        for (BookingDetails booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null;
    }
}
