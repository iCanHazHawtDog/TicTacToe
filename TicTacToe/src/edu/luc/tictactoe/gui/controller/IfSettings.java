/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IfSettings.java
 *
 * Created on Oct 10, 2011, 6:36:34 PM
 */
package edu.luc.tictactoe.gui.controller;

/**
 *
 * @author Paul Stasiuk
 * 
 * Not used at the moment.
 * 
 * 
 */
public class IfSettings extends javax.swing.JFrame {

    /** Creates new form IfSettings */
    public IfSettings() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        settingsLabel = new javax.swing.JLabel();
        changeSoundsBtn = new javax.swing.JButton();
        playerIconBtn = new javax.swing.JButton();
        mainMenuBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.luc.tictactoe.gui.controller.TicTacToeUIApp.class).getContext().getResourceMap(IfSettings.class);
        settingsLabel.setText(resourceMap.getString("settingsLabel.text")); // NOI18N
        settingsLabel.setName("settingsLabel"); // NOI18N

        changeSoundsBtn.setText(resourceMap.getString("changeSoundsBtn.text")); // NOI18N
        changeSoundsBtn.setName("changeSoundsBtn"); // NOI18N
        changeSoundsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                changeSoundsBtnMouseReleased(evt);
            }
        });

        playerIconBtn.setText(resourceMap.getString("playerIconBtn.text")); // NOI18N
        playerIconBtn.setName("playerIconBtn"); // NOI18N
        playerIconBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playerIconBtnMouseReleased(evt);
            }
        });

        mainMenuBtn.setText(resourceMap.getString("mainMenuBtn.text")); // NOI18N
        mainMenuBtn.setName("mainMenuBtn"); // NOI18N
        mainMenuBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mainMenuBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(changeSoundsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playerIconBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mainMenuBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(settingsLabel)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(settingsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(changeSoundsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playerIconBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainMenuBtn)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void changeSoundsBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeSoundsBtnMouseReleased
// TODO add your handling code here:
}//GEN-LAST:event_changeSoundsBtnMouseReleased

private void playerIconBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerIconBtnMouseReleased
// TODO add your handling code here:
}//GEN-LAST:event_playerIconBtnMouseReleased

private void mainMenuBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuBtnMouseReleased
    //If the user wants to go back to the main menu, this button is selected.
    this.dispose();
    TicTacToeUIApp.getApplication().getMainFrame().setVisible(true);
    
}//GEN-LAST:event_mainMenuBtnMouseReleased

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
            java.util.logging.Logger.getLogger(IfSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IfSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IfSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IfSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new IfSettings().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeSoundsBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton mainMenuBtn;
    private javax.swing.JButton playerIconBtn;
    private javax.swing.JLabel settingsLabel;
    // End of variables declaration//GEN-END:variables
}
