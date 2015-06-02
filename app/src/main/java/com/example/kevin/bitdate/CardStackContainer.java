package com.example.kevin.bitdate;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by kevin on 6/1/15.
 */
public class CardStackContainer extends RelativeLayout implements View.OnTouchListener {

    private static final String TAG = "Card Stack Container";
    private CardAdapter mAdapter;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPositionX;
    private float mPositionY;

    public CardStackContainer(Context context) {
        this(context, null, 0);
    }

    public CardStackContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardStackContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(CardAdapter adapter) {
        mAdapter = adapter;
        if (mAdapter.getCount() > 0) {
            CardView cardView = mAdapter.getView(0, null, this);
            cardView.setOnTouchListener(this);
            addView(cardView);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mLastTouchX = event.getX();
                mLastTouchY = event.getY();

                mPositionX = v.getX();
                mPositionY = v.getY();

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                float xMove = event.getX();
                float yMove = event.getY();

                float changeX = xMove - mLastTouchX;
                float changeY = yMove - mLastTouchY;

                mPositionX += changeX;
                mPositionY += changeY;

                v.setX(mPositionX);
                v.setY(mPositionY);

                break;
        }
        return true;
    }
}
