package com.example.songzhihang.listheadtest;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private static final String TAG = "MainActivity";

    int padding;
    TextView textview;
    private int statusBarHeight;
    private int contentTop;
    private int titleBarHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(new MyAdapter());
        listView.setOnScrollListener(this);
        textview = (TextView) findViewById(R.id.text);
        padding=((View)(textview.getParent())).getPaddingTop()+  ((View)(textview.getParent())).getPaddingBottom();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;  //状态栏高
        contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        titleBarHeight = contentTop - statusBarHeight; //标题栏高
        Log.e(TAG, "statusBarHeight: "+statusBarHeight );
        Log.e(TAG, "contentTop: "+contentTop );
        Log.e(TAG, "titleBarHeight: "+titleBarHeight );
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = new TextView(MainActivity.this);
                holder.view = (TextView) convertView;
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.view.setText("我是:" + position);
            holder.view.setTextSize(30);

            return convertView;
        }

        class ViewHolder {
            TextView view;
        }


    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View firstItem = view.getChildAt(0);
        textview = (TextView) MainActivity.this.findViewById(R.id.text);
        if (firstItem != null && firstItem instanceof LinearLayout) {
            Log.e(TAG, "firstItem.getY(): " + firstItem.getY());
            Log.e(TAG, "firstItem.getHeight(): " + firstItem.getHeight());
            Log.e(TAG, "view.getY(): " + view.getY());
            Log.e(TAG, "onScroll: " + ((LinearLayout) firstItem).getChildAt(1).getHeight());
            Log.e(TAG, "padding: " + padding);
            if (-firstItem.getY() >= firstItem.getHeight() - ((LinearLayout) firstItem).getChildAt(1).getHeight()) {
                textview.setVisibility(View.VISIBLE);
            } else {
                textview.setVisibility(View.GONE);
            }

//            if(getWinHeight()-view.getY()-padding-contentTop<=firstItem.getY()+firstItem.getHeight()){
            if(-firstItem.getY()<=680){
                findViewById(R.id.buttom_text).setVisibility(View.VISIBLE);
            }else{
                findViewById(R.id.buttom_text).setVisibility(View.GONE);
            }

        } else {
            textview.setVisibility(View.VISIBLE);
            findViewById(R.id.buttom_text).setVisibility(View.GONE);
        }


    }


    private int getWinHeight() {
        WindowManager wm = this.getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        Log.e(TAG, "getWinHeight: "+height );
        return height;
    }

}
