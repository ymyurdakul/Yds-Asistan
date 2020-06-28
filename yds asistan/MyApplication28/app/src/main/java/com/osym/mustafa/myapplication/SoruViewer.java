package com.osym.mustafa.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SoruViewer extends AppCompatActivity {

    @BindView(R.id.soruviewer_toolbar)Toolbar toolbar;
     @BindView(R.id.tvQuestion)TextView tvQuestion;
    @BindView(R.id.tvTopDescriptivePAragraph)TextView tvTopDescriptiveParagpraph;
     @BindView(R.id.soruViewer_tvAResult)TextView tvAResult;
     @BindView(R.id.soruViewer_tvBResult)TextView tvBResult;
     @BindView(R.id.soruViewer_tvCResult)TextView tvCResult;
     @BindView(R.id.soruViewer_tvDResult)TextView tvDResult;
     @BindView(R.id.soruViewer_tvEResult)TextView tvEResult;

    DenemeSinav denemeSinav;
    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_viewer);
        ButterKnife.bind(this);
        denemeSinav=(DenemeSinav) getIntent().getSerializableExtra("extra") ;
     // Toast.makeText(getBaseContext(),denemeSinav.getSorular().size()+"ter",Toast.LENGTH_SHORT).show();
   setUiAsDenemeSinav();
    }
    // TODO: 2/26/2020  DEGER ATAMASINDA SORUN VAR DENEMLERIN SORUSULARI BOS GELİYOR
    // TODO: AMA SIZE I 3 GOSTERIYOR
    public void setUiAsDenemeSinav()
    {

        Soru soru=denemeSinav.getSorular().get(0);
        tvAResult.setText(soru.getA_choice());
        tvBResult.setText(soru.getB_choice());
        tvCResult.setText(soru.getC_choice());
        tvDResult.setText(soru.getD_choice());
        tvEResult.setText(soru.getE_choice());
        tvTopDescriptiveParagpraph.setText(soru.getTopDescriptiveParagraph());
        tvQuestion.setText(soru.getQuestion());
       // Toast.makeText(getBaseContext(), soru.toString(), Toast.LENGTH_LONG).show();
    }
}
