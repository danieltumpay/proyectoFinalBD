
package Clases;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Cls_BuscarLaboratorio {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conexion CN;
    private DefaultTableModel DT;
    private final String SQL_SELECT_LABORATORIO = "SELECT lab_id,nombre,correo FROM laboratorio";
    
    public Cls_BuscarLaboratorio(){
        PS = null;
        CN = new Conexion();
    }
    
    private DefaultTableModel setTitulosLaboratorio(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }            
        };
        DT.addColumn("Código");
        DT.addColumn("Nombre");
        DT.addColumn("Correo");
        
        return DT;
    }
    
    public DefaultTableModel getDatosLaboratorio(){
        try {
            setTitulosLaboratorio();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_LABORATORIO);
            RS = PS.executeQuery();
            Object[] fila = new Object[3];
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
            SQL = "SELECT lab_id,nombre,correo FROM laboratorio WHERE lab_id like '"+inf+"'";
        }
        else {
            SQL = "SELECT lab_id,nombre,correo FROM laboratorio WHERE nombre like '"+inf+"'";
        }
        try {
            setTitulosLaboratorio();
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
