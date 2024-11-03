// Внешняя система оплаты
class ExternalPaymentSystem {
    public void makePayment(String account, double amount) {
        System.out.println("Processing payment through external system for account " + account);
    }
}

// Adapter для системы оплаты
interface PaymentService {
    void processPayment(String account, double amount);
}

class PaymentAdapter implements PaymentService {
    private ExternalPaymentSystem externalPaymentSystem = new ExternalPaymentSystem();

    @Override
    public void processPayment(String account, double amount) {
        externalPaymentSystem.makePayment(account, amount);
    }
}
