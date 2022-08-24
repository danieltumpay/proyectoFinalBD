
package Clases;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Cls_BuscarAccion {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conexion CN;
    private DefaultTableModel DT;
    private final String SQL_SELECT_ACCION = "SELECT codigo, nombre, descripcion FROM accion";
    
    public Cls_BuscarAccion(){
        PS = null;
        CN = new Conexion();
    }
    
    private DefaultTableModel setTitulosAccion(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }            
        };
        DT.addColumn("Código");
        DT.addColumn("Nombre");
        DT.addColumn("Descripción");
        
        return DT;
    }
    
    public DefaultTableModel getDatosAccion(){
        try {
            setTitulosAccion();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_ACCION);
            RS = PS.executeQuery();
            Object[] fila = new Object[4];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
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
    
    public DefaultTableModel getDatoP(int crt, String inf){
        String SQL;
        if (crt==2){
            SQL = "SELECT codigo, nombre, descripcion FROM accion WHERE codigo like '"+inf+"'";
        }
        else {
            SQL = "SELECT codigo, nombre, descripcion FROM accion WHERE descripcion like '"+inf+"'";
        }
        try {
            setTitulosAccion();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[4];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
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
