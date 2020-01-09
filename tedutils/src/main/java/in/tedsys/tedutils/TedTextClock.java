package in.tedsys.tedutils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextClock;

public class TedTextClock extends TextClock {
    private int typeFaceType;
    public TedTextClock(Context context) {
        super(context);
    }

    public TedTextClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TedTextView,
                0,0
        );
        try{
            typeFaceType = array.getInteger(R.styleable.TedTextView_font_name,0);
        }finally {
            array.recycle();
        }
        if(!isInEditMode()){
            setTypeface(new TypeFaceFactory(context).getTypeFace(typeFaceType));
        }

    }

    public void setFontName(int fontName){
        typeFaceType = fontName;
    }
}
