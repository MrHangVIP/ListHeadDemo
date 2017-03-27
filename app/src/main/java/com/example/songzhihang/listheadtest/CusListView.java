package com.example.songzhihang.listheadtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * @discription
 * @autor songzhihang
 * @time 2017/3/15  下午8:58
 **/
public class CusListView extends ListView{


    public CusListView(Context context) {
        super(context);
        init();
    }

    public CusListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CusListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

     void init(){

         LinearLayout headView=new LinearLayout(getContext());
         LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//         headView.setLayoutParams(params);
         headView.setOrientation(LinearLayout.VERTICAL);

         View misshead=inflate(getContext(),R.layout.dismiss_head_layout,null);
         View showhead=inflate(getContext(),R.layout.head_layout,null);

         headView.addView(misshead);
         headView.addView(showhead);
         addHeaderView(headView);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
