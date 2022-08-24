
package Clases;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;


public class masVendido {
    
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conexion CN;
    private DefaultTableModel DT;
    private final String SQL_SELECT_RANKING = "SELECT inv_pro_codigo, pro_descripcion, inv_salidas FROM inventario INNER JOIN producto ON inv_pro_codigo = pro_codigo ORDER BY inv_salidas DESC";

    public masVendido() {
        PS = null;
        CN = new Conexion();
    }
    
    private DefaultTableModel setTitulosRanking(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        DT.addColumn("Codigo");
        DT.addColumn("Nombre del producto");
        DT.addColumn("Cantidad vendida");
        
        return DT;
    }
    
    public DefaultTableModel getDatosRanking(){
        try {
            setTitulosRanking();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_RANKING);
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
    
}
