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

public class RenderizadorCategoria extends DefaultTableCellRenderer {

    private int cor, id;
    

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                

        cell.setForeground(Color.black);
        
        if(column == 0)
            id = (Integer) value;

        if (column == 2) {
            cor = getCor(id);
            cell.setBackground(new Color(cor));

        } else {
            cell.setBackground(Color.white);
        }

        return cell;
    }
    
    
    public int getCor(int id){
        CategoriaTransacaoController categoriaController;
        CategoriaTransacao categoria;
        try {
            categoriaController = new CategoriaTransacaoController();
            categoria = categoriaController.buscaCategoria(id, MeuEscritorio.usuarioLogado.email);
            if(categoria == null)
                return -1;
            return categoria.cor;
            
        } catch (Exception ex) {
            
        }
        return 0;
        
    }
}