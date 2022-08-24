package Vista;

import Clases.Cls_Productos;
import static Vista.Frm_Principal.contenedor;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

public class Frm_Productos extends javax.swing.JInternalFrame {
    private final Cls_Productos CP;
    TableColumnModel columnModel;
    public static int enviar = 0;
    int num = 0;
    
    public Frm_Productos() {
        initComponents();
        CP = new Cls_Productos();
        columnModel = jtb_productos.getColumnModel();
        listar();
        iniciar();
        bt_actualizar.setEnabled(false);
        bt_eliminar.setEnabled(false);
        bt_guardar.setEnabled(false);
    }

    private void listar(){
        jtb_productos.setModel(CP.getDatosProductos());
        columnModel.getColumn(1).setPreferredWidth(600);
    }
    
    private void iniciar(){
        txt_codigo.setEnabled(false);
        txt_descripcion.setEnabled(false);
        txt_Precio.setEnabled(false);
        
    }
    
    private void activar(){
        txt_codigo.setEnabled(true);
        txt_descripcion.setEnabled(true);
        txt_Precio.setEnabled(true);
        txt_codigo.requestFocus();
    }
    
    private void limpiar(){
        txt_codigo.setText("");
        txt_descripcion.setText("");
        txt_Precio.setText("");
        txt_codigo.requestFocus();
        jtb_productos.clearSelection();
    }
    
