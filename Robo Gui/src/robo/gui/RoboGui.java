/*
    RoboGUI main class
 */
package robo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeSupport;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final JFrame myFrame = new JFrame("Main_Frame");
        try {
            
            //read properties files...
            GetProperties("RoboGUI.properties");
            //popluate vars from props
            nMaxHeight   = Integer.valueOf(properties.getProperty("MAX_HEIGHT"));
            nMaxWidth    = Integer.valueOf(properties.getProperty("MAX_WIDTH"));
            NetName      = properties.getProperty("NETWORK_NAME");
            PrinterType  = properties.getProperty("PRINTER_STYLE");
            LogDir       = properties.getProperty("LOG_DIRECTORY");
            logErr       = new FileHandler(LogDir + "RoboGUI.log", true);

            //customize main frame
            // Disables decorations for this frame. By setting undecorated
            // to true will remove the frame's title bar including the maximize,
            // minimize and the close icon.
            myFrame.setUndecorated(true);            
            //create panel
            Main_Panel mainPanel = new Main_Panel(NetName + ":" + PrinterType);

            // setbackground of panel 
            mainPanel.setBackground(Color.blue); 

            // add panel to frame 
            myFrame.getContentPane().add(mainPanel); 
            // set the size of frame 
            myFrame.setSize(nMaxHeight, nMaxWidth); 
            
            myFrame.toFront();
            myFrame.setVisible(true);
            
            while(true) {
                /* 
                 * loop 4Eva (or until they click the exit button 
                 * exit button will not be in the final/release verson or
                 * will be hidden and disabled if it is. All of the event 
                 * handlers should be in the Main_Panel class or at worst
                 * myFrame (which is currently NOT even subclassed -- just 
                 * a default JFrame);
                 */
            }
            //SaveProps on Exit
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
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
                properties.setProperty("MAX_HEIGHT", "350");
                properties.setProperty("MAX_WIDTH", "400");
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
