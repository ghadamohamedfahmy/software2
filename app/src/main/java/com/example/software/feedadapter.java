package com.example.software;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public  class feedadapter extends RecyclerView.Adapter<feedadapter.MyViewHolder>{
        List<Upload> mhome;
        Context context;

public feedadapter(Context context,List HomeList) {
        this.context=context;
        this.mhome = HomeList;
        }
@Override
public int getItemCount() {
        return mhome.size();
        }

@Override
public feedadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.activity_recycleviewfeed,parent, false);
        return new feedadapter.MyViewHolder(v);
        }

@Override
public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
          Upload recyclehome = mhome.get(position);
   holder.placeTitle.setText(recyclehome.getTitle().toString());
    String value =recyclehome.getImageUrl().toString();
    Picasso.with(context).load(value).into(holder.placeImage);
       /* Glide.with(context)
        .load(((Upload) recyclehome).getImageUrl())
        .thumbnail(Glide.with(context).load(R.drawable.giphy))
        .apply(new RequestOptions()
        .error(R.drawable.broken_image))
        .into(holder.placeImage);*/
        }
public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView placeTitle;
    public ImageView placeImage;
    public TextView  cityTitle;
    public RatingBar rating;
    public MyViewHolder(View view) {
        super(view);
        placeTitle=(TextView)view.findViewById(R.id.place);
       // cityTitle  =(TextView)view.findViewById(R.id.city);
        placeImage=view.findViewById(R.id.imageplace);
      //  rating=view.findViewById(R.id.ratingBar);
    }
}

    public Upload getRestaurant(int position) {
        return mhome.get(position);
    }
}
