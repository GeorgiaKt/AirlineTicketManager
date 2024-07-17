import javax.swing.*;
import java.awt.*;

public class StatisticsWindow extends JFrame {
    private JPanel panelMain;
    private JLabel totalTickets;

    public StatisticsWindow(){
        super();
        panelMain = new JPanel();
        totalTickets = new JLabel("Total Tickets: ");
    }

    public void prepareUIStatisticsWindow(){
        this.setSize(400, 450);
        this.setTitle("Statistics");
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));

        totalTickets.setAlignmentX(CENTER_ALIGNMENT);

        panelMain.add(totalTickets);
        
        calculateStatistics();

        this.add(panelMain, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void calculateStatistics() {
    }
}
