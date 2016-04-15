/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author rodrigors
 */
public class DecoraDesktopPane extends JDesktopPane {
    
    private Image img;  
  
        public DecoraDesktopPane() {  
            try {  
                img = new ImageIcon(this.getClass().getResource("grafico.jpg")).getImage();
            } catch (Exception ex) {  
            }  
        }  
  
    
    
    @Override  
     
        public void paintComponent(Graphics g) {  
            super.paintComponent(g);  
            if (img != null) {  
                Dimension dimension = this.getSize();
                dimension.setSize(800, 600);
  
                g.drawImage(img,0, 0, 800, 600, this);
            } else {  
                g.drawString("Imagem nao encontrada", 50, 50);  
            }  
        }  
}
