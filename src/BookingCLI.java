//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Scanner;

class BookingCLI {
    private Scanner scanner;
    private BookingManager bookingManager;
    private BookingService bookingService;
    private PaymentService paymentService;
    private BookingInvoker invoker;

    BookingCLI() {
        this.scanner = new Scanner(System.in);
        this.bookingManager = BookingManager.getInstance();
        this.bookingService = new BookingServiceProxy();
        this.paymentService = new PaymentAdapter();
        this.invoker = new BookingInvoker();
    }

    public void start() {
        System.out.println("Welcome to the Booking System!");

        while(true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Book a Room");
            System.out.println("2. Show Bookings");
            System.out.println("3. Make a Payment");
            System.out.println("4. Exit");
            switch (this.scanner.nextLine()) {
                case "1":
                    this.bookRoom();
                    break;
                case "2":
                    this.showBookings();
                    break;
                case "3":
                    this.makePayment();
                    break;
                case "4":
                    System.out.println("Exiting the Booking System.");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void bookRoom() {
        System.out.println("\n--- Booking a Room ---");
        System.out.println("Choose room type:");
        System.out.println("1. Single Room - A cozy room for one guest. Price: $100/night");
        System.out.println("2. Double Room - A spacious room for two guests. Price: $150/night");
        System.out.println("3. Suite - A luxurious suite with a separate living area. Price: $250/night");
        System.out.println("4. Deluxe Room - An upscale room with premium amenities. Price: $350/night");
        Object room;
        switch (this.scanner.nextLine()) {
            case "1":
                room = new SingleRoom();
                break;
            case "2":
                room = new DoubleRoom();
                break;
            case "3":
                room = new Suite();
                break;
            case "4":
                room = new DeluxeRoom();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        System.out.println("\nRoom created: " + ((Room)room).getType());
        System.out.println("\n--- Enter Booking Details ---");
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = this.scanner.nextLine();
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = this.scanner.nextLine();
        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate checkOut = LocalDate.parse(checkOutDate);
        if (checkOut.isBefore(checkIn)) {
            System.out.println("Check-out date must be after check-in date.");
        } else {
            System.out.print("Enter the number of guests: ");
            int guests = Integer.parseInt(this.scanner.nextLine());
            if (guests <= 0) {
                System.out.println("Number of guests must be greater than zero.");
            } else {
                System.out.print("Would you like to add breakfast (yes/no)? ");
                boolean breakfastIncluded = this.scanner.nextLine().equalsIgnoreCase("yes");
                System.out.print("Would you like to add parking (yes/no)? ");
                boolean parkingIncluded = this.scanner.nextLine().equalsIgnoreCase("yes");
                long numberOfNights = ChronoUnit.DAYS.between(checkIn, checkOut);
                double totalPrice = (double)numberOfNights * ((Room)room).getPrice();
                BookingDetails bookingDetails = new BookingDetails(((Room)room).getType(), checkIn, checkOut, guests, breakfastIncluded, parkingIncluded, totalPrice);
                System.out.println("\n--- Booking Summary ---");
                System.out.printf("Room Type: %s, Check-in: %s, Check-out: %s, Guests: %d, Breakfast: %s, Parking: %s, Total Price: $%.2f for %d nights%n", bookingDetails.getRoomType(), bookingDetails.getCheckInDate(), bookingDetails.getCheckOutDate(), bookingDetails.getNumberOfGuests(), bookingDetails.isBreakfastIncluded() ? "Included" : "Not Included", bookingDetails.isParkingIncluded() ? "Included" : "Not Included", bookingDetails.getTotalPrice(), numberOfNights);
                BookingCommand command = new AddBookingCommand(this.bookingManager, bookingDetails);
                this.invoker.executeCommand(command);
                System.out.println("\nBooking successful!\n");
            }
        }
    }

    private void showBookings() {
        System.out.println("Current bookings:");
        Iterator var1 = this.bookingManager.getBookings().iterator();

        while(var1.hasNext()) {
            BookingDetails booking = (BookingDetails)var1.next();
            String bookingInfo = String.format("ID: %d, Room Type: %s, Check-in: %s, Check-out: %s, Guests: %d, Total Price: $%.2f", booking.getBookingId(), booking.getRoomType(), booking.getCheckInDate(), booking.getCheckOutDate(), booking.getNumberOfGuests(), booking.getTotalPrice());
            System.out.println("- " + bookingInfo);
        }

    }

    private void makePayment() {
        this.showBookings();
        System.out.print("Enter the booking ID to make a payment (or type 'exit' to cancel): ");
        String bookingChoice = this.scanner.nextLine();
        if (!bookingChoice.equalsIgnoreCase("exit")) {
            try {
                int bookingId = Integer.parseInt(bookingChoice);
                BookingDetails bookingDetails = this.bookingManager.getBookingDetails(bookingId);
                if (bookingDetails == null) {
                    System.out.println("Invalid booking ID. Please try again.");
                    return;
                }

                System.out.println("\n--- Make a Payment ---");
                double totalPrice = bookingDetails.getTotalPrice();
                System.out.printf("Your total booking price is: $%.2f\n", totalPrice);
                System.out.print("Enter payment account: ");
                String account = this.scanner.nextLine();
                this.paymentService.processPayment(account, totalPrice);
                System.out.println("Payment processed successfully!");
            } catch (NumberFormatException var7) {
                System.out.println("Invalid input. Please enter a valid booking ID.");
            }

        }
    }
}
