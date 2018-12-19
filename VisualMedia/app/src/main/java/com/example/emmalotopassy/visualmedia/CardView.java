package com.example.emmalotopassy.visualmedia;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * TODO: document your custom view class.
 */
public class CardView extends AppCompatImageView {

    private int back;
    private int front;
    private boolean faceUp = false;

    public CardView(Context context) {
        super(context);
        init(null, 0);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);

        //TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CardView, defStyle, 0);

    }


    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CardView, defStyle, 0);

        if (a.hasValue(R.styleable.CardView_cardBack)) {
            back = a.getResourceId(R.styleable.CardView_cardBack, R.drawable.cardback);
        }

        if (a.hasValue(R.styleable.CardView_cardValue)) {
            front = a.getResourceId(R.styleable.CardView_cardValue, R.drawable.redcard);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public int getFront() {

        return front;
    }

    public int getBack() {

        return back;
    }

    public void setFront(int cardImage) {
        front = cardImage;
    }

    public void setBack(int cardImage) {
        back = cardImage;
    }

    public void flipCard() {
        if (faceUp) {
            this.setImageResource(back);
            faceUp = false;
        } else {
            this.setImageResource(front);
            faceUp = true;
        }
    }

}

