
package Vista;

import Clases.Cls_Laboratorio;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

public class Frm_Laboratorio extends javax.swing.JInternalFrame {
    private final Cls_Laboratorio CP;
    TableColumnModel columnModel;
    int num = 0;

    public Frm_Laboratorio() {
        initComponents();
        CP = new Cls_Laboratorio();
        columnModel = tabla_laboratorio.getColumnModel();
        listar();
        iniciar();
        bt_actualizar.setEnabled(false);
        bt_eliminar.setEnabled(false);
        bt_guardar.setEnabled(false);
    }
    
    private void listar(){
        tabla_laboratorio.setModel(CP.getDatosLaboratorio());
        columnModel.getColumn(1).setPreferredWidth(600);
    }
    
    private void iniciar(){
        txt_codigo.setEnabled(false);
        txt_correo.setEnabled(false);
        txt_direccion.setEnabled(false);
        txt_nombre.setEnabled(false);        
    }
    
    private void activar(){
        txt_codigo.setEnabled(true);
        txt_correo.setEnabled(true);
        txt_direccion.setEnabled(true);
        txt_nombre.setEnabled(true);
        txt_codigo.requestFocus();
    }
    
    private void limpiar(){
        txt_codigo.setText("");
        txt_correo.setText("");
        txt_nombre.setText("");
        txt_direccion.setText("");
        txt_direccion.requestFocus();
        tabla_laboratorio.clearSelection();
    }
    
    private void guardar(){
        String codigo = txt_codigo.getText();
        String nombre = txt_nombre.getText();
        String direccion = txt_direccion.getText();
        String correo = txt_correo.getText();

        if(num == 0){
            CP.registrarLaboratorio(codigo,nombre,direccion,correo);  
            listar();
            limpiar();
            iniciar();
            bt_actualizar.setEnabled(false);
        }else{
            int row = tabla_laboratorio.getSelectedRow();
            String codigo_old = tabla_laboratorio.getValueAt(row, 0).toString();
            
            int respuesta = CP.actualizarLaboratorio(codigo,nombre,direccion,correo,codigo_old);
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

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_correo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_laboratorio = new javax.swing.JTable();
        bt_nuevo = new javax.swing.JButton();
        bt_guardar = new javax.swing.JButton();
        bt_actualizar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dubai Medium", 1, 24)); // NOI18N
        jLabel2.setText("Registro de Laboratorios");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 18, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dubai Light", 0, 14)); // NOI18N
        jLabel1.setText("Código del Laboratorio*");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 93, -1, -1));

        txt_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codigoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 169, 35));

        jLabel3.setFont(new java.awt.Font("Dubai Light", 2, 14)); // NOI18N
        jLabel3.setText("Llene la información respectiva de los laboratorios.");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dubai Light", 0, 14)); // NOI18N
        jLabel5.setText("Nombre*");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        txt_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_direccionActionPerformed(evt);
            }
        });
        getContentPane().add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 169, 35));

        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 169, 35));

        txt_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_correoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 169, 35));

        jLabel6.setFont(new java.awt.Font("Dubai Light", 0, 14)); // NOI18N
        jLabel6.setText("Dirección *");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dubai Light", 0, 14)); // NOI18N
        jLabel7.setText("Correo*");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, -1, -1));

        tabla_laboratorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_laboratorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_laboratorioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_laboratorio);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 780, 220));

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
        getContentPane().add(bt_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, -1));

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
        getContentPane().add(bt_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, -1, -1));

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
        getContentPane().add(bt_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, -1, -1));

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
        getContentPane().add(bt_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 450, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoActionPerformed
  
    }//GEN-LAST:event_txt_codigoActionPerformed

    private void txt_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_direccionActionPerformed

    }//GEN-LAST:event_txt_direccionActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed

    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_correoActionPerformed

    }//GEN-LAST:event_txt_correoActionPerformed

    private void tabla_laboratorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_laboratorioMouseClicked
        bt_actualizar.setEnabled(true);
        bt_eliminar.setEnabled(true);

        int row = tabla_laboratorio.getSelectedRow();
        txt_codigo.setText(tabla_laboratorio.getValueAt(row, 0).toString());
        txt_nombre.setText(tabla_laboratorio.getValueAt(row, 1).toString());
        txt_direccion.setText(tabla_laboratorio.getValueAt(row, 2).toString());
        txt_correo.setText(tabla_laboratorio.getValueAt(row, 3).toString());
        
    }//GEN-LAST:event_tabla_laboratorioMouseClicked

    private void bt_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_nuevoActionPerformed
        activar();
        limpiar();
        bt_guardar.setEnabled(true);
    }//GEN-LAST:event_bt_nuevoActionPerformed

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardarActionPerformed
        guardar();

    }//GEN-LAST:event_bt_guardarActionPerformed

    private void bt_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_actualizarActionPerformed
        num = 1;
        activar();
        bt_actualizar.setEnabled(false);
        bt_guardar.setEnabled(true);
        bt_eliminar.setEnabled(false);
    }//GEN-LAST:event_bt_actualizarActionPerformed

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        int fila = tabla_laboratorio.getSelectedRowCount();
        if (fila < 1){
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla");
        }
        else{
            int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar?","Eliminar Producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(resp==0){
                if (CP.eliminarLaboratorio(tabla_laboratorio.getValueAt(tabla_laboratorio.getSelectedRow(), 0).toString()) > 0){
                    listar();
                    limpiar();
                    bt_eliminar.setEnabled(false);
                    bt_actualizar.setEnabled(false);
                    bt_guardar.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_actualizar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JButton bt_nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_laboratorio;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
