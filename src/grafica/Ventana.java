/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.plaf.BorderUIResource;
import logica.Pokemon;
import java.applet.AudioClip;
import java.awt.Font;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.audio.*;

/**
 *
 * @author HP
 */
public class Ventana extends JFrame implements ActionListener {

    private ArrayList<Pokemon> pokemones = new ArrayList<>();

    private Dibujo pnInicio = new Dibujo("FONDO2");
    private Dibujo pnMenu = new Dibujo("FONDO1");
    public Dibujo pnOpciones = new Dibujo("FONDO4");
    private Dibujo pnBatalla = new Dibujo("FONDO4");
    private Dibujo pnArena = new Dibujo("Arena1");
    private JPanel pnControl = new JPanel();
    private JPanel pnAtaques = new JPanel();
    private JPanel pnJuego = new JPanel();
    private Dibujo2 pnPokemones = new Dibujo2();
    private Dibujo pnCargando = new Dibujo("FONDOCargando3");
    //Botones
    private JButton btInicio = new JButton(new ImageIcon("imagen/inicio.png"));
    private JButton btBatalla = new JButton(new ImageIcon("imagen/batalla.png"));
    private JButton btOpciones = new JButton(new ImageIcon("imagen/opciones.png"));
    private JButton btSalir = new JButton(new ImageIcon("imagen/salir.png"));
    private JButton btAtras = new JButton(new ImageIcon("imagen/atras.png"));
    private JButton btAtras2 = new JButton(new ImageIcon("imagen/atras.png"));
    private JButton btMusica = new JButton("Silenciar Música de fondo");

    private JButton btnpoder1 = new JButton();
    private JButton btnpoder2 = new JButton();
    private JButton btnpoder3 = new JButton();
    private JButton btnpoder4 = new JButton();

    private JLabel sprite = new JLabel();
    private JLabel sprite2 = new JLabel();
    private JLabel HP = new JLabel(new ImageIcon("imagen/PH.png"));
    private JLabel HP2 = new JLabel(new ImageIcon("imagen/PH2.png"));
    private JLabel name = new JLabel();
    private JLabel name2 = new JLabel();

    private JTextArea vida = new JTextArea();
    private JTextArea vida2 = new JTextArea();

    private JTextArea dialogo = new JTextArea();
    private JLabel rayo = new JLabel(new ImageIcon());
    private JLabel fin = new JLabel();
    private JLabel cargando = new JLabel(new ImageIcon("imagen/giphy.gif"));

    //Efecto de los botones
    ImageIcon batalla = new ImageIcon("imagen/batalla2.png");
    ImageIcon opciones = new ImageIcon("imagen/opciones2.png");
    ImageIcon salir = new ImageIcon("imagen/salir2.png");
    ImageIcon aluchar = new ImageIcon("imagen/aluchar2.png");
    ImageIcon regresar = new ImageIcon("imagen/atras2.png");
    ImageIcon inicio = new ImageIcon("imagen/inicio2.png");

    private Thread hilo;

    private Pokemon elegido;
    private Pokemon rival;

    //Numero Aleatorio para el pokemon rival
    private int aleatorio;
    //Posicion del pokemon elegido
    int x = 170;
    int y = 280;

    //Posicion del pokemon rival
    int x2 = 560;
    int y2 = 130;

    public Ventana() throws Exception {
        super("POKÉMON PRO-II");

        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        //setResizable(false);
        IniciarMusica();

        //Paneles del juego añadidos y ocultados
        this.add(pnInicio);
        this.add(pnMenu);
        pnMenu.setVisible(false);
        this.add(pnBatalla);
        pnBatalla.setVisible(false);
        this.add(pnOpciones);
        pnOpciones.setVisible(false);
        this.add(pnJuego);
        pnJuego.setVisible(false);
        this.add(pnCargando);
        pnCargando.setVisible(false);
        pnPokemones.setBounds(0, 0, 800, 500);
        pnPokemones.setLayout(new GridLayout(2, 10, 10, 30));////////////////////////////////////7

        cargarPokemones();
        for (Pokemon pokemon : pokemones) {
            JButton aux = new JButton(pokemon.getNombre(), pokemon.getImagen());
            aux.setContentAreaFilled(false);
            aux.setBorderPainted(false);
            aux.setSize(50, 600);

            aux.addActionListener(e -> {
                //Si se escoje el pokemon se cambian los panel
                pnBatalla.setVisible(false);
                pnPokemones.setVisible(false);
                //Primero se muestra la pantalla cargando y luego el juego
                hilo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            pnCargando.setBounds(0, 0, 800, 600);
                            pnCargando.add(cargando);
                            pnCargando.setLayout(null);
                            cargando.setBounds(350, 350, 100, 100);
                            pnCargando.setVisible(true);
                            pnJuego.setVisible(false);

                            Thread.sleep(2000);
                            repaint();
                            pnCargando.setVisible(false);
                            pnJuego.setVisible(true);

                        } catch (Exception e) {
                        }
                    }
                });
                hilo.start();

                //Si se escoje el pokemon se guarda en la variable "elegido"
                elegido = pokemon;
                System.out.println(pokemon.getNombre() + " ELEGIDO!");

