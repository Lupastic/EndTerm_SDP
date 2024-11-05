//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.List;

public class BookingInvoker {
    private List<BookingCommand> commandHistory = new ArrayList();

    public BookingInvoker() {
    }

    public void executeCommand(BookingCommand command) {
        command.execute();
        this.commandHistory.add(command);
    }
}
