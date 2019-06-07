package com.example.software;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Database extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ListView mlist;
    List mhome = new ArrayList<String>();
    private ArrayList<String> muser= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        mDatabase =FirebaseDatabase.getInstance().getReference();
        //FirebaseDatabase database =
      //  DatabaseReference myRef = database.getReference();
        mlist = findViewById(R.id.lista);
        final ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,mhome);
        mlist.setAdapter(arrayAdapter);
        mDatabase.child("home").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
           String value = dataSnapshot.getValue().toString();

               // Upload restaurant=dataSnapshot.getValue(Upload.class);
                mhome.add(value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
