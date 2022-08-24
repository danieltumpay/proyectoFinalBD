
package Vista;

import Clases.Cls_BuscarAccion;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Frm_BuscarAccion extends javax.swing.JInternalFrame {
    private final Cls_BuscarAccion CP;
    TableColumnModel columnModel;
    
    DefaultTableModel DT = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    public Frm_BuscarAccion() {
        initComponents();
        CP = new Cls_BuscarAccion();
        columnModel = tabla.getColumnModel();
        listar();
    }
    
    private void listar(){
        tabla.setModel(CP.getDatosAccion());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jrb_codigo = new javax.swing.JRadioButton();
        jrb_nombre = new javax.swing.JRadioButton();
        txt_busqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Dubai Medium", 1, 12)); // NOI18N
        jLabel1.setText("Búsqueda por:");

        jrb_codigo.setBackground(new java.awt.Color(255, 255, 255));
        jrb_codigo.setFont(new java.awt.Font("Dubai", 0, 12)); // NOI18N
        jrb_codigo.setText("Código");
        jrb_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_codigoActionPerformed(evt);
            }
        });

        jrb_nombre.setBackground(new java.awt.Color(255, 255, 255));
        jrb_nombre.setFont(new java.awt.Font("Dubai", 0, 12)); // NOI18N
        jrb_nombre.setText("Descripción");
        jrb_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_nombreActionPerformed(evt);
            }
        });

        txt_busqueda.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_busquedaCaretUpdate(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrb_codigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrb_nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_busqueda)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jrb_nombre)
                    .addComponent(jrb_codigo)
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrb_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_codigoActionPerformed

    }//GEN-LAST:event_jrb_codigoActionPerformed

    private void jrb_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_nombreActionPerformed

    }//GEN-LAST:event_jrb_nombreActionPerformed

    private void txt_busquedaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_busquedaCaretUpdate
        if(jrb_nombre.isSelected()){
            String inf = txt_busqueda.getText();
            tabla.setModel(CP.getDatoP(1,inf));
        }

        if(jrb_codigo.isSelected()){
            String inf = txt_busqueda.getText();
            tabla.setModel(CP.getDatoP(2,inf));
        }

        if(txt_busqueda.getText().isEmpty()){
            tabla.setModel(CP.getDatosAccion());
        }
    }//GEN-LAST:event_txt_busquedaCaretUpdate

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if(Frm_Productos.enviar == 1){
            int row = tabla.getSelectedRow();
            Frm_Productos.txt_codigoA.setText(tabla.getValueAt(row, 0).toString());
            dispose();
        } 

    }//GEN-LAST:event_tablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrb_codigo;
    private javax.swing.JRadioButton jrb_nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txt_busqueda;
    // End of variables declaration//GEN-END:variables
}
