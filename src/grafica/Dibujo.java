/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Dibujo extends JPanel {
    private String imagen;
    public Dibujo(String fondo) {
        imagen="imagen/"+fondo+".png";
    }
    
    @Override
    protected void paintComponent (Graphics fondo) {
        super.paintComponents(fondo);
        
        ImageIcon icono = new ImageIcon(imagen);
        fondo.drawImage(icono.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        fondo.drawOval(20, 20, 20, 20);
    }
}
