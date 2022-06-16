package com.example.tripplannerapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Trip trip = list.get(position);
        holder.nameTrip.setText(trip.getTripName());
        holder.startTrip.setText(trip.getStartDate());
        holder.endTrip.setText(trip.getEndDate());
    }

    @Override
    public int getItemCount() { return list.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTrip, startTrip, endTrip;

        public MyViewHolder(@NonNull View itemView, TripRecyclerViewInterface tripRecyclerViewInterface) {
            super(itemView);
            nameTrip = itemView.findViewById(R.id.trip_name);
            startTrip = itemView.findViewById(R.id.trip_start);
            endTrip = itemView.findViewById(R.id.trip_end);

            // Click on trips to view trip details
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
