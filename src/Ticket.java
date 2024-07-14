public class Ticket {
    private int ticketId;
    private String issueDate; // day month year
    private String clientName;
    private String itinerary;
    private int ticketPrice;
    private int luggage;    // number of luggage
    private String airlines;    // airline company
    private String identityNumber;
    private String departureTime;


    public Ticket() {
        ticketId = 0;
        issueDate = "Uknown";
        clientName = "Uknown";
        itinerary = "Uknown";
        luggage = 0;
        airlines = "Uknown";
        identityNumber = "Uknown";
        departureTime = "Uknown";
        ticketPrice = 0;

    }

    public Ticket(int t_id, String date, String name, String itin, int lug, String airl, String id, String depart, int price) {
        this.ticketId = t_id;
        this.issueDate = date;
        this.clientName = name;
        this.itinerary = itin;
        this.luggage = lug;
        this.airlines = airl;
        this.identityNumber = id;
        this.departureTime = depart;
        this.ticketPrice = price;

    }

    //getters
    public int getTicketId() {
        return ticketId;
    }

    //setters
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "TicketId=" + ticketId +
                " ,\t IssueDate= " + issueDate +
                " ,\t ClientName= " + clientName +
                " ,\t Itinerary= " + itinerary +
                " ,\t Luggage= " + luggage +
                " ,\t Airlines= " + airlines +
                " ,\t IdentityNumber= " + identityNumber +
                " ,\t DepartureTime= " + departureTime +
                " ,\t TicketPrice= " + ticketPrice;
    }
}
