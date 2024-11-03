import java.util.ArrayList;
import java.util.List;

public class BookingInvoker {
    private List<BookingCommand> commandHistory = new ArrayList<>();

    public void executeCommand(BookingCommand command) {
        command.execute();
        commandHistory.add(command);
    }
}