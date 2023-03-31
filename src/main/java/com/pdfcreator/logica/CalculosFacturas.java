package com.pdfcreator.logica;

import com.pdfcreator.exceptions.ListaProductosVacia;
import com.pdfcreator.modelos.Producto;
import java.util.List;

/**
 *
 * @author ggamboa
 */
public class CalculosFacturas {

    public static float calcularIVA(List<Producto> productos) throws ListaProductosVacia {

        float iva = 0;

        if (productos != null) {
            for (Producto p : productos) {

                if (!p.isExentoIVA()) {

                    iva += p.getIva() * p.getImporte() / 100;

                }

            }
        } else {
            throw new ListaProductosVacia();
        }

        return iva;
    }

    public static float calcularIRPF(List<Producto> productos) throws ListaProductosVacia {

        float irpf = 0;

        if (productos != null) {
            for (Producto p : productos) {

                if (!p.isExentoIRPF()) {

                    irpf += p.getIrpf() * p.getImporte() / 100;

                }

            }
        } else {
            throw new ListaProductosVacia();
        }

        return irpf;
    }

    public static float calcularSUBTOTAL(List<Producto> productos) throws ListaProductosVacia {

        float total = 0;

        if (productos != null) {
            for (Producto p : productos) {

                total += p.getImporte();

            }
        } else {
            throw new ListaProductosVacia();
        }

        return total;
    }

}
