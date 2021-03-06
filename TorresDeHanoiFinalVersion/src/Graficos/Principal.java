/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import TorresDeHanoi.Juego;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1numd = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextField1nivelbi = new javax.swing.JTextField();
        jButton1GA = new javax.swing.JButton();
        jButton2GP = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Torres de Hanoi");

        jLabel3.setText("Inserte el numero de discos");

        jComboBox1numd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6", "7", "8" }));
        jComboBox1numd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1numdActionPerformed(evt);
            }
        });

        jLabel4.setText("Inserte el nivel de backtracking inverso");

        jTextField1nivelbi.setText("0");

        jButton1GA.setText("Generar Automaticamente");
        jButton1GA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1GAActionPerformed(evt);
            }
        });

        jButton2GP.setText("Generar por pasos");
        jButton2GP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2GPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1numd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField1nivelbi, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jButton1GA))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton2GP))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1numd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1nivelbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1GA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2GP)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1numdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1numdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1numdActionPerformed

    private void jButton1GAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1GAActionPerformed
        // TODO add your handling code here:
        try{
        int ndiscos= Integer.parseInt(jComboBox1numd.getSelectedItem().toString());
        int backinv= Integer.parseInt(jTextField1nivelbi.getText());
        int k=initmaxMovimientos(ndiscos);
        
        if(backinv<0||backinv>=k){
            JOptionPane.showMessageDialog(rootPane, "Error en el nivel de backtracking, introduce un valor positivo y que no sobrepase el limite");
        }else{
            Juego j=new Juego(ndiscos,backinv);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error al introducir el nivel de backtracking");
        }
    }//GEN-LAST:event_jButton1GAActionPerformed

    private void jButton2GPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2GPActionPerformed
        // TODO add your handling code here:
                try{
        int ndiscos= Integer.parseInt(jComboBox1numd.getSelectedItem().toString());
        int backinv= Integer.parseInt(jTextField1nivelbi.getText());
        int k=initmaxMovimientos(ndiscos);
        
        if(backinv<0||backinv>=k){
            JOptionPane.showMessageDialog(rootPane, "Error en el nivel de backtracking, introduce un valor positivo y que no sobrepase el limite");
        }else{
            GeneradorPasos gp=new GeneradorPasos(ndiscos,backinv);
            gp.setVisible(true);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error al introducir el nivel de backtracking");
        }
    }//GEN-LAST:event_jButton2GPActionPerformed
    private int initmaxMovimientos(int numdiscos) {
        int max = 1;
        for (int i = 0; i < numdiscos; i++) {
            max = max * 2;
        }
        max = max - 1;
        return max;
        //System.out.print(max);//
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1GA;
    private javax.swing.JButton jButton2GP;
    private javax.swing.JComboBox<String> jComboBox1numd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1nivelbi;
    // End of variables declaration//GEN-END:variables
}
