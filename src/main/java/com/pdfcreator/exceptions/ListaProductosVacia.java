package com.pdfcreator.exceptions;

/**
 *
 * @author gustavogamboa
 */
public class ListaProductosVacia extends Exception{

    private static final long serialVersionUID = -7093395127661232747L;
	
    private String mensaje;

    public ListaProductosVacia() {
        this.mensaje = "La lista de productos introducida está vacía. Por favor revisa la carga de la colección.";
    }
    
    public ListaProductosVacia(String message) {
        super(message);
        this.mensaje = message;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
    
    
    
}
