package in.tedsys.tedutils;

import android.content.Context;
import android.graphics.Typeface;

public class TypeFaceFactory {
    final String LATO_REGULAR = "fonts/Lato_Regular.ttf";
    final String LATO_BOLD = "fonts/Lato_Bold.ttf";
    final String LATO_ITALIC = "fonts/Lato_Italic.ttf";
    final String PRODUCT_SANS_REGULAR = "fonts/Product_Sans_Regular.ttf";
    final String PRODUCT_SANS_BOLD = "fonts/Product_Sans_Bold.ttf";
    final String PRODUCT_SANS_ITALIC = "fonts/Product_Sans_Italic.ttf";

    private static Typeface lato_regular;
    private static Typeface lato_bold;
    private static Typeface lato_italic;
    private static Typeface sans_regular;
    private static Typeface sans_bold;
    private static Typeface sans_italic;

    public TypeFaceFactory(Context context) {
        lato_regular = Typeface.createFromAsset(context.getAssets(), LATO_REGULAR);
        lato_bold = Typeface.createFromAsset(context.getAssets(), LATO_BOLD);
        lato_italic = Typeface.createFromAsset(context.getAssets(), LATO_ITALIC);
        sans_regular = Typeface.createFromAsset(context.getAssets(), PRODUCT_SANS_REGULAR);
        sans_bold = Typeface.createFromAsset(context.getAssets(), PRODUCT_SANS_BOLD);
        sans_italic = Typeface.createFromAsset(context.getAssets(), PRODUCT_SANS_ITALIC);
    }

    public Typeface getLato_regular() {
        return lato_regular;
    }

    public Typeface getLato_bold() {
        return lato_bold;
    }

    public Typeface getLato_italic() {
        return lato_italic;
    }

    public Typeface getSans_regular() {
        return sans_regular;
    }

    public Typeface getSans_bold() {
        return sans_bold;
    }

    public Typeface getSans_italic() {
        return sans_italic;
    }

    public Typeface getTypeFace(int type) {
        switch (type) {

            case Constants.LATO_ITLC:
                return getLato_italic();
            case Constants.LATO_BOLD:
                return getLato_bold();
            case Constants.SANS_RGLR:
                return getSans_regular();
            case Constants.SANS_BOLD:
                return getSans_bold();
            case Constants.SANS_ITLC:
                return getSans_italic();
            case Constants.LATO_RGLR:
            default:
                return getLato_regular();
        }
    }

    public interface Constants {
        int LATO_RGLR = 1,
                LATO_ITLC = 2,
                LATO_BOLD = 3,
                SANS_RGLR = 4,
                SANS_ITLC = 5,
                SANS_BOLD = 6;
    }
}
