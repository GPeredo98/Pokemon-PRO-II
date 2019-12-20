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
public class Dibujo2 extends JPanel {

    public Dibujo2() {
    }
    @Override
    protected void paintComponent (Graphics fondo) {
        super.paintComponents(fondo);
        ImageIcon icono = new ImageIcon("imagen/FONDO4.png");
        fondo.drawImage(icono.getImage(), 0, 0, 800, 600, null);
    }

    
    
}
