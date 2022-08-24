
package Clases;

import Conexion.Conexion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
public class Cls_Laboratorio {
    
    private CallableStatement PS;
    private ResultSet RS;
    private final Conexion CN;
    private DefaultTableModel DT;
    public Cls_Laboratorio(){
        PS = null;
        CN = new Conexion();
    }
    
    private DefaultTableModel setTitulosProductos(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("Código");
        DT.addColumn("Nombre");
        DT.addColumn("Correo");
        DT.addColumn("Telefono");
        return DT;
    }
    
    public DefaultTableModel getDatosLaboratorio(){
        try {
            setTitulosProductos();
            PS = CN.getConnection().prepareCall("{?=call listarLaboratorio}");
            PS.registerOutParameter(1, OracleTypes.CURSOR);         
            RS = PS.executeQuery();
            RS=((OracleCallableStatement)PS).getCursor(1);
            Object[] fila = new Object[4];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
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
    
    public void registrarLaboratorio(String codigo, String nombre, String direccion, String correo){
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("{call add_laboratorio(?,?,?,?,?)}");
            PS.setString(1, codigo);
            PS.setString(2, nombre);
            PS.setString(3, direccion);
            PS.setString(4, correo);
            PS.registerOutParameter(5, Types.VARCHAR);
            PS.executeQuery(); 
             String dat=PS.getString(5);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null,dat);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el Laboratorio.");
            System.err.println("Error al registrar el Laboratorio." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
    }   
    
    public int actualizarLaboratorio(String codigo, String nombre, String direccion, String correo, String codigo_old){
       
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("{call modificar_laboratorio(?,?,?,?,?)}");
            PS.setString(1, codigo_old);
            PS.setString(2, nombre);
            PS.setString(3,direccion);
            PS.setString(4,correo);
            PS.registerOutParameter(5, Types.VARCHAR);
            PS.executeQuery();   
            String dat=PS.getString(5);
            res = PS.executeUpdate();
            if(res > 0){
                    JOptionPane.showMessageDialog(null, dat);
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos del cliente." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public int eliminarLaboratorio(String codigo){
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("{call eliminar_laboratorio(?,?)}");
            PS.setString(1,codigo);
            PS.registerOutParameter(2, Types.VARCHAR);
            PS.executeQuery();   
            String dat=PS.getString(2);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, dat);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible eliminar el Laboratorio.");
            System.err.println("Error al eliminar Laboratorio." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
}
