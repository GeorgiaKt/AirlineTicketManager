import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class NewTicketWindow extends JFrame {

    static int count = 1; // counter used for id
    private JLabel labelTicketId;
    private JTextField textIssueDate;
    private JLabel labelTextIssueDate;
    private JTextField textClientName;
    private JLabel labelTextClientName;
    private JTextField textItinerary;
    private JLabel labelTextItinerary;
    private JLabel labelTicketPrice;
    private JComboBox comboLuggage;
    private JLabel labelComboLuggage;
    private JComboBox comboAirlines;
    private JLabel labelComboAirlines;
    private JTextField textIdentityNumber;
    private JLabel labelTextIdentityNumber;
    private JComboBox comboDeparture;
    private JLabel labelComboDeparture;
    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelBottom;
    private JButton btnSave;

    private int price = 50;


    public NewTicketWindow() {
        super();
        labelTicketId = new JLabel("Ticket Id: " + count);
        textIssueDate = new JTextField(20);
        labelTextIssueDate = new JLabel("Issue Date: ");
        textClientName = new JTextField(18);
        labelTextClientName = new JLabel("Client Name: ");
        textItinerary = new JTextField(16);
        labelTextItinerary = new JLabel("Itinerary: ");
        labelTicketPrice = new JLabel("Ticket Price: " + price);
        comboLuggage = new JComboBox();
        labelComboLuggage = new JLabel("Luggage: ");
        comboAirlines = new JComboBox();
        labelComboAirlines = new JLabel("Airlines: ");
        textIdentityNumber = new JTextField();
        labelTextIdentityNumber = new JLabel("Identity Number: ");
        comboDeparture = new JComboBox();
        labelComboDeparture = new JLabel("Departure Time: ");

        panelMain = new JPanel();
        panelLeft = new JPanel();
        panelRight = new JPanel();
        panelBottom = new JPanel();

        btnSave = new JButton("Save");

    }

    public void prepareUINewTicketWindow() {
        this.setSize(450, 550);
        this.setTitle("New Ticket");
        this.setLocation(900, 200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);   // make it non resizable

        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));

        placeComponentsOnNewTicketWindow();

        addItemsInComboBoxes();

        addActionListeners();

        // add panels on frame
        this.add(panelMain, BorderLayout.CENTER);
        this.add(panelLeft, BorderLayout.WEST);
        this.add(panelRight, BorderLayout.EAST);
        this.add(panelBottom, BorderLayout.SOUTH);


        this.setVisible(true);
    }

    private void addActionListeners() {
        // listener for comboLuggage in order to update ticket price each time a different number of luggage is selected
        comboLuggage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int luggage = Integer.parseInt((String) Objects.requireNonNull(comboLuggage.getSelectedItem()));
                int ticketPrice = 50;
                //change ticket price based on the number of luggage selected
                if (luggage == 1)
                    ticketPrice = ticketPrice + 30;
                else if (luggage == 2)
                    ticketPrice = ticketPrice + 40;
                else if (luggage == 3)
                    ticketPrice = ticketPrice + 55;
                else if (luggage == 4)
                    ticketPrice = ticketPrice + 65;

                labelTicketPrice.setText("Ticket Price: " + ticketPrice);  // update ticket price label
                price = ticketPrice;
            }
        });
        // listener for btnSave in order to get values of all fields and check that all have value before saving the ticket to a txt file
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // local variables for user's input
                int ticketId;
                String issueDate;
                String clientName;
                String itinerary;
                int luggage;
                String airlines;
                String identityNumber;
                String departureTime;
                int ticketPrice;

                ticketId = count;
                // get field values
                labelTicketId.setText("Ticket Id: " + ticketId);
                issueDate = textIssueDate.getText().trim();
                clientName = textClientName.getText().trim();
                itinerary = textItinerary.getText().trim();
                luggage = Integer.parseInt((String) Objects.requireNonNull(comboLuggage.getSelectedItem()));
                airlines = (String) comboAirlines.getSelectedItem();
                identityNumber = textIdentityNumber.getText().trim();
                departureTime = (String) comboDeparture.getSelectedItem();
                ticketPrice = price;

                // check if any of the fields is empty, show corresponding message, if not, save ticket to file
                if (issueDate.isEmpty() || clientName.isEmpty() || itinerary.isEmpty() || (airlines.equalsIgnoreCase("(Select flight airlines)")) || identityNumber.isEmpty() || (departureTime.equalsIgnoreCase("(Select departure time)"))) {
                    JOptionPane.showMessageDialog(NewTicketWindow.this, "Nothing to save", "Saving Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Ticket ticket = new Ticket(ticketId, issueDate, clientName, itinerary, luggage, airlines, identityNumber, departureTime, ticketPrice);
                    TicketApp.ticketstList.add(ticket); // add ticket to list

                    final JFileChooser fc = new JFileChooser();
                    int returnVal = fc.showSaveDialog(NewTicketWindow.this);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String fileName = fc.getSelectedFile().getPath();

                        // increase counter after selecting file
                        count++;
                        ticketId = count;

                        if (fileName != null && !fileName.isEmpty()) {
                            saveTicketToFile(ticket, fileName);
                        }
                    }
                }

            }
        });
    }

    private void saveTicketToFile(Ticket ticket, String fileName) {
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(fileName, true));

            // for every new run append mes + date & time before the first ticket
            if (ticket.getTicketId() == 1) {
                LocalDateTime dateTimeNow = LocalDateTime.now();
                DateTimeFormatter formDateTimeNow = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDateTime = formDateTimeNow.format(dateTimeNow);

                file.write("> Ticket Registration started at: " + formattedDateTime);
                file.newLine();
            }

            // write ticket info to file
            file.write(ticket.toString());
            file.newLine();

            file.close();
            // update user that ticket is saved
            JOptionPane.showMessageDialog(NewTicketWindow.this, "Record saved to " + fileName, "Save completed", JOptionPane.INFORMATION_MESSAGE);

            setVisible(false);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(btnSave, "Can't access " + fileName, "File access error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addItemsInComboBoxes() {
        // add comboboxes items
        comboLuggage.addItem("0");
        comboLuggage.addItem("1");
        comboLuggage.addItem("2");
        comboLuggage.addItem("3");
        comboLuggage.addItem("4");

        comboAirlines.addItem("(Select flight airlines)");
        comboAirlines.addItem("Aegean Airlines");
        comboAirlines.addItem("Ryan Airlines");
        comboAirlines.addItem("Swiss Airlines");
        comboAirlines.addItem("Olympic Airlines");
        comboAirlines.addItem("British Airways");

        comboDeparture.addItem("(Select departure time)");
        comboDeparture.addItem("6AM");
        comboDeparture.addItem("9AM");
        comboDeparture.addItem("3PM");
        comboDeparture.addItem("7PM");
    }

    private void placeComponentsOnNewTicketWindow() {
        // place components at the center
        labelTicketId.setAlignmentX(Component.CENTER_ALIGNMENT);
        textIssueDate.setAlignmentX(CENTER_ALIGNMENT);
        labelTextIssueDate.setAlignmentX(CENTER_ALIGNMENT);
        textClientName.setAlignmentX(CENTER_ALIGNMENT);
        labelTextClientName.setAlignmentX(CENTER_ALIGNMENT);
        textItinerary.setAlignmentX(CENTER_ALIGNMENT);
        labelTextItinerary.setAlignmentX(CENTER_ALIGNMENT);

        comboLuggage.setAlignmentX(CENTER_ALIGNMENT);
        labelComboLuggage.setAlignmentX(CENTER_ALIGNMENT);
        comboAirlines.setAlignmentX(CENTER_ALIGNMENT);
        labelComboAirlines.setAlignmentX(CENTER_ALIGNMENT);
        textIdentityNumber.setAlignmentX(CENTER_ALIGNMENT);
        labelTextIdentityNumber.setAlignmentX(CENTER_ALIGNMENT);
        comboDeparture.setAlignmentX(CENTER_ALIGNMENT);
        labelComboDeparture.setAlignmentX(CENTER_ALIGNMENT);
        labelTicketPrice.setAlignmentX(CENTER_ALIGNMENT);

        panelMain.add(Box.createVerticalGlue());
        panelMain.add(Box.createHorizontalGlue());

        // add components on main panel
        panelMain.add(labelTicketId);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(labelTextIssueDate);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(textIssueDate);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(labelTextClientName);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(textClientName);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(labelTextItinerary);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(textItinerary);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(labelComboLuggage);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(comboLuggage);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(labelComboAirlines);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(comboAirlines);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(labelTextIdentityNumber);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(textIdentityNumber);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(labelComboDeparture);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(comboDeparture);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMain.add(labelTicketPrice);
        panelMain.add(Box.createRigidArea(new Dimension(0, 10)));

        panelMain.add(Box.createHorizontalGlue());
        panelMain.add(Box.createVerticalGlue());

        panelBottom.add(btnSave);
    }

}
