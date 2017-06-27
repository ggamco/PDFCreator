package com.pdfcreator.modelos;

public class Emisor {
	
	private String nombre;
	private String direccion;
	private int zipCode;
	private String ciudad;
	private String cif;
	private String fecha;
	private String iban;
	
	public Emisor() {
		super();
	}

	public Emisor(String nombre, String direccion, int zipCode, String ciudad, String cif,
			String fecha, String iban) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.zipCode = zipCode;
		this.ciudad = ciudad;
		this.cif = cif;
		this.fecha = fecha;
		this.iban = iban;
	}
	
	public String[] toArray() {
		String[] datos = {fecha, nombre, cif, getDireccionDocumento(), iban};
		return datos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}
	
	public String getDireccionDocumento() {
		return direccion + ", " + zipCode + " - " + ciudad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	
}
