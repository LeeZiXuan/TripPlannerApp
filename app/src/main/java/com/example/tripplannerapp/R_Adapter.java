package com.example.tripplannerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class R_Adapter extends RecyclerView.Adapter<R_Adapter.MyViewHolder> {

    private final R_RecyclerViewInterface r_recyclerViewInterface;
    Context context;
    ArrayList<RestaurantData> R_list;

    public R_Adapter(Context context, ArrayList<RestaurantData> R_list, R_RecyclerViewInterface r_recyclerViewInterface) {
        this.context = context;
        this.R_list = R_list;
        this.r_recyclerViewInterface = r_recyclerViewInterface;
    }

    /*@NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.restaurant_view,parent, false);
        return new R_Adapter.MyViewHolder(v, r_recyclerViewInterface);
    }

     */

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.restaurant_view,parent, false);
        return new MyViewHolder(v, r_recyclerViewInterface);
    }

    /*@Override
    public void onBindViewHolder(@NonNull R_Adapter.MyViewHolder holder, int position) {
        RestaurantData R_data = R_list.get(position);
        holder.restaurant_name.setText(R_data.getR_id());
        holder.R_start.setText(R_data.getR_startDate());
        holder.R_end.setText(R_data.getR_endDate());
    }

     */

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RestaurantData R_data = R_list.get(position);
        holder.restaurant_name.setText(R_data.getR_id());
        holder.R_start.setText(R_data.getR_startDate());
        holder.R_end.setText(R_data.getR_endDate());
    }

    @Override
    public int getItemCount() {
        return R_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView restaurant_name, R_start, R_end;
        public MyViewHolder(@NonNull View itemView, R_RecyclerViewInterface r_recyclerViewInterface) {
            super(itemView);
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            R_start = itemView.findViewById(R.id.R_start);
            R_end = itemView.findViewById(R.id.R_end);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(r_recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            r_recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
