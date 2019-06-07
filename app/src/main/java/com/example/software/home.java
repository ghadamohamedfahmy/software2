package com.example.software;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class home extends AppCompatActivity implements recycleviewfeed.OnReclyclerClickListener{
    String imageUrl;
    String title;
    //implements feedadapter.OnItemClickListener{
    static final String TAG = "home";
    private Spinner spinner;
    private ImageButton button, bit;
    NetworkInfo netInfo;

    private TextView mtextView;
    feedadapter mfeedAdapter;
    private ArrayList<recycleviewfeed> recycleview;
        List mhome = new ArrayList<Upload>();
    private RequestQueue mRequestQueue;
    RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ProgressBar mProgressCircle;
    private DatabaseReference mDatabase;
    //private List<Upload> mUploads;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    DatabaseReference child = myRef.child("home");
    //@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // mDatabase = FirebaseDatabase.getInstance().getReference("Cairo");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = conMgr.getActiveNetworkInfo();
        mRecyclerView = findViewById(R.id.recyclehome);
        mRecyclerView.addOnItemTouchListener(new recycleviewfeed(this,mRecyclerView,this));
       // mRecyclerView.setHasFixedSize(true);
       // mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = findViewById(R.id.progress_circle);
        button = findViewById(R.id.user);
        mtextView = findViewById(R.id.textView);

        child.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                loadData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                loadData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       // recycleview = new ArrayList<>();
        //  view = new ArrayList<>();
      //  mRequestQueue = Volley.newRequestQueue(this);
        spinner = findViewById(R.id.spinner1);
        // button = findViewById(R.id.selectcategory);
        List<String> categories = new ArrayList<>();
        categories.add(0, "Choose Category");
        categories.add("cairo");
        categories.add("Aswan");
        categories.add("Alex");
        categories.add("luxor");
        categories.add("Other");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        //Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Choose Category")) {
                    //do nothing
                }
                //tetkarr lkol category
                else if (parent.getItemAtPosition(position).equals("cairo")) {
                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();

                    //show selected spinner item
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    OpenActivity_arts();
                    //anything else you want to do on item selection do here
                } else if (parent.getItemAtPosition(position).equals("luxor")) {
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    openActivity_carpenter();
                } else if (parent.getItemAtPosition(position).equals("Aswan")) {
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    openActivity_real();
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // TODO Auto-generated method stub
            }
        });}
    final ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,mhome);
    private void loadData(DataSnapshot dataSnapshot) {
        Upload restaurant=dataSnapshot.getValue(Upload.class);
        mhome.add(restaurant);
        mfeedAdapter = new feedadapter(home.this, mhome);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mfeedAdapter);
        mProgressCircle.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onItemClick(View view, int postition) {
        /*Intent intent = new Intent(this,RestaurantDetails.class);
        intent.putExtra("RESTAURANT_TRANSFER", mfeedAdapter.getRestaurant(postition));
        startActivity(intent);*/
    }

    @Override
    public void onItemLongClick(View view, int postition) {

    }

        // DocumentReference docRef = db.collection("country").document("name");

       /* db.collection("country")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mtextView.setText(document.getId() + document.getData());
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            mtextView.setText("error");
                        }
                    }
                });*/


   /* public class ReadData implements ValueEventListener{

        @Override
        public void onDataChange( DataSnapshot dataSnapshot) {
           // String var = dataSnapshot.getValue(String.class);
            mtextView.setText(dataSnapshot.getValue(String.class));
        }

        @Override
        public void onCancelled( DatabaseError databaseError) {

        }
    }*/
        //@Override
        //public void onItemClick ( int position){
            // Intent detailIntent = new Intent(this, DetailActivity.class);
            // getdetails clickedItem = view.get(position);
           // recycleviewfeed clickedItem = recycleview.get(position);

        /*detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getservice());
        detailIntent.putExtra(EXTRA_LIKES, clickedItem.getrateCount());
        detailIntent.putExtra(EXTRA_date, clickedItem.getdate());
        detailIntent.putExtra(EXTRA_discrebtion, clickedItem.getdiscrebtion());
        detailIntent.putExtra(EXTRA_time, clickedItem.gettime());
        detailIntent.putExtra(EXTRA_cost, clickedItem.getcost());
        detailIntent.putExtra(EXTRA_direction, clickedItem.getdirection());
        detailIntent.putExtra(EXTRA_server, clickedItem.getspname());


        startActivity(detailIntent);*/

    public void OpenActivity_arts(){
        //// Intent intent= new Intent(this,activity_.class);
        Intent intent= new Intent (this,cairo.class);
        startActivity( intent);

    }
    public void openActivity_carpenter(){

        Intent intent= new Intent (this,Aswan.class);
        startActivity( intent);

    }
    public void openActivity_real(){
        //// Intent intent= new Intent(this,activity_.class);
        Intent intent= new Intent (this,Alex.class);
        startActivity( intent);

    }
    public void open_addd_services(){
        Intent intent= new Intent(this,luxor.class);
        startActivity( intent);
    }
    public void req(){
        Intent intent= new Intent(this, others.class);
        startActivity( intent);
    }
}

