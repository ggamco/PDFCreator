package com.pdfcreator.logica;

import com.pdfcreator.modelos.Producto;
import java.util.List;

/**
 *
 * @author ggamboa
 */
public class CalculosFacturas {
    
    public static float calcularIVA(List<Producto> productos){
        
        float iva = 0;
        
        for (Producto p : productos) {
            
            if(!p.isExentoIVA()){
                
                iva += p.getIVA() * p.getImporte();
                
            }
            
        }
                
        return iva;
    }
    
    public static float calcularIRPF(List<Producto> productos){
        
        float irpf = 0;
        
        for (Producto p : productos) {
            
            if(!p.isExentoIRPF()){
                
                irpf += p.getIRPF() * p.getImporte();
                
            }
            
        }
        
        return irpf;
    }
    
    public static float calcularSUBTOTAL(List<Producto> productos){
        
        float total = 0;
        
        for (Producto p : productos) {
                            
                total += p.getImporte();
                            
        }
        
        return total;
    }
    
}
