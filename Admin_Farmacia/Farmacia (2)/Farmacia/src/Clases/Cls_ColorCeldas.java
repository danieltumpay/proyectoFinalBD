package Clases;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;


public class Cls_ColorCeldas extends DefaultTableCellRenderer {
    

@Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col){
 
super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);


    if (table.getValueAt(row,4).getClass().equals(Integer.class) ) {
        
        int numero = Integer.parseInt(table.getValueAt(row, 4).toString());
        if(numero < 10){
            setBackground(Color.PINK);
        }else{
         setBackground(Color.WHITE);
        } 
    } 

 return this;

}

}
