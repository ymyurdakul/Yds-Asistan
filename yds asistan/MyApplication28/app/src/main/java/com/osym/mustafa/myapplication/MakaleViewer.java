package com.osym.mustafa.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mapzen.speakerbox.Speakerbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.emperor.animatedcheckbox.AnimatedCheckBox;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MakaleViewer extends AppCompatActivity {

    Makale makale;
    boolean durumFlag=false,playStatusFlag;
    @BindView(R.id.makaleleviewer_toolbar)Toolbar toolbar;
    @BindView(R.id.makaleviewer_tv_turkce)TextView tvTurkce;
    @BindView(R.id.makaleviewer_tv_inglizce)TextView tvIngilizce;
    @BindView(R.id.makale_btnView)Button btnView;
    @BindView(R.id.makaleviewer_isread)AnimatedCheckBox checkBox;
    @BindView(R.id.makale_btnDinle)Button btnDinle;
    Speakerbox speakerbox;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makale_viewer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvTurkce.setText("");
        tvIngilizce.setText("");

        makale=(Makale) getIntent().getSerializableExtra(MakaleViewHolder.MAKALE_EXTRA);
        if (makale==null) {
            Toast.makeText(getBaseContext(), "hata makale", Toast.LENGTH_LONG).show();
            return;
        }
        toolbar.setTitle(toolbar.getTitle()+"  "+makale.getId());
         tvIngilizce.setText(makale.getEn_text());

        btnView.setOnClickListener(e->click());
        if (makale.isRead())
        checkBox.setChecked(true);
        speakerbox =new Speakerbox(getApplication());
        btnDinle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (playStatusFlag==false) {
                   speakerbox.unmute();
                   speakerbox.play(makale.getEn_text());
                   btnDinle.setText("Durdur");
               }
               else{
                   speakerbox.stop();
                   btnDinle.setText("Dinle");
               }
                playStatusFlag=!playStatusFlag;
            }
        });
        checkBox.setOnChangeListener(new Function1<Boolean, Unit>() {
            @Override
            public Unit invoke(Boolean aBoolean) {
                makale.setRead(aBoolean);
                 setMakaleStatusBySp(makale);
                return null;
            }
        });
    }

     void click()
    {
        if (durumFlag==false) {
            tvTurkce.setText(makale.getTr_text());
            btnView.setText("Gizle");
        }
        else {
            tvTurkce.setText("");
            btnView.setText("Göster");
        }
        durumFlag=!durumFlag;
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        speakerbox.stop();
        speakerbox.shutdown();
    }

    public  void setMakaleStatusBySp(Makale makale)
    {
        //Boolean val=MainPage.makaleSharedPreferences.getBoolean(String.valueOf(makale.getId()),true);
        //Toast.makeText(getBaseContext(),"Eski değer"+String.valueOf(val),Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor=MainPage.makaleSharedPreferences.edit();
        editor.putBoolean(String.valueOf(makale.getId()),makale.isRead());
        editor.commit();

    }
}
