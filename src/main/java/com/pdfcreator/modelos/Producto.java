package com.pdfcreator.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private String codigo;
    private String descripcion;
    private float cantidad;
    private float precio;
    private float iva;
    private float irpf;
    private boolean exentoIVA;
    private boolean exentoIRPF;

    public float getImporte() {
        return cantidad * precio;
    }

}
