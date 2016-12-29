package com.jy.animal.frame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by jing on 12/29/16.
 * 执行动画View
 */
public class MyScrollerView extends ScrollView{

    private int mHeight;

    public MyScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollerView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        ViewGroup vp = (ViewGroup) getChildAt(0);
        for (int i = 0; i < vp.getChildCount(); i++) {
            if(vp.getChildAt(i) instanceof MyAnimalView){
                MyAnimalView itemView = (MyAnimalView) vp.getChildAt(i);

                int topView = itemView.getTop();
                int scrollY = t;
                int toScreenTopDistance = topView - scrollY;

                int visiableHeight = mHeight - toScreenTopDistance;
                if(visiableHeight > 0 && visiableHeight <= itemView.getHeight()){
                    float rate = visiableHeight/(float)itemView.getHeight();
                    itemView.excuteAnimal(rate);
                }
            }
        }
    }
}
