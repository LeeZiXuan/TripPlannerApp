package com.example.tripplannerapp;

/*public class RestaurantAdapter extends FirebaseRecyclerAdapter<RestaurantData,RestaurantAdapter.myViewHolder> {

    Context context;
    List<EditRestaurant> list;

    public RestaurantAdapter(@NonNull FirebaseRecyclerOptions<RestaurantData> options, EditRestaurant context){

        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull RestaurantAdapter.myViewHolder holder, @SuppressLint("RecyclerView") int i, @NonNull RestaurantData R_data) {

        getRef(i).getKey();

        holder.R_name.setText(R_data.getR_id());
        holder.R_start.setText(R_data.getR_startDate());
        holder.R_end.setText(R_data.getR_endDate());
        holder.R_address.setText(R_data.getR_address());

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.edit_popup))
                        .setExpanded(true,1080)
                        .create();

                View view = dialog.getHolderView();


                EditText start = view.findViewById((R.id.txt_start));
                EditText end = view.findViewById((R.id.txtEnd));
                EditText address = view.findViewById((R.id.txtAddress));
                Button btnUpdate =view.findViewById(R.id.btnUpdate);

                start.setText(R_data.getR_startDate());
                end.setText(R_data.getR_endDate());
                address.setText(R_data.getR_address());





                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("r_startDate",start.getText().toString());
                        map.put("r_endDate",end.getText().toString());
                        map.put("r_address",address.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("RestaurantData")
                                .child(getRef(i).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>(){

                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.R_name.getContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener(){

                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.R_name.getContext(),"Error While Updating.",Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });


                    }
                });

                dialog.show();
            }
        });

    }


    @NonNull
    @Override
    public RestaurantAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_recycler,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView R_name,R_start,R_end,R_address;
        Button btn_edit,btn_delete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            R_name =(TextView) itemView.findViewById(R.id.restaurant_name);
            R_start =(TextView) itemView.findViewById(R.id.R_start);
            R_end =(TextView) itemView.findViewById(R.id.R_end);
            R_address =(TextView) itemView.findViewById(R.id.R_address);


            btn_edit =(Button)itemView.findViewById(R.id.R_btn_edit);
            btn_delete =(Button)itemView.findViewById(R.id.R_btn_delete);
        }
    }


}
*/