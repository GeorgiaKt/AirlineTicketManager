import java.util.ArrayList;

public class TicketApp {
    static ArrayList<Ticket> ticketsList;

    public static void main(String[] args) {
        ticketsList = new ArrayList<>();

        AppFrame frame = new AppFrame();
        frame.prepareUI();
    }
}
