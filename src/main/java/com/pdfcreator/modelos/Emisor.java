package com.pdfcreator.modelos;

public class Emisor {

    private String nombre;
    private String direccion;
    private int zipCode;
    private String ciudad;
    private String cif;
    private String fechaEmision;
    private String iban;

    public Emisor() {
        super();
    }

    public Emisor(String nombre, String direccion, int zipCode, String ciudad, String cif, String iban) {
        super();
        this.nombre = nombre;
        this.direccion = direccion;
        this.zipCode = zipCode;
        this.ciudad = ciudad;
        this.cif = cif;
        this.iban = iban;
    }

    public String[] toArray() {
        String[] datos = {nombre, cif, getDireccionDocumento(), iban};
        return datos;
    }

    public String getDireccionDocumento() {
        return direccion + ", " + zipCode + " - " + ciudad;
    }

}
