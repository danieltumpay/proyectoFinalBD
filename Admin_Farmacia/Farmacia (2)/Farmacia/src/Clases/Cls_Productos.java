
package Clases;

import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class Cls_Productos {
   // private PreparedStatement PS;
     private CallableStatement PS;
    private ResultSet RS;
    private final Conexion CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_PRODUCTOS = "INSERT INTO producto (pro_codigo,pro_descripcion,pro_precio, lab_id, accionID) values (?,?,?,?,?)";
    private final String SQL_SELECT_PRODUCTOS = "SELECT *FROM producto";
    
    public Cls_Productos(){
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
        DT.addColumn("Precio");
        DT.addColumn("Laboratorio");
        DT.addColumn("Accion");        
        return DT;
    }
    
    public DefaultTableModel getDatosProductos(){
        try {
            setTitulosProductos();
                PS = CN.getConnection().prepareCall("{?=call listarProducto}");
           PS.registerOutParameter(1, OracleTypes.CURSOR);         
            RS = PS.executeQuery();
            RS=((OracleCallableStatement)PS).getCursor(1);
            Object[] fila = new Object[5];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getFloat(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos."+e.getMessage());
        } finally{
            PS = null;
            RS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }
    
    public int registrarProducto(String codigo, String descripcion, String precio, String laboratorio, String accion){
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("{call add_produc(?,?,?,?,?,?)}");
            PS.setString(1, codigo);
            PS.setString(2, descripcion);
            PS.setFloat(3, Float.valueOf(precio));
            PS.setString(4, laboratorio);
            PS.setString(5, accion);
            PS.registerOutParameter(6, Types.VARCHAR);
            PS.executeQuery();   
            String dat=PS.getString(6);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null,dat);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el producto.");
            System.err.println("Error al registrar el producto." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public void insertarProductoInventario(String codigoProducto, String des){
        int res;
        try {
            PS = CN.getConnection().prepareCall("{call add_inv(?,?)}");
            PS.setString(1, codigoProducto);
            PS.setString(2, des);
            PS.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al insertar registro en la tabla inventario." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
    }
    
    public int verificarCodigoInventario(String codigo){
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("SELECT count(inv_pro_codigo) from inventario where inv_pro_codigo='"+codigo+"'");
            RS = PS.executeQuery();
            
            while(RS.next()){
                res = RS.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al devolver cantidad de registros." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    
    public int actualizarProducto(String codigo, String descripcion, String precio, String laboratorio, String accion, String codigo_old){
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("{call modificar_producto(?,?,?,?,?,?)}");
            PS.setString(1, codigo_old);
            PS.setString(2, descripcion);
            PS.setString(3,precio);
            PS.setString(4,laboratorio);
            PS.setString(5, accion);
            PS.registerOutParameter(6, Types.VARCHAR);
            PS.executeQuery();   
            String dat=PS.getString(6);
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
    
    public int eliminarProducto(String codigo){
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("{call eliminar_producto(?,?)}");
            PS.setString(1,codigo);
            System.out.println(codigo);
            PS.registerOutParameter(2, Types.VARCHAR);
            PS.executeQuery();   
            String dat=PS.getString(2);
            System.out.println(dat);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, dat);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible eliminar el producto.");
            System.err.println("Error al eliminar producto." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
}