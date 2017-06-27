package com.pdfcreator.modelos;

public class Receptor {
	
	private String nombre;
	private String direccion;
	private int zipCode;
	private String ciudad;
	private String cif;
	private String email;
	
	public Receptor() {
		super();
	}

	public Receptor(String nombre, String direccion, int zipCode, String ciudad, String cif, String email) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.zipCode = zipCode;
		this.ciudad = ciudad;
		this.cif = cif;
		this.email = email;
	}
	
	public String[] toArray() {
		String[] cliente = {nombre, direccion, getDireccionLineaDos(), cif};
		return cliente;
	}
	
	private String getDireccionLineaDos() {
		return zipCode + " - " + ciudad;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
