import javax.swing.*;
import java.awt.*;

import io.github.parubok.text.multiline.MultilineLabel;

public class StatisticsWindow extends JFrame {
    private JPanel panelMain;
    private JPanel panelBottom;
    private JLabel totalTicketsLabel;
    private JLabel totalTicketsCostLabel;
    private JLabel maxTicketCostLabel;
    private JLabel minTicketCostLabel;
    private JLabel noIssuedTicketsLabel;
    private MultilineLabel airlinesLabel;


    public StatisticsWindow() {
        super();
        panelMain = new JPanel();
        panelBottom = new JPanel();
        totalTicketsLabel = new JLabel();
        totalTicketsCostLabel = new JLabel();
        maxTicketCostLabel = new JLabel();
        minTicketCostLabel = new JLabel();
        noIssuedTicketsLabel = new JLabel();
        airlinesLabel = new MultilineLabel();
    }

    public void prepareUIStatisticsWindow() {
        this.setSize(500, 450);
        this.setTitle("Statistics");
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));
        // center components
        totalTicketsLabel.setAlignmentX(CENTER_ALIGNMENT);
        totalTicketsCostLabel.setAlignmentX(CENTER_ALIGNMENT);
        maxTicketCostLabel.setAlignmentX(CENTER_ALIGNMENT);
        minTicketCostLabel.setAlignmentX(CENTER_ALIGNMENT);
        noIssuedTicketsLabel.setAlignmentX(CENTER_ALIGNMENT);
        airlinesLabel.setAlignmentX(CENTER_ALIGNMENT);
        airlinesLabel.setLineSpacing(1.8f); // add spacing between the multiple lines the label might contain

        panelMain.add(Box.createVerticalGlue());
        panelMain.add(Box.createHorizontalGlue());
        // add components on main panel
        panelMain.add(totalTicketsLabel);
        panelMain.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMain.add(totalTicketsCostLabel);
        panelMain.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMain.add(maxTicketCostLabel);
        panelMain.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMain.add(minTicketCostLabel);
        panelMain.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMain.add(noIssuedTicketsLabel);
        panelMain.add(Box.createVerticalGlue());
        panelMain.add(Box.createHorizontalGlue());
        // add multiline-label to bottom panel
        panelBottom.add(Box.createVerticalGlue());
        panelBottom.add(airlinesLabel);
        panelBottom.add(Box.createVerticalGlue());

        calculateStatistics();

        this.add(panelMain, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void calculateStatistics() {
        if (!TicketApp.ticketsList.isEmpty()) {
            int totalTickets = TicketApp.ticketsList.size();
            int totalCost = 0;
            // initialize local variables with values of the first issued ticket
            int maxPrice = TicketApp.ticketsList.get(0).getTicketPrice();
            int maxId = TicketApp.ticketsList.get(0).getTicketId();
            int minPrice = TicketApp.ticketsList.get(0).getTicketPrice();
            int minId = TicketApp.ticketsList.get(0).getTicketId();
            // counters for the number of issued tickets per airline
            int aegeanAirlines = 0;
            int ryanAirlines = 0;
            int swissAirlines = 0;
            int olympicAirlines = 0;
            int britishAirlines = 0;

            for (Ticket i : TicketApp.ticketsList) {
                totalCost = totalCost + i.getTicketPrice(); // calculate total cost of tickets
                // find tickets with max & min ticket prices
                if (i.getTicketPrice() > maxPrice) {
                    maxPrice = i.getTicketPrice();
                    maxId = i.getTicketId();
                }
                if (i.getTicketPrice() < minPrice) {
                    minPrice = i.getTicketPrice();
                    minId = i.getTicketId();
                }
                // calculate number of tickets per airline
                switch (i.getAirlines()) {
                    case "Aegean Airlines" -> aegeanAirlines++;
                    case "Ryan Airlines" -> ryanAirlines++;
                    case "Swiss Airlines" -> swissAirlines++;
                    case "Olympic Airlines" -> olympicAirlines++;
                    case "British Airways" -> britishAirlines++;
                }
            }

            // calculate percentages
            // max & min prices
            float maxPricePerc = ((float) maxPrice / totalCost) * 100;
            float minPricePerc = ((float) minPrice / totalCost) * 100;
            // number of issued tickets per airline
            float aegeanPerc = ((float) aegeanAirlines / totalTickets) * 100;
            float ryanPerc = ((float) ryanAirlines / totalTickets) * 100;
            float swissPerc = ((float) swissAirlines / totalTickets) * 100;
            float olympicPerc = ((float) olympicAirlines / totalTickets) * 100;
            float britishPerc = ((float) britishAirlines / totalTickets) * 100;

            // update labels
            totalTicketsLabel.setText("Total Tickets: " + totalTickets);
            totalTicketsCostLabel.setText("Total Tickets Cost: " + totalCost + " €");
            maxTicketCostLabel.setText("Max Ticket Cost: " + maxPrice + " € from TicketId: " + maxId + " (" + String.format("%.2f", maxPricePerc) + "% of the total cost)");
            minTicketCostLabel.setText("Min Ticket Cost: " + minPrice + " € from TicketId: " + minId + " (" + String.format("%.2f", minPricePerc) + "% of the total cost)");
            airlinesLabel.setText("Percentages of issued tickets per airline: \n" +
                    String.format("%.2f", aegeanPerc) + "% for Aegean Airlines\n" +
                    String.format("%.2f", ryanPerc) + "% for Ryan Airlines\n" +
                    String.format("%.2f", swissPerc) + "% for Swiss Airlines\n" +
                    String.format("%.2f", olympicPerc) + "% for Olympic Airlines\n" +
                    String.format("%.2f", britishPerc) + "% for British Airways\n"
            );

        } else
            noIssuedTicketsLabel.setText("No issued tickets yet!");
    }
}
