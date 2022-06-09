package com.example.tripplannerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AddNotification extends FirebaseRecyclerAdapter<Trip,AddNotification.myViewHolder>{
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AddNotification(@NonNull FirebaseRecyclerOptions<Trip> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Trip model) {
        holder.placeNotis.setText(model.getDestination());
        holder.message.setText(model.getStartDate());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message,parent,false);
        return null;
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView placeNotis, message;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            placeNotis =(TextView) itemView.findViewById(R.id.place);
            message =(TextView) itemView.findViewById(R.id.reminder);

        }
    }
}
