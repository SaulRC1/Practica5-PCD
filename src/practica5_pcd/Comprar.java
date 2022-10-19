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
public class Comprar extends Thread {

    private Tienda miTienda;
    private CanvasGenerador cv;

    public Comprar(Tienda T, CanvasGenerador cv) {
        miTienda = T;
        this.cv = cv;
    }

    @Override
    public void run() {
        try {
            System.out.println("Entro a Comprar con id: " + this.getId());
            Random aleatorio = new Random();
            cv.representaComprador((int) this.getId());
            Thread.sleep((aleatorio.nextInt(3) + 1) * 1000);
            char Quien = miTienda.EntraComprar((int) this.getId());

            Thread.sleep((aleatorio.nextInt(3) + 1) * 1000);
            miTienda.SaleComprar(Quien);
            if (Quien == 'v') {
                cv.borraVendedor();
            }
            else{
                cv.borraTecnico();
            }
            System.out.println("Salgo de Comprar con id: " + this.getId());
        } catch (InterruptedException ex) {
            Logger.getLogger(Comprar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
