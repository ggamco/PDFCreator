package com.pdfcreator.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import com.pdfcreator.utiles.TipoDocumento;

/**
 *
 * @author ggamboa
 */
public class PDF extends HttpServlet {

    private static final long serialVersionUID = 3060041056106176275L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        String line;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Documento documento = null;

        try {
            while ((line = req.getReader().readLine()) != null) {
                sb.append(line);
            }
            documento = gson.fromJson(sb.toString(), Documento.class);
        }
        catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
        }

        String path = getServletContext().getRealPath("/template");
        FacturaTemplate factura = new FacturaTemplate(path);

        ByteArrayOutputStream baos;

        try {
            baos = factura.crearDocumento(documento);
            StringBuilder fileName = new StringBuilder().append(documento.getNumeroDocumento()).append("_")
                    .append(LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())).append("_")
                    .append(TipoDocumento.resolveCode(documento.getTipoDocumento())).append(".pdf");
            resp.setHeader("Expires", "0");
            resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            resp.setHeader("Pragma", "public");
            resp.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            resp.setContentType("application/pdf");

            resp.setContentLength(baos.size());

            ServletOutputStream out = resp.getOutputStream();
            baos.writeTo(out);
            out.flush();
        }
        catch (ListaProductosVacia ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            resp.sendRedirect("./?error=listaVacia");
        }

    }

}
