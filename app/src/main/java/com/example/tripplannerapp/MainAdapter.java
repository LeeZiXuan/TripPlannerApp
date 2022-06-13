package com.example.tripplannerapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder>{


    public MainAdapter(FirebaseRecyclerOptions<MainModel> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull MainModel model) {
        holder.hotelName.setText(model.getHotelName());
        holder.orderDate.setText(model.getOrderDate());
        holder.quantity.setText(model.getQuantity());
        holder.roomType.setText(model.getRoomType());
        holder.price.setText(model.getPrice());

        Glide.with(holder.img.getContext())
                .load(model.getrImg())
                .placeholder(com.google.firebase.appcheck.interop.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.google.firebase.appcheck.interop.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1080)
                        .create();
                //dialogPlus.show();

                View view = dialogPlus.getHolderView();
                EditText orderDate = view.findViewById((R.id.txtOrderDate));
                EditText roomType = view.findViewById((R.id.txtRoomType));
                EditText quantity = view.findViewById((R.id.txtQuantity));
                Button btnUpdate =view.findViewById(R.id.btnUpdate);

                orderDate.setText(model.getOrderDate());
                roomType.setText(model.getRoomType());
                quantity.setText(model.getQuantity());
                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("orderDate",orderDate.getText().toString());
                        map.put("roomType",roomType.getText().toString());
                        map.put("quantity",quantity.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Hotels")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>(){

                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.hotelName.getContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener(){

                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.hotelName.getContext(),"Error While Updating.",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });


    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView hotelName,orderDate,roomType,quantity,price;
        Button btn_edit,btn_delete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img =(CircleImageView) itemView.findViewById(R.id.img1);
            hotelName =(TextView) itemView.findViewById(R.id.hotelnametext);
            orderDate =(TextView) itemView.findViewById(R.id.orderDate);
            roomType =(TextView) itemView.findViewById(R.id.roomTypetext);
            quantity =(TextView) itemView.findViewById(R.id.quantity);
            price =(TextView) itemView.findViewById(R.id.price);

            btn_edit =(Button)itemView.findViewById(R.id.btn_edit);
            btn_delete =(Button)itemView.findViewById(R.id.btn_delete);
        }
    }
}
