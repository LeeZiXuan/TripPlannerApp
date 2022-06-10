package com.example.tripplannerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Adapter
public class AddNotification extends RecyclerView.Adapter<AddNotification.MyViewHolder> {


    Context context;
    ArrayList<Trip> list;

    public AddNotification(Context context, ArrayList<Trip> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.message,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Trip trip = list.get(position);
        holder.nameTrip.setText(trip.getTripName());
        holder.startTrip.setText(trip.getStartDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTrip, startTrip;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTrip = itemView.findViewById(R.id.place);
            startTrip = itemView.findViewById(R.id.reminder);


        }
    }
}
