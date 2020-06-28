package com.osym.mustafa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import me.ibrahimsn.lib.SmoothBottomBar;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Kelimeler extends AppCompatActivity {
    KelimeFragment kelimeFragment;
    FragmentManager fragmentManager;
    @BindView(R.id.kelimeler_toolbar)Toolbar toolbar;
    @BindView(R.id.kelimeler_framelayout)FrameLayout frameLayout;
    @BindView(R.id.kelimeler_smoothbar)SmoothBottomBar smoothBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelimeler);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        kelimeFragment=new KelimeFragment();

        // Write a message to the database
        changeFragment(kelimeFragment);
        smoothBottomBar.setOnItemSelected(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer integer) {
                switch (integer.intValue()){
                    case 0:
                    {
                        changeFragment(kelimeFragment);
                    }
                    break;
                    case 1:{

                }
                    break;
                    case 2:{

                    }break;

                }
                return null;
            }
        });


    }
    public  void  changeFragment(@NonNull Fragment fragment){
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.kelimeler_framelayout,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cikmismenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        kelimeFragment.refreshRec();
        return super.onOptionsItemSelected(item);
    }

}
