package com.pdfcreator.utiles;

/**
 *
 * @author ggamboa
 */
public enum TipoDocumento{
    PRESUPUESTO("Presupuesto"),
    FACTURA("Factura"),
    UNKWOWN("Desconocido");
    
    private String tipo;
    
    private TipoDocumento(String tipo){
        this.tipo = tipo;
    }
    
    public String tipo(){
        return tipo;
    }
    
}
