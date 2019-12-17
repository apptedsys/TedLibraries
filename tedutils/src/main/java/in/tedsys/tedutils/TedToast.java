package in.tedsys.tedutils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

public class TedToast {
    public static final int LATO_RGLR = 1;
    public static final int LATO_ITLC = 2;
    public static final int LATO_BOLD = 3;
    public static final int SANS_RGLR = 4;
    public static final int SANS_ITLC = 5;
    public static final int SANS_BOLD = 6;

    private static Context context;
    private static TedToast mInstance;
    private static String message;
    private static int drawable;
    private static int font;
    private static int gravity;
    private static int xOffset;
    private static int yOffset;
    private static float textsize;
    private static boolean islong = false;


    public TedToast(Context context) {
        this.context = context;
    }

    public static synchronized TedToast get(Context context) {
        if (mInstance == null) {
            mInstance = new TedToast(context);
        }
        return mInstance;
    }

    public TedToast message(String message) {
        mInstance.message = message;
        return mInstance;
    }

    public TedToast font(int font) {
        mInstance.font = font;
        return mInstance;
    }

    public TedToast islong(boolean islong) {
        mInstance.islong = islong;
        return mInstance;
    }

    public TedToast drawable(int drawable) {
        mInstance.drawable = drawable;
        return mInstance;
    }

    public TedToast gravity(int gravity) {
        mInstance.gravity = gravity;
        return mInstance;
    }

    public TedToast xoffset(int xOffset) {
        mInstance.xOffset = xOffset;
        return mInstance;
    }

    public TedToast yoffset(int yOffset) {
        mInstance.yOffset = yOffset;
        return mInstance;
    }

    public TedToast textsize(int textsize) {
        mInstance.textsize = textsize;
        return mInstance;
    }

    public static void show() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout, null);

        ImageView image = layout.findViewById(R.id.toast_image);
        if (drawable != 0) {
            image.setImageResource(drawable);
        } else {
            image.setVisibility(View.GONE);
        }
        if (message != null) {
            AppCompatTextView textV = layout.findViewById(R.id.toast_text);
            textV.setText(message);
            textV.setTypeface(new TypeFaceFactory(context).getTypeFace(font));
            textV.setTextSize(textsize != 0f ? textsize : 17);
        }
        if (context != null) {
            Toast toast = new Toast(context);
            if (gravity != 0) {
                toast.setGravity(gravity, xOffset != 0 ? xOffset : 0, yOffset != 0 ? yOffset : 0);
            }
            toast.setDuration((islong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        } else {
            throw new NullPointerException("Context could not be NULL!");
        }
    }
}
