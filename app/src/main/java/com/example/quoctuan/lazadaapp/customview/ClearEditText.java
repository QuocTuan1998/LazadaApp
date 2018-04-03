package com.example.quoctuan.lazadaapp.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.quoctuan.lazadaapp.R;

/**
 * Created by quoctuan on 28/03/2018.
 */

@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText{
    Drawable crossX,nonecrossX,drawable;
    boolean visible = false;

    public ClearEditText(Context context) {
        super(context);
        create();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        create();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        create();
    }

    @SuppressLint("NewApi")
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        create();
    }

    private void create() {
        crossX = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        nonecrossX = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();
        setting();
    }

    private void setting() {
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible? crossX:nonecrossX;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (MotionEvent.ACTION_DOWN == event.getAction() &&
            event.getX() >= (getRight() - drawable.getBounds().width())) {
            setText("");
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if (lengthAfter == 0 && start == 0) {
            visible = false;
            setting();
        }else {
            visible = true;
            setting();
        }
    }
}
