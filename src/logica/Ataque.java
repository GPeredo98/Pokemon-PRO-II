/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author HP
 */
public class Ataque {

    private String nombre;
    private int daño;
    private String tipo;

    public Ataque() {
    }

    public Ataque(String nombre, int daño, String tipo) {
        this.nombre = nombre;
        this.daño = daño;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNomnbre(String nomnbre) {
        this.nombre = nomnbre;
    }

    public int getDaño() {
        return daño;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }
    
    public String getTipo() {
        return tipo;
    }

}


