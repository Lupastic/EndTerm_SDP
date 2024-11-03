// BookingHandler.java
abstract class BookingHandler {
    protected BookingHandler nextHandler;

    public void setNextHandler(BookingHandler handler) {
        this.nextHandler = handler;
    }

    public abstract void handleRequest(String request);
}

class AvailabilityHandler extends BookingHandler {
    public void handleRequest(String request) {
        if (request.contains("availability")) {
            System.out.println("Checking room availability...");
        }
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class PaymentHandler extends BookingHandler {
    public void handleRequest(String request) {
        if (request.contains("payment")) {
            System.out.println("Processing payment...");
        }
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
