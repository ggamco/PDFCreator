package com.pdfcreator.utiles;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;

/**
 *
 * @author ggamboa
 */
public class AppFontFactory {

    //Assume that the fonts is folder which contains all the fonts you want to use it
    static {
        FontFactory.register("font/Open_Sans/OpenSans-Regular.ttf", "OpenSans_regular");
        FontFactory.register("font/Open_Sans/OpenSans-Light.ttf", "OpenSans_light");
    }

    public static Font getOpenSansFont(String type, float size) {
        String fuente = "OpenSans_" + type;

        return FontFactory.getFont(fuente, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, size);
    }
}
