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
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * this class will implement all of the physical control options
 * for the printer. Interfaces to the printer itself (via GCode)
 * and the OctoPi instance (via the API) as well. 
 * 
 * @author markt
 */
public class PrinterControl {
    //member vars
    private Properties props;
    private JFrame pFrame;
    private Main_Panel mainPnl;
    private RunPrint doPrinting = null;

    public void setDoPrinting(RunPrint doPrinting) {
        this.doPrinting = doPrinting;
        mainPnl.setPrinterProc(doPrinting);
    }

    public void setParent(JFrame mFrame, Main_Panel mPanel) {
        this.pFrame = mFrame;
        mainPnl = mPanel;
    }
    
    public void setProps(Properties props) {
        this.props = props;
    }

    //simple constructor
    public void PrinterControl()    {
        
    }
    
    public void setPrintProgress(String text)
    {
        if(!text.isEmpty())   {
            mainPnl.setStatus(text);           
        }
    }
    
    public void setPrintProgress(String text, int percent)
    {
        if((percent >= 0) && (percent <= 100))   {
            mainPnl.setStatus(text, percent);
        }
    }

    public void filesMenu()   {
        if(pFrame.getContentPane() != null) {
            pFrame.getContentPane().removeAll();
        }

        FilesPanel filePanel = new FilesPanel();
        filePanel.setProps(props);
        filePanel.setMain(pFrame, mainPnl);
        pFrame.getContentPane().add(filePanel);        
        filePanel.setPrinter(this);
        pFrame.validate();
        pFrame.setVisible(true);
    }
    
    public void startPrintRun(String fileName)  {
        
        if(doPrinting != null)  {
            //print job still running...
            
        } else  {
            doPrinting = new RunPrint(fileName);
            doPrinting.setParent(this);
            doPrinting.start();
            mainPnl.setPrinterProc(doPrinting);
        }
        
    }
}
