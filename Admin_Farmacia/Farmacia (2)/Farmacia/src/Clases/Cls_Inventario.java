package Clases;

import Conexion.Conexion;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class Cls_Inventario {
    
    private CallableStatement PS;
    private ResultSet RS;
    private final Conexion CN;
    private DefaultTableModel DT;
    
    
    public Cls_Inventario(){
        PS = null;
        CN = new Conexion();
    }
    
    private DefaultTableModel setTitulosInventario(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("Código");
        DT.addColumn("Descripción");
        DT.addColumn("Entrada");
        DT.addColumn("Salida");
        DT.addColumn("Stock");
        return DT;
    }
    
    public DefaultTableModel getDatosInventario(){
        try {
            setTitulosInventario();
            PS = CN.getConnection().prepareCall("{?=call listarInventario}");
	PS.registerOutParameter(1, OracleTypes.CURSOR);    
            RS = PS.executeQuery();
	RS=((OracleCallableStatement)PS).getCursor(1);
            Object[] fila = new Object[5];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getInt(4);
                fila[4] = RS.getInt(5);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos."+e.getMessage());
        } finally{
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }
}