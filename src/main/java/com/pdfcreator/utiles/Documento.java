package com.pdfcreator.utiles;

/**
 *
 * @author ggamboa
 */
public enum Documento{
    PRESUPUESTO("Presupuesto"),
    FACTURA("Factura"),
    UNKWOWN("Desconocido");
    
    private String tipo;
    
    private Documento(String tipo){
        this.tipo = tipo;
    }
    
    public String tipo(){
        return tipo;
    }
    
}
