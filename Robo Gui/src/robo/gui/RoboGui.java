/*
/*
 * Copyright (C) 2019 markt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.

    RoboGUI main class
 */
package robo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.beans.PropertyChangeSupport;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author john
 */
public class RoboGui {
    //vars
    static Properties   properties = new Properties();
    static FileHandler  logErr;
    private static final Logger ErrorLogger = Logger.getLogger("RoboGUIError");
    private static int nMaxHeight;
    private static int nMaxWidth;
    private static String NetName;
    private static String PrinterType;
    private static String LogDir;
    private static final JFrame myFrame = new JFrame("Main_Frame");
    private static PrinterControl printer;
   

    public static Properties getProperties() {
        return properties;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean bFirst = false;
        
        try {
            printer = new PrinterControl();
            //read properties files...
            GetProperties("RoboGUI.properties");
            //popluate vars from props
            try {
                nMaxHeight   = Integer.valueOf(properties.getProperty("MAX_HEIGHT"));
                nMaxWidth    = Integer.valueOf(properties.getProperty("MAX_WIDTH"));
            } catch(Exception ex)   {
                //may bot be spcified -- we will use defaults. 
                // java - get screen size using the Toolkit class
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                // the screen height
                nMaxHeight = screenSize.height;
                // the screen width
                nMaxWidth = screenSize.width;
            }
            NetName      = properties.getProperty("NETWORK_NAME");
            PrinterType  = properties.getProperty("PRINTER_STYLE");
            LogDir       = properties.getProperty("LOG_DIRECTORY");

            File f = new File(LogDir);
            if (f.exists() && f.isDirectory()) {
                logErr       = new FileHandler(LogDir + "RoboGUI.log", true);
            } else  {
                logErr = new FileHandler(System.getProperty("user.home") + "/RoboGui.log", true);
            }
            
            
            //customize main frame
            // Disables decorations for this frame. By setting undecorated
            // to true will remove the frame's title bar including the maximize,
            // minimize and the close icon.
            myFrame.setUndecorated(true);            
            //create panel
            Main_Panel mainPanel = new Main_Panel(NetName + ":" + PrinterType);

            // setbackground of panel 
            mainPanel.setBackground(Color.black); 
            mainPanel.setPrinter(printer);
            mainPanel.setProps(properties);
            mainPanel.setParent(myFrame);
            // add panel to frame 
            myFrame.getContentPane().add(mainPanel); 
            // set the size of frame 
            myFrame.setSize(new Dimension(nMaxWidth, nMaxHeight));
            myFrame.setPreferredSize(new Dimension(nMaxWidth, nMaxHeight));
            myFrame.toFront();
            myFrame.setVisible(true);
            printer.setParent(myFrame, mainPanel);
            mainPanel.setScreenSize();
            mainPanel.start_panel();
            myFrame.validate(); // because you added panel after setVisible was called
            myFrame.repaint(); // because you added panel after setVisible was called
            myFrame.pack();
            while(true) {
                /* 
                 * loop 4Eva (or until they click the exit button 
                 * exit button will not be in the final/release verson or
                 * will be hidden and disabled if it is. All of the event 
                 * handlers should be in the Main_Panel class or at worst
                 * myFrame (which is currently NOT even subclassed -- just 
                 * a default JFrame);
                 */
                if(bFirst == false) {
//                    mainPanel.firePrinterBtn();
                    bFirst = true;
                }
            }
            //SaveProps on Exit
        } catch (HeadlessException | IOException | SecurityException ex)  {
        }
    }

    public static PrinterControl getPrinter() {
        return printer;
    }
    
    public static void GetProperties(String dir)  {
        try {
            properties.load(new FileInputStream(dir));
        } catch (IOException e) {
                StringWriter sw = new StringWriter();
                String       stacktrace;

                System.out.println("Can't read properties file, will create on exit. " + e.getMessage());
                e.printStackTrace(new PrintWriter(sw));
                stacktrace = sw.toString();
                //set defaults
                LogDir = System.getProperty("user.dir");
                properties.setProperty("LOG_DIRECTORY", LogDir);
                //properties.setProperty("MAX_HEIGHT", "430");
                //properties.setProperty("MAX_WIDTH", "350");
                properties.setProperty("NETWORK_NAME", "ROBO3D_DEFAULT");
                properties.setProperty("PRINTER_STYLE", "C2");
        }         
    }

    public static void SaveProperties()	{
        // Write properties file.
        try {
            properties.store(new FileOutputStream("RoboGUI.properties"), null);
        } catch (IOException e) {
                StringWriter sw = new StringWriter();
                String       stacktrace;

                System.out.println("Error saving properties file. " + e.getMessage());
                getErrorLogger().log(Level.SEVERE, "Error saving properties file. {0}", e.getMessage());
                e.printStackTrace(new PrintWriter(sw));
                stacktrace = sw.toString();
                getErrorLogger().log(Level.INFO, stacktrace);
        }
}    

    /**
     * @return the ErrorLogger
     */
    public static Logger getErrorLogger() {
        return ErrorLogger;
    }

    /**
     * @return the nMaxHeight
     */
    public static int getnMaxHeight() {
        return nMaxHeight;
    }

    /**
     * @return the nMaxWidth
     */
    public static int getnMaxWidth() {
        return nMaxWidth;
    }

    /**
     * @return the NetName
     */
    public static String getNetName() {
        return NetName;
    }

    /**
     * @return the PrinterType
     */
    public static String getPrinterType() {
        return PrinterType;
    }
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_ERRORLOGGER = "ErrorLogger";
    public static final String PROP_NMAXHEIGHT = "nMaxHeight";
    public static final String PROP_NMAXWIDTH = "nMaxWidth";
    public static final String PROP_NETNAME = "NetName";
    public static final String PROP_PRINTERTYPE = "PrinterType";
}
