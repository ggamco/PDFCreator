package com.pdfcreator.modelos;

/**
 *
 * @author ggamboa
 */
public class Producto {
    
    private String codigo;
    private String descripcion;
    private float cantidad;
    private float precio;
    private float IVA;
    private float IRPF;
    private boolean exentoIVA;
    private boolean exentoIRPF;

    public Producto(String codigo, String descripcion, float cantidad, float precio, float IVA, float IRPF, boolean exentoIVA, boolean exentoIRPF) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.IVA = IVA;
        this.IRPF = IRPF;
        this.exentoIVA = exentoIVA;
        this.exentoIRPF = exentoIRPF;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getIVA() {
        return IVA;
    }

    public void setIVA(float IVA) {
        this.IVA = IVA;
    }

    public float getIRPF() {
        return IRPF;
    }

    public void setIRPF(float IRPF) {
        this.IRPF = IRPF;
    }

    public float getImporte() {
        return cantidad * precio;
    }

    public boolean isExentoIVA() {
        return exentoIVA;
    }

    public void setExentoIVA(boolean exentoIVA) {
        this.exentoIVA = exentoIVA;
    }

    public boolean isExentoIRPF() {
        return exentoIRPF;
    }

    public void setExentoIRPF(boolean exentoIRPF) {
        this.exentoIRPF = exentoIRPF;
    }
    
    
    
}
