package com.jy.animal.frame;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by jing on 12/29/16.
 * 截取事件和属性
 */
public class MyLinerLayout extends LinearLayout{
    private Context mContext;
    public MyLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOrientation(VERTICAL);
    }

    public MyLinerLayout(Context context) {
        super(context);
        mContext = context;
        setOrientation(VERTICAL);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(mContext,attrs);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if(((MyLayoutParams)params).hasAnimal()){
            MyAnimalView myAnimalView = new MyAnimalView(mContext);

            myAnimalView.setAlphaFrom(((MyLayoutParams) params).alphaFrom);
            myAnimalView.setAlphaTo(((MyLayoutParams) params).alphaTo);

            myAnimalView.setFromBottom(((MyLayoutParams) params).translate);
            myAnimalView.setFromLeft(((MyLayoutParams) params).translate);

            myAnimalView.setScaleX(((MyLayoutParams) params).isScaleX);

            myAnimalView.addView(child);

            super.addView(myAnimalView,index,params);
        }else {
            super.addView(child, index, params);
        }
    }


    public class MyLayoutParams extends LinearLayout.LayoutParams{

        float alphaFrom;
        float alphaTo;
        int translate;
        boolean isScaleX;

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c,attrs);

            TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.animalFrame);
            alphaFrom = typedArray.getFloat(R.styleable.animalFrame_alphaFrom,-1);
            alphaTo = typedArray.getFloat(R.styleable.animalFrame_alphaTo, -1);
            translate = typedArray.getInt(R.styleable.animalFrame_tranlate, -1);
            isScaleX = typedArray.getBoolean(R.styleable.animalFrame_scaleX, false);
            typedArray.recycle();
        }

        public boolean hasAnimal() {
            return alphaFrom != -1 && alphaTo != -1
                    || translate != -1
                    || isScaleX;
        }
    }
}
