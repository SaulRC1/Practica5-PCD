/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5_pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author SaulRC1
 */
public class CanvasGenerador extends Canvas {

    private ArrayList<String> Cola_de_Espera;

    private String Tecnico;
    private String Vendedor;

    public CanvasGenerador() {
        super();
        Cola_de_Espera = new ArrayList<>();

        Tecnico = null;
        Vendedor = null;
        setBackground(Color.DARK_GRAY);
        this.setSize(1280, 768);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Image offscreen = createImage(this.getWidth(), this.getHeight());
        Graphics bg = offscreen.getGraphics();
        int separacion = 0;

        bg.setColor(Color.white);
        Font f1 = new Font("Courier", Font.BOLD, 70);
        Font f2 = new Font("Courier", Font.BOLD, 30);
        bg.setFont(f1);
        bg.drawString("Vendedor", 250, 125);
        bg.drawString("Tecnico", 250, 275);
        bg.fillRect(50, 50, 100, 100);

        bg.fillRect(50, 200, 100, 100);

        for (int i = 0; i < 10; i++) {
            bg.fillRect(separacion + 100, 400, 100, 100);
            separacion += 110;
        }
        
        bg.setFont(f2);
        bg.setColor(Color.black);
        separacion = 0;
        for (int i = 0; i < Cola_de_Espera.size(); i++) {
            bg.drawString(Cola_de_Espera.get(i), separacion + 110, 450);
            separacion += 100;
        }
        
        if(Tecnico != null){
            bg.drawString(Tecnico, 65, 275);
        }
        
        if(Vendedor != null){
            bg.drawString(Vendedor, 65, 120);
        }
        
        bg.setColor(Color.white);
        bg.setFont(f1);
        bg.drawString("Cola de Espera", 400, 600);

        g.drawImage(offscreen, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public synchronized void representaComprador(int idComprador) {
        Cola_de_Espera.add("C[" + idComprador + "]");
        repaint();
    }

    public synchronized void representaReparador(int idReparador) {
        Cola_de_Espera.add("R[" + idReparador + "]");
        repaint();
    }

    /*public void borraEspera(){
        Cola_de_Espera.remove(0);
    }*/
    public synchronized void insertaTecnico(int id, char tipo) {

        if (tipo == 'c') {
            Tecnico = "C[" + id + "]";
            Cola_de_Espera.remove("C[" + id + "]");
        } else {
            Tecnico = "R[" + id + "]";
            Cola_de_Espera.remove("R[" + id + "]");
        }
        
        repaint();

    }
    
    public synchronized void borraTecnico(){
        Tecnico = null;
        repaint();
    }

    public synchronized void insertaVendedor(int id) {
        Vendedor = "C[" + id + "]";
        Cola_de_Espera.remove("C[" + id + "]");
        repaint();
    }
    
    public synchronized void borraVendedor(){
        Vendedor = null;
        repaint();
    }

}
