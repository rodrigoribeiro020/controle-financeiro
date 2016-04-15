/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.transacao;

import controller.TransacaoController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;
import model.Transacao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import view.usuario.MeuEscritorio;

/**
 *
 * @author victorhfs
 */
public class Grafico extends javax.swing.JInternalFrame {

    
    private ChartPanel chartPanel;
    public Grafico(String filtro) {
        chartPanel = createChart(filtro);
        initComponents();        
        grafico();

    }

    private void grafico() {

        setClosable(true);
        setLayout(new BorderLayout(0, 5));
        add(chartPanel, BorderLayout.CENTER);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setHorizontalAxisTrace(false);
        chartPanel.setVerticalAxisTrace(false);
        

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(createDate());
        panel.add(createZoom());
        add(panel, BorderLayout.SOUTH);
        pack();

    }

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
            .addGap(0, 280, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private JComboBox createTrace() {
        final JComboBox trace = new JComboBox();
        final String[] traceCmds = {"Enable Trace", "Disable Trace"};
        trace.setModel(new DefaultComboBoxModel(traceCmds));
        trace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (traceCmds[0].equals(trace.getSelectedItem())) {
                    chartPanel.setHorizontalAxisTrace(true);
                    chartPanel.setVerticalAxisTrace(true);
                    chartPanel.repaint();
                } else {
                    chartPanel.setHorizontalAxisTrace(false);
                    chartPanel.setVerticalAxisTrace(false);
                    chartPanel.repaint();
                }
            }
        });
        return trace;
    }

    private JComboBox createDate() {
        final JComboBox date = new JComboBox();
        final String[] dateCmds = {"Horizontal Dates", "Vertical Dates"};
        date.setModel(new DefaultComboBoxModel(dateCmds));
        date.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFreeChart chart = chartPanel.getChart();
                XYPlot plot = (XYPlot) chart.getPlot();
                DateAxis domain = (DateAxis) plot.getDomainAxis();
                if (dateCmds[0].equals(date.getSelectedItem())) {
                    domain.setVerticalTickLabels(false);
                } else {
                    domain.setVerticalTickLabels(true);
                }
            }
        });
        return date;
    }

    private JButton createZoom() {
        final JButton auto = new JButton(new AbstractAction("Auto Zoom") {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartPanel.restoreAutoBounds();
            }
        });
        return auto;
    }

    private ChartPanel createChart(String filtro) {
        XYDataset roiData = createDataset(filtro);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title, "Date", "Value", roiData, true, true, false);
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer =
                (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true);
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        currency.setMaximumFractionDigits(0);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(currency);
        return new ChartPanel(chart);
    }

    private XYDataset createDataset(String filtro) {
        TimeSeriesCollection tsc = new TimeSeriesCollection();

        tsc.addSeries(createSeriesProvento("Transações", filtro));
        //tsc.addSeries(createSeries("Actual", 100));
        //tsc.addSeries(createSeries("Actual", 300));

        return tsc;
    }

    private TimeSeries createSeriesProvento(String name, String filtro) {
        TimeSeries series = new TimeSeries(name);
        TransacaoController transacaoController;


        Transacao t;

        try {

            transacaoController = new TransacaoController();
            
            List transacoes = transacaoController.listComFiltro(filtro);
            Iterator it = transacoes.iterator();
            double somador = 0;
            while (it.hasNext()) {
                t = (Transacao) it.next();
                int ano = Integer.parseInt(t.dataInsercao.substring(6, 10));
                int mes = Integer.parseInt(t.dataInsercao.substring(3, 5));
                int dia = Integer.parseInt(t.dataInsercao.substring(0, 2));
                String data = "" + mes + "/" + dia + "/" + ano;
                if(t.tipoTransacao.equals("Despesa"))
                    somador -= t.valor;
                if(t.tipoTransacao.equals("Provento"))
                    somador += t.valor;
                series.add(new Day(new Date(data)), somador);

            }

        } catch (Exception e) {
        }

        return series;
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        dispose();
    }
}