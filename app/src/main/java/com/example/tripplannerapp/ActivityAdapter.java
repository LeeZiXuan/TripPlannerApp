package com.example.tripplannerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder> {

    private final ActivityRecyclerViewInterface activityRecyclerViewInterface;
    Context context;
    ArrayList<Activity> Actlist;

    public ActivityAdapter(Context context, ArrayList<Activity> Actlist, ActivityRecyclerViewInterface activityRecyclerViewInterface) {
        this.context = context;
        this.Actlist = Actlist;
        this.activityRecyclerViewInterface = activityRecyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_activityentry,parent, false);
        return new MyViewHolder(v, activityRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Activity activity = Actlist.get(position);
        holder.nameActivity.setText(activity.getAct_name());
        holder.startActivity.setText(activity.getAct_startDateTime());
        holder.endActivity.setText(activity.getAct_endDateTime());
    }

    @Override
    public int getItemCount() {
        return Actlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameActivity, startActivity, endActivity;
        public MyViewHolder(@NonNull View itemView, ActivityRecyclerViewInterface activityRecyclerViewInterface) {
            super(itemView);
            nameActivity = itemView.findViewById(R.id.activity_name);
            startActivity = itemView.findViewById(R.id.activity_start);
            endActivity = itemView.findViewById(R.id.activity_end);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(activityRecyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            activityRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
