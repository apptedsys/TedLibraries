package in.tedsys.tedutils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatCheckBox;

public class TedCheckBox extends AppCompatCheckBox {
    private int typeFaceType;

    public TedCheckBox(Context context) {
        super(context);
    }

    public TedCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TedTextView,
                0, 0
        );
        try {
            typeFaceType = array.getInteger(R.styleable.TedTextView_font_name, 0);
        } finally {
            array.recycle();
        }
        if (!isInEditMode()) {
            setTypeface(new TypeFaceFactory(context).getTypeFace(typeFaceType));
        }
    }

    public TedCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
