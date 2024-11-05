class AvailabilityHandler extends BookingHandler {

    public void handleRequest(String request) {
        if (request.contains("availability")) {
            System.out.println("Checking room availability...");
        }

        if (this.nextHandler != null) {
            this.nextHandler.handleRequest(request);
        }

    }
}
