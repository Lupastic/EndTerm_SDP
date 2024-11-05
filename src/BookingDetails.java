//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.time.LocalDate;

public class BookingDetails {
    private static int counter = 0;
    private int bookingId;
    private String roomType;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfGuests;
    private boolean breakfastIncluded;
    private boolean parkingIncluded;
    private double totalPrice;

    public BookingDetails(String roomType, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests, boolean breakfastIncluded, boolean parkingIncluded, double totalPrice) {
        this.bookingId = ++counter;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.breakfastIncluded = breakfastIncluded;
        this.parkingIncluded = parkingIncluded;
        this.totalPrice = totalPrice;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }

    public int getNumberOfGuests() {
        return this.numberOfGuests;
    }

    public int getBookingId() {
        return this.bookingId;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public boolean isBreakfastIncluded() {
        return this.breakfastIncluded;
    }

    public boolean isParkingIncluded() {
        return this.parkingIncluded;
    }

    public String toString() {
        return String.format("Booking ID: %d, Room Type: %s, Check-in: %s, Check-out: %s, Guests: %d, Breakfast: %s, Parking: %s, Total Price: $%.2f", this.bookingId, this.roomType, this.checkInDate, this.checkOutDate, this.numberOfGuests, this.breakfastIncluded ? "Included" : "Not Included", this.parkingIncluded ? "Included" : "Not Included", this.totalPrice);
    }
}
