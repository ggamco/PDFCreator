package com.pdfcreator.utiles;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdfcreator.exceptions.ListaProductosVacia;
import com.pdfcreator.modelos.Documento;
import com.pdfcreator.modelos.Producto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.pdfcreator.logica.CalculosFacturas.*;
import java.text.DecimalFormat;
import java.util.Base64;

/**
 *
 * @author ggamboa
 */
public class FacturaTemplate {

    private static PdfWriter writer;
    private DecimalFormat df = new DecimalFormat("0.00");
    
    private float SUBTOTAL;
    private float IVA;
    private float IRPF;
    private float TOTAL;
    
    private ByteArrayOutputStream baos;
    private Document document;
    private Font fuenteRegular;
    private Font fuenteSemiBold;
    
    /**
     *
     * @param path -> Ruta a los recursos.
     * @param lista -> Productos facturados/presupuestados
     */
    public FacturaTemplate(String path) {
        //registramos las fuentes personalizadas
        FontFactory.register(path + "/font/Open_Sans/OpenSans-Light.ttf", "OpenSans_light");
        FontFactory.register(path + "/font/Open_Sans/OpenSans-Regular.ttf", "OpenSans_regular");
        FontFactory.register(path + "/font/Open_Sans/OpenSans-Semibold.ttf", "OpenSans_semibold");
        FontFactory.register(path + "/font/Open_Sans/OpenSans-Bold.ttf", "OpenSans_bold");

        this.fuenteRegular = FontFactory.getFont("OpenSans_regular", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10);
        this.fuenteSemiBold = FontFactory.getFont("OpenSans_semibold", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10);
    }

