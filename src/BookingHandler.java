//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

abstract class BookingHandler {
    protected BookingHandler nextHandler;

    BookingHandler() {
    }

    public void setNextHandler(BookingHandler handler) {
        this.nextHandler = handler;
    }

    public abstract void handleRequest(String var1);
}
