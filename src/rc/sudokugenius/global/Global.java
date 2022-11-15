package rc.sudokugenius.global;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import rc.properties.Properties;

public class Global {

    public static final boolean DEVELOPMENT = !true;
    public static final Global GLOBAL = new Global();
    public static final String DEFAULT_LAF = "Windows";

    /* PATHS */
    public static final String APP_PACKAGE = "/rc/sudokugenius/";
    public static final String FONTS_PACKAGE = APP_PACKAGE + "views/fonts/";
    public static final String IMAGES_PACKAGE = APP_PACKAGE + "views/images/";

    /* FONTS */
    public static final Font CHALK_FONT = DEVELOPMENT
            ? GLOBAL.getFont(FONTS_PACKAGE + "EraserDust.ttf")
            : GLOBAL.getFont("EraserDust.ttf");

    /* ICONS */
    public static final String APP_ICON = APP_PACKAGE + "views/images/tux.png";

    /* PROPERTIES */
    public static final String PROPERTIES_FILE_NAME = "besttimes.properties";
    public static final String PASSWORD = "sUdOKu_gENiuS";
    public static final Properties PROPERTIES = new Properties(new File(Global.PROPERTIES_FILE_NAME));
    public static final boolean ENCRYPT_VALUES = true;

    private Font getFont(String path) {
        Font font = null;

        try {
            font = DEVELOPMENT
                    ? Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getResource(path).toURI()))
                    : Font.createFont(Font.TRUETYPE_FONT, new File("./" + path));
        } catch (FontFormatException ex) {
        } catch (IOException ex) {
        } catch (URISyntaxException ex) {
        }

        return font;
    }
}
