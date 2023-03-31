package com.pdfcreator.modelos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    private int tipoDocumento;
    private int numeroDocumento;
    private String sufijo;
    private String fechaEmision;
    private String fechaValidez;
    private String nota;
    private String logo;
    private Emisor emisor;
    private Receptor receptor;
    private List<Producto> listaProductos;

}
