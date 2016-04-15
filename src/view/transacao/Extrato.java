/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.transacao;

import controller.TransacaoController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.Transacao;
import view.ButtonColumn;
import view.RenderizadorTransacao;
import view.usuario.MeuEscritorio;
import view.usuario.TelaInicial;

/**
 *
 * @author victor
 */
public class Extrato extends javax.swing.JInternalFrame {

    private TransacaoController transacaoController;

    public Extrato() {
        initComponents();
        dataPadrao();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        try{
            MaskFormatter mask = new MaskFormatter("##/##/####");
            dataInicial = new javax.swing.JFormattedTextField(mask);
  }catch(Exception ex){

            }


            try{
                MaskFormatter mask = new MaskFormatter("##/##/####");
                dataFinal = new javax.swing.JFormattedTextField(mask);
        }catch(Exception ex){

        }

                setClosable(true);

                jButton1.setText("Buscar");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                });

                jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
                jLabel3.setText("Extrato");

                jTable1.setBackground(new java.awt.Color(254, 254, 254));
                jTable1.setDefaultRenderer(Object.class, new view.RenderizadorTransacao());
                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "ID", "Despesa/Provento", "Descrição", "Valor", "Categoria", "Recorrencia", "Data de inserçao"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                jTable1.setFocusable(false);
                jTable1.setRowSelectionAllowed(false);
                jScrollPane2.setViewportView(jTable1);

                jLabel1.setText("Data Inicial:");

                jLabel2.setText("Data Final:");

                labelValor.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
                labelValor.setText("!Valor");

                jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
                jLabel5.setText("SALDO:");

          

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelValor)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(jButton1)
                        .addComponent(dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelValor)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpaTabela();
        preencheTabela();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField dataFinal;
    private javax.swing.JFormattedTextField dataInicial;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelValor;
    // End of variables declaration//GEN-END:variables

    public void dataPadrao() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = s.format(c.getTime());
        c.add(c.MONTH, 1);
        String dataAtualMaisUm = s.format(c.getTime());
        dataInicial.setText(dataAtual);
        dataFinal.setText(dataAtualMaisUm);
    }
    
    public void limpaTabela() {
        DefaultTableModel dm = (DefaultTableModel) jTable1.getModel();
        int rowCount = dm.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    

    public void preencheTabela() {

        final DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try {
            transacaoController = new TransacaoController();
            List transacoes = transacaoController.listTransacaoPorData(dataInicial.getText(), dataFinal.getText(), MeuEscritorio.usuarioLogado.email);
            Iterator it = transacoes.iterator();
            Transacao t;
            Double saldo = transacaoController.calculaSaldo(transacoes, dataInicial.getText(), dataFinal.getText());
            if (saldo > 0) {
                labelValor.setForeground(Color.green);
            } else {
                labelValor.setForeground(Color.red);
            }
            labelValor.setText(String.valueOf(saldo));

            while (it.hasNext()) {
                t = (Transacao) it.next();
                model.addRow(new Object[]{t.id, t.tipoTransacao, t.descricao, t.valor, t.categoria,
                    t.tipoRecorrencia, t.dataInsercao});
            }
            
            
        } catch (Exception e) {

        }

    }

}