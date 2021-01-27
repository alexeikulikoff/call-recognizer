package com.mibs.recoginizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

	
	private static String rabbitmqHost;
	private static String rabbitmqUsername;
	private static String rabbitmqPpassword;
	private static String rabbitmqQueue;
	
	private static final Logger logger =  LoggerFactory.getLogger(Utils.class.getName());
	public static void initConfig(String config) {
		Properties prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream( config );
			prop.load( fis );
			rabbitmqHost = prop.getProperty("rabbitmqHost");
			rabbitmqUsername = prop.getProperty("rabbitmaUser");
			rabbitmqPpassword = prop.getProperty("rabbitmqPassword");
			rabbitmqQueue = prop.getProperty("rabbitmqQueue");
		
		} catch (Exception e1) {
		  e1.printStackTrace();
	    JOptionPane.showMessageDialog(null, "Error while loading configuratio file!");
      System.exit(0);
			
		}
	}
  
	public static String getRabbitmqQueue() {
    return rabbitmqQueue;
  }

  public static void initRabbitMQ() {
	
	}
	public static String getRabbitmqHost() {
		return rabbitmqHost;
	}

	public static String getRabbitmqUsername() {
		return rabbitmqUsername;
	}

	public static String getRabbitmqPpassword() {
		return rabbitmqPpassword;
	}

	
}
