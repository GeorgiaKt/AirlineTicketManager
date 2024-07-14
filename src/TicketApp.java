import java.util.ArrayList;

public class TicketApp {
    static ArrayList<Ticket> ticketstList;

    public static void main(String[] args) {
        ticketstList = new ArrayList<>();

        AppFrame frame = new AppFrame();
        frame.prepareUI();

    }

}
