package com.example.techbulls.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techbulls.MainStructure.MoviePOJO;
import com.example.techbulls.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdp extends RecyclerView.Adapter<MovieAdp.ViewHolder> {
    private ArrayList<MoviePOJO> dataList;
    Context c;

    public MovieAdp(ArrayList<MoviePOJO> dataList, Context con) {
        this.dataList = dataList;
        c = con;
    }

    public void setData(ArrayList<MoviePOJO> newItems) {
        this.dataList = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movieitem, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_sub_title.setText(dataList.get(position).getYear());
        holder.txt_title.setText(dataList.get(position).getName());
        Picasso.get().load(dataList.get(position).getImage()).into(holder.img_movie);
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title,txt_sub_title;
        ImageView img_movie;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            img_movie = (ImageView) itemView.findViewById(R.id.img_movie);
            txt_sub_title = (TextView) itemView.findViewById(R.id.txt_sub_title);
        }


    }
}
