package com.jy.animal.frame;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by jing on 12/29/16.
 * 监听滑动执行动画
 */
public class MyAnimalView extends FrameLayout {

    private int FROM_BOTTOM_VALUE = 0x1;
    private int FROM_LEFT_VALUE = 0x4;

    private float mAlphaFrom;

    private float mAlphaTo;

    private boolean isScaleX;

    private boolean isFromLeft;

    private boolean isFromBottom;

    private int mHeight;

    private int mWidth;

    public void setScaleX(boolean scaleX) {
        isScaleX = scaleX;
    }

    public void setFromLeft(int tranlate) {
        isFromLeft = hasOriention(tranlate,FROM_LEFT_VALUE);;
    }

    public void setFromBottom(int tranlate) {
        isFromBottom = hasOriention(tranlate,FROM_BOTTOM_VALUE);
    }

    private boolean hasOriention(int tranlate, int attrValue) {
        return (tranlate & attrValue) == attrValue;
    }

    public void setAlphaFrom(float alphaFrom) {
        mAlphaFrom = alphaFrom;
    }

    public void setAlphaTo(float alphaTo) {
        mAlphaTo = alphaTo;
    }

    public MyAnimalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAnimalView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void excuteAnimal(float rate) {
        if (mAlphaFrom != -1 && mAlphaTo != -1) {
            setAlpha((mAlphaTo - mAlphaFrom)*rate);
        }

        if(isFromBottom){
            setTranslationY(mHeight*(1 - rate));
        }

        if(isFromLeft){
            setTranslationX(-mWidth*(1 - rate));
        }
        if(isScaleX){
           setScaleX(rate);
        }
    }

}
