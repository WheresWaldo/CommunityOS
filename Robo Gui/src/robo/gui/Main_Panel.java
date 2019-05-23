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
                multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
                printerBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer_active.png")).getImage().getScaledInstance((int)(printerBtn.getWidth() * multi), printerBtn.getHeight()/2, Image.SCALE_SMOOTH)));
                foo = new ImageIcon("/Media/settings.png");
                multi = (filesBtn.getHeight()/2) / foo.getIconHeight();
                utlBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings.png")).getImage().getScaledInstance((int)(utlBtn.getWidth() * multi), utlBtn.getHeight()/2, Image.SCALE_SMOOTH)));
            }
        }
    }

    public void setProps(Properties props) {
        this.props = props;
    }
    
    public void setStatus(String text)  {
        RoboStatLbl.setText(text);
    }
    
    public void setStatus(String text, int percent) {
        if(doPrinting != null)  {
            printStatLbl.setText(text);
            printProgressBar.setValue(percent);
        } else  {
            RoboStatLbl.setText(text);
            printProgressBar.setValue(0);
        }
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
        fileLbl = new javax.swing.JLabel();
        prntrLbl = new javax.swing.JLabel();
        utlLbl = new javax.swing.JLabel();
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
        btmPnlUtility = new javax.swing.JPanel();
        utlUpdateBtn = new javax.swing.JButton();
        utlWizardsBtn = new javax.swing.JButton();
        utlNetwrkBtn = new javax.swing.JButton();
        utlOptBtn = new javax.swing.JButton();
        utlSysBtn = new javax.swing.JButton();
        utlFanBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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

        fileLbl.setBackground(new java.awt.Color(51, 51, 51));
        fileLbl.setFont(new java.awt.Font("Courier New", 1, 11)); // NOI18N
        fileLbl.setForeground(new java.awt.Color(255, 255, 255));
        fileLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fileLbl.setText("Files");
        topPanel.add(fileLbl);

        prntrLbl.setBackground(new java.awt.Color(51, 51, 51));
        prntrLbl.setFont(new java.awt.Font("Courier New", 1, 11)); // NOI18N
        prntrLbl.setForeground(new java.awt.Color(255, 255, 255));
        prntrLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prntrLbl.setText("Printer");
        topPanel.add(prntrLbl);

        utlLbl.setBackground(new java.awt.Color(51, 51, 51));
        utlLbl.setFont(new java.awt.Font("Courier New", 1, 11)); // NOI18N
        utlLbl.setForeground(new java.awt.Color(255, 255, 255));
        utlLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        utlLbl.setText("Utility");
        topPanel.add(utlLbl);

        add(topPanel);

        btmPnlFiles.setBackground(new java.awt.Color(255, 255, 51));
        btmPnlFiles.setForeground(new java.awt.Color(255, 255, 255));
        btmPnlFiles.setToolTipText("");
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
        btmPnlMain.setPreferredSize(new java.awt.Dimension(405, 153));

        quitBtn.setText("Quit");
        quitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBtnActionPerformed(evt);
            }
        });

        RoboStatLbl.setBackground(new java.awt.Color(51, 51, 51));
        RoboStatLbl.setForeground(new java.awt.Color(255, 255, 255));
        RoboStatLbl.setText("Robo Ready");

        tempCtrlBtn.setBackground(new java.awt.Color(0, 0, 255));
        tempCtrlBtn.setForeground(new java.awt.Color(255, 255, 255));
        tempCtrlBtn.setText("Temp. Controls");
        tempCtrlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempCtrlBtnActionPerformed(evt);
            }
        });

        MtrControlBtn.setBackground(new java.awt.Color(0, 0, 255));
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

        javax.swing.GroupLayout btmPnlMainLayout = new javax.swing.GroupLayout(btmPnlMain);
        btmPnlMain.setLayout(btmPnlMainLayout);
        btmPnlMainLayout.setHorizontalGroup(
            btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btmPnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btmPnlMainLayout.createSequentialGroup()
                        .addComponent(quitBtn)
                        .addGap(64, 64, 64)
                        .addComponent(tempCtrlBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MtrControlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PRINTER_DETAILS, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btmPnlMainLayout.createSequentialGroup()
                .addComponent(RoboStatLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlMainLayout.createSequentialGroup()
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(extruderTempLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btmPnlMainLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)))
                .addGap(53, 53, 53))
            .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btmPnlMainLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(348, Short.MAX_VALUE)))
        );
        btmPnlMainLayout.setVerticalGroup(
            btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btmPnlMainLayout.createSequentialGroup()
                .addComponent(PRINTER_DETAILS, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MtrControlBtn)
                    .addComponent(quitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tempCtrlBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RoboStatLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(extruderTempLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlMainLayout.createSequentialGroup()
                    .addContainerGap(133, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addContainerGap()))
        );

        add(btmPnlMain);

        btmPnlUtility.setBackground(new java.awt.Color(0, 0, 0));
        btmPnlUtility.setForeground(new java.awt.Color(0, 0, 255));

        utlUpdateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/Updates.png"))); // NOI18N
        utlUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlUpdateBtnActionPerformed(evt);
            }
        });

        utlWizardsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/Wizards.png"))); // NOI18N
        utlWizardsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlWizardsBtnActionPerformed(evt);
            }
        });

        utlNetwrkBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/Networking.png"))); // NOI18N

        utlOptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlOptBtnActionPerformed(evt);
            }
        });

        utlSysBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlSysBtnActionPerformed(evt);
            }
        });

        utlFanBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/Fans.png"))); // NOI18N
        utlFanBtn.setName(""); // NOI18N
        utlFanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utlFanBtnActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fan Control");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Wizards");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Network");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Update");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Options");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("System");

        javax.swing.GroupLayout btmPnlUtilityLayout = new javax.swing.GroupLayout(btmPnlUtility);
        btmPnlUtility.setLayout(btmPnlUtilityLayout);
        btmPnlUtilityLayout.setHorizontalGroup(
            btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btmPnlUtilityLayout.createSequentialGroup()
                .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btmPnlUtilityLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4)
                        .addGap(105, 105, 105)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btmPnlUtilityLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(utlUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(utlOptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(utlSysBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btmPnlUtilityLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(utlFanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(utlWizardsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(utlNetwrkBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btmPnlUtilityLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1)
                        .addGap(89, 89, 89)
                        .addComponent(jLabel2)
                        .addGap(94, 94, 94)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btmPnlUtilityLayout.setVerticalGroup(
            btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlUtilityLayout.createSequentialGroup()
                .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(utlFanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(utlWizardsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(utlNetwrkBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(btmPnlUtilityLayout.createSequentialGroup()
                        .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(utlUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(utlOptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(utlSysBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(btmPnlUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))))
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

        printStatLbl.setText("jLabel7");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(btmPnlPrintingLayout.createSequentialGroup()
                        .addComponent(quitBtn1)
                        .addGap(61, 61, 61)
                        .addComponent(pausePrintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelPrintlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 94, Short.MAX_VALUE))
                    .addComponent(printProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        btmPnlPrintingLayout.setVerticalGroup(
            btmPnlPrintingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btmPnlPrintingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(printStatLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btmPnlPrintingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quitBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pausePrintBtn)
                    .addComponent(cancelPrintlBtn))
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
            btmPnlMain.setVisible(false);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel fileLbl;
    private javax.swing.JButton filesBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton pausePrintBtn;
    private javax.swing.JProgressBar printProgressBar;
    private javax.swing.JLabel printStatLbl;
    private javax.swing.JButton printerBtn;
    private javax.swing.JLabel prntrLbl;
    private javax.swing.JButton quitBtn;
    private javax.swing.JButton quitBtn1;
    private javax.swing.JButton tempCtrlBtn;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton utlBtn;
    private javax.swing.JButton utlFanBtn;
    private javax.swing.JLabel utlLbl;
    private javax.swing.JButton utlNetwrkBtn;
    private javax.swing.JButton utlOptBtn;
    private javax.swing.JButton utlSysBtn;
    private javax.swing.JButton utlUpdateBtn;
    private javax.swing.JButton utlWizardsBtn;
    // End of variables declaration//GEN-END:variables
}
