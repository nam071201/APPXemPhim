package com.example.moviephimapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyViewHolder> {

    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }
    private List<DataModel> dataModels = new ArrayList<>();
    public void renewItems(List<DataModel> dataModels){
        this.dataModels = dataModels;
        notifyDataSetChanged();
    }

    public void deleteItems(int position){
        this.dataModels.remove(position);
        notifyDataSetChanged();
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item,parent, false);


        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {

        viewHolder.trailer_title.setText(dataModels.get(position).getTtitle());

        Glide.with(viewHolder.itemView).load(dataModels.get(position).getTurl()).into(viewHolder.slider_image);
        viewHolder.play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trailer_video = new Intent(context,PlayerActivity.class);
                trailer_video.putExtra("vid",dataModels.get(position).getTvid());
                trailer_video.putExtra("title",dataModels.get(position).getTtitle());
                v.getContext().startActivity(trailer_video);


            }
        });


    }

    @Override
    public int getCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends SliderViewAdapter.ViewHolder{
        ImageView slider_image;
        TextView trailer_title;
        FloatingActionButton play_button;


        public MyViewHolder(View itemView) {
            super(itemView);

            slider_image = itemView.findViewById(R.id.image_thumbnail);
            trailer_title = itemView.findViewById(R.id.trailer_title);
            play_button = itemView.findViewById(R.id.floatingActionButton);



        }
    }
}
