/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.mibs.recoginizer;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.lang3.SerializationUtils;

import com.mibs.asterisk.web.Patient;
import com.mibs.asterisk.web.PatientHistory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;



public class App {
  
  
    public String getGreeting() {
        return "Hello world.";
    }
    private static void createAndShowGUI() throws IOException, TimeoutException {
      //Check the SystemTray support
      if (!SystemTray.isSupported()) {
        System.out.println("SystemTray is not supported");
        return;
      }
      
      final PopupMenu popup = new PopupMenu();
      final TrayIcon trayIcon = new TrayIcon(createImage("phone.gif", "tray icon"));
      final SystemTray tray = SystemTray.getSystemTray();

      // Create a popup menu components
      MenuItem aboutItem = new MenuItem("About");
     
      MenuItem exitItem = new MenuItem("Exit");

      //Add components to popup menu
      popup.add(aboutItem);
      popup.addSeparator();
   
      popup.add(exitItem);

      trayIcon.setPopupMenu(popup);

      List<JFrame> frames = new ArrayList<>();
      try {
        tray.add(trayIcon);
        try {
          ConnectionFactory factory = new ConnectionFactory();
          factory.setHost(Utils.getRabbitmqHost());
          factory.setUsername(Utils.getRabbitmqUsername());
          factory.setPassword(Utils.getRabbitmqPpassword());
          factory.setPort(5672);
        

          Connection connection = factory.newConnection();
          Channel channel = connection.createChannel();

          channel.queueDeclare(Utils.getRabbitmqQueue(), false, false, false, null);
         
          DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            PatientHistory patient = (PatientHistory) SerializationUtils.deserialize(delivery.getBody());
            for(JFrame fr : frames) {
                fr.setVisible(false);
                fr.dispose();
            }
            
            
            MessageDialog messageDialog = new MessageDialog(patient);
            JFrame frame =  messageDialog.createAndShowGUI();
         
            frames.add(frame);
       
            
          };
          channel.basicConsume(Utils.getRabbitmqQueue(), true, deliverCallback, consumerTag -> {});
        }catch(Exception e) {
          JOptionPane.showMessageDialog(null, "Message error :  " + e.getMessage());
         
          System.exit(0);
        }
        
      } catch (AWTException e) {
        System.out.println("TrayIcon could not be added.");
        return;
      }

      trayIcon.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(null, "This dialog box is run from System Tray");
        }
      });

      aboutItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(null, "MIBS Call Recognizer ");
        }
      });

    


      ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          MenuItem item = (MenuItem) e.getSource();
          //TrayIcon.MessageType type = null;
        
          if ("Error".equals(item.getLabel())) {
            //type = TrayIcon.MessageType.ERROR;
            trayIcon.displayMessage("Sun TrayIcon Demo", "This is an error message", TrayIcon.MessageType.ERROR);

          } else if ("Warning".equals(item.getLabel())) {
            //type = TrayIcon.MessageType.WARNING;
            trayIcon.displayMessage("Sun TrayIcon Demo", "This is a warning message", TrayIcon.MessageType.WARNING);

          } else if ("Info".equals(item.getLabel())) {
            //type = TrayIcon.MessageType.INFO;
            trayIcon.displayMessage("Sun TrayIcon Demo", "This is an info message", TrayIcon.MessageType.INFO);

          } else if ("None".equals(item.getLabel())) {
            //type = TrayIcon.MessageType.NONE;
            trayIcon.displayMessage("Sun TrayIcon Demo", "This is an ordinary message", TrayIcon.MessageType.NONE);
          }
        }
      };

    

      exitItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          tray.remove(trayIcon);
          System.exit(0);
        }
      });
    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
      //URL imageURL = App.class.getResource(path);
      URL imageURL = App.class.getClassLoader().getResource(path);
      if (imageURL == null) {
        System.err.println("Resource not found: " + path);
        return null;
      } else {
        return (new ImageIcon(imageURL, description)).getImage();
      }
    }
  
    
   
    public static void main(String[] args) {
      
      if (args != null && args.length > 0) {
        Utils.initConfig(args[0]);
      } else {
        JOptionPane.showMessageDialog(null, "Message error : Where is config file?");
        System.exit(0);
      }
      try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
      } catch (UnsupportedLookAndFeelException ex) {
        ex.printStackTrace();
      } catch (IllegalAccessException ex) {
        ex.printStackTrace();
      } catch (InstantiationException ex) {
        ex.printStackTrace();
      } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
      }
     
      UIManager.put("swing.boldMetal", Boolean.FALSE);
      //Schedule a job for the event-dispatching thread:
      //adding TrayIcon.
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          try {
            createAndShowGUI();
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }); 
    }
}
