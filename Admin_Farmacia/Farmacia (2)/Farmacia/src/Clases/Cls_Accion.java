package Clases;

import Conexion.Conexion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class Cls_Accion {
    
    private CallableStatement PS;
    private ResultSet RS;
    private final Conexion CN;
    private DefaultTableModel DT;
    
    public Cls_Accion(){
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
	PS = CN.getConnection().prepareCall("{?=call listarAccion}");
            PS.registerOutParameter(1, OracleTypes.CURSOR);         
            RS = PS.executeQuery();
            RS=((OracleCallableStatement)PS).getCursor(1);
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
    
    public void registrarAccion(String codigo, String nombre, String descripcion){
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("{call add_accion(?,?,?,?)}");
            PS.setString(1, codigo);
            PS.setString(2, nombre);
            PS.setString(3, descripcion);
	PS.registerOutParameter(4, Types.VARCHAR);
            PS.executeQuery(); 
            String dat=PS.getString(4);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, dat);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la Accion.");
            System.err.println("Error al registrar Accion." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
    }   
    
    public int actualizarAccion(String codigo, String nombre, String descripcion, String codigo_old){
   
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("{call modificar_laboratorio(?,?,?,?)}");
	PS.setString(1, codigo_old);
            PS.setString(2, nombre);
            PS.setString(3,descripcion);
            PS.registerOutParameter(4, Types.VARCHAR);
            PS.executeQuery();   
            String dat=PS.getString(4);
            res = PS.executeUpdate();
            if(res > 0){
                    JOptionPane.showMessageDialog(null, dat);
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public int eliminarAccion(String codigo){
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("{call eliminar_accion(?,?)}");
	PS.setString(1,codigo);
            PS.registerOutParameter(2, Types.VARCHAR);
            PS.executeQuery();   
            String dat=PS.getString(2);
            res = PS.executeUpdate();
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Accion eliminada con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible eliminar la Accion.");
            System.err.println("Error al eliminar." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
}