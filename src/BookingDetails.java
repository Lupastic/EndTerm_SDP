import java.time.LocalDate;

public class BookingDetails {
    private static int counter = 0; // Счетчик для генерации уникальных ID
    private int bookingId;
    private String roomType;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfGuests;
    private boolean breakfastIncluded;
    private boolean parkingIncluded;
    private double totalPrice;

    public BookingDetails(String roomType, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests, boolean breakfastIncluded, boolean parkingIncluded, double totalPrice) {
        this.bookingId = ++counter; // Увеличиваем счетчик и присваиваем уникальный идентификатор
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.breakfastIncluded = breakfastIncluded;
        this.parkingIncluded = parkingIncluded;
        this.totalPrice = totalPrice;
    }

    public String getRoomType() {
        return roomType; // Возвращает тип комнаты
    }

    public LocalDate getCheckInDate() {
        return checkInDate; // Возвращает дату заезда
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate; // Возвращает дату выезда
    }

    public int getNumberOfGuests() {
        return numberOfGuests; // Возвращает количество гостей
    }

    public int getBookingId() {
        return bookingId; // Возвращает ID бронирования
    }

    public double getTotalPrice() {
        return totalPrice; // Возвращает общую стоимость бронирования
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded; // Возвращает, включен ли завтрак
    }

    public boolean isParkingIncluded() {
        return parkingIncluded; // Возвращает, включена ли парковка
    }

    @Override
    public String toString() {
        return String.format("Booking ID: %d, Room Type: %s, Check-in: %s, Check-out: %s, Guests: %d, Breakfast: %s, Parking: %s, Total Price: $%.2f",
                bookingId, roomType, checkInDate, checkOutDate, numberOfGuests,
                breakfastIncluded ? "Included" : "Not Included",
                parkingIncluded ? "Included" : "Not Included",
                totalPrice);
    }
}
