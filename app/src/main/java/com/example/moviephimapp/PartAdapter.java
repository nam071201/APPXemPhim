package com.example.moviephimapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PartAdapter extends RecyclerView.Adapter<PartAdapter.MyViewHodelr> {

    private List<PartModel> models;

    public PartAdapter(List<PartModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public MyViewHodelr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_item,parent,false);


        return new MyViewHodelr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodelr holder, int position) {
        holder.part_name.setText(models.get(position).getPart());
        Glide.with(holder.itemView).load(models.get(position).getUrl()).into(holder.part_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),PlayerActivity.class);
                intent.putExtra("title",models.get(position).getPart());
                intent.putExtra("vid",models.get(position).getVidUrl());
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHodelr extends RecyclerView.ViewHolder{
        ImageView part_image;
        TextView part_name;

        public MyViewHodelr(@NonNull View itemView) {
            super(itemView);
            part_image = itemView.findViewById(R.id.part_image);
            part_name = itemView.findViewById(R.id.part_name);
        }
    }
}
