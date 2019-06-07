package com.example.software;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class recycleviewfeed extends RecyclerView.SimpleOnItemTouchListener {
    private static final String TAG = "recycleviewfeed";

    interface OnReclyclerClickListener{
        void onItemClick(View view, int postition);
        void onItemLongClick(View view,int postition);
    }

    private final recycleviewfeed.OnReclyclerClickListener mListener;
    private final GestureDetectorCompat mGetGestureDetector;

    public recycleviewfeed(Context context, final RecyclerView recyclerView, recycleviewfeed.OnReclyclerClickListener listener) {
        mListener = listener;
        mGetGestureDetector=new GestureDetectorCompat(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childVeiw = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childVeiw!=null&&mListener!=null){
                    mListener.onItemClick(childVeiw,recyclerView.getChildAdapterPosition(childVeiw));
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

                View childView=recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView!=null && mListener!=null){
                    mListener.onItemClick(childView,recyclerView.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if(mGetGestureDetector!=null){
            boolean result =mGetGestureDetector.onTouchEvent(e);
            return result;
        }else {
            return false;
        }

    }










   /* private String mImageUrl;
        private String mplace;
        private String mspname;
        private int mrate;
        private String mtime;
        private String mdate;
        private String mcost;
        private String mdirection;
        private String mdiscrebtion;
        public recycleviewfeed(String imageUrl, String place, int rate) {
            mImageUrl = imageUrl;
            mplace = place;

            mrate =rate;
        }
        public recycleviewfeed(String imageUrl, String place, int rate, String spname) {
            mImageUrl = imageUrl;
            mplace = place;
            mspname=spname;
            mrate =rate;
        }
        /*public recycleviewfeed(String imageUrl, String place, int rate,String spname,String time,String date
                ,String cost,String direction,String discrebtion) {
            mImageUrl = imageUrl;
            mplace = place;
            mspname=spname;
            mrate =rate;
            mtime=time;
            mdate=date;
            mcost=cost;
            mdiscrebtion=discrebtion;
            mdirection = direction;
        }*//*
        public String getspname() {
            return mspname;
        }

        public String getImageUrl() {
            return mImageUrl;
        }

        public String getservice() {
            return mplace;
        }

        public int getrateCount() {
            return mrate;
        }
        public String gettime() {
            return mtime;
        }

        public String getdate() {
            return mdate;
        }

        public String getcost() {
            return mcost;
        }
        public String getdiscrebtion() {
            return mdiscrebtion;
        }
        public String getdirection() {
            return mdirection;
        }*/
}
