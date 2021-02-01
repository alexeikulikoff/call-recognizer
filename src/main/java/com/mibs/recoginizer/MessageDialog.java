package com.mibs.recoginizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.mibs.asterisk.web.MedicalResearch;
import com.mibs.asterisk.web.PatientHistory;
import com.mibs.asterisk.web.Research;

public class MessageDialog extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private static Color sheetColor = Color.ORANGE;
  private PatientHistory history;
  private JFrame frame;

  public MessageDialog(PatientHistory history) {
    super();
    frame = new JFrame("call me");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    // frame.setAlwaysOnTop(true);
    frame.setUndecorated(true);
    this.history = history;

  }

  public JFrame createAndShowGUI() throws IOException {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
    }

    String phone = "8778888888";

    if (history != null) {
      ;
      phone = history.getPhone();

    }

    JLabel phoneLabel = new JLabel(phone, null, JLabel.CENTER);
    phoneLabel.setBackground(Color.red);
    phoneLabel.setFont(new Font("TimesRoman", Font.PLAIN, 14));

    JPanel panel0 = new JPanel();
    JPanel panel1 = new JPanel();

    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
    panel1.add(phoneLabel);

    List<MedicalResearch> researches = history.getMedicalResearches();
    researches.forEach(r -> {
      JLabel label = new JLabel("  " + r.getPatinentName(), null, JLabel.CENTER);
      panel1.add(Box.createRigidArea(new Dimension(0, 5)));
      panel1.add(label);

      Set<Research> sortedResearches = new TreeSet<>();

      r.getResearch().entrySet().forEach(m -> {
        sortedResearches.add(new Research(m.getValue()));
      });

      sortedResearches.forEach(s -> {
        JLabel historyLine = new JLabel("    " + s.getText(), null, JLabel.CENTER);
        panel1.add(Box.createRigidArea(new Dimension(0, 5)));
        panel1.add(historyLine);
      });

    });

    frame.addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) {
        frame.setVisible(false);
        frame.dispose();
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }

    });

    GroupLayout layout = new GroupLayout(panel0);
    panel0.setLayout(layout);

    panel0.setBorder(BorderFactory.createLineBorder(new Color(69, 96, 117)));

    layout.setHorizontalGroup(layout.createSequentialGroup().addGap(25)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panel1)

        // .addComponent(panel4)
        ).addGap(25));
    layout.setVerticalGroup(

        layout.createSequentialGroup().addGap(25).addComponent(panel1)
            // .addComponent(panel4)
            .addGap(25));

    frame.add(panel0);

    frame.pack();
    // frame.setLocationRelativeTo(null); // center it

    Dimension srcSize = Toolkit.getDefaultToolkit().getScreenSize();
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
    Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
    Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

    int taskBarHeight = srcSize.height - winSize.height;

    // int x = (int) rect.getMaxX() - frame.getWidth()-taskBarHeight/2;
    // int y = (int) rect.getMaxY() - frame.getHeight()-taskBarHeight-10;

    int x = (int) (rect.getMaxX() - frame.getWidth()) / 2;
    int y = (int) 10;

    frame.setLocation(x, y);

    frame.setVisible(true);

    return frame;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }

}
