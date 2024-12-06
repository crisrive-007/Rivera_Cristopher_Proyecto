/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ghostgame;

import javax.swing.JOptionPane;

/**
 *
 * @author river
 */
public class reportes extends javax.swing.JFrame {

    /**
     * Creates new form reportes
     */
    public reportes() {
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

        ranking = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        historial = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(560, 240));

        ranking.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ranking.setText("Ranking");
        ranking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankingActionPerformed(evt);
            }
        });

        salir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        salir.setText("Volver al Inicio");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ghosts");

        historial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        historial.setText("Historial");
        historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ranking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(historial, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(historial)
                .addGap(18, 18, 18)
                .addComponent(ranking)
                .addGap(18, 18, 18)
                .addComponent(salir)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankingActionPerformed
        // TODO add your handling code here:
        GhostGame.ordenar_jugadores();
        JOptionPane.showMessageDialog(this, GhostGame.jugadores[0]+"\n"+GhostGame.jugadores[1]+"\n"+GhostGame.jugadores[2]+"\n"+GhostGame.jugadores[3]+"\n"+GhostGame.jugadores[4]+"\n"+GhostGame.jugadores[5]+"\n"+GhostGame.jugadores[6]+"\n"+GhostGame.jugadores[7]+"\n"+GhostGame.jugadores[8]+"\n"+GhostGame.jugadores[9]);
    }//GEN-LAST:event_rankingActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        menu_principal menu = new menu_principal();
        menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historialActionPerformed
        // TODO add your handling code here:
        String[] logs = GhostGame.juego.jugador1.getLogs();
        StringBuilder historial = new StringBuilder();
        for (String log : logs) {
            if (log != null) {
                historial.append(log).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, historial.toString());
    }//GEN-LAST:event_historialActionPerformed

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
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton historial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton ranking;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
