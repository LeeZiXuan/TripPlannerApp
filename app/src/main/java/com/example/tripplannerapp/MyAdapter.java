package com.example.tripplannerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final TripRecyclerViewInterface tripRecyclerViewInterface;
    Context context;
    ArrayList<Trip> list;

    public MyAdapter(Context context, ArrayList<Trip> list, TripRecyclerViewInterface tripRecyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.tripRecyclerViewInterface = tripRecyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.tripentry,parent, false);
        return new MyViewHolder(v, tripRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Trip trip = list.get(position);
        holder.nameTrip.setText(trip.getTripName());
        holder.startTrip.setText(trip.getStartDate());
        holder.endTrip.setText(trip.getEndDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTrip, startTrip, endTrip;
        public MyViewHolder(@NonNull View itemView, TripRecyclerViewInterface tripRecyclerViewInterface) {
            super(itemView);
            nameTrip = itemView.findViewById(R.id.trip_name);
            startTrip = itemView.findViewById(R.id.trip_start);
            endTrip = itemView.findViewById(R.id.trip_end);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tripRecyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            tripRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
