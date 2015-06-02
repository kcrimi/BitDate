package com.example.kevin.bitdate;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by kevin on 6/1/15.
 */
public class CardStackContainer extends RelativeLayout {

    public CardStackContainer(Context context) {
        this(context, null, 0);
    }

    public CardStackContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardStackContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
