import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketListWindow extends JFrame {
    public TicketListWindow() {
        super();
    }

    public void prepareUITicketListWindow() {
        this.setSize(350, 400);
        this.setTitle("Ticket List");
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        JPanel panelUp = new JPanel();
        //JPanel panel = new JPanel();

        panelUp.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnNewTicket = new JButton("New Ticket");
        JButton btnRefresh = new JButton("Refresh");
        JButton btnClose = new JButton("Close");

        panelUp.add(btnNewTicket);
        panelUp.add(btnRefresh);
        panelUp.add(btnClose);


        JTextArea area = new JTextArea();
        //area.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane.setPreferredSize(new Dimension(450,450));
        //scrollPane.setLayout();
        this.add(scrollPane, BorderLayout.EAST);

        this.add(panelUp, BorderLayout.PAGE_START);
        this.add(area, BorderLayout.CENTER);


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
                //re-opening the file
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

}
