//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class PaymentHandler extends BookingHandler {
    PaymentHandler() {
    }

    public void handleRequest(String request) {
        if (request.contains("payment")) {
            System.out.println("Processing payment...");
        }

        if (this.nextHandler != null) {
            this.nextHandler.handleRequest(request);
        }

    }
}
