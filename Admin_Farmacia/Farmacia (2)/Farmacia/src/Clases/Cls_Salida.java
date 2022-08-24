package Clases;

import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class Cls_Salida {
    private CallableStatement PS;
    private ResultSet RS;
    private final Conexion CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_SALIDA = "INSERT INTO salida (sal_factura, sal_pro_codigo, sal_fecha, sal_cantidad) values (?,?,?,?)";
    private final String SQL_SELECT_SALIDA = "SELECT sal_factura, sal_fecha, sal_pro_codigo, pro_descripcion, sal_cantidad FROM salida INNER JOIN producto ON sal_pro_codigo = pro_codigo";
    
    public Cls_Salida(){
        PS = null;
        CN = new Conexion();
    }
    
    private DefaultTableModel setTitulosSalida(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("N° de Factura");
        DT.addColumn("Fecha");
        DT.addColumn("Código de Producto");
        DT.addColumn("Descripción");
        DT.addColumn("Cantidad");
        return DT;
    }
    
    public DefaultTableModel getDatosSalida(){
        try {
            setTitulosSalida();
            PS = CN.getConnection().prepareCall("{?=call listarSalida}");
            PS.registerOutParameter(1, OracleTypes.CURSOR);         
            RS = PS.executeQuery();
            RS=((OracleCallableStatement)PS).getCursor(1);
            Object[] fila = new Object[5];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getDate(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
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
    
    public int registrarSalida(String nfactura, String codigo, Date fecha, int cantidad,String desc){
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("{call add_salida(?,?,?,?,?,?)}");
            PS.setString(1, nfactura);
            PS.setString(2, codigo);
            PS.setDate(3, fecha);
            PS.setInt(4, cantidad);
            PS.setString(5, desc);
            PS.registerOutParameter(6, Types.VARCHAR);
             PS.executeQuery();
              String dat=PS.getString(6);
            res = PS.executeUpdate();
            
            if(res > 0){
                JOptionPane.showMessageDialog(null, dat);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la salida.");
            System.err.println("Error al registrar la salida." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public int verificarStock(String codigo){
        int res=0;
        try {
            PS = CN.getConnection().prepareCall("SELECT inv_stock from inventario where inv_pro_codigo='"+codigo+"'");
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
}
