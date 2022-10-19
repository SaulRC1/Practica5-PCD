/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5_pcd;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Reparar implements Runnable {
    private Tienda miTienda;
    private CanvasGenerador cv;

    public Reparar(Tienda miTienda, CanvasGenerador cv) {
        this.miTienda = miTienda;
        this.cv = cv;
    }
    
    
    @Override
    public void run() {
        try {
            System.out.println("Soy un hilo Reparar con id: " + Thread.currentThread().getId());
            Random aleatorio = new Random();
            cv.representaReparador((int)Thread.currentThread().getId());
            Thread.sleep((aleatorio.nextInt(3) + 1)*1000);
            miTienda.EntraReparar((int)Thread.currentThread().getId());
            Thread.sleep((aleatorio.nextInt(3) + 1)*1000);
            miTienda.SaleReparar();
            cv.borraTecnico();
            System.out.println("Salgo de Reparar con id: " + Thread.currentThread().getId());
        } catch (InterruptedException ex) {
            Logger.getLogger(Reparar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
