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

    class MainPanel.java
 */

package robo.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * RoboGUI Main Panel
 */

/**
 *
 * @author john
 */
public class Main_Panel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    public RunPrint doPrinting = null;
    private String name;
    private Properties props;
    private int selectedPanel = 0;
    private JFrame parent;

    public RunPrint getPrinterProc()    {
        return doPrinting;
    }
    
    public void setPrinterProc(RunPrint p)  {
        doPrinting = p;
    }
    
    public void setDoPrinting(RunPrint doPrinting) {
        this.doPrinting = doPrinting;
    }
    
    public JPanel getMainPnl()  {
        return btmPnlMain;
    }
    
    public JPanel getPrintPnl() {
        return btmPnlPrinting;
    }

    public RunPrint getDoPrinting() {
        return this.doPrinting;
    }

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
    
    public void start_panel(){
        if(doPrinting != null)  {
            //should be printing
            btmPnlPrinting.setVisible(true);
            btmPnlMain.setVisible(false);
        } else  {
            btmPnlPrinting.setVisible(false);
            btmPnlMain.setVisible(true);
            ImageIcon foo = new ImageIcon("/Media/files.png");
            //don't try to do this before frame is drawn
            int high = filesBtn.getHeight();
            if (filesBtn.getHeight() > 0)   {
                double multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
                filesBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/files.png")).getImage().getScaledInstance((int)(filesBtn.getWidth() * multi), filesBtn.getHeight()/2, Image.SCALE_SMOOTH)));
                foo = new ImageIcon("/Media/printer_active.png");
                multi = (printerBtn.getHeight()/2) / foo.getIconHeight();
                printerBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer_active.png")).getImage().getScaledInstance((int)(printerBtn.getWidth() * multi), printerBtn.getHeight()/2, Image.SCALE_SMOOTH)));
                foo = new ImageIcon("/Media/settings.png");
                multi = (utlBtn.getHeight()/2) / foo.getIconHeight();
                utlBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings.png")).getImage().getScaledInstance((int)(utlBtn.getWidth() * multi), utlBtn.getHeight()/2, Image.SCALE_SMOOTH)));
            }
        }
    }

    public void setProps(Properties props) {
        this.props = props;
    }
    

    public void setStatus(String text)  {
        RoboStatLbl.setText(text);
        printStatLbl.setText(text);
    }
    
    public void setStatus(String text, int percent) {
        printStatLbl.setText(text);
        RoboStatLbl.setText(text);
        printProgressBar.setValue(percent);
    }
    
    public void setPrinter(PrinterControl printer) {
        this.printer = printer;
    }
    public void setParent(JFrame Parent) {
        this.parent = Parent;
    }

    public void setPrintProgress(int percent)   {
        if (percent > 100) {
            printProgressBar.setValue(100);
            return;
        }
        if (percent < 0)    {
            printProgressBar.setValue(0);
            return;
        }
        printProgressBar.setValue(percent);        
    }
    public void setScreenSize(){
         int main_width = parent.getWidth();
         int main_height = parent.getHeight();
         filesBtn.setVisible(false);
         printerBtn.setVisible(false);
         utlBtn.setVisible(false);
         //topPanel.setSize(main_width, main_height/3);
         topPanel.setPreferredSize(new Dimension(main_width, main_height/3));
         //topPanel.setLocation(0, 0);
         int btn_panel_width = main_width;
         int btn_panel_height = main_height/3;
         filesBtn.setSize(btn_panel_width/3 - 5, btn_panel_height);
         filesBtn.setPreferredSize(new Dimension(btn_panel_width/3 - 5, btn_panel_height));
         filesBtn.setHorizontalTextPosition(JLabel.CENTER);
         filesBtn.setVerticalTextPosition(JLabel.BOTTOM);
         //printerBtn.setSize(btn_panel_width/3, btn_panel_height);
         printerBtn.setPreferredSize(new Dimension(btn_panel_width/3 - 5, btn_panel_height));
         printerBtn.setHorizontalTextPosition(JLabel.CENTER);
         printerBtn.setVerticalTextPosition(JLabel.BOTTOM);
         //utlBtn.setSize(btn_panel_width/3, btn_panel_height);
         utlBtn.setPreferredSize(new Dimension(btn_panel_width/3 - 5, btn_panel_height));
         utlBtn.setHorizontalTextPosition(JLabel.CENTER);
         utlBtn.setVerticalTextPosition(JLabel.BOTTOM);
         //filesBtn.setLocation(0, 0);
         
         //printerBtn.setLocation(btn_panel_width/3, 0);
         //utlBtn.setLocation((btn_panel_width/3)*2, 0);
         main_height = parent.getHeight();
         //btmPnlMain.setSize(main_width, (main_height/3) * 2);
         btmPnlMain.setPreferredSize(new Dimension(main_width, (main_height/3) * 2));
         btmPnlFiles.setPreferredSize(new Dimension(main_width, (main_height/3) * 2));
         btmPnlUtility.setPreferredSize(new Dimension(main_width, (main_height/3) * 2));
         //fix file buttons here
         int file_height = (main_height/3) * 2;
         int file_width = main_width;
         //LocalStoreBtn.setSize(file_width/2 - 5, file_height);
         LocalStoreBtn.setPreferredSize(new Dimension(file_width/2 - 5, file_height));
         LocalStoreBtn.setLocation(0, 0);
         USBStoreBtn.setPreferredSize(new Dimension(file_width/2 - 5, file_height));
         USBStoreBtn.setLocation(file_width/2, 0);
         LocalStoreBtn.setHorizontalTextPosition(JLabel.CENTER);
         LocalStoreBtn.setVerticalTextPosition(JLabel.BOTTOM);
         USBStoreBtn.setHorizontalTextPosition(JLabel.CENTER);
         USBStoreBtn.setVerticalTextPosition(JLabel.BOTTOM);
         //btmPnlUtility.setLocation(0, file_width/2);
         //btmPnlMain.setLocation(0, main_height+1);
         filesBtn.setVisible(true);
         printerBtn.setVisible(true);
         utlBtn.setVisible(true);
         invalidate();
    }

    
    private PrinterControl printer;
    
    /**
     * Creates new form Main_Panel
     * @throws java.awt.FontFormatException
     */
    public Main_Panel() throws FontFormatException {
	try {
            initComponents();
            //create the font to use. Specify the size!
            Font customFont;
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("/Media/S-Core - CoreSansD55Bold.otf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            //use the font
            PRINTER_DETAILS.setFont(customFont);            
            btmPnlFiles.setVisible(false);
            btmPnlMain.setVisible(true);
            btmPnlUtility.setVisible(false);
            
            start_panel();
        } catch (Exception ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Main_Panel(BorderLayout borderLayout) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setPrinterActive()
    {
        printerBtnActionPerformed(null);
    }
    
    Main_Panel(String netName)  {
        try {
	    initComponents();
            //create the font to use. Specify the size!
            Font customFont;
            String dir = System.getProperty("user.dir");
            File myFont = new File(dir + File.separator  + "/Media/S-Core - CoreSansD55Bold.otf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, myFont).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            //use the font
            PRINTER_DETAILS.setFont(customFont);            
            PRINTER_DETAILS.setText(netName);        
            btmPnlFiles.setVisible(false);
            btmPnlMain.setVisible(true);
            btmPnlUtility.setVisible(false);
            
            start_panel();
        } catch (Exception ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "deprecation"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        filesBtn = new javax.swing.JButton();
        printerBtn = new javax.swing.JButton();
        utlBtn = new javax.swing.JButton();
        btmPnlFiles = new javax.swing.JPanel();
        LocalStoreBtn = new javax.swing.JButton();
        USBStoreBtn = new javax.swing.JButton();
        btmPnlMain = new javax.swing.JPanel();
        quitBtn = new javax.swing.JButton();
        RoboStatLbl = new javax.swing.JLabel();
        tempCtrlBtn = new javax.swing.JButton();
        MtrControlBtn = new javax.swing.JButton();
        PRINTER_DETAILS = new javax.swing.JLabel();
        extruderTempLbl = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btmPnlUtility = new javax.swing.JPanel();
        topLeftPnl = new javax.swing.JPanel();
        fanBtnLbl = new javax.swing.JLabel();
        utlFanBtn = new javax.swing.JButton();
        topCntrPnl = new javax.swing.JPanel();
        utlWizardsBtn = new javax.swing.JButton();
        wizBtnLbl = new javax.swing.JLabel();
        topRightPnl = new javax.swing.JPanel();
        utlNetwrkBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        BtmLeftPnl = new javax.swing.JPanel();
        utlUpdateBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        BtmCntrPnl = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        utlOptBtn = new javax.swing.JButton();
        utlSysPnl = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        utlSysBtn = new javax.swing.JButton();
        btmPnlPrinting = new javax.swing.JPanel();
        quitBtn1 = new javax.swing.JButton();
        pausePrintBtn = new javax.swing.JButton();
        cancelPrintlBtn = new javax.swing.JButton();
        printProgressBar = new javax.swing.JProgressBar();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        printStatLbl = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setMaximumSize(new java.awt.Dimension(430, 350));
        setMinimumSize(new java.awt.Dimension(430, 350));
        setNextFocusableComponent(this);
        setPreferredSize(new java.awt.Dimension(430, 350));
        setRequestFocusEnabled(false);

        topPanel.setBackground(new java.awt.Color(51, 51, 255));
        topPanel.setMaximumSize(new java.awt.Dimension(350, 32767));

        filesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/files.png"))); // NOI18N
        filesBtn.setText("Files");
        filesBtn.setName(""); // NOI18N
        filesBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filesBtnActionPerformed(evt);
            }
        });
        topPanel.add(filesBtn);

        printerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer.png"))); // NOI18N
        printerBtn.setText("Printer");
        printerBtn.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                printerBtnComponentResized(evt);
            }
        });
        printerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printerBtnActionPerformed(evt);
            }
        });
        topPanel.add(printerBtn);

        utlBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings.png"))); // NOI18N
        utlBtn.setText("Utilities");
        utlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlBtnActionPerformed(evt);
            }
        });
        topPanel.add(utlBtn);

        add(topPanel);

        btmPnlFiles.setBackground(new java.awt.Color(255, 255, 51));
        btmPnlFiles.setForeground(new java.awt.Color(255, 255, 255));
        btmPnlFiles.setToolTipText("");
        btmPnlFiles.setName(""); // NOI18N
        btmPnlFiles.setPreferredSize(new java.awt.Dimension(420, 182));

        LocalStoreBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Files_Icons/File_Options/Local Storage.png"))); // NOI18N
        LocalStoreBtn.setText("Local Storage");
        LocalStoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalStoreBtnActionPerformed(evt);
            }
        });
        btmPnlFiles.add(LocalStoreBtn);

        USBStoreBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/USB Storage.png"))); // NOI18N
        USBStoreBtn.setText("USB Storage");
        btmPnlFiles.add(USBStoreBtn);

        add(btmPnlFiles);

        btmPnlMain.setBackground(new java.awt.Color(51, 51, 51));
        btmPnlMain.setForeground(new java.awt.Color(0, 255, 255));
        btmPnlMain.setMinimumSize(new java.awt.Dimension(397, 137));
        btmPnlMain.setPreferredSize(new java.awt.Dimension(420, 182));

        quitBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        quitBtn.setText("Quit");
        quitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBtnActionPerformed(evt);
            }
        });

        RoboStatLbl.setBackground(new java.awt.Color(51, 51, 51));
        RoboStatLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        RoboStatLbl.setForeground(new java.awt.Color(255, 255, 255));
        RoboStatLbl.setText("Robo Ready");

        tempCtrlBtn.setBackground(new java.awt.Color(0, 0, 255));
        tempCtrlBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tempCtrlBtn.setForeground(new java.awt.Color(255, 255, 255));
        tempCtrlBtn.setText("Temp. Controls");
        tempCtrlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempCtrlBtnActionPerformed(evt);
            }
        });

        MtrControlBtn.setBackground(new java.awt.Color(0, 0, 255));
        MtrControlBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MtrControlBtn.setForeground(new java.awt.Color(255, 255, 255));
        MtrControlBtn.setText("Motor Controls");
        MtrControlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MtrControlBtnActionPerformed(evt);
            }
        });

        PRINTER_DETAILS.setBackground(new java.awt.Color(51, 51, 51));
        PRINTER_DETAILS.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        PRINTER_DETAILS.setForeground(new java.awt.Color(0, 255, 255));
        PRINTER_DETAILS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PRINTER_DETAILS.setText("default");

        extruderTempLbl.setBackground(new java.awt.Color(0, 0, 0));
        extruderTempLbl.setForeground(new java.awt.Color(255, 255, 255));
        extruderTempLbl.setText("Extruder Temp.");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Bed Temp.");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("0 / 0  ");

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("0 / 0 ");

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("°C");

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("°C");

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new StretchIcon("Temperature.png", true));

        javax.swing.GroupLayout btmPnlMainLayout = new javax.swing.GroupLayout(btmPnlMain);
        btmPnlMain.setLayout(btmPnlMainLayout);
        btmPnlMainLayout.setHorizontalGroup(
            btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlMainLayout.createSequentialGroup()
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btmPnlMainLayout.createSequentialGroup()
                        .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btmPnlMainLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(extruderTempLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btmPnlMainLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlMainLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(RoboStatLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btmPnlMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btmPnlMainLayout.createSequentialGroup()
                                .addComponent(quitBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tempCtrlBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MtrControlBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(PRINTER_DETAILS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
            .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btmPnlMainLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addContainerGap(363, Short.MAX_VALUE)))
        );
        btmPnlMainLayout.setVerticalGroup(
            btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btmPnlMainLayout.createSequentialGroup()
                .addComponent(PRINTER_DETAILS, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tempCtrlBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btmPnlMainLayout.createSequentialGroup()
                        .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quitBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(MtrControlBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RoboStatLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(extruderTempLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlMainLayout.createSequentialGroup()
                    .addContainerGap(156, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        add(btmPnlMain);

        btmPnlUtility.setBackground(new java.awt.Color(0, 0, 0));
        btmPnlUtility.setForeground(new java.awt.Color(0, 0, 255));
        btmPnlUtility.setPreferredSize(new java.awt.Dimension(780, 380));

        fanBtnLbl.setBackground(new java.awt.Color(0, 0, 0));
        fanBtnLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fanBtnLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fanBtnLbl.setText("Fan Control");

        utlFanBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/Fans.png"))); // NOI18N
        utlFanBtn.setName(""); // NOI18N
        utlFanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlFanBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topLeftPnlLayout = new javax.swing.GroupLayout(topLeftPnl);
        topLeftPnl.setLayout(topLeftPnlLayout);
        topLeftPnlLayout.setHorizontalGroup(
            topLeftPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLeftPnlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(topLeftPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(utlFanBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanBtnLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        topLeftPnlLayout.setVerticalGroup(
            topLeftPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLeftPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(utlFanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fanBtnLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        utlWizardsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/Wizards.png"))); // NOI18N
        utlWizardsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlWizardsBtnActionPerformed(evt);
            }
        });

        wizBtnLbl.setBackground(new java.awt.Color(0, 0, 0));
        wizBtnLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        wizBtnLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wizBtnLbl.setText("Wizards");

        javax.swing.GroupLayout topCntrPnlLayout = new javax.swing.GroupLayout(topCntrPnl);
        topCntrPnl.setLayout(topCntrPnlLayout);
        topCntrPnlLayout.setHorizontalGroup(
            topCntrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topCntrPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topCntrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(wizBtnLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(utlWizardsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topCntrPnlLayout.setVerticalGroup(
            topCntrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topCntrPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(utlWizardsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wizBtnLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        utlNetwrkBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/Networking.png"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Network");

        javax.swing.GroupLayout topRightPnlLayout = new javax.swing.GroupLayout(topRightPnl);
        topRightPnl.setLayout(topRightPnlLayout);
        topRightPnlLayout.setHorizontalGroup(
            topRightPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topRightPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(utlNetwrkBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topRightPnlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topRightPnlLayout.setVerticalGroup(
            topRightPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topRightPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(utlNetwrkBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        utlUpdateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/Updates.png"))); // NOI18N
        utlUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlUpdateBtnActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Update");

        javax.swing.GroupLayout BtmLeftPnlLayout = new javax.swing.GroupLayout(BtmLeftPnl);
        BtmLeftPnl.setLayout(BtmLeftPnlLayout);
        BtmLeftPnlLayout.setHorizontalGroup(
            BtmLeftPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtmLeftPnlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BtmLeftPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(utlUpdateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        BtmLeftPnlLayout.setVerticalGroup(
            BtmLeftPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtmLeftPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(utlUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Options");

        utlOptBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/White_Utilities/Options.png"))); // NOI18N
        utlOptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlOptBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BtmCntrPnlLayout = new javax.swing.GroupLayout(BtmCntrPnl);
        BtmCntrPnl.setLayout(BtmCntrPnlLayout);
        BtmCntrPnlLayout.setHorizontalGroup(
            BtmCntrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtmCntrPnlLayout.createSequentialGroup()
                .addGroup(BtmCntrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(utlOptBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addContainerGap())
        );
        BtmCntrPnlLayout.setVerticalGroup(
            BtmCntrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtmCntrPnlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(utlOptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("System");

        utlSysBtn.setIcon(new javax.swing.ImageIcon("E:\\markt\\Documents\\NetBeansProjects\\CommunityOS\\Robo Gui\\src\\Media\\System_Icons\\Shutdown 2.png")); // NOI18N
        utlSysBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlSysBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout utlSysPnlLayout = new javax.swing.GroupLayout(utlSysPnl);
        utlSysPnl.setLayout(utlSysPnlLayout);
        utlSysPnlLayout.setHorizontalGroup(
            utlSysPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(utlSysPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(utlSysPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(utlSysBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        utlSysPnlLayout.setVerticalGroup(
            utlSysPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, utlSysPnlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(utlSysBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout btmPnlUtilityLayout = new javax.swing.GroupLayout(btmPnlUtility);
        btmPnlUtility.setLayout(btmPnlUtilityLayout);
        btmPnlUtilityLayout.setHorizontalGroup(
            btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlUtilityLayout.createSequentialGroup()
                .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(BtmLeftPnl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(topLeftPnl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtmCntrPnl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topCntrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(topRightPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(utlSysPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        btmPnlUtilityLayout.setVerticalGroup(
            btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btmPnlUtilityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(topRightPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(topCntrPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(topLeftPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(utlSysPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BtmLeftPnl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtmCntrPnl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(btmPnlUtility);

        btmPnlPrinting.setBackground(new java.awt.Color(51, 51, 51));
        btmPnlPrinting.setForeground(new java.awt.Color(0, 255, 255));
        btmPnlPrinting.setPreferredSize(new java.awt.Dimension(405, 153));

        quitBtn1.setText("Quit");
        quitBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBtn1ActionPerformed(evt);
            }
        });

        pausePrintBtn.setBackground(new java.awt.Color(0, 0, 255));
        pausePrintBtn.setForeground(new java.awt.Color(255, 255, 255));
        pausePrintBtn.setText("Pause");
        pausePrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pausePrintBtnActionPerformed(evt);
            }
        });

        cancelPrintlBtn.setBackground(new java.awt.Color(0, 0, 255));
        cancelPrintlBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelPrintlBtn.setText("cancel");
        cancelPrintlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelPrintlBtnActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Extruder Temp.");

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Bed Temp.");

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("0 / 0");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("0 / 0");

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("°C");

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("°C");

        printStatLbl.setBackground(new java.awt.Color(0, 0, 0));
        printStatLbl.setForeground(new java.awt.Color(255, 255, 255));
        printStatLbl.setToolTipText("");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new StretchIcon("red temp icon.png", true));

        javax.swing.GroupLayout btmPnlPrintingLayout = new javax.swing.GroupLayout(btmPnlPrinting);
        btmPnlPrinting.setLayout(btmPnlPrintingLayout);
        btmPnlPrintingLayout.setHorizontalGroup(
            btmPnlPrintingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btmPnlPrintingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btmPnlPrintingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btmPnlPrintingLayout.createSequentialGroup()
                        .addComponent(printStatLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlPrintingLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlPrintingLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addComponent(printProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btmPnlPrintingLayout.createSequentialGroup()
                        .addComponent(quitBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pausePrintBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelPrintlBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addGap(26, 26, 26))))
        );
        btmPnlPrintingLayout.setVerticalGroup(
            btmPnlPrintingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btmPnlPrintingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(printStatLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btmPnlPrintingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btmPnlPrintingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(quitBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pausePrintBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelPrintlBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btmPnlPrintingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(btmPnlPrintingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        add(btmPnlPrinting);
    }// </editor-fold>//GEN-END:initComponents

    private void filesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filesBtnActionPerformed
        setScreenSize();        
        btmPnlMain.setVisible(false);
        btmPnlFiles.setVisible(true);
        btmPnlUtility.setVisible(false);
        selectedPanel = 1;
        ImageIcon foo = new ImageIcon("/Media/files_active.png");
        double multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        filesBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/files_active.png")).getImage().getScaledInstance((int)(filesBtn.getWidth() * multi), filesBtn.getHeight()/2, Image.SCALE_SMOOTH)));
        //filesBtn.setIcon(new ImageIcon(Class.class.getResource("/Media/files_active.png")));
        foo = new ImageIcon("/Media/printer.png");
        multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        printerBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer.png")).getImage().getScaledInstance((int)(printerBtn.getWidth() * multi), printerBtn.getHeight()/2, Image.SCALE_SMOOTH)));
        //printerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer.png"))); // NOI18N
        foo = new ImageIcon("/Media/settings.png");
        multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        utlBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings.png")).getImage().getScaledInstance((int)(utlBtn.getWidth() * multi), utlBtn.getHeight()/2, Image.SCALE_SMOOTH)));
        //utlBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings.png")));
        //foo = new ImageIcon("Media/Files_Icons/File_Options/Local Storage.png");
        //multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        //LocalStoreBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("Media/Files_Icons/File_Options/Local Storage.png")).getImage().getScaledInstance((int)(LocalStoreBtn.getWidth() * multi), LocalStoreBtn.getHeight()/2, Image.SCALE_SMOOTH)));
        //foo = new ImageIcon("Media/Files_Icons/File_Options/USB Storage.png");
        //multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        //USBStoreBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("Media/Files_Icons/File_Options/USB Storage.png")).getImage().getScaledInstance((int)(USBStoreBtn.getWidth() * multi), USBStoreBtn.getHeight()/2, Image.SCALE_SMOOTH)));
    }//GEN-LAST:event_filesBtnActionPerformed
	
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {
		                                        
    }              
	
    private void optionsBtnActionPerformed(java.awt.event.ActionEvent evt) {
		                                        
    }              

    private void fansBtnActionPerformed(java.awt.event.ActionEvent evt) {
		//GEN-FIRST:event_fansBtnActionPerformed
	}//GEN-LAST:event_fansBtnActionPerformed
	
    private void networkBtnActionPerformed(java.awt.event.ActionEvent evt) {
		//GEN-FIRST:event_networkBtnActionPerformed
	}//GEN-LAST:event_networkBtnActionPerformed
    
    private void printerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printerBtnActionPerformed
        setScreenSize();
        if(doPrinting != null)  {
            //are printing
            btmPnlMain.setVisible(false);
            btmPnlFiles.setVisible(false);
            btmPnlUtility.setVisible(false);
            btmPnlPrinting.setVisible(true);            
        } else  {
            //are not printing
            btmPnlMain.setVisible(true);
            btmPnlFiles.setVisible(false);
            btmPnlUtility.setVisible(false);
            btmPnlPrinting.setVisible(false);
        }
        selectedPanel = 0;
        ImageIcon foo = new ImageIcon("/Media/files.png");
        double multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        filesBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/files.png")).getImage().getScaledInstance((int)(filesBtn.getWidth() * multi), filesBtn.getHeight()/2, Image.SCALE_SMOOTH)));
        foo = new ImageIcon("/Media/printer_active.png");
        multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        printerBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer_active.png")).getImage().getScaledInstance((int)(printerBtn.getWidth() * multi), printerBtn.getHeight()/2, Image.SCALE_SMOOTH)));
        foo = new ImageIcon("/Media/settings.png");
        multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        utlBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings.png")).getImage().getScaledInstance((int)(utlBtn.getWidth() * multi), utlBtn.getHeight()/2, Image.SCALE_SMOOTH)));
        //filesBtn.setIcon(new ImageIcon(Class.class.getResource("/Media/files.png")));
        //printerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer_active.png"))); // NOI18N
        //utlBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings.png")));  
    }//GEN-LAST:event_printerBtnActionPerformed

    private void utlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utlBtnActionPerformed
        setScreenSize();
        btmPnlUtility.setVisible(true);
        btmPnlMain.setVisible(false);
        btmPnlFiles.setVisible(false);       
        selectedPanel = 2;
        ImageIcon foo = new ImageIcon("/Media/files.png");
        double multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        filesBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/files.png")).getImage().getScaledInstance((int)(filesBtn.getWidth() * multi), filesBtn.getHeight()/2, Image.SCALE_SMOOTH)));
        foo = new ImageIcon("/Media/printer.png");
        multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        printerBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer.png")).getImage().getScaledInstance((int)(printerBtn.getWidth() * multi), printerBtn.getHeight()/2, Image.SCALE_SMOOTH)));
        foo = new ImageIcon("/Media/settings_active.png");
        multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
        utlBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings_active.png")).getImage().getScaledInstance((int)(utlBtn.getWidth() * multi), utlBtn.getHeight()/2, Image.SCALE_SMOOTH)));
        //filesBtn.setIcon(new ImageIcon(Class.class.getResource("/Media/files.png")));
        //printerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer.png"))); // NOI18N
        //utlBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings_active.png")));    
    }//GEN-LAST:event_utlBtnActionPerformed

    private void MtrControlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MtrControlBtnActionPerformed
        
    }//GEN-LAST:event_MtrControlBtnActionPerformed

    private void tempCtrlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempCtrlBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tempCtrlBtnActionPerformed

    private void quitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBtnActionPerformed
        RoboGui.SaveProperties();
        System.exit(0);
    }//GEN-LAST:event_quitBtnActionPerformed

    private void utlFanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utlFanBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_utlFanBtnActionPerformed

    private void utlWizardsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void utlUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utlUpdateBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_utlUpdateBtnActionPerformed

    private void utlOptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utlOptBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_utlOptBtnActionPerformed

    private void utlSysBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utlSysBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_utlSysBtnActionPerformed

    private void LocalStoreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalStoreBtnActionPerformed
        printer.setProps(props);
        printer.filesMenu();
    }//GEN-LAST:event_LocalStoreBtnActionPerformed

    private void quitBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBtn1ActionPerformed
        RoboGui.SaveProperties();
        System.exit(0);
    }//GEN-LAST:event_quitBtn1ActionPerformed

    private void pausePrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausePrintBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pausePrintBtnActionPerformed

    private void cancelPrintlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelPrintlBtnActionPerformed
        if(doPrinting != null)  {
            doPrinting.cancel();
            doPrinting = null;
            start_panel();
        }
    }//GEN-LAST:event_cancelPrintlBtnActionPerformed

    private void printerBtnComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_printerBtnComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_printerBtnComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BtmCntrPnl;
    private javax.swing.JPanel BtmLeftPnl;
    private javax.swing.JButton LocalStoreBtn;
    private javax.swing.JButton MtrControlBtn;
    private javax.swing.JLabel PRINTER_DETAILS;
    private javax.swing.JLabel RoboStatLbl;
    private javax.swing.JButton USBStoreBtn;
    private javax.swing.JPanel btmPnlFiles;
    private javax.swing.JPanel btmPnlMain;
    private javax.swing.JPanel btmPnlPrinting;
    private javax.swing.JPanel btmPnlUtility;
    private javax.swing.JButton cancelPrintlBtn;
    private javax.swing.JLabel extruderTempLbl;
    private javax.swing.JLabel fanBtnLbl;
    private javax.swing.JButton filesBtn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton pausePrintBtn;
    private javax.swing.JProgressBar printProgressBar;
    private javax.swing.JLabel printStatLbl;
    private javax.swing.JButton printerBtn;
    private javax.swing.JButton quitBtn;
    private javax.swing.JButton quitBtn1;
    private javax.swing.JButton tempCtrlBtn;
    private javax.swing.JPanel topCntrPnl;
    private javax.swing.JPanel topLeftPnl;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel topRightPnl;
    private javax.swing.JButton utlBtn;
    private javax.swing.JButton utlFanBtn;
    private javax.swing.JButton utlNetwrkBtn;
    private javax.swing.JButton utlOptBtn;
    private javax.swing.JButton utlSysBtn;
    private javax.swing.JPanel utlSysPnl;
    private javax.swing.JButton utlUpdateBtn;
    private javax.swing.JButton utlWizardsBtn;
    private javax.swing.JLabel wizBtnLbl;
    // End of variables declaration//GEN-END:variables
}
