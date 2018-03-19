package com.example.quoctuan.lazadaapp.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.quoctuan.lazadaapp.R;

/**
 * Created by quoctuan on 19/03/2018.
 */

public class PasswordEditText extends AppCompatEditText {

    Drawable eye,eyeStrike;
    boolean visible = false;
    boolean useStrike = false;
    Drawable drawable;
    int alpha = (int) (255 * .90f);

    public PasswordEditText(Context context) {
        super(context);
        create(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        create(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        create(attrs);
    }

    public void create(AttributeSet attrs){
        if (attrs != null) {
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs,
                    R.styleable.PasswordEditText,
                    0,
                    0
            );
            this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike,false);
        }
        eye = ContextCompat.getDrawable(getContext(),
                R.drawable.ic_visibility_black_24dp).
                mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(),
                R.drawable.ic_visibility_off_black_24dp).
                mutate();
        setting();
    }

    public void setting() {
        setInputType(InputType.TYPE_CLASS_TEXT |
                (visible ?
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                    InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible ? eyeStrike : eye;
        drawable.setAlpha(alpha);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],
                drawables[1],
                drawable,
                drawables[3]
                );

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width())) {
            visible = !visible;
            setting();
            invalidate();
        }

        return super.onTouchEvent(event);
    }
}
