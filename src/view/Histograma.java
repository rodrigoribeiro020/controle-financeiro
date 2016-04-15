/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CategoriaTransacaoController;
import controller.TransacaoController;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.CategoriaTransacao;
import model.Transacao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import view.usuario.MeuEscritorio;

/**
 *
 * @author victorhfs
 */
public class Histograma extends javax.swing.JInternalFrame {

    private int a = 0;

    public Histograma(String dataInicial, String dataFinal) {
        initComponents();
        XYSeriesDemo3("teste", dataInicial, dataFinal);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void XYSeriesDemo3(final String title, String dataInicial, String dataFinal) {
        CategoriaTransacaoController categoriaController;
        CategoriaTransacao categoria;

        IntervalXYDataset dataset = createDataset(dataInicial, dataFinal);

        JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private IntervalXYDataset createDataset(String dataInicial, String dataFinal) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        TransacaoController transacaoController;
        CategoriaTransacaoController categoriaController;
        CategoriaTransacao categoria;
        Transacao transacao;
        try {
            categoriaController = new CategoriaTransacaoController();
            List categorias = categoriaController.toList(MeuEscritorio.usuarioLogado.email);
            Iterator itCat = categorias.iterator();

            transacaoController = new TransacaoController();

            while (itCat.hasNext()) {
                double saldo = 0;
                List transacoesPorData = new ArrayList<Transacao>();
                List transacoes = transacaoController.listTransacaoPorData(dataInicial, dataFinal, MeuEscritorio.usuarioLogado.email);
                Iterator it = transacoes.iterator();
                Transacao t;

                categoria = (CategoriaTransacao) itCat.next();
                XYSeries series = new XYSeries(categoria.nome);

                while (it.hasNext()) {
                    t = (Transacao) it.next();
                    if (t.categoria.equals(categoria.nome)) {

                        transacoesPorData.add(t);
                    }
                }

                saldo = transacaoController.calculaSaldo(transacoesPorData, dataInicial, dataFinal);
                a += 1;
                series.add(a, saldo);

                dataset.addSeries(series);
            }

        } catch (Exception e) {
        }

        return dataset;
    }

    private JFreeChart createChart(IntervalXYDataset dataset) {
        final JFreeChart chart = ChartFactory.createXYBarChart(
                "HISTOGRAMA",
                "X",
                false,
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        XYPlot plot = (XYPlot) chart.getPlot();

        CategoriaTransacaoController categoriaController;
        CategoriaTransacao categoria;

        try {
            categoriaController = new CategoriaTransacaoController();
            List categorias = categoriaController.toList(MeuEscritorio.usuarioLogado.email);
            Iterator it = categorias.iterator();

            while (it.hasNext()) {
                categoria = (CategoriaTransacao) it.next();

                if (categoria.orcamento != 0) {
                    IntervalMarker target = new IntervalMarker(-1 * categoria.orcamento, -1 * categoria.orcamento);
                    target.setLabel("Orçamento de '" + categoria.nome + "'");
                    target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
                    target.setLabelAnchor(RectangleAnchor.LEFT);
                    target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
                    target.setPaint(new Color(222, 222, 255, 128));
                    plot.addRangeMarker(target, Layer.FOREGROUND);
                }

            }
        } catch (Exception e) {
        }
        return chart;
    }
}