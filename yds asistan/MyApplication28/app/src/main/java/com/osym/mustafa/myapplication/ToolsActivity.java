package com.osym.mustafa.myapplication;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import me.ibrahimsn.lib.OnItemReselectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class ToolsActivity extends AppCompatActivity {
    @BindView(R.id.toolstopbar)SmoothBottomBar smoothBottomBar;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    DenemeSonucuFragment denemeSonucuFragment;
    PuanHesaplamaFragment puanHesaplamaFragment;
    ZamanFragment zamanFragment;
    NotlarFragment notlarFragment;
    @BindView(R.id.tools_toolbar)Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        notlarFragment=new NotlarFragment();
        zamanFragment=new ZamanFragment();
        denemeSonucuFragment=new DenemeSonucuFragment();
        puanHesaplamaFragment=new PuanHesaplamaFragment();

        frameLayout=(FrameLayout)findViewById(R.id.tools_framelayout);

        changeFragment(puanHesaplamaFragment);
        smoothBottomBar.setOnItemReselectedListener(new OnItemReselectedListener() {
            @Override
            public void onItemReselect(int i) {
                Toast.makeText(getBaseContext(),i+"res",Toast.LENGTH_SHORT).show();
            }
        });
        smoothBottomBar.setOnItemSelected(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer integer) {
                switch (integer.intValue())
                {
                    case 1:{
                        changeFragment(denemeSonucuFragment);
                    }
                    break;
                    case 0:
                        changeFragment(puanHesaplamaFragment);
                        break;
                    case  2:
                        changeFragment(zamanFragment);
                        break;
                    case  3:
                        changeFragment(notlarFragment);
                        break;

                }
                return null;
            }
        });



    }
    public  void  changeFragment(@NonNull  Fragment fragment){
        fragmentManager=getSupportFragmentManager();
         FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
       fragmentTransaction.replace(R.id.tools_framelayout,fragment);
       fragmentTransaction.commit();
    }
}
