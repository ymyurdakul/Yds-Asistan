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
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CikmisSorularActivity extends AppCompatActivity {

    @BindView(R.id.cikmis_toolbar)Toolbar toolbar;
    @BindView(R.id.cikmis_recyclerview)RecyclerView recyclerView;
    ArrayList<DenemeSinav>denemeler;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cikmis_sorular);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        denemeler=new ArrayList<>(0);

        MainPage.fbDatabase.getReference("exams") .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot>children= dataSnapshot.getChildren();
                for (DataSnapshot ds:children)
                {
                    DenemeSinav sinav1= ds.getValue(DenemeSinav.class);

                  //  Toast.makeText(getBaseContext(),sinav1.getDonem().toString(),Toast.LENGTH_LONG).show();
                   Iterable<DataSnapshot>dsChilds= ds.child("sorular").getChildren();
                   ArrayList<Soru>sorus=new ArrayList<>(0);
                   for (DataSnapshot subchild:dsChilds)
                   {

                       Soru soru=subchild.getValue(Soru.class);
                       sorus.add(soru);
                      // Toast.makeText(getBaseContext(),soru.toString(),Toast.LENGTH_SHORT).show();
                   }
                   sinav1.setSorular(sorus);
                  denemeler.add(sinav1);

                }

               // Toast.makeText(getBaseContext(),denemeler.get(0).getSorular().size()+"  deneme 0 ın soru sayısı",Toast.LENGTH_SHORT).show();
              //  Toast.makeText(getBaseContext(),denemeler.get(1).getSorular().size()+"   deneme 1 in soru sayısı ",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


       //Toast.makeText(getBaseContext(),denemeler.size()+"size",Toast.LENGTH_SHORT).show();

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
        DenemeSinaviAdapter denemeSinaviAdapter=new DenemeSinaviAdapter(getBaseContext(),denemeler);
        recyclerView.setAdapter(denemeSinaviAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));


    }
}
