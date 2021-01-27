package com.mibs.recoginizer;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.mibs.asterisk.web.Patient;
import com.mibs.asterisk.web.PatientHistory;

public class MessageDialog extends JFrame implements ActionListener{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static Color sheetColor = Color.ORANGE;
  private PatientHistory patient;
  private JFrame frame;
  public MessageDialog( PatientHistory patient ) {
    super();
    frame = new JFrame("call me");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setAlwaysOnTop( true );
    frame.setUndecorated( true );
    this.patient = patient;
  
  }
  public  JFrame createAndShowGUI() throws IOException {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
    }

    
    String phone = "8778888888";
    String fname= "FFFFFFF";
    String sname = "SSSSSSSS";
    String lname = "LLLLLLLLL";
    
    if (patient != null) {;
     phone = patient.getPhone();
 //    fname= patient.getPhone()
//     sname = patient.getSname();
//     lname = patient.getLname();
    }
         
     JLabel  label1 = new JLabel(phone,null, JLabel.CENTER);
     label1.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
//     JLabel  label2 = new JLabel( fname+ "  " + sname,null, JLabel.CENTER);
//     label2.setFont(new Font("TimesRoman", Font.BOLD, 40)); 
//     JLabel label3 = new JLabel(lname,null, JLabel.CENTER);
//     label3.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
       
     JPanel panel0 = new JPanel();
     JPanel panel1 = new JPanel();
     JPanel panel2 = new JPanel();
     JPanel panel3 = new JPanel();  
     JPanel panel4 = new JPanel();  
    
     panel0.setBackground(sheetColor);
     panel1.setBackground(sheetColor);
     panel2.setBackground(sheetColor);
     panel3.setBackground(sheetColor);
     panel4.setBackground(sheetColor);
  
     panel1.add(label1);
//     panel2.add(label2);
//     panel3.add(label3);
//   
    
    
    
    frame.addMouseListener( new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        frame.setVisible(false);
        frame.dispose();
      }
      @Override
      public void mouseEntered(MouseEvent arg0) {}
      @Override
      public void mouseExited(MouseEvent arg0) {}
      @Override
      public void mousePressed(MouseEvent arg0) {}
      @Override
      public void mouseReleased(MouseEvent arg0) {}
    });
  
    GroupLayout layout = new GroupLayout(panel0);
    panel0.setLayout(layout);
  
    panel0.setBorder(BorderFactory.createLineBorder(new Color(69,96,117)));
    
    layout.setHorizontalGroup(
           layout.createSequentialGroup()
              .addGap(25)
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panel1)
                    .addComponent(panel2)
                    .addComponent(panel3)
                //  .addComponent(panel4)
                    )
              .addGap(25)
                  );
    layout.setVerticalGroup(
      
           layout.createSequentialGroup()
           .addGap(25)
           .addComponent(panel1)
               .addComponent(panel2)
             .addComponent(panel3)
          //   .addComponent(panel4)
             .addGap(25)
        );
    
   
  
    frame.add(panel0);
    
    frame.pack();
   // frame.setLocationRelativeTo(null); // center it
    
   
    Dimension srcSize = Toolkit.getDefaultToolkit().getScreenSize();
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
    Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
    Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    
    int taskBarHeight = srcSize.height - winSize.height;
   
  //  int x = (int) rect.getMaxX() - frame.getWidth()-taskBarHeight/2;
  //  int y = (int) rect.getMaxY() - frame.getHeight()-taskBarHeight-10;
    
    int x = (int) (rect.getMaxX() - frame.getWidth())/2;
    int y = (int) 10;
    
    frame.setLocation(x, y);
    
    frame.setVisible(true);
   
    return frame;
  }
  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    
  }
  
}
