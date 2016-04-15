/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.transacao;

import controller.CategoriaTransacaoController;
import controller.TransacaoController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.CategoriaTransacao;
import model.Transacao;
import org.jfree.chart.ChartPanel;
import view.ButtonColumn;
import view.GerarGrafico;
import view.Histograma;
import view.RenderizadorTransacao;
import view.usuario.MeuEscritorio;
import view.usuario.TelaInicial;

/**
 *
 * @author victor
 */
public class Relatorio extends javax.swing.JInternalFrame {

    private TransacaoController transacaoController;

    public Relatorio() {
        initComponents();
        dataPadrao();
        preencheComboBox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jCheckBoxDespesa = new javax.swing.JCheckBox();
        jCheckBoxProvento = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        try{
            MaskFormatter mask = new MaskFormatter("##/##/####");
            dataInicial = new javax.swing.JFormattedTextField(mask);
            

                dataFinal = new javax.swing.JFormattedTextField(mask);
  }catch(Exception ex){

            }


                setClosable(true);

                jButton1.setText("Gerar Lista");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                });

                jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
                jLabel3.setText("Relatorio");

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

                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione a Categoria" }));
                jComboBox1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jComboBox1ActionPerformed(evt);
                    }
                });

                jButton2.setText("Gerar Gráfico");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton2ActionPerformed(evt);
                    }
                });

                jCheckBoxDespesa.setText("Despesa");

                jCheckBoxProvento.setText("Provento");

                jButton3.setText("Gerar Histograma");
                jButton3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton3ActionPerformed(evt);
                    }
                });

          
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxDespesa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxProvento)
                                .addGap(46, 46, 46)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(6, 6, 6)
                                .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 238, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jCheckBoxDespesa)
                    .addComponent(jCheckBoxProvento)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String filtro = gerarFiltro();

        limpaTabela();
        preencheTabela(filtro);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String filtro = gerarFiltro();
        try {
            Grafico plot = new Grafico(filtro);

            MeuEscritorio.jDesktopPane1.add(plot);
            plot.setMaximum(true);
            plot.setVisible(true);

        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String filtro = gerarFiltro();
        try {
            Histograma h = new Histograma(dataInicial.getText(), dataFinal.getText());
            MeuEscritorio.jDesktopPane1.add(h);
            h.setMaximum(true);
            h.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField dataFinal;
    private javax.swing.JFormattedTextField dataInicial;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBoxDespesa;
    private javax.swing.JCheckBox jCheckBoxProvento;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
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

    private String gerarFiltro() {

        String filtro = "";

        String datainicial = dataPadraotoSQL(dataInicial.getText());
        String datafinal = dataPadraotoSQL(dataFinal.getText());
        if (!dataInicial.getText().isEmpty() || !dataFinal.getText().isEmpty()) {
            filtro += "dataInsercao BETWEEN '" + datainicial + "' AND '" + datafinal + "' AND ";
        }

        String itemSelecionado = (String) jComboBox1.getSelectedItem();
        if (!itemSelecionado.equals("Selecione a Categoria")) {
            filtro += "categoria = '" + itemSelecionado + "' AND ";
        }


        if (!(jCheckBoxProvento.isSelected() && jCheckBoxDespesa.isSelected())) {

            if (jCheckBoxProvento.isSelected()) {
                filtro += "tipoTransacao = 'Provento' AND ";
            }
            if (jCheckBoxDespesa.isSelected()) {
                filtro += "tipoTransacao = 'Despesa' AND ";
            }
        }

        filtro += "emailUsuario = '" + MeuEscritorio.usuarioLogado.email + "'";
        return filtro;

    }

    private void preencheComboBox() {
        try {
            CategoriaTransacaoController categoriaController = new CategoriaTransacaoController();
            List categorias = categoriaController.toList(MeuEscritorio.usuarioLogado.email);
            Iterator it = categorias.iterator();

            CategoriaTransacao categoria;
            while (it.hasNext()) {
                categoria = (CategoriaTransacao) it.next();
                jComboBox1.addItem(categoria.nome);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public JRadioButton getSelectedRadio(ButtonGroup group) {
        for (Enumeration eRadio = group.getElements(); eRadio.hasMoreElements();) {
            JRadioButton radioButton = (JRadioButton) eRadio.nextElement();

            if (radioButton.getModel() == group.getSelection()) {
                return radioButton;
            }
        }

        return null;
    }

    public void limpaTabela() {
        DefaultTableModel dm = (DefaultTableModel) jTable1.getModel();
        int rowCount = dm.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    public void preencheTabela(String filtro) {

        final DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try {
            transacaoController = new TransacaoController();
            List transacoes = transacaoController.listComFiltro(filtro);
            Iterator it = transacoes.iterator();
            Transacao t;

            while (it.hasNext()) {
                t = (Transacao) it.next();
                model.addRow(new Object[]{t.id, t.tipoTransacao, t.descricao, t.valor, t.categoria,
                    t.tipoRecorrencia, t.dataInsercao});
            }
        } catch (Exception e) {
        }

    }

    private String dataPadraotoSQL(String data) {
        // dd/mm/yyyy
        String nova = null, dia, mes, ano;
        dia = data.substring(0, 2);
        mes = data.substring(3, 5);
        ano = data.substring(6, 10);

        nova = nova.format("%s-%s-%s", ano, mes, dia);
        return nova;
    }
}