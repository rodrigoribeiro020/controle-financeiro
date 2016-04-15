/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.TransacaoController;
import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import model.Transacao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import static org.jfree.chart.demo.PieChartDemo1.createDemoPanel;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import view.usuario.MeuEscritorio;

/**
 *
 * @author Rodrigo
 */
public class GerarGrafico extends ApplicationFrame {
    
    public GerarGrafico(String title, String filtro) {
        super(title);
        setContentPane(createDemoPanel(filtro));
    }
    
    private PieDataset createDataset(String filtro) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        if(contaTrasacoesDespesasFilstrada(filtro) > 0 && contaTrasacoesProventoFilstrada(filtro) == 0){
            dataset.setValue("Despesas", new Double(contaTrasacoesDespesasFilstrada(filtro)));
        }
        
        else if(contaTrasacoesDespesasFilstrada(filtro) == 0 && contaTrasacoesProventoFilstrada(filtro) > 0){
            dataset.setValue("Proventos", new Double(contaTrasacoesProventoFilstrada(filtro)));
        }else{
            dataset.setValue("Despesas", new Double(contaTrasacoesDespesasFilstrada(filtro)));
            dataset.setValue("Proventos", new Double(contaTrasacoesProventoFilstrada(filtro)));
        }
        
    
        return dataset;
       
    }
    
    
    private JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
                "Gráfico de Transações", // chart title
                dataset, // data
                true, // include legend
                true,
                false
                
         
        );
    
        
        
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Provento", new Color(68,157,68));
        plot.setSectionPaint("Despesa", new Color(217,83,79));
        plot.setLabelFont(new Font("Segoue UI", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
        
       
        
    }
    
    private double contaTrasacoesDespesasFilstrada(String filtro){
         double contadorDespesaFiltrada = 0;   
         try {
            TransacaoController transacaoController; 
            transacaoController = new TransacaoController();
            List transacoes = transacaoController.listComFiltro(filtro);
            Iterator it = transacoes.iterator();
            Transacao t;

            while (it.hasNext()) {
                t = (Transacao) it.next();
                if(t.tipoTransacao.equals("Despesa")){
                    contadorDespesaFiltrada++;
                }
            }
            
        } catch (Exception e) {

        }
         return contadorDespesaFiltrada;
    }
    
    private double contaTrasacoesProventoFilstrada(String filtro){
        double contadorProventoFiltrada = 0;   
         try {
            TransacaoController transacaoController; 
            transacaoController = new TransacaoController();
            List transacoes = transacaoController.listComFiltro(filtro);
            Iterator it = transacoes.iterator();
            Transacao t;

            while (it.hasNext()) {
                t = (Transacao) it.next();
                if(t.tipoTransacao.equals("Provento")){
                    contadorProventoFiltrada++;
                }
            }
            
        } catch (Exception e) {

        }
         return contadorProventoFiltrada;
    }
    
    public JPanel createDemoPanel(String filtro) {
        JFreeChart chart = createChart(createDataset(filtro));
        return new ChartPanel(chart);
        
    }

   
    
    
}