    public ByteArrayOutputStream CrearDocumento(Documento documento) throws ListaProductosVacia {

        this.baos = new ByteArrayOutputStream();
        this.document = new Document();
        
        try {
            writer = PdfWriter.getInstance(document, baos);
        } catch (DocumentException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
        
        document.open();
        try{
        	addTitulo(documento.getTipoDocumento().tipo());
        	cargarLogo(documento);
        }catch(Exception e){
        	e.printStackTrace();
        }
        crearGraficos();
        crearTablaProductos();
        crearTablaDesglose();
        try{
        	cargarTextosBase(documento.getTipoDocumento().tipo());
        	cargarTextosFactura(documento);
        	addProductos(documento);
        }catch(Exception e){
        	e.printStackTrace();
        }
        document.close();

        return baos;
    }

    private void addTitulo(String titulo) {
    	System.out.println(titulo);
    	document.addTitle(titulo);
    }

    private void cargarLogo(Documento documento) {

        try {
            Image image = Image.getInstance(Base64.getDecoder().decode(documento.getLogo()));
            System.out.println(documento.getLogo());
            float aspectRatio = ((image.getWidth() * 60) / image.getHeight());
            image.scaleAbsolute(aspectRatio, 60f);
            image.setAbsolutePosition(36f, 750f);
            document.add(image);
        } catch (BadElementException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        } catch (DocumentException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
    }

    private void crearGraficos() {

        PdfContentByte cb = writer.getDirectContent();

        //Rectangulo gris cabecera de la factura
        Rectangle rec = new Rectangle(0, 540, 596, 720);
        rec.setBackgroundColor(new GrayColor(0.90f));
        rec.setBorderColorTop(new GrayColor(0.80f));
        rec.setBorderWidthTop(4f);
        cb.rectangle(rec);

        //Preparamos el grafico de rombo 
        float rotate = 45;
        float width = 22;
        float height = 22;
        float x = 298;
        float y = 528;

        float angle = (float) (-rotate * (Math.PI / 180));
        float fxScale = (float) (Math.cos(angle));
        float fyScale = (float) (Math.cos(angle));
        float fxRote = (float) (-Math.sin(angle));
        float fyRote = (float) (Math.sin(angle));

        //Creamos la plantilla para este rombo
        PdfTemplate template = writer.getDirectContent().createTemplate(width, height);

        //Creamos el rombo
        Rectangle rombo = new Rectangle(0, 0, width, height);
        template.rectangle(0, 0, width, height);
        rombo.setBackgroundColor(new GrayColor(0.90f));
        template.rectangle(rombo);
        template.fill();

        //Introducimos el rombo
        writer.getDirectContent().addTemplate(template, fxScale, fxRote, fyRote, fyScale, x, y);
    }

    /**
     *
     * @param tipoDocumento -> Factura o Presupuesto
     */
    private void cargarTextosBase(String tipo) {
    	System.out.println(tipo);
        cargarTituloDocumento(tipo);
        cargarTextosReferencias(tipo);
    }

    private void cargarTituloDocumento(String tipo) {
        PdfContentByte title = writer.getDirectContent();

        Font fontTitulo = FontFactory.getFont("OpenSans_light", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0);
        BaseFont bfTitulo = fontTitulo.getBaseFont();

        title.saveState();
        title.beginText();
        title.setFontAndSize(bfTitulo, 22);
        title.showTextAligned(2, tipo, 570, 690, 0);
        title.endText();
        title.restoreState();
    }

    private void cargarTextosReferencias(String tipo) {
        PdfContentByte ref = writer.getDirectContent();
        System.out.println(tipo);
        BaseFont bfTitulo = fuenteSemiBold.getBaseFont();
        ref.saveState();
        ref.beginText();
        ref.setFontAndSize(bfTitulo, 10);
        ref.setColorFill(new GrayColor(0.2f));
        ref.showTextAligned(0, tipo + " Nº:", 36, 700, 0);
        ref.showTextAligned(0, "Fecha:", 36, 685, 0);
        ref.showTextAligned(0, "Nombre:", 36, 670, 0);
        ref.showTextAligned(0, "NIF:", 36, 655, 0);
        ref.showTextAligned(0, "Dirección:", 36, 640, 0);
        ref.showTextAligned(0, "Cuenta IBAN:", 36, 625, 0);

        ref.showTextAligned(0, "Cliente:", 36, 600, 0);

        ref.endText();
        ref.restoreState();
    }

    public void cargarTextosFactura(Documento documento) {

    	String[] datos = documento.getEmisor().toArray();
    	String[] cliente = documento.getReceptor().toArray();
    	String numeroDocumento = String.valueOf(documento.getNumeroDocumento());
    	
    	for(String dato : datos){
    		System.out.println(dato);
    	}
    	
    	for(String dato : cliente){
    		System.out.println(dato);
    	}
    	
        PdfContentByte ref = writer.getDirectContent();
        BaseFont bf = fuenteRegular.getBaseFont();
        ref.saveState();
        ref.beginText();
        ref.setFontAndSize(bf, 10);
        ref.setColorFill(new GrayColor(0.35f));

        ref.showTextAligned(0, numeroDocumento, 136, 700, 0);
        
        for (int i = 0; i < datos.length; i++) {
            if (datos[i] != null) {
                ref.showTextAligned(0, datos[i], 136, (685 - (i * 15)), 0);
            }
        }

        for (int i = 0; i < cliente.length; i++) {
            if (cliente[i] != null) {
                ref.showTextAligned(0, cliente[i], 136, (600 - (i * 15)), 0);
            }
        }

        ref.endText();
        ref.restoreState();

    }

    private void crearTablaProductos() {
        PdfContentByte cb = writer.getDirectContent();

        //Rectangulo gris cabecera de la factura
        Rectangle rec = new Rectangle(0, 490, 596, 520);
        rec.setBackgroundColor(BaseColor.WHITE);
        rec.setBorderColorTop(new GrayColor(0.80f));
        rec.setBorderColorBottom(new GrayColor(0.90f));
        rec.setBorderWidthTop(1.25f);
        rec.setBorderWidthBottom(0.5f);
        cb.rectangle(rec);

        PdfContentByte ref = writer.getDirectContent();

        BaseFont bfTitulo = fuenteRegular.getBaseFont();
        ref.setColorFill(new GrayColor(0.2f));
        ref.saveState();
        ref.beginText();
        ref.setFontAndSize(bfTitulo, 8);

        ref.showTextAligned(0, "Código", 36, 502, 0);
        ref.showTextAligned(0, "Descripción", 100, 502, 0);
        ref.showTextAligned(2, "Cantidad", 430, 502, 0);
        ref.showTextAligned(2, "Precio", 500, 502, 0);
        ref.showTextAligned(2, "Importe", 570, 502, 0);
        ref.endText();
        ref.restoreState();
    }
    
    private void crearTablaDesglose() {
        PdfContentByte cb = writer.getDirectContent();

        //Rectangulo gris cabecera de la factura
        Rectangle rec = new Rectangle(0, 60, 596, 200);
        rec.setBackgroundColor(BaseColor.WHITE);
        rec.setBorderColorTop(new GrayColor(0.80f));
        //rec.setBorderColorBottom(new GrayColor(0.98f));
        rec.setBorderWidthTop(1.25f);
        //rec.setBorderWidthBottom(0.5f);
        cb.rectangle(rec);
        
        //Rectangulo gris cabecera de la factura
        Rectangle rec2 = new Rectangle(370, 70, 580, 105);
        rec2.setBackgroundColor(new GrayColor(0.90f));
        cb.rectangle(rec2);

        PdfContentByte ref = writer.getDirectContent();

        BaseFont bfTitulo = fuenteRegular.getBaseFont();
        ref.setColorFill(new GrayColor(0.2f));
        ref.saveState();
        ref.beginText();
        ref.setFontAndSize(bfTitulo, 8);

        ref.showTextAligned(0, "* Indica un concepto exento de impuestos. ** Indica un concepto parcialmente sujeto a impuestos", 36, 185, 0);
        
        ref.showTextAligned(2, "Subtotal", 430, 160, 0);
        ref.showTextAligned(2, "IVA (21.00%)", 430, 140, 0);
        ref.showTextAligned(2, "IRPF (7.00%)", 430, 120, 0);
        
        bfTitulo = fuenteSemiBold.getBaseFont();
        ref.setFontAndSize(bfTitulo, 20);
        ref.showTextAligned(2, "Total", 430, 80, 0);
        ref.endText();
        ref.restoreState();
    }

    private void addProductos(Documento documento) throws ListaProductosVacia {
        
        PdfContentByte ref = writer.getDirectContent();

        BaseFont bfTitulo = fuenteRegular.getBaseFont();
        ref.setColorFill(new GrayColor(0.2f));
        ref.saveState();
        ref.beginText();
        ref.setFontAndSize(bfTitulo, 8);
        if (documento.getListaProductos() != null){
	        for (int i = 0; i < documento.getListaProductos().size(); i++) {
	            
	            Producto p = documento.getListaProductos().get(i);
	            
	            ref.showTextAligned(0, p.getCodigo(), 36, 470 - (i*15), 0);
	            ref.showTextAligned(0, p.getDescripcion(), 100, 470 - (i*15), 0);
	            ref.showTextAligned(2, df.format(p.getCantidad()), 430, 470 - (i*15), 0);
	            ref.showTextAligned(2, df.format(p.getPrecio()).concat(" €"), 500, 470 - (i*15), 0);
	            
	            String importe = df.format(p.getImporte()).concat(" €");
	            
	            if(p.isExentoIVA() | p.isExentoIRPF()) {
	                if(p.isExentoIRPF() && p.isExentoIVA()){
	                    importe = importe.concat(" **");
	                }else{
	                    importe = importe.concat(" *");
	                }
	            }
	            
	            ref.showTextAligned(2, importe, 570, 470 - (i*15), 0);
	        }
	        
	        SUBTOTAL = calcularSUBTOTAL(documento.getListaProductos());
	        IVA = calcularIVA(documento.getListaProductos());
	        IRPF = calcularIRPF(documento.getListaProductos());
	        TOTAL = SUBTOTAL + IVA - IRPF;
	        
	        ref.showTextAligned(2, df.format(SUBTOTAL).concat(" €"), 570, 160, 0);
	        ref.showTextAligned(2, df.format(IVA).concat(" €"), 570, 140, 0);
	        ref.showTextAligned(2, df.format(IRPF).concat(" €"), 570, 120, 0);
        } else {
        	throw new ListaProductosVacia("Lista vacia");
        }
        bfTitulo = fuenteSemiBold.getBaseFont();
        ref.setFontAndSize(bfTitulo, 20);
        ref.showTextAligned(2, df.format(TOTAL).concat(" €"), 570, 80, 0);
        
        ref.endText();
        ref.restoreState();
    }

}
