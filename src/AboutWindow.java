import javax.swing.*;
import java.awt.*;

public class AboutWindow extends JFrame {
    private JPanel panelAbout;
    private JLabel labelNameApp;
    private JLabel labelDeveloperName;
    private JLabel labelCompletionDate;
    private JLabel labelImage;

    public AboutWindow(){
        super();
        //create objects
        panelAbout = new JPanel();
        panelAbout.setLayout(new BoxLayout(panelAbout, BoxLayout.PAGE_AXIS));

        labelNameApp = new JLabel("Application's Name:     Airline Ticket Manager");
        labelDeveloperName = new JLabel("Developer's Name:     Katara Georgia");
        labelCompletionDate = new JLabel("Ticket's App Completion Date:   13/06/2021");

        labelImage = new JLabel (new ImageIcon("./desktopScreenshot.png"));

    }

    public void prepareUIAboutWindow() {
        this.setSize(650,500);
        this.setTitle("About");
        this.setLocation(400,200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        labelNameApp.setAlignmentX(CENTER_ALIGNMENT);
        labelDeveloperName.setAlignmentX(CENTER_ALIGNMENT);
        labelCompletionDate.setAlignmentX(CENTER_ALIGNMENT);
        labelImage.setAlignmentX(CENTER_ALIGNMENT);

        // place panel at the center
        panelAbout.add(Box.createVerticalGlue());
        panelAbout.add(Box.createHorizontalGlue());

        // place components
        panelAbout.add(labelNameApp);
        panelAbout.add(Box.createRigidArea(new Dimension(0,10)));
        panelAbout.add(labelDeveloperName);
        panelAbout.add(Box.createRigidArea(new Dimension(0,10)));
        panelAbout.add(labelCompletionDate);
        panelAbout.add(Box.createRigidArea(new Dimension(0,10)));
        panelAbout.add(labelImage);

        panelAbout.add(Box.createHorizontalGlue());
        panelAbout.add(Box.createVerticalGlue());

        this.add(panelAbout, BorderLayout.CENTER);

        this.setVisible(true);
    }

}
