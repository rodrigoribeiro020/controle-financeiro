/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.usuario;

import controller.TransacaoController;
import controller.UsuarioController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Transacao;
import model.Usuario;
import view.ButtonColumn;
import view.RenderizadorTransacao;
import view.transacao.*;
import view.usuario.MeuEscritorio;
import view.usuario.TelaInicial;

/**
 *
 * @author victor
 */
public class ContasVinculadas extends javax.swing.JInternalFrame {

    private TransacaoController transacaoController;

    public ContasVinculadas() {
        initComponents();
        preencheTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);

        jButton1.setText("Criar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Contas Vinculadas");

        jTable1.setBackground(new java.awt.Color(254, 254, 254));
        jTable1.setDefaultRenderer(Object.class, new view.RenderizadorTransacao());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Dica de Senha", "email", ""
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setRowSelectionAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CriarContaVinculada conta = new CriarContaVinculada();
        MeuEscritorio.jDesktopPane1.add(conta);
        try {
            conta.setMaximum(true);
        } catch (PropertyVetoException ex) {
            
        }
        conta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public void preencheTabela() {

        final DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        UsuarioController usuarioController;
        try {

            usuarioController = new UsuarioController();
            Iterator it = usuarioController.contasVinculadas(MeuEscritorio.usuarioLogado.email).iterator();
            Usuario usr;

            while (it.hasNext()) {
                usr = (Usuario) it.next();
                model.addRow(new Object[]{usr.nome, usr.dicaSenha, usr.email, "Deletar"});

            }

            Action delete = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable) e.getSource();
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    UsuarioController usuarioController;

                    int coluna = 2;
                    Object obj = jTable1.getValueAt(jTable1.getSelectedRow(), coluna);
                    String email = (String) obj;
                    try {
                        usuarioController = new UsuarioController();
                        Usuario usr = usuarioController.buscarUsuario(email);
                        usuarioController.removerUsuario(usr);
                    } catch (Exception ex) {

                    }

                    ((DefaultTableModel) table.getModel()).removeRow(modelRow);
                }
            };

            ButtonColumn buttonColumn2 = new ButtonColumn(jTable1, delete, 3);
            buttonColumn2.setMnemonic(KeyEvent.VK_D);

        } catch (Exception e) {
        }

    }
}
