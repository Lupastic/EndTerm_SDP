import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingManager {
    private static BookingManager instance;
    private List<BookingDetails> bookings = new ArrayList();

    private BookingManager() {
    }

    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }

        return instance;
    }

    public void addBooking(BookingDetails booking) {
        this.bookings.add(booking);
    }

    public List<BookingDetails> getBookings() {
        return this.bookings;
    }

    public BookingDetails getBookingDetails(int bookingId) {
        Iterator var2 = this.bookings.iterator();

        BookingDetails booking;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            booking = (BookingDetails)var2.next();
        } while(booking.getBookingId() != bookingId);

        return booking;
    }
}
