package com.pdfcreator.modelos;

import java.util.List;

import com.pdfcreator.utiles.TipoDocumento;

public class Documento {

	private int tipoDocumento;
	private int numeroDocumento;
	private String logo;
	private Emisor emisor;
	private Receptor receptor;
	private List<Producto> listaProductos;

	public Documento() {
		super();
	}

	public Documento(int tipoDocumento, int numeroDocumento, Emisor emisor, Receptor receptor,
			List<Producto> listaProductos) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.emisor = emisor;
		this.receptor = receptor;
		this.listaProductos = listaProductos;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public TipoDocumento getTipoDocumento() {
		
		TipoDocumento type = TipoDocumento.UNKWOWN;
		
		switch(tipoDocumento){
			case 0: type = TipoDocumento.PRESUPUESTO; break;
			case 1: type = TipoDocumento.FACTURA;
		}
		
		return type;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Emisor getEmisor() {
		return emisor;
	}

	public void setEmisor(Emisor emisor) {
		this.emisor = emisor;
	}

	public Receptor getReceptor() {
		return receptor;
	}

	public void setReceptor(Receptor receptor) {
		this.receptor = receptor;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

}
