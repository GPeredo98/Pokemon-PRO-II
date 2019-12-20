/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.applet.AudioClip;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author HP
 */
public class Pokemon {

    private String nombre;
    private int vida;
    private ImageIcon imagen;
    private ImageIcon sprite;
    protected ArrayList<Ataque> ataques = new ArrayList<>();

    private int x, y;

    private Thread hilo;

    public Pokemon(String nombre, int vida) {
        this.nombre = nombre;
        this.vida = vida;
        imagen = new ImageIcon("imagen/" + nombre + ".jpg");
    }

    public void addAtaque(String nombre, int daño, String tipo) {
        ataques.add(new Ataque(nombre, daño, tipo));
    }

    public Ataque getAtaque(int pos) {
        return ataques.get(pos);
    }

    public ArrayList<Ataque> getAtaques() {
        return ataques;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {

        return vida;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public void setAtaques(ArrayList<Ataque> ataques) {
        this.ataques = ataques;
    }

    public Icon getImagen() {
        imagen = new ImageIcon("imagen/" + nombre + ".jpg");
        return imagen;
    }

    public Icon getSpriteBack() {
        sprite = new ImageIcon("imagen/sprites/" + nombre + "_Back.gif");
        return sprite;
    }

    public Icon getSpriteFront() {
        sprite = new ImageIcon("imagen/sprites/" + nombre + "_Front.gif");
        return sprite;
    }

}
