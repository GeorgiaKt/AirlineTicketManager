import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class TicketListWindow extends JFrame {
    private JPanel panelUp;
    private JPanel panelBottom;
    private JButton btnNewTicket;
    private JButton btnRefresh;
    private JButton btnClose;
    private JTextArea ticketsTextArea;
    private JScrollPane scrollPane;
    private JButton statisticsBtn;
    private ArrayList<Ticket> sortedTicketsList;


    public TicketListWindow() {
        super();

        panelUp = new JPanel();
        panelBottom = new JPanel();
        btnNewTicket = new JButton("New Ticket");
        btnRefresh = new JButton("Refresh");
        btnClose = new JButton("Close");
        ticketsTextArea = new JTextArea();
        scrollPane = new JScrollPane(ticketsTextArea);
        statisticsBtn = new JButton("Statistics");
        sortedTicketsList = new ArrayList<>();

    }

    public void prepareUITicketListWindow() {
        this.setSize(900, 500);
        this.setTitle("Ticket List");
        this.setLocation(900, 200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        panelUp.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
        // add components
        panelUp.add(btnNewTicket);
        panelUp.add(btnRefresh);
        panelUp.add(btnClose);
        panelBottom.add(statisticsBtn);

        ticketsTextArea.setEditable(false);

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(panelUp, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);


        fillTicketsTextArea();


        btnNewTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                NewTicketWindow newTicketWindow = new NewTicketWindow();
                newTicketWindow.prepareUINewTicketWindow();
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticketsTextArea.setText(""); // clear text area
                fillTicketsTextArea();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        statisticsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatisticsWindow statisticsWindow = new StatisticsWindow();
                statisticsWindow.prepareUIStatisticsWindow();
            }
        });


        this.setVisible(true);
    }

    private void fillTicketsTextArea() {
        sortTicketListArray();
        for (Ticket i : sortedTicketsList) {
            ticketsTextArea.append(i.toString() + "\n");
        }
    }

    private void sortTicketListArray() {
        // sort tickets based on their price
        sortedTicketsList.clear(); // remove tickets
        sortedTicketsList.addAll(TicketApp.ticketsList); // add all tickets from list
        sortedTicketsList.sort(new Comparator<Ticket>() {
            @Override
            public int compare(Ticket o1, Ticket o2) {
                return Double.compare(o1.getTicketPrice(), o2.getTicketPrice());
            }
        });
    }
}
