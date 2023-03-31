package com.pdfcreator.utiles;

import java.util.Arrays;

public enum TipoDocumento {
    PRESUPUESTO(0),
    FACTURA(1),
    UNKWOWN(2);

    private int code;

    private TipoDocumento(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public static TipoDocumento resolveCode(int code) {
        return Arrays.stream(TipoDocumento.values()).filter(element -> element.code() == code).findFirst().orElse(TipoDocumento.UNKWOWN);
    }

}