                //Si se escoje el pokemon se añaden sus graficas a la arena 
                sprite.setIcon(elegido.getSpriteBack());
                pnArena.add(sprite);

                if (elegido.getNombre() == "Raichu") {
                    x = 150;
                    y = 250;
                    sprite.setBounds(x, y, 200, 200);
                } else {

                    sprite.setBounds(x, y, 200, 200);
                }
                if (elegido.getNombre() == "Charizard") {
                    x = 130;
                    y = 250;
                    sprite.setBounds(x, y, 200, 200);
                }
                if (elegido.getNombre() == "Articuno") {
                    x = 130;
                    y = 250;
                    sprite.setBounds(x, y, 200, 200);
                }

                name.setText(elegido.getNombre().toUpperCase());
                pnArena.add(name);
                name.setBounds(100, 200 - 20, 300, 100);
                name.setFont(new java.awt.Font("HighlandGothicFLF", 0, 18));

                pnArena.add(vida);
                vida.setBounds(93, 244, 274, 15);
                vida.setBackground(Color.green);

                //Si es ecoje el pokemon se escoje aleatoriamente el pokemon
                //a enfrentar y se guarda en la variable "rival"
                aleatorio = (int) Math.floor(Math.random() * pokemones.size());
                rival = pokemones.get(aleatorio);
                System.out.println(elegido.getNombre() + " vs " + rival.getNombre());
                dialogo.setText("\n      ¡¡Ha comenzado la batalla entre " + elegido.getNombre() + " contra " + rival.getNombre() + "!!");

                //Se añaden las graficas del rival a la arena
                sprite2.setIcon(rival.getSpriteFront());
                pnArena.add(sprite2);

                name2.setText(rival.getNombre().toUpperCase());

                if (rival.getNombre() == "Absol") {
                    x2 = 510;
                    y2 = 110;
                    sprite2.setBounds(x2, y2, 200, 200);
                }
                if (rival.getNombre() == "Charizard") {
                    x2 = 500;
                    y2 = 110;
                }
                if (rival.getNombre() == "Articuno") {
                    x2 = 540;
                    y2 = 110;
                }
                if (rival.getNombre() == "Rhyhorn") {
                    x2 = 565;
                    y2 = 150;
                }
                if (rival.getNombre() == "Venusaur") {
                    x2 = 550;
                    y2 = 140;
                }
                sprite2.setBounds(x2, y2, 200, 200);
                pnArena.add(name2);
                name2.setBounds(520, 50 - 15, 300, 100);
                name2.setFont(new java.awt.Font("HighlandGothicFLF", 0, 14));

                pnArena.add(vida2);
                vida2.setBounds(508, 95, 200, 12);
                vida2.setBackground(Color.green);

                //Se escoje y se reproduce su sonido
                this.repaint();
                this.validate();

                btnpoder1.setText(elegido.getAtaque(0).getNombre());
                btnpoder2.setText(elegido.getAtaque(1).getNombre());
                btnpoder3.setText(elegido.getAtaque(2).getNombre());
                btnpoder4.setText(elegido.getAtaque(3).getNombre());
                pnAtaques.add(btnpoder1);
                pnAtaques.add(btnpoder2);
                pnAtaques.add(btnpoder3);
                pnAtaques.add(btnpoder4);

