import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

class BookingCLI {
    private Scanner scanner = new Scanner(System.in);
    private BookingManager bookingManager = BookingManager.getInstance();
    private BookingService bookingService = new BookingServiceProxy();
    private PaymentService paymentService = new PaymentAdapter();
    private BookingInvoker invoker = new BookingInvoker();

    public void start() {
        System.out.println("Welcome to the Booking System!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Book a Room");
            System.out.println("2. Show Bookings");
            System.out.println("3. Make a Payment");
            System.out.println("4. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> bookRoom();
                case "2" -> showBookings();
                case "3" -> makePayment();
                case "4" -> {
                    System.out.println("Exiting the Booking System.");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
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

        Room room;
        String roomChoice = scanner.nextLine();

        switch (roomChoice) {
            case "1" -> room = new SingleRoom();
            case "2" -> room = new DoubleRoom();
            case "3" -> room = new Suite();
            case "4" -> room = new DeluxeRoom();
            default -> {
                System.out.println("Invalid choice. Please try again.");
                return;
            }
        }

        System.out.println("\nRoom created: " + room.getType());

        System.out.println("\n--- Enter Booking Details ---");

        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();

        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        // Преобразование строк в LocalDate
        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate checkOut = LocalDate.parse(checkOutDate);

        // Проверка дат
        if (checkOut.isBefore(checkIn)) {
            System.out.println("Check-out date must be after check-in date.");
            return;
        }

        System.out.print("Enter the number of guests: ");
        int guests = Integer.parseInt(scanner.nextLine());

        if (guests <= 0) {
            System.out.println("Number of guests must be greater than zero.");
            return;
        }

        System.out.print("Would you like to add breakfast (yes/no)? ");
        boolean breakfastIncluded = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Would you like to add parking (yes/no)? ");
        boolean parkingIncluded = scanner.nextLine().equalsIgnoreCase("yes");

        // Рассчитываем количество дней между датами
        long numberOfNights = ChronoUnit.DAYS.between(checkIn, checkOut);

        // Рассчитываем общую стоимость бронирования
        double totalPrice = numberOfNights * room.getPrice();

        // Создаем объект BookingDetails
        BookingDetails bookingDetails = new BookingDetails(room.getType(), checkIn, checkOut, guests, breakfastIncluded, parkingIncluded, totalPrice);

        System.out.println("\n--- Booking Summary ---");
        System.out.printf("Room Type: %s, Check-in: %s, Check-out: %s, Guests: %d, Breakfast: %s, Parking: %s, Total Price: $%.2f for %d nights%n",
                bookingDetails.getRoomType(), bookingDetails.getCheckInDate(), bookingDetails.getCheckOutDate(), bookingDetails.getNumberOfGuests(),
                bookingDetails.isBreakfastIncluded() ? "Included" : "Not Included",
                bookingDetails.isParkingIncluded() ? "Included" : "Not Included",
                bookingDetails.getTotalPrice(), numberOfNights);

        // Используем команду для добавления бронирования
        BookingCommand command = new AddBookingCommand(bookingManager, bookingDetails);
        invoker.executeCommand(command);
        System.out.println("\nBooking successful!\n");
    }


    private void showBookings() {
        System.out.println("Current bookings:");
        for (BookingDetails booking : bookingManager.getBookings()) {
            String bookingInfo = String.format(
                    "ID: %d, Room Type: %s, Check-in: %s, Check-out: %s, Guests: %d, Total Price: $%.2f",
                    booking.getBookingId(),
                    booking.getRoomType(),
                    booking.getCheckInDate(),
                    booking.getCheckOutDate(),
                    booking.getNumberOfGuests(),
                    booking.getTotalPrice()
            );
            System.out.println("- " + bookingInfo);
        }
    }

    private void makePayment() {
        // Показать доступные бронирования
        showBookings();

        System.out.print("Enter the booking ID to make a payment (or type 'exit' to cancel): ");
        String bookingChoice = scanner.nextLine();

        if (bookingChoice.equalsIgnoreCase("exit")) {
            return; // Отменить операцию
        }

        try {
            int bookingId = Integer.parseInt(bookingChoice); // Пробуем преобразовать строку в ID
            BookingDetails bookingDetails = bookingManager.getBookingDetails(bookingId);

            if (bookingDetails == null) {
                System.out.println("Invalid booking ID. Please try again.");
                return;
            }

            System.out.println("\n--- Make a Payment ---");
            double totalPrice = bookingDetails.getTotalPrice();
            System.out.printf("Your total booking price is: $%.2f\n", totalPrice);
            System.out.print("Enter payment account: ");
            String account = scanner.nextLine();

            paymentService.processPayment(account, totalPrice);
            System.out.println("Payment processed successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid booking ID.");
        }
    }
}

interface BookingCommand {
    void execute();
}

class AddBookingCommand implements BookingCommand {
    private BookingManager bookingManager;
    private BookingDetails booking;

    public AddBookingCommand(BookingManager bookingManager, BookingDetails booking) {
        this.bookingManager = bookingManager;
        this.booking = booking;
    }

    @Override
    public void execute() {
        bookingManager.addBooking(booking);
        System.out.println("Booking added: " + booking);
    }
}
