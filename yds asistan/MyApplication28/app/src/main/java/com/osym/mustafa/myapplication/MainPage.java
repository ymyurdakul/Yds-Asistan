package com.osym.mustafa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import in.championswimmer.libsocialbuttons.BtnSocial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.nikartm.button.FitButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mapzen.speakerbox.Speakerbox;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainPage extends AppCompatActivity {

    public static  AppDatabase appDatabase;
    public static DenemeDao denemeDao;
    public static NotDao notDao;
    FitButton btnAraclar;
    FitButton btncikmisSorular;
    FitButton btnDenemeler;
    FitButton btnHakkinda;
    FitButton btnKelimeler;
    FitButton btnKonular;
    FitButton btnMakaler;
    FitButton btnOsym;
    public static Speakerbox speakerbox;
    public static FirebaseDatabase fbDatabase;
    Context context=this;
    public static   SharedPreferences makaleSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initialize();
        speakerbox=new Speakerbox(getApplication());

        /*
        fbDatabase.getReference().child("words").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String,String>maks=(HashMap<String, String>) dataSnapshot.getValue();
               String t= maks.values().toString();
                 Toast.makeText(getBaseContext(),t,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
        makaleSharedPreferences =getSharedPreferences("MakaleDurumlar", Context.MODE_PRIVATE);
        SharedPreferences first=getSharedPreferences("isFirstStart",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=first.edit();
        editor.putBoolean("1",true);
        editor.commit();
        boolean status=first.getBoolean("isFirst",true);
        if (status)
        {
            Toast.makeText(getBaseContext(),"ilk yükleme",Toast.LENGTH_SHORT).show();
            firstLoadingMakaleStatus();
            editor.putBoolean("1",true);
        }

        BtnSocial c;
    }


    private void   firstLoadingMakaleStatus()
    {

        for (int i=1;i<17;i++) {
            SharedPreferences.Editor editor = makaleSharedPreferences.edit();
            editor.putBoolean(String.valueOf(i),false);
            editor.commit();
        }
    }

   private  void initialize()
   {
       fbDatabase=FirebaseDatabase.getInstance();
       //Db initalization
       appDatabase=AppDatabase.getInstance(this);
       denemeDao=appDatabase.getDenemDao();
       notDao=appDatabase.getNotDao();
       //View Initialization
       btnAraclar=(FitButton)findViewById(R.id.btnAraclar);
       btncikmisSorular=(FitButton) findViewById(R.id.btnCikmisSorular);
       btnDenemeler=(FitButton) findViewById(R.id.btnDenemeler);
       btnHakkinda=(FitButton)findViewById(R.id.btnHakkinda);
       btnKelimeler=(FitButton) findViewById(R.id.btnKelimeler);
       btnKonular=(FitButton) findViewById(R.id.btnKonular);
       btnMakaler=(FitButton)findViewById(R.id.btnMakaleler);
       btnOsym=(FitButton) findViewById(R.id.btnOsym);
       //Click Events
       btnAraclar.setOnClickListener(click->startCustomActivity(ToolsActivity.class));
       btnMakaler.setOnClickListener(click->startCustomActivity(Makaleler.class));
       btnKelimeler.setOnClickListener(click->startCustomActivity(Kelimeler.class));
       btnHakkinda.setOnClickListener(click->startCustomActivity(Hakkinda.class));
       btncikmisSorular.setOnClickListener(click->startCustomActivity(CikmisSorularActivity.class));
       btnOsym.setOnClickListener(click->startCustomActivity(Osym.class));
   }

    private void startCustomActivity(Class classx)
    {
        startActivity(new Intent(this,classx));

    }
}
