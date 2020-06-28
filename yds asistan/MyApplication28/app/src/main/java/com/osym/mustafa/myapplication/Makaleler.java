package com.osym.mustafa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Makaleler extends AppCompatActivity {
    ArrayList<Makale>makales;
    @BindView(R.id.makaleler_toolbar)Toolbar toolbar;
    @BindView(R.id.makaleler_recyclerview)RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makaleler);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        makales=new ArrayList<>();
       MainPage.fbDatabase.getReference().child("articles").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Iterable<DataSnapshot>items= dataSnapshot.getChildren();
                for (DataSnapshot item:items)
                {
                   Makale makale= item.getValue(Makale.class);
                    makales.add(makale);
                }

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

        refreshRec();




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cikmismenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        refreshRec();
        return super.onOptionsItemSelected(item);
    }
    public  void refreshRec()
    {
        MakaleAdapter adapter=new MakaleAdapter(makales,getBaseContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(adapter);


    }


}
