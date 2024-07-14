import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketListWindow extends JFrame {
    private JPanel panelUp;
    private JButton btnNewTicket;
    private JButton btnRefresh;
    private JButton btnClose;
    private JTextArea ticketsArea;
    private JScrollPane scrollPane;


    public TicketListWindow() {
        super();

        panelUp = new JPanel();
        //JPanel panel = new JPanel();
        btnNewTicket = new JButton("New Ticket");
        btnRefresh = new JButton("Refresh");
        btnClose = new JButton("Close");
        ticketsArea = new JTextArea();
        scrollPane = new JScrollPane(ticketsArea);

    }

    public void prepareUITicketListWindow() {
        this.setSize(900, 500);
        this.setTitle("Ticket List");
        this.setLocation(900, 200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        panelUp.setLayout(new FlowLayout(FlowLayout.CENTER));

        panelUp.add(btnNewTicket);
        panelUp.add(btnRefresh);
        panelUp.add(btnClose);

        ticketsArea.setEditable(false);

        fillTicketsTextArea();

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(panelUp, BorderLayout.PAGE_START);
        this.add(scrollPane, BorderLayout.CENTER);


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
                ticketsArea.setText(""); // clear text area
                fillTicketsTextArea();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        this.setVisible(true);
    }

    private void fillTicketsTextArea() {
        for (Ticket i : TicketApp.ticketstList){
            ticketsArea.append(i.toString() + "\n");
        }
    }

}
