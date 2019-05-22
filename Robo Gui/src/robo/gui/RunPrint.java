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

/**
 *
 * @author markt
 */
public class RunPrint extends Thread{

    private String fileToPrint;
    private File filePrinting;
    
    /**
     *
     * @param printFile
     */
    public RunPrint(String printFile)
    {
        super(printFile);
        fileToPrint = printFile;
    }
    public void run() {
        int i = 0;
        
        try
        {
            System.out.println("background Print thread task started");
            while (i < 120) {
                Thread.sleep(2500);
                System.out.println("tick-tock...: printing " + fileToPrint);
                i++;
            }
        }
        catch (Exception ex)    {
            System.out.println("background Print thread exception: ");
            ex.printStackTrace();
        }
        finally
        {
            System.out.println("background Print thread task complete");
        }
    }

}

