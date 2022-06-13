package com.example.tripplannerapp;

/*public class EditRestaurant extends AppCompatActivity {

    RecyclerView recyclerView;
    RestaurantAdapter R_adapter; //adapter
    ArrayList<RestaurantData> R_list;
    Button editRestaurantBtn;
    Button deleteRestaurantBtn;

    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    FirebaseDatabase db;
    FirebaseUser user;
    String uid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_recycler);

        recyclerView = findViewById(R.id.restaurantList);


        editRestaurantBtn = findViewById(R.id.R_btn_edit);
        deleteRestaurantBtn = findViewById(R.id.R_btn_delete);
        databaseReference = FirebaseDatabase.getInstance().getReference("RestaurantData");

        R_list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        FirebaseRecyclerOptions<RestaurantData> context =
                new FirebaseRecyclerOptions.Builder<RestaurantData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RestaurantData"), RestaurantData.class)
                        .build();

        R_adapter = new RestaurantAdapter(context, this);
        recyclerView.setAdapter(R_adapter);
    }


        protected void onStart() {
            super.onStart();
            R_adapter.startListening();
        }

        protected void onStop() {
            super.onStop();
            R_adapter.stopListening();
        }


    }
*/

