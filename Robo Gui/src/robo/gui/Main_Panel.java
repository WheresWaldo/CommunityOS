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
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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
    private RunPrint doPrinting = null;
    private String name;
    private Properties props;
    private int selectedPanel = 0;
    private JFrame parent;

    public void setDoPrinting(RunPrint doPrinting) {
        this.doPrinting = doPrinting;
    }

    public RunPrint getDoPrinting() {
        return this.doPrinting;
    }
    public void start_panel(){
        //printerBtn.doClick();
        //filesBtnActionPerformed(null);
        //topPanel.repaint();
        
    }

    public void setProps(Properties props) {
        this.props = props;
    }
    
    public void setStatus(String text)  {
        RoboStatLbl.setText(text);
    }
    
    public void setStatus(String text, int percent) {
        RoboStatLbl.setText(text);
        PrintProgress.setValue(percent);
    }
    
    public void setPrinter(PrinterControl printer) {
        this.printer = printer;
    }
    public void setParent(JFrame Parent) {
        this.parent = Parent;
    }

    public void setPrintProgress(int percent)   {
        if (percent > 100) {
            PrintProgress.setValue(100);
            return;
        }
        if (percent < 0)    {
            PrintProgress.setValue(0);
            return;
        }
        PrintProgress.setValue(percent);        
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
         //printerBtn.setSize(btn_panel_width/3, btn_panel_height);
         printerBtn.setPreferredSize(new Dimension(btn_panel_width/3 - 5, btn_panel_height));
         //utlBtn.setSize(btn_panel_width/3, btn_panel_height);
         utlBtn.setPreferredSize(new Dimension(btn_panel_width/3 - 5, btn_panel_height));
         //filesBtn.setLocation(0, 0);
         
         //printerBtn.setLocation(btn_panel_width/3, 0);
         //utlBtn.setLocation((btn_panel_width/3)*2, 0);
         main_height = parent.getHeight();
         //btmPnlMain.setSize(main_width, (main_height/3) * 2);
         btmPnlMain.setPreferredSize(new Dimension(main_width, (main_height/3) * 2));
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
    @SuppressWarnings("unchecked")
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
        FilesPane = new javax.swing.JSplitPane();
        LocalStoreBtn = new javax.swing.JButton();
        USBStoreBtn = new javax.swing.JButton();
        btmPnlMain = new javax.swing.JPanel();
        quitBtn = new javax.swing.JButton();
        RoboStatLbl = new javax.swing.JLabel();
        tempCtrlBtn = new javax.swing.JButton();
        MtrControlBtn = new javax.swing.JButton();
        PRINTER_DETAILS = new javax.swing.JLabel();
        PrintProgress = new javax.swing.JProgressBar();
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

        setBackground(new java.awt.Color(51, 51, 51));
        setMaximumSize(new java.awt.Dimension(430, 350));
        setMinimumSize(new java.awt.Dimension(430, 350));
        setNextFocusableComponent(this);
        setPreferredSize(new java.awt.Dimension(430, 350));
        setRequestFocusEnabled(false);

        topPanel.setBackground(new java.awt.Color(51, 51, 255));
        topPanel.setMaximumSize(new java.awt.Dimension(350, 32767));

        filesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/files.png"))); // NOI18N
        filesBtn.setName(""); // NOI18N
        filesBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filesBtnActionPerformed(evt);
            }
        });
        topPanel.add(filesBtn);

        printerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer.png"))); // NOI18N
        printerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printerBtnActionPerformed(evt);
            }
        });
        topPanel.add(printerBtn);

        utlBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings.png"))); // NOI18N
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

        btmPnlFiles.setBackground(new java.awt.Color(0, 0, 0));
        btmPnlFiles.setForeground(new java.awt.Color(255, 255, 255));
        btmPnlFiles.setToolTipText("");
        btmPnlFiles.setPreferredSize(new java.awt.Dimension(420, 182));

        FilesPane.setDividerLocation(215);

        LocalStoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalStoreBtnActionPerformed(evt);
            }
        });
        FilesPane.setLeftComponent(LocalStoreBtn);

        USBStoreBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/USB Storage.png"))); // NOI18N
        FilesPane.setRightComponent(USBStoreBtn);

        javax.swing.GroupLayout btmPnlFilesLayout = new javax.swing.GroupLayout(btmPnlFiles);
        btmPnlFiles.setLayout(btmPnlFilesLayout);
        btmPnlFilesLayout.setHorizontalGroup(
            btmPnlFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FilesPane, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        btmPnlFilesLayout.setVerticalGroup(
            btmPnlFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FilesPane, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
        PRINTER_DETAILS.setText("default");

        javax.swing.GroupLayout btmPnlMainLayout = new javax.swing.GroupLayout(btmPnlMain);
        btmPnlMain.setLayout(btmPnlMainLayout);
        btmPnlMainLayout.setHorizontalGroup(
            btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btmPnlMainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(RoboStatLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(btmPnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(btmPnlMainLayout.createSequentialGroup()
                        .addComponent(quitBtn)
                        .addGap(52, 52, 52)
                        .addComponent(tempCtrlBtn)
                        .addGap(18, 18, 18)
                        .addComponent(MtrControlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PRINTER_DETAILS, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(PrintProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btmPnlMainLayout.setVerticalGroup(
            btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btmPnlMainLayout.createSequentialGroup()
                .addComponent(PRINTER_DETAILS)
                .addGap(4, 4, 4)
                .addComponent(PrintProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(btmPnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempCtrlBtn)
                    .addComponent(MtrControlBtn)
                    .addComponent(quitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RoboStatLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void filesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filesBtnActionPerformed
        filesBtn.setIcon(new ImageIcon(Class.class.getResource("/Media/files_active.png")));
        printerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer.png"))); // NOI18N
        utlBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings.png")));        
        btmPnlMain.setVisible(false);
        btmPnlFiles.setVisible(true);
        btmPnlUtility.setVisible(false);
        selectedPanel = 1;
        setScreenSize();
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
        filesBtn.setIcon(new ImageIcon(Class.class.getResource("/Media/files.png")));
        printerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer_active.png"))); // NOI18N
        utlBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings.png")));        
        btmPnlMain.setVisible(true);
        btmPnlFiles.setVisible(false);
        btmPnlUtility.setVisible(false);
        selectedPanel = 0;
        setScreenSize();
    }//GEN-LAST:event_printerBtnActionPerformed

    private void utlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utlBtnActionPerformed
        filesBtn.setIcon(new ImageIcon(Class.class.getResource("/Media/files.png")));
        printerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/printer.png"))); // NOI18N
        utlBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/settings_active.png")));    
        btmPnlUtility.setVisible(true);
        btmPnlMain.setVisible(false);
        btmPnlFiles.setVisible(false);       
        selectedPanel = 2;
        setScreenSize();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane FilesPane;
    private javax.swing.JButton LocalStoreBtn;
    private javax.swing.JButton MtrControlBtn;
    private javax.swing.JLabel PRINTER_DETAILS;
    private javax.swing.JProgressBar PrintProgress;
    private javax.swing.JLabel RoboStatLbl;
    private javax.swing.JButton USBStoreBtn;
    private javax.swing.JPanel btmPnlFiles;
    private javax.swing.JPanel btmPnlMain;
    private javax.swing.JPanel btmPnlUtility;
    private javax.swing.JLabel fileLbl;
    private javax.swing.JButton filesBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton printerBtn;
    private javax.swing.JLabel prntrLbl;
    private javax.swing.JButton quitBtn;
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