    private void guardar(){
        String codigo = txt_codigo.getText();
        String descripcion = txt_descripcion.getText();
        String precio = txt_Precio.getText();
        String laboratorio = txt_codigoL.getText();
        String accion = txt_codigoA.getText();

        if(num == 0){
            int respuesta = CP.registrarProducto(codigo,descripcion,precio,laboratorio,accion);
            if(respuesta > 0){
                if(CP.verificarCodigoInventario(codigo) == 0){
                    CP.insertarProductoInventario(codigo,descripcion);
                }                
                listar();
                limpiar();
                iniciar();
                bt_actualizar.setEnabled(false);
            }
        }else{
            int row = jtb_productos.getSelectedRow();
            String codigo_old = jtb_productos.getValueAt(row, 0).toString();
            
            int respuesta = CP.actualizarProducto(codigo, descripcion,precio,laboratorio,accion,codigo_old);
            if(respuesta >0){
                listar();
                limpiar();
                iniciar();
                num=0;
            }
        }       
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_productos = new javax.swing.JTable();
        bt_guardar = new javax.swing.JButton();
        bt_actualizar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_nuevo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_Precio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_codigoL = new javax.swing.JTextField();
        jbt_buscar = new javax.swing.JButton();
        txt_codigoA = new javax.swing.JTextField();
        jbt_buscar1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Productos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dubai Light", 0, 14)); // NOI18N
        jLabel1.setText("Código de Producto *");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 91, -1, -1));

        txt_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codigoActionPerformed(evt);
            }
        });
        jPanel1.add(txt_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 116, 169, 35));

        jtb_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_productosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtb_productos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 169, 804, 238));

        bt_guardar.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        bt_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_grabar.png"))); // NOI18N
        bt_guardar.setText("Guardar");
        bt_guardar.setBorderPainted(false);
        bt_guardar.setContentAreaFilled(false);
        bt_guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });
        jPanel1.add(bt_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 425, -1, -1));

        bt_actualizar.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        bt_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_modificar.png"))); // NOI18N
        bt_actualizar.setText("Modificar");
        bt_actualizar.setBorderPainted(false);
        bt_actualizar.setContentAreaFilled(false);
        bt_actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_actualizarActionPerformed(evt);
            }
        });
        jPanel1.add(bt_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 425, -1, -1));

        bt_eliminar.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        bt_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_eliminar.png"))); // NOI18N
        bt_eliminar.setText("Eliminar");
        bt_eliminar.setBorderPainted(false);
        bt_eliminar.setContentAreaFilled(false);
        bt_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(bt_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(682, 425, -1, -1));

        bt_nuevo.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        bt_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_nuevo.png"))); // NOI18N
        bt_nuevo.setText("Nuevo");
        bt_nuevo.setBorderPainted(false);
        bt_nuevo.setContentAreaFilled(false);
        bt_nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_nuevoActionPerformed(evt);
            }
        });
        jPanel1.add(bt_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 425, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dubai Light", 0, 14)); // NOI18N
        jLabel5.setText("Nombre *");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 91, -1, -1));
        jPanel1.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 116, 182, 35));

        jLabel2.setFont(new java.awt.Font("Dubai Medium", 1, 24)); // NOI18N
        jLabel2.setText("Registro de Productos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 28, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dubai Light", 2, 14)); // NOI18N
        jLabel3.setText("Llene la información respectiva de los productos.");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 66, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dubai Light", 0, 14)); // NOI18N
        jLabel6.setText("Precio *");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 91, -1, -1));
        jPanel1.add(txt_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 116, 100, 35));

        jLabel4.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        jLabel4.setText("Código del Laboratorio *");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 91, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        jLabel7.setText("Código de la Accion");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(782, 91, -1, -1));

        txt_codigoL.setEditable(false);
        jPanel1.add(txt_codigoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 120, 30));

        jbt_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_consultas.png"))); // NOI18N
        jbt_buscar.setBorderPainted(false);
        jbt_buscar.setContentAreaFilled(false);
        jbt_buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_buscarActionPerformed(evt);
            }
        });
        jPanel1.add(jbt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        txt_codigoA.setEditable(false);
        jPanel1.add(txt_codigoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 120, 120, 30));

        jbt_buscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_consultas.png"))); // NOI18N
        jbt_buscar1.setBorderPainted(false);
        jbt_buscar1.setContentAreaFilled(false);
        jbt_buscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbt_buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_buscar1ActionPerformed(evt);
            }
        });
        jPanel1.add(jbt_buscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 120, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardarActionPerformed
        guardar();
        
    }//GEN-LAST:event_bt_guardarActionPerformed

    private void jtb_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_productosMouseClicked
        bt_actualizar.setEnabled(true);
        bt_eliminar.setEnabled(true);
        
        int row = jtb_productos.getSelectedRow(); 
        txt_codigo.setText(jtb_productos.getValueAt(row, 0).toString());
        txt_descripcion.setText(jtb_productos.getValueAt(row, 1).toString());
    }//GEN-LAST:event_jtb_productosMouseClicked

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        int fila = jtb_productos.getSelectedRowCount();
        if (fila < 1){
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla");
        }
        else{
            int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar?","Eliminar Producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(resp==0){
                if (CP.eliminarProducto(jtb_productos.getValueAt(jtb_productos.getSelectedRow(), 0).toString()) > 0){
                    listar();
                    limpiar();
                    bt_eliminar.setEnabled(false);
                    bt_actualizar.setEnabled(false);
                    bt_guardar.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void bt_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_nuevoActionPerformed
        activar();
        limpiar();
        bt_guardar.setEnabled(true);
    }//GEN-LAST:event_bt_nuevoActionPerformed

    private void bt_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_actualizarActionPerformed
        num = 1;
        activar();
        bt_actualizar.setEnabled(false);
        bt_guardar.setEnabled(true);
        bt_eliminar.setEnabled(false);
    }//GEN-LAST:event_bt_actualizarActionPerformed

    private void txt_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigoActionPerformed

    private void jbt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_buscarActionPerformed
        enviar = 1;

        Frm_BuscarLaboratorio l = new Frm_BuscarLaboratorio();
        Frm_Principal.contenedor.add(l);
        Dimension desktopSize = contenedor.getSize();
        Dimension FrameSize = l.getSize();
        l.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        l.toFront();
        l.setVisible(true);
    }//GEN-LAST:event_jbt_buscarActionPerformed

    private void jbt_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_buscar1ActionPerformed
        enviar = 1;

        Frm_BuscarAccion a = new Frm_BuscarAccion();
        Frm_Principal.contenedor.add(a);
        Dimension desktopSize = contenedor.getSize();
        Dimension FrameSize = a.getSize();
        a.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        a.toFront();
        a.setVisible(true);
    }//GEN-LAST:event_jbt_buscar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_actualizar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JButton bt_nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbt_buscar;
    private javax.swing.JButton jbt_buscar1;
    private javax.swing.JTable jtb_productos;
    private javax.swing.JTextField txt_Precio;
    private javax.swing.JTextField txt_codigo;
    public static javax.swing.JTextField txt_codigoA;
    public static javax.swing.JTextField txt_codigoL;
    private javax.swing.JTextField txt_descripcion;
    // End of variables declaration//GEN-END:variables
}