                pnAtaques.repaint();

            });
            pnPokemones.add(aux);
        }
        btnpoder1.addActionListener(this);
        btnpoder2.addActionListener(this);
        btnpoder3.addActionListener(this);
        btnpoder4.addActionListener(this);

        this.repaint();

        //Configuración del Panel de Inicio
        pnInicio.setBounds(0, 0, 800, 600);
        pnInicio.setBackground(Color.yellow);
        pnInicio.setLayout(null);

        btInicio.setBounds(300, 450, 200, 60);
        pnInicio.add(btInicio);

        btInicio.setBorderPainted(false);
        btInicio.setContentAreaFilled(false);
        btInicio.setRolloverIcon(inicio);
        btInicio.addActionListener(this);

        //Configuración del Panel Menú
        pnMenu.setBounds(0, 0, 800, 600);
        pnMenu.setBackground(Color.yellow);
        pnMenu.setLayout(null);

        pnMenu.add(btBatalla);
        btBatalla.setBounds(450, 300, 200, 60);
        btBatalla.setBorderPainted(false);
        btBatalla.setContentAreaFilled(false);
        btBatalla.setRolloverIcon(batalla);
        btBatalla.addActionListener(this);
        btBatalla.addActionListener(this);

        pnMenu.add(btOpciones);
        btOpciones.setBounds(450, 380, 200, 60);
        btOpciones.setBorderPainted(false);
        btOpciones.setContentAreaFilled(false);
        btOpciones.setRolloverIcon(opciones);
        btOpciones.addActionListener(this);

        pnMenu.add(btSalir);
        btSalir.setBounds(450, 460, 200, 60);
        btSalir.setBorderPainted(false);
        btSalir.setContentAreaFilled(false);
        btSalir.setRolloverIcon(salir);
        btSalir.addActionListener(this);

        //Configuración del Panel de Batalla
        pnBatalla.setBounds(0, 0, 800, 600);
        pnBatalla.setBackground(Color.yellow);
        pnBatalla.setLayout(null);

        pnBatalla.add(pnPokemones);

        pnBatalla.add(btAtras);
        btAtras.addActionListener(this);
        btAtras.setBounds(650, 500, 120, 60);
        btAtras.setBorderPainted(false);
        btAtras.setContentAreaFilled(false);
        btAtras.setRolloverIcon(regresar);

        //Configuracion del Panel del juego
        pnJuego.setBounds(0, 0, 800, 600);
        pnJuego.setLayout(null);
        pnJuego.setBackground(Color.DARK_GRAY);
        pnJuego.add(pnArena);
        pnArena.setBounds(0, 0, 800, 450);
        pnArena.setLayout(null);
        pnControl.setLayout(null);
        pnControl.setBackground(Color.DARK_GRAY);

        pnControl.setBounds(0, 450, 400, 121);
        pnAtaques.setBounds(405, 455, 375, 100);
        pnJuego.add(pnControl);
        pnJuego.add(pnAtaques);
        pnArena.add(HP);
        HP.setBounds(80, 200, 300, 100);
        pnArena.add(HP2);
        HP2.setBounds(460, 50, 300, 100);

        //Configuración del panel Ataques
        pnControl.add(dialogo);
        pnAtaques.setBackground(Color.DARK_GRAY);
        pnAtaques.setLayout(new GridLayout(2, 2));/////////////////////////////////////////////////
        dialogo.setBounds(5, 5, 390, 100);
        dialogo.setEditable(false);
        dialogo.setBackground(Color.white);
        dialogo.setBorder(BorderUIResource.getEtchedBorderUIResource());
        dialogo.setFont(new java.awt.Font("HighlandGothicFLF", 0, 11));

        //Configuración del Panel de Opciones
        pnOpciones.setBounds(0, 0, 800, 600);
        pnOpciones.setBackground(Color.white);
        pnOpciones.setLayout(null);

        pnOpciones.add(btAtras2);
        btAtras2.addActionListener(this);
        btAtras2.setBounds(650, 500, 120, 60);
        btAtras2.setBorderPainted(false);
        btAtras2.setContentAreaFilled(false);
        btAtras2.setRolloverIcon(regresar);
        pnOpciones.add(btMusica);
        pnOpciones.setLayout(null);
        btMusica.setBounds(100, 200, 200, 50);
        btMusica.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Acciones de los botones de poderes en el juego
        if (btnpoder1 == e.getSource()) {
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Se muestra el poder escogido
                        dialogo.setText("\n      ¡¡" + elegido.getNombre() + " ha usado " + btnpoder1.getText() + "!!");
                        Thread.sleep(2000);
                        //Se ejecuta el ataque
                        dialogo.setText("\n      . . .");
                        
                        //Se lanza el rayo
                        rayo.setIcon(new ImageIcon("imagen/" + elegido.getAtaque(1).getTipo() + ".png"));
                        LanzarRayo();
                        //Se muestra si el ataque fue exitoso, la animacion y se calcula el daño
                        int posibilidadDeAtaqueExitoso = (int) Math.floor(Math.random() * 2 + 1);
                        System.out.println(posibilidadDeAtaqueExitoso);
                        if (posibilidadDeAtaqueExitoso == 2) {
                            dialogo.setText("\n      ¡Ataque exitoso!");
                            Thread.sleep(1000);

                            SacudirPokemonRival();

                            //Daño al rival
                            vida2.setBounds(508, 95, vida2.getWidth() - (elegido.getAtaque(1).getDaño() + 40), 12);
                            if (vida2.getWidth() <= 0) {
                                JOptionPane.showMessageDialog(null, elegido.getNombre() + " ha ganado la batalla");
                                volverAjugar();
                            }
                            Thread.sleep(1000);
                            if (vida2.getWidth() <= 100) {
                                vida2.setBackground(Color.RED);
                                pnArena.repaint();
                                HP2.repaint();
                            }
                        } else {
                            dialogo.setText("\n      ¡" + rival.getNombre() + " ha esquivado el ataque!");
                            Thread.sleep(1000);
                            MoverPokemonRival();
                        }
                        Thread.sleep(1000);
                        pnAtaques.remove(btnpoder1);
                        pnAtaques.remove(btnpoder2);
                        pnAtaques.remove(btnpoder3);
                        pnAtaques.remove(btnpoder4);
                        pnAtaques.repaint();

                        dialogo.setText("\n      " + rival.getNombre() + " prepara su ataque...");
                        Thread.sleep(1000);
                        int al = (int) Math.floor(Math.random() * rival.getAtaques().size());
                        dialogo.setText("\n      ¡¡" + rival.getNombre() + " ha usado " + rival.getAtaque(al).getNombre() + "!!");
                        Thread.sleep(2000);
                        dialogo.setText("\n      . . .");
                        rayo.setIcon(new ImageIcon("imagen/" + rival.getAtaque(al).getTipo() + ".png"));
                        RecibirRayo();

                        int posibilidadDeDefensaExitosa = (int) Math.floor(Math.random() * 2 + 1);
                        System.out.println(posibilidadDeDefensaExitosa);
                        if (posibilidadDeDefensaExitosa == 2) {
                            dialogo.setText("\n      ¡" + elegido.getNombre() + " no logró esquivar el ataque!");
                            Thread.sleep(1000);
                            SacudirPokemonElegido();
                            //Quitar vida a nuestro pokemon
                            vida.setBounds(93, 244, vida.getWidth() - (rival.getAtaque(al).getDaño() + 80), 15);
                            if (vida.getWidth() <= 0) {
                                JOptionPane.showMessageDialog(null, rival.getNombre() + " ha ganado la batalla");
                                volverAjugar();
                                Thread.sleep(40000);

                            }
                            if (vida.getWidth() <= 100) {
                                vida.setBackground(Color.RED);
                                HP.repaint();
                            }
                        } else {
                            dialogo.setText("\n      ¡Defensa exitosa!");
                            Thread.sleep(1000);
                            MoverPokemonElegido();
                        }
                        Thread.sleep(1000);
                        pnAtaques.add(btnpoder1);
                        pnAtaques.add(btnpoder2);
                        pnAtaques.add(btnpoder3);
                        pnAtaques.add(btnpoder4);
                        pnAtaques.repaint();

                    } catch (Exception e) {
                    }
                }
            });
            hilo.start();
            repaint();
        }
        if (btnpoder2 == e.getSource()) {
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Se muestra el poder escogido
                        dialogo.setText("\n      ¡¡" + elegido.getNombre() + " ha usado " + btnpoder2.getText() + "!!");
                        Thread.sleep(2000);
                        //Se ejecuta el ataque
                        dialogo.setText("\n      . . .");
                        //Se lanza el rayo
                        rayo.setIcon(new ImageIcon("imagen/" + elegido.getAtaque(2).getTipo() + ".png"));
                        LanzarRayo();
                        //Se muestra si el ataque fue exitoso, la animacion y se calcula el daño
                        int posibilidadDeAtaqueExitoso = (int) Math.floor(Math.random() * 2 + 1);
                        System.out.println(posibilidadDeAtaqueExitoso);
                        if (posibilidadDeAtaqueExitoso == 2) {
                            dialogo.setText("\n      ¡Ataque exitoso!");
                            Thread.sleep(1000);

                            SacudirPokemonRival();

                            //Daño al rival
                            vida2.setBounds(508, 95, vida2.getWidth() - (elegido.getAtaque(1).getDaño() + 40), 12);
                            if (vida2.getWidth() <= 0) {
                                JOptionPane.showMessageDialog(null, elegido.getNombre() + " ha ganado la batalla");
                                volverAjugar();
                            }
                            Thread.sleep(1000);
                            if (vida2.getWidth() <= 100) {
                                vida2.setBackground(Color.RED);
                            }
                        } else {
                            dialogo.setText("\n      ¡" + rival.getNombre() + " ha esquivado el ataque!");
                            Thread.sleep(1000);
                            MoverPokemonRival();
                        }
                        Thread.sleep(1000);
                        pnAtaques.remove(btnpoder1);
                        pnAtaques.remove(btnpoder2);
                        pnAtaques.remove(btnpoder3);
                        pnAtaques.remove(btnpoder4);
                        pnAtaques.repaint();

                        dialogo.setText("\n      " + rival.getNombre() + " prepara su ataque...");
                        Thread.sleep(1000);
                        int al = (int) Math.floor(Math.random() * rival.getAtaques().size());
                        dialogo.setText("\n      ¡¡" + rival.getNombre() + " ha usado " + rival.getAtaque(al).getNombre() + "!!");
                        Thread.sleep(2000);
                        rayo.setIcon(new ImageIcon("imagen/" + rival.getAtaque(al).getTipo() + ".png"));
                        
                        dialogo.setText("\n      . . .");
                        RecibirRayo();
                        
                        int posibilidadDeDefensaExitosa = (int) Math.floor(Math.random() * 2 + 1);
                        System.out.println(posibilidadDeDefensaExitosa);
                        if (posibilidadDeDefensaExitosa == 2) {
                            dialogo.setText("\n      ¡" + elegido.getNombre() + " no logró esquivar el ataque!");
                            Thread.sleep(1000);
                            SacudirPokemonElegido();
                            //Quitar vida a nuestro pokemon
                            vida.setBounds(93, 244, vida.getWidth() - (rival.getAtaque(al).getDaño() + 80), 15);
                            if (vida.getWidth() <= 0) {
                                JOptionPane.showMessageDialog(null, rival.getNombre() + " ha ganado la batalla");
                                volverAjugar();
                                Thread.sleep(40000);

                            }
                            if (vida.getWidth() <= 100) {
                                vida.setBackground(Color.RED);
                                HP.repaint();
                            }
                        } else {
                            dialogo.setText("\n      ¡Defensa exitosa!");
                            Thread.sleep(1000);
                            MoverPokemonElegido();
                        }
                        Thread.sleep(1000);
                        pnAtaques.add(btnpoder1);
                        pnAtaques.add(btnpoder2);
                        pnAtaques.add(btnpoder3);
                        pnAtaques.add(btnpoder4);
                        pnAtaques.repaint();

                    } catch (Exception e) {
                    }
                }
            });
            hilo.start();
            repaint();
        }
        if (btnpoder3 == e.getSource()) {
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Se muestra el poder escogido
                        dialogo.setText("\n      ¡¡" + elegido.getNombre() + " ha usado " + btnpoder3.getText() + "!!");
                        Thread.sleep(2000);
                        //Se ejecuta el ataque
                        dialogo.setText("\n      . . .");
                        //Se lanza el rayo
                        rayo.setIcon(new ImageIcon("imagen/" + elegido.getAtaque(3).getTipo() + ".png"));
                        LanzarRayo();
                        //Se muestra si el ataque fue exitoso, la animacion y se calcula el daño
                        int posibilidadDeAtaqueExitoso = (int) Math.floor(Math.random() * 2 + 1);
                        System.out.println(posibilidadDeAtaqueExitoso);
                        if (posibilidadDeAtaqueExitoso == 2) {
                            dialogo.setText("\n      ¡Ataque exitoso!");
                            Thread.sleep(1000);

                            SacudirPokemonRival();

                            //Daño al rival
                            vida2.setBounds(508, 95, vida2.getWidth() - (elegido.getAtaque(1).getDaño() + 40), 12);
                            if (vida2.getWidth() <= 0) {
                                JOptionPane.showMessageDialog(null, elegido.getNombre() + " ha ganado la batalla");
                                volverAjugar();
                            }
                            Thread.sleep(1000);
                            if (vida2.getWidth() <= 100) {
                                vida2.setBackground(Color.RED);
                            }
                        } else {
                            dialogo.setText("\n      ¡" + rival.getNombre() + " ha esquivado el ataque!");
                            Thread.sleep(1000);
                            MoverPokemonRival();
                        }
                        Thread.sleep(1000);
                        pnAtaques.remove(btnpoder1);
                        pnAtaques.remove(btnpoder2);
                        pnAtaques.remove(btnpoder3);
                        pnAtaques.remove(btnpoder4);
                        pnAtaques.repaint();

                        dialogo.setText("\n      " + rival.getNombre() + " prepara su ataque...");
                        Thread.sleep(1000);
                        int al = (int) Math.floor(Math.random() * rival.getAtaques().size());
                        dialogo.setText("\n      ¡¡" + rival.getNombre() + " ha usado " + rival.getAtaque(al).getNombre() + "!!");
                        Thread.sleep(2000);
                        rayo.setIcon(new ImageIcon("imagen/" + rival.getAtaque(al).getTipo() + ".png"));
                        
                        dialogo.setText("\n      . . .");
                        RecibirRayo();
                        int posibilidadDeDefensaExitosa = (int) Math.floor(Math.random() * 2 + 1);
                        System.out.println(posibilidadDeDefensaExitosa);
                        if (posibilidadDeDefensaExitosa == 2) {
                            dialogo.setText("\n      ¡" + elegido.getNombre() + " no logró esquivar el ataque!");
                            Thread.sleep(1000);
                            SacudirPokemonElegido();
                            //Quitar vida a nuestro pokemon
                            vida.setBounds(93, 244, vida.getWidth() - (rival.getAtaque(al).getDaño() + 80), 15);
                            if (vida.getWidth() <= 0) {
                                JOptionPane.showMessageDialog(null, rival.getNombre() + " ha ganado la batalla");
                                volverAjugar();
                                Thread.sleep(40000);

                            }
                            if (vida.getWidth() <= 100) {
                                vida.setBackground(Color.RED);
                                HP.repaint();
                            }
                        } else {
                            dialogo.setText("\n      ¡Defensa exitosa!");
                            Thread.sleep(1000);
                            MoverPokemonElegido();
                        }
                        Thread.sleep(1000);
                        pnAtaques.add(btnpoder1);
                        pnAtaques.add(btnpoder2);
                        pnAtaques.add(btnpoder3);
                        pnAtaques.add(btnpoder4);
                        pnAtaques.repaint();

                    } catch (Exception e) {
                    }
                }
            });
            hilo.start();
            repaint();
        }
        if (btnpoder4 == e.getSource()) {
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Se muestra el poder escogido
                        dialogo.setText("\n      ¡¡" + elegido.getNombre() + " ha usado " + btnpoder4.getText() + "!!");
                        Thread.sleep(2000);
                        //Se ejecuta el ataque
                        dialogo.setText("\n      . . .");
                        rayo.setIcon(new ImageIcon("imagen/" + elegido.getAtaque(4).getTipo() + ".png"));
                        //Se lanza el rayo
                        LanzarRayo();
                        //Se muestra si el ataque fue exitoso, la animacion y se calcula el daño
                        int posibilidadDeAtaqueExitoso = (int) Math.floor(Math.random() * 2 + 1);
                        System.out.println(posibilidadDeAtaqueExitoso);
                        if (posibilidadDeAtaqueExitoso == 2) {
                            dialogo.setText("\n      ¡Ataque exitoso!");
                            Thread.sleep(1000);

                            SacudirPokemonRival();

                            //Daño al rival
                            vida2.setBounds(508, 95, vida2.getWidth() - (elegido.getAtaque(1).getDaño() + 40), 12);
                            if (vida2.getWidth() <= 0) {
                                JOptionPane.showMessageDialog(null, elegido.getNombre() + " ha ganado la batalla");
                                volverAjugar();
                            }
                            Thread.sleep(1000);
                            if (vida2.getWidth() <= 100) {
                                vida2.setBackground(Color.RED);
                            }
                        } else {
                            dialogo.setText("\n      ¡" + rival.getNombre() + " ha esquivado el ataque!");
                            Thread.sleep(1000);
                            MoverPokemonRival();
                        }
                        Thread.sleep(1000);
                        pnAtaques.remove(btnpoder1);
                        pnAtaques.remove(btnpoder2);
                        pnAtaques.remove(btnpoder3);
                        pnAtaques.remove(btnpoder4);
                        pnAtaques.repaint();

                        dialogo.setText("\n      " + rival.getNombre() + " prepara su ataque...");
                        Thread.sleep(1000);
                        int al = (int) Math.floor(Math.random() * rival.getAtaques().size());
                        dialogo.setText("\n      ¡¡" + rival.getNombre() + " ha usado " + rival.getAtaque(al).getNombre() + "!!");
                        Thread.sleep(2000);
                        rayo.setIcon(new ImageIcon("imagen/" + rival.getAtaque(al).getTipo() + ".png"));
                        
                        dialogo.setText("\n      . . .");
                        RecibirRayo();

                        int posibilidadDeDefensaExitosa = (int) Math.floor(Math.random() * 2 + 1);
                        System.out.println(posibilidadDeDefensaExitosa);
                        if (posibilidadDeDefensaExitosa == 2) {
                            dialogo.setText("\n      ¡" + elegido.getNombre() + " no logró esquivar el ataque!");
                            Thread.sleep(1000);
                            SacudirPokemonElegido();
                            //Quitar vida a nuestro pokemon
                            vida.setBounds(93, 244, vida.getWidth() - (rival.getAtaque(al).getDaño() + 80), 15);
                            if (vida.getWidth() <= 0) {
                                JOptionPane.showMessageDialog(null, rival.getNombre() + " ha ganado la batalla");
                                volverAjugar();
                                Thread.sleep(40000);

                            }
                            if (vida.getWidth() <= 100) {
                                vida.setBackground(Color.RED);
                                HP.repaint();
                            }
                        } else {
                            dialogo.setText("\n      ¡Defensa exitosa!");
                            Thread.sleep(1000);
                            MoverPokemonElegido();
                        }
                        Thread.sleep(1000);
                        pnAtaques.add(btnpoder1);
                        pnAtaques.add(btnpoder2);
                        pnAtaques.add(btnpoder3);
                        pnAtaques.add(btnpoder4);
                        pnAtaques.repaint();

                    } catch (Exception e) {
                    }
                }
            });
            hilo.start();
            repaint();
        }

        //Acciones de los botones del menú
        if (btInicio == e.getSource()) {
            pnMenu.setVisible(true);
            pnInicio.setVisible(false);
            this.repaint();

        }
        //Botones del menú
        if (btBatalla == e.getSource()) {
            pnBatalla.setVisible(true);
            pnMenu.setVisible(false);
            this.repaint();
        }
        if (btAtras == e.getSource()) {

        }

        if (btAtras == e.getSource()) {
            pnBatalla.setVisible(false);
            pnMenu.setVisible(true);
            this.repaint();
        }

        if (btAtras2 == e.getSource()) {
            pnMenu.setVisible(true);
            pnOpciones.setVisible(false);

            this.repaint();
        }

        if (btOpciones == e.getSource()) {
            pnOpciones.setVisible(true);
            pnMenu.setVisible(false);
            this.repaint();
        }

        if (btSalir == e.getSource()) {
            System.exit(0);
            System.out.println("salir");
        }
    }

    public void cargarPokemones() {
        Pokemon raichu = new Pokemon("Raichu", 200);
        Pokemon blastoise = new Pokemon("Blastoise", 200);
        Pokemon venusaur = new Pokemon("Venusaur", 200);
        Pokemon articuno = new Pokemon("Articuno", 200);
        Pokemon rhyhorn = new Pokemon("Rhyhorn", 200);
        Pokemon mewtwo = new Pokemon("Mewtwo", 200);
        Pokemon charizard = new Pokemon("Charizard", 200);
        Pokemon absol = new Pokemon("Absol", 200);
        Pokemon electivire = new Pokemon("Electivire", 200);
        Pokemon feraligatr = new Pokemon("Feraligatr", 200);
        Pokemon pyroar = new Pokemon("Pyroar", 200);
        Pokemon magmortar = new Pokemon("Magmortar", 200);

        pokemones.add(pyroar);
        pokemones.add(articuno);

        pokemones.add(absol);
        pokemones.add(charizard);

        pokemones.add(mewtwo);

        pokemones.add(raichu);
        pokemones.add(blastoise);
        pokemones.add(venusaur);
        pokemones.add(electivire);
        pokemones.add(feraligatr);
        pokemones.add(magmortar);
        pokemones.add(rhyhorn);

        raichu.addAtaque("Impactrueno", 70, "electricidad");
        raichu.addAtaque("Látigo", 45, "electricidad");
        raichu.addAtaque("Onda Trueno", 40, "electricidad");
        raichu.addAtaque("Rayo", 50, "electricidad");

        blastoise.addAtaque("Doble filo", 70, "agua");
        blastoise.addAtaque("Rayo Burbuja", 45, "agua");
        blastoise.addAtaque("Hidrobomba", 40, "agua");
        blastoise.addAtaque("Mega puño", 50, "agua");

        venusaur.addAtaque("Drenadoras", 70, "planta");
        venusaur.addAtaque("Gigadrenado", 50, "planta");
        venusaur.addAtaque("Hoja afilada", 45, "planta");
        venusaur.addAtaque("Placaja", 70, "planta");

        mewtwo.addAtaque("Onda Mental", 40, "energia");
        mewtwo.addAtaque("Bola Sombra", 30, "viento");
        mewtwo.addAtaque("Rayo Hielo", 40, "hielo");
        mewtwo.addAtaque("Esfera Aural", 40, "hielo");

        articuno.addAtaque("Rayo Hielo", 55, "hielo");
        articuno.addAtaque("Agilidad", 55, "viento");
        articuno.addAtaque("Remolino", 45, "viento");
        articuno.addAtaque("Viento Cortante", 55, "viento");

        rhyhorn.addAtaque("Cornada", 40, "tierra");
        rhyhorn.addAtaque("Taladradora", 40, "tierra");
        rhyhorn.addAtaque("Terratemblor", 40, "tierra");
        rhyhorn.addAtaque("Pedrada", 40, "tierra");

        electivire.addAtaque("Campo Eléctrico", 50, "electricidad");
        electivire.addAtaque("Cortina Plasma", 50, "electricidad");
        electivire.addAtaque("Bola Voltio", 50, "electricidad");
        electivire.addAtaque("Onda Trueno", 50, "electricidad");

        feraligatr.addAtaque("Cola Férrea", 50, "agua");
        feraligatr.addAtaque("Hiperrayo", 50, "electricidad");
        feraligatr.addAtaque("Viento Hielo", 50, "hielo");
        feraligatr.addAtaque("Torbellino", 50, "viento");

        absol.addAtaque("Mofa", 50, "energia");
        absol.addAtaque("Canto Mortal", 50, "energia");
        absol.addAtaque("Tajo Umbrío", 50, "energia");
        absol.addAtaque("Ventisca", 50, "energia");

        charizard.addAtaque("Garra", 50, "fuego");
        charizard.addAtaque("Bola de fuego", 50, "fuego");
        charizard.addAtaque("Tajo Aéreo", 50, "fuego");
        charizard.addAtaque("Puño fuego", 50, "fuego");

        pyroar.addAtaque("Garra", 50, "fuego");
        pyroar.addAtaque("Bola de fuego", 50, "fuego");
        pyroar.addAtaque("Tajo Aéreo", 50, "fuego");
        pyroar.addAtaque("Puño fuego", 50, "fuego");

        magmortar.addAtaque("Bola de fuego", 40, "fuego");
        magmortar.addAtaque("Lava", 40, "fuego");
        magmortar.addAtaque("Incinerador", 40, "fuego");
        magmortar.addAtaque("Llamas", 40, "fuego");

    }

    public void volverAjugar() {

        vida.setBounds(93, 244, 274, 15);
        vida2.setBounds(508, 95, 200, 12);

        /*pnInicio.setVisible(false);
        pnMenu.setVisible(false);
        pnPokemones.setVisible(true);
        pnControl.setVisible(false);
        pnAtaques.setVisible(false);
        pnJuego.setVisible(false);
        pnOpciones.setVisible(false);*/
        pnJuego.setVisible(false);
        pnBatalla.setVisible(true);
        

        repaint();

    }

    public void SacudirPokemonRival() throws InterruptedException {
        sprite2.setLocation(x2 + 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2 - 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2 + 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2 - 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2 + 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2 - 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2 + 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2 - 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2 + 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2 - 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2 + 3, y2);
        Thread.sleep(50);
        sprite2.setLocation(x2, y2);
    }

    public void MoverPokemonRival() throws InterruptedException {
        sprite2.setLocation(x2 + 3, y2);
        Thread.sleep(20);
        sprite2.setLocation(x2 + 6, y2);
        Thread.sleep(60);
        sprite2.setLocation(x2 + 9, y2);
        Thread.sleep(20);
        sprite2.setLocation(x2 + 12, y2);
        Thread.sleep(60);
        sprite2.setLocation(x2 + 15, y2);
        Thread.sleep(20);
        sprite2.setLocation(x2 + 18, y2);
        Thread.sleep(60);
        sprite2.setLocation(x2 + 21, y2);
        Thread.sleep(20);
        sprite2.setLocation(x2 + 24, y2);
        Thread.sleep(60);
        sprite2.setLocation(x2 + 24, y2);
        Thread.sleep(20);
        sprite2.setLocation(x2 + 21, y2);
        Thread.sleep(60);
        sprite2.setLocation(x2 + 18, y2);
        Thread.sleep(20);
        sprite2.setLocation(x2 + 15, y2);
        Thread.sleep(60);
        sprite2.setLocation(x2 + 12, y2);
        Thread.sleep(20);
        sprite2.setLocation(x2 + 9, y2);
        Thread.sleep(60);
        sprite2.setLocation(x2 + 6, y2);
        Thread.sleep(20);
        sprite2.setLocation(x2 + 3, y2);
        Thread.sleep(60);
        sprite2.setLocation(x2, y2);
        Thread.sleep(20);
    }

    public void SacudirPokemonElegido() throws InterruptedException {
        sprite.setLocation(x - 5, y);
        Thread.sleep(50);
        sprite.setLocation(x + 5, y);
        Thread.sleep(50);
        sprite.setLocation(x - 5, y);
        Thread.sleep(50);
        sprite.setLocation(x + 5, y);
        Thread.sleep(50);
        sprite.setLocation(x - 5, y);
        Thread.sleep(50);
        sprite.setLocation(x + 5, y);
        Thread.sleep(50);
        sprite.setLocation(x - 5, y);
        Thread.sleep(50);
        sprite.setLocation(x + 5, y);
        Thread.sleep(50);
        sprite.setLocation(x, y);
        Thread.sleep(50);
    }

    public void MoverPokemonElegido() throws InterruptedException {
        sprite.setLocation(x, y);
        Thread.sleep(50);
        sprite.setLocation(x - 5, y);
        Thread.sleep(50);
        sprite.setLocation(x - 10, y);
        Thread.sleep(50);
        sprite.setLocation(x - 15, y);
        Thread.sleep(50);
        sprite.setLocation(x - 20, y);
        Thread.sleep(50);
        sprite.setLocation(x - 25, y);
        Thread.sleep(50);
        sprite.setLocation(x - 30, y);
        Thread.sleep(50);
        sprite.setLocation(x - 25, y);
        Thread.sleep(50);
        sprite.setLocation(x - 20, y);
        Thread.sleep(50);
        sprite.setLocation(x - 15, y);
        Thread.sleep(50);
        sprite.setLocation(x - 10, y);
        Thread.sleep(50);
        sprite.setLocation(x - 5, y);
        Thread.sleep(50);
        sprite.setLocation(x, y);
        Thread.sleep(50);
    }

    public void LanzarRayo() throws InterruptedException {
        rayo.setBounds(225, 340, 100, 100);
        pnArena.add(rayo);
        pnArena.repaint();

        rayo.setLocation(250, 325);
        Thread.sleep(60);
        rayo.setLocation(275, 310);
        Thread.sleep(60);
        rayo.setLocation(300, 300);
        Thread.sleep(60);
        rayo.setLocation(325, 290);
        Thread.sleep(60);
        rayo.setLocation(350, 275);
        Thread.sleep(60);
        rayo.setLocation(375, 260);
        Thread.sleep(60);
        rayo.setLocation(400, 250);
        Thread.sleep(60);
        rayo.setLocation(425, 240);
        Thread.sleep(60);
        rayo.setLocation(450, 225);
        Thread.sleep(60);
        rayo.setLocation(475, 210);
        Thread.sleep(60);
        rayo.setLocation(500, 200);
        Thread.sleep(60);
        rayo.setLocation(525, 190);
        Thread.sleep(60);
        rayo.setLocation(550, 175);
        Thread.sleep(60);

        pnArena.remove(rayo);
        pnArena.repaint();

        //Thread.sleep(1000);
    }

    public void RecibirRayo() throws InterruptedException {

        rayo.setBounds(550, 175, 100, 100);
        pnArena.add(rayo);
        pnArena.repaint();

        rayo.setLocation(525, 190);
        Thread.sleep(60);
        rayo.setLocation(500, 200);
        Thread.sleep(60);
        rayo.setLocation(475, 210);
        Thread.sleep(60);
        rayo.setLocation(450, 225);
        Thread.sleep(60);
        rayo.setLocation(425, 240);
        Thread.sleep(60);
        rayo.setLocation(400, 260);
        Thread.sleep(60);
        rayo.setLocation(375, 275);
        Thread.sleep(60);
        rayo.setLocation(350, 290);
        Thread.sleep(60);
        rayo.setLocation(325, 300);
        Thread.sleep(60);
        rayo.setLocation(300, 310);
        Thread.sleep(60);
        rayo.setLocation(275, 325);
        Thread.sleep(60);
        rayo.setLocation(250, 340);
        Thread.sleep(60);
        rayo.setLocation(225, 350);
        Thread.sleep(60);

        pnArena.remove(rayo);
        pnArena.repaint();

        //Thread.sleep(1000);
    }

    public static void IniciarMusica()
            throws Exception {

        String sonido = "sonido\\Pokemon.wav";
        InputStream in = new FileInputStream(sonido);
        AudioStream audio = new AudioStream(in);
        AudioPlayer.player.start(audio);

    }
}

//Colocar bien cada pokemon en su lugar ---echo---
//Colocar bien la esfera a ser lanzada ---echo---
//luego de acabar la batalla cargar los pokemones denuevo
//Implementar en opciones la opcion de quitar la musica
//Implementar sonidos cuando se lanza un poder

