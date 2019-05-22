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
 */
package robo.gui;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Properties;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author markt
 */
public class FilesPanel extends javax.swing.JPanel {
    private PrinterControl printer; 
    private Properties props;
    private JFrame pFrame;
    private Main_Panel mainPnl;

    public void setProps(Properties props) {
        this.props = props;
    }

    public void setPrinter(PrinterControl printer) {
        this.printer = printer;
    }
    
    public void setMain(JFrame pF, JPanel pM)   {
        pFrame = pF;
        mainPnl = (Main_Panel) pM;        
    }
    
    /**
     * Creates new form FilesPanel
     */
    public FilesPanel() {
        String fileDir = null;
        DefaultListModel model1 = new DefaultListModel();
        
        initComponents();

        try {
            fileDir = props.getProperty("FILES_DIR");
        } catch(Exception ex)   {
            //property not set, default to user home
            fileDir = System.getProperty("user.dir");
        }
        File o = new File(fileDir);

        File[] yourFileList = o.listFiles();
        for(File f : yourFileList) {
            model1.addElement(f.getName());
        }
        filesList.setModel(model1);
        filesList.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileTopPnl = new javax.swing.JPanel();
        bckArrow = new javax.swing.JLabel();
        topFilePnlLbl = new javax.swing.JLabel();
        topFilePnlMenu = new javax.swing.JLabel();
        fileSidePnl = new javax.swing.JPanel();
        filePnlDownArrow = new javax.swing.JLabel();
        filePnlUpArrow = new javax.swing.JLabel();
        fileListPnl = new javax.swing.JScrollPane();
        filesList = new javax.swing.JList<>();

        fileTopPnl.setBackground(new java.awt.Color(51, 51, 51));
        fileTopPnl.setForeground(new java.awt.Color(255, 255, 255));

        bckArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/back.png"))); // NOI18N
        bckArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bckArrowMouseClicked(evt);
            }
        });

        topFilePnlLbl.setBackground(new java.awt.Color(51, 51, 51));
        topFilePnlLbl.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        topFilePnlLbl.setForeground(new java.awt.Color(255, 255, 255));
        topFilePnlLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topFilePnlLbl.setText("Local");
        topFilePnlLbl.setFocusable(false);

        topFilePnlMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topFilePnlMenu.setIcon(new javax.swing.ImageIcon("E:\\markt\\Documents\\NetBeansProjects\\CommunityOS\\Robo Gui\\Media\\Files_Icons\\Hamburger_lines.png")); // NOI18N
        topFilePnlMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                topFilePnlMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fileTopPnlLayout = new javax.swing.GroupLayout(fileTopPnl);
        fileTopPnl.setLayout(fileTopPnlLayout);
        fileTopPnlLayout.setHorizontalGroup(
            fileTopPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileTopPnlLayout.createSequentialGroup()
                .addComponent(bckArrow, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topFilePnlLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topFilePnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        fileTopPnlLayout.setVerticalGroup(
            fileTopPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bckArrow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(topFilePnlLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(topFilePnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
        );

        filePnlDownArrow.setBackground(new java.awt.Color(51, 51, 51));
        filePnlDownArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/Down.png"))); // NOI18N
        filePnlDownArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filePnlDownArrowMouseClicked(evt);
            }
        });

        filePnlUpArrow.setBackground(new java.awt.Color(51, 51, 51));
        filePnlUpArrow.setForeground(new java.awt.Color(51, 51, 51));
        filePnlUpArrow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filePnlUpArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/robo/gui/Up.png"))); // NOI18N
        filePnlUpArrow.setToolTipText("");
        filePnlUpArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filePnlUpArrowMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fileSidePnlLayout = new javax.swing.GroupLayout(fileSidePnl);
        fileSidePnl.setLayout(fileSidePnlLayout);
        fileSidePnlLayout.setHorizontalGroup(
            fileSidePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filePnlDownArrow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
            .addGroup(fileSidePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(filePnlUpArrow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
        );
        fileSidePnlLayout.setVerticalGroup(
            fileSidePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fileSidePnlLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(filePnlDownArrow, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(fileSidePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(fileSidePnlLayout.createSequentialGroup()
                    .addComponent(filePnlUpArrow, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 126, Short.MAX_VALUE)))
        );

        filesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        filesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filesListMouseClicked(evt);
            }
        });
        fileListPnl.setViewportView(filesList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileTopPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fileListPnl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileSidePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fileTopPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fileSidePnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fileListPnl, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bckArrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bckArrowMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bckArrowMouseClicked

    private void topFilePnlMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topFilePnlMenuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_topFilePnlMenuMouseClicked

    private void filePnlUpArrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filePnlUpArrowMouseClicked
        int selItem = filesList.getSelectedIndex();
        
        if (selItem > 0)  {
            filesList.setSelectedIndex(selItem - 1);
        }
    }//GEN-LAST:event_filePnlUpArrowMouseClicked

    private void filePnlDownArrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filePnlDownArrowMouseClicked
        int selItem = filesList.getSelectedIndex();
        
        if (selItem < filesList.getModel().getSize())  {
            filesList.setSelectedIndex(selItem + 1);
        }
    }//GEN-LAST:event_filePnlDownArrowMouseClicked

    private void filesListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filesListMouseClicked
        String fileName = filesList.getSelectedValue();
        
        pFrame.getContentPane().removeAll();

        pFrame.getContentPane().add(mainPnl);        
        pFrame.validate();
        pFrame.setVisible(true);
        mainPnl.setPrinterActive();
        if (mainPnl.getDoPrinting() == null)    {
            printer.startPrintRun(fileName);
        }
//         new Thread(r).start();
//         //this line will execute immediately, not waiting for your task to complete
    }//GEN-LAST:event_filesListMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bckArrow;
    private javax.swing.JScrollPane fileListPnl;
    private javax.swing.JLabel filePnlDownArrow;
    private javax.swing.JLabel filePnlUpArrow;
    private javax.swing.JPanel fileSidePnl;
    private javax.swing.JPanel fileTopPnl;
    private javax.swing.JList<String> filesList;
    private javax.swing.JLabel topFilePnlLbl;
    private javax.swing.JLabel topFilePnlMenu;
    // End of variables declaration//GEN-END:variables
}
