/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdfcreator.servlets;

import com.pdfcreator.modelos.Producto;
import com.pdfcreator.utiles.Documento;
import com.pdfcreator.utiles.FacturaTemplate;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ggamboa
 */
public class PDF extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Producto> lista = new ArrayList<>();
        
        for (int i = 0; i < 18; i++) {
            
            Producto p = null;
            
            if(i%2 == 0){
                p = new Producto("HJAVA", "Hora imapartiendo clase de JAVA", (12 + (i*3)), 1 + i, 0.21f, 0.07f, false, false);
            } else{
                p = new Producto("HJAVA", "Hora imapartiendo clase de JAVA", (12 + (i*3)), 1 + i, 0.21f, 0.07f, false, false);
            }
            
            lista.add(p);
        }
        
        
        String path = getServletContext().getRealPath("/template");
        FacturaTemplate factura = new FacturaTemplate(path, lista);
        
        ByteArrayOutputStream baos = factura.CrearDocumento(Documento.PRESUPUESTO);
        
        resp.setHeader("Expires", "0");
        resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        resp.setHeader("Pragma", "public");
        resp.addHeader("Content-Disposition", "attachment; filename="+Documento.PRESUPUESTO.tipo());

        resp.setContentType("application/pdf");

        resp.setContentLength(baos.size());

        ServletOutputStream out = resp.getOutputStream();
        baos.writeTo(out);
        out.flush();

    }

}
