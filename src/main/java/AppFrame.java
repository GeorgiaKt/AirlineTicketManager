import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppFrame extends JFrame {
    private JPanel panelCenter;
    //private JPanel panelUp;
    private JButton btnNewTicket;
    private JButton btnTicketList;
    private JButton btnAbout;
    private JButton btnExit;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newTicketItem, ticketListItem, aboutItem, exitItem;


    public AppFrame() {
        super();
        // create objects
        panelCenter = new JPanel();
        //panelUp = new JPanel();
        btnNewTicket = new JButton("New Ticket");
        btnTicketList = new JButton("Ticket List");
        btnAbout = new JButton("About");
        btnExit = new JButton("Exit");
        // menu components
        menuBar = new JMenuBar();
        newTicketItem = new JMenuItem("New Ticket");
        ticketListItem = new JMenuItem("Ticket List");
        aboutItem = new JMenuItem("About");
        exitItem = new JMenuItem("Exit");
        menu = new JMenu("Menu");

    }

    private void startAboutWindow() {
        AboutWindow aboutWindow = new AboutWindow();
        aboutWindow.prepareUIAboutWindow();
    }

    private void startTicketListWindow() {
        TicketListWindow ticketListWindow = new TicketListWindow();
        ticketListWindow.prepareUITicketListWindow();
    }

    private void startNewTicketWindow() {
        NewTicketWindow newTicketWindow = new NewTicketWindow();
        newTicketWindow.prepareUINewTicketWindow();
    }

    public void prepareUI() {
        this.setSize(400, 450);
        this.setTitle("Airline Ticket Manager");
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add items to menu
        menuBar.add(menu);
        newTicketItem = menu.add("New Ticket");
        ticketListItem = menu.add("Ticket List");
        aboutItem = menu.add("About");
        exitItem = menu.add("Exit");

        addActionListenersForMenuItems();

        placeButtonsOnPanelCenter();

        // set border for panelCenter
        panelCenter.setBorder(BorderFactory.createEtchedBorder());
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.PAGE_AXIS));

        addActionListenersForMainButtons();

        this.add(panelCenter, BorderLayout.CENTER);
        this.add(menuBar, BorderLayout.NORTH);

        this.setVisible(true);  // set frame to visible
    }

    private void addActionListenersForMenuItems() {
        newTicketItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);    // main window will not be visible
                startNewTicketWindow();
            }
        });
        ticketListItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                startTicketListWindow();
            }
        });
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAboutWindow();
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });
    }

    private void placeButtonsOnPanelCenter() {
        // place buttons at the center
        btnNewTicket.setAlignmentX(CENTER_ALIGNMENT);
        btnTicketList.setAlignmentX(CENTER_ALIGNMENT);
        btnAbout.setAlignmentX(CENTER_ALIGNMENT);
        btnExit.setAlignmentX(CENTER_ALIGNMENT);

        panelCenter.add(Box.createVerticalGlue());  // place the panel at the center
        panelCenter.add(Box.createHorizontalGlue());

        // place buttons on panel
        panelCenter.add(btnNewTicket);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnTicketList);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnAbout);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnExit);

        panelCenter.add(Box.createHorizontalGlue());
        panelCenter.add(Box.createVerticalGlue());
    }

    private void addActionListenersForMainButtons() {
        btnNewTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                startNewTicketWindow();
            }
        });
        btnTicketList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                startTicketListWindow();
            }
        });
        btnAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAboutWindow();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        });
    }

    // when the user chooses to exit the app
    private void exitApp() {
        int i = JOptionPane.showConfirmDialog(AppFrame.this, "Do you want to exit the app?");
        if (i == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
