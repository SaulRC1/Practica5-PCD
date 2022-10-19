/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5_pcd;

/**
 *
 * @author usuario
 */
public class Tienda {
    
    boolean Vendedor_Libre = true;
    boolean Tecnico_Libre = true;
    boolean Tecnico_Atiende = false;
    private CanvasGenerador cv;
    int ncompradores = 0;
    
    public Tienda(CanvasGenerador cv){
        this.cv = cv;
    }
    
    public synchronized void EntraReparar(int id) throws InterruptedException{
        while(!Tecnico_Libre || ncompradores > 2){
            wait();
        }
        
        Tecnico_Libre = false;
        cv.insertaTecnico(id, 'r');
        
    }
    
    public synchronized void SaleReparar(){
        Tecnico_Libre = true;
        
        notifyAll();
    }
    
    public synchronized char EntraComprar(int id) throws InterruptedException{
        ncompradores++;
        char Quien;
        while(!Vendedor_Libre && (!Tecnico_Libre || ncompradores > 2)){
            wait();
        }
        ncompradores--;
        if(Vendedor_Libre){
            Vendedor_Libre = false;
            Quien = 'v';
            cv.insertaVendedor(id);
            
        }
        else{
            Tecnico_Libre = false;
            Quien = 't';
            cv.insertaTecnico(id, 'c');
        }
        
        ncompradores--;
        return Quien;
    }
    
    public synchronized void SaleComprar(char Liberar){
        if(Liberar == 'v'){
            Vendedor_Libre = true;
        }
        else{
            Tecnico_Libre = true;
        }
        
        notifyAll();
    }
    
}
