
package Clases;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class almacenes {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conexion CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_ALMACENES = "INSERT INTO almacenes (alm_codigo,alm_nombre) values (?,?)";
    private final String SQL_SELECT_ALMACENES = "SELECT *FROM almacenes";
    
    public almacenes(){
        PS = null;
        CN = new Conexion();
    }
    
    private DefaultTableModel setTitulosAlmacenes(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("Código");
        DT.addColumn("nombre");
        return DT;
    }
    
    public DefaultTableModel getDatosAlamcenes(){
        try {
            setTitulosAlmacenes();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_ALMACENES);
            RS = PS.executeQuery();
            Object[] fila = new Object[3];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
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
    
    public int registrarALmacen(String codigo, String nombre ){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_ALMACENES);
            PS.setString(1, codigo);
            PS.setString(2, nombre);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Producto registrado con éxito.");
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
    /*
    public void insertarProductoInventario(String codigoAlmacen){
        int res;
        try {
            PS = CN.getConnection().prepareStatement("CALL NUEVO_PRODUCTO('"+codigoAlmacen+"')");
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
            PS = CN.getConnection().prepareStatement("SELECT count(inv_pro_codigo) from inventario where inv_pro_codigo='"+codigo+"'");
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
    }*/
    
    
    public int actualizarAlamcen(String codigo, String nombre, String codigo_old){
        String SQL = "UPDATE almacenes SET alm_codigo='"+codigo+"',alm_nombre='"+nombre+"' WHERE alm_codigo='"+codigo_old+"'";
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "actualizado con éxito");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public int eliminarProducto(String codigo){
        String SQL = "DELETE from alamcenes WHERE alm_codigo ='"+codigo+"'";
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "eliminado con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible eliminar.");
            System.err.println("Error al eliminar." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
}