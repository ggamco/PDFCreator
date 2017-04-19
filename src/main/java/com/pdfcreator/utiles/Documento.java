/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdfcreator.utiles;

/**
 *
 * @author ggamboa
 */
public enum Documento{
    PRESUPUESTO("Presupuesto"),
    FACTURA("Factura");
    
    private String tipo;
    
    private Documento(String tipo){
        this.tipo = tipo;
    }
    
    public String tipo(){
        return tipo;
    }
    
}
