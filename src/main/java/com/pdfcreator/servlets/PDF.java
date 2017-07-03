package com.pdfcreator.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pdfcreator.exceptions.ListaProductosVacia;
import com.pdfcreator.modelos.Documento;
import com.pdfcreator.utiles.FacturaTemplate;

/**
 *
 * @author ggamboa
 */
public class PDF extends HttpServlet {

    private static final long serialVersionUID = 3060041056106176275L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Estoy llamando al server PDF");
		
		String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		StringBuilder sb = new StringBuilder();
		String line;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Documento documento = null;
		
		try{
			System.out.println("entro en el try...");
			while((line = req.getReader().readLine()) != null){
				System.out.println("entro en el while");
				sb.append(line);
				System.out.println(sb.toString());
			}
			
			documento = (Documento) gson.fromJson(sb.toString(), Documento.class);
			System.out.print(documento.getLogo());
			
		}catch(Exception e){
			System.out.println("exception: " + e.getMessage());
		}
		
		String path = getServletContext().getRealPath("/template");
        FacturaTemplate factura = new FacturaTemplate(path);

        ByteArrayOutputStream baos;
        
        try {
            baos = factura.CrearDocumento(documento);
            //StringBuilder fileName = new StringBuilder().append(documento.getNumeroDocumento()).append("_").append(fecha).append("_").append(documento.getTipoDocumento().tipo()).append(".pdf");
            resp.setHeader("Expires", "0");
            resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            resp.setHeader("Pragma", "public");
            //resp.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            resp.addHeader("Content-Disposition", "attachment; filename=archivo.pdf");
            resp.setContentType("application/pdf");

            resp.setContentLength(baos.size());

            ServletOutputStream out = resp.getOutputStream();
            baos.writeTo(out);
            out.flush();
        } catch (ListaProductosVacia ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            resp.sendRedirect("./?error=listaVacia");
        }

    }

}
/*ESTRUCTURA JSON DATOS ENVIADOS
{
	"tipoDocumento": 1,
	"numeroDocumento": 1,
	"logo": "base64string",
	"emisor": {
		"nombre": "Gustavo Gamboa Cordero",
		"direccion": "Calle de la Aurora 29",
		"zipCode": 28760,
		"ciudad": "Tres Cantos",
		"cif": "X12345678",
		"fecha": "27/06/2017",
		"iban": "ES99 1234 5678 9012 3456 7890"
	},
	"receptor": {
		"nombre": "Vass",
		"direccion": "Avenida de Europa 1",
		"zipCode": 28080,
		"ciudad": "Alcobendas",
		"cif": "B12345678",
		"email": "gustavo.gamboa@mad.vass.es"
	},
	"listaProductos": [
		{
			"codigo": "HJAON",
			"descripcion": "Hora de clase impartida al grupo T119. Incluye 7 alumnos ONLINE",
			"cantidad": 20.00,
			"precio": 37.00,
			"IVA": 0.00,
			"IRPF": 7.00,
			"exentoIVA": true,
			"exentoIRPF": false
		},
		{
			"codigo": "HJAVA",
			"descripcion": "Hora de clase impartida al grupo M37.",
			"cantidad": 18.00,
			"precio": 30.00,
			"IVA": 0.00,
			"IRPF": 7.00,
			"exentoIVA": true,
			"exentoIRPF": false
		}
	]	
}
*/