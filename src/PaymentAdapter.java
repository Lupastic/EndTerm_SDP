class PaymentAdapter implements PaymentService {
    private ExternalPaymentSystem externalPaymentSystem = new ExternalPaymentSystem();

    PaymentAdapter() {
    }

    public void processPayment(String account, double amount) {
        this.externalPaymentSystem.makePayment(account, amount);
    }
}
