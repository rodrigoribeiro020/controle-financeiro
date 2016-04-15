package view;

import controller.CategoriaTransacaoController;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import controller.TransacaoController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CategoriaTransacao;
import view.usuario.MeuEscritorio;

public class RenderizadorTransacao extends DefaultTableCellRenderer {

    private int cor;

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


        cell.setForeground(Color.black);



        if (column == 4) {
            
            cor = getCor((String) value);
            cell.setBackground(new Color(cor));

        } else {
            cell.setBackground(Color.white);
        }

        return cell;
    }
    
    
    public int getCor(String nome){
        CategoriaTransacaoController categoriaController;
        CategoriaTransacao categoria;
        try {
            if(nome.equals("") || nome == null)
                return -1;
            
            categoriaController = new CategoriaTransacaoController();
            categoria = categoriaController.buscaCategoriaPorNome(nome, MeuEscritorio.usuarioLogado.email);            
            return categoria.cor;
            
        } catch (Exception ex) {
            
        }
        return 0;
        
    }
}