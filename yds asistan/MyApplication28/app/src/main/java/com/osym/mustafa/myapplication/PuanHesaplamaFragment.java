package com.osym.mustafa.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class PuanHesaplamaFragment extends   androidx.fragment.app.Fragment{
    Button btnHedefArtir,btnHedefAzalt,btnDogruArtir,btnDogruAzalt,btnYanlisArtir,btnYanlisAzalt,btnHesapla;
    EditText edtHedef,edtDogruSayisi,edtYanlisSayisi;
    TextView tvYdsPuan,tvYdsHarfKarsiligi,tvGerekenPuan,tvGerekenDogru;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.acrivity_fragment_puanhesaplama, container, false);
        initialize(v);
        MobileAds.initialize(v.getContext(),"ca-app-pub-8775574156097763~6602498144");
        AdView adView=v.findViewById(R.id.adView1);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        return v;
    }

   public void initialize(View v)
   {
       btnHedefArtir=(Button)v.findViewById(R.id.ph_btnHedefArtir);
       btnHedefAzalt=(Button)v.findViewById(R.id.ph_btnHedefAzalt);
       btnDogruArtir=(Button)v.findViewById(R.id.ph_btnDogruArtir);
       btnDogruAzalt=(Button)v.findViewById(R.id.ph_btnDogruAzalt);
       btnYanlisArtir=(Button)v.findViewById(R.id.ph_btnYanlisArtir);
       btnYanlisAzalt=(Button)v.findViewById(R.id.ph_btnYanlisAzalt);
       btnHesapla=(Button)v.findViewById(R.id.ph_btnHesapla);
       edtHedef=(EditText)v.findViewById(R.id.ph_edthedefPuan);
       edtDogruSayisi=(EditText)v.findViewById(R.id.ph_edtdogrucevap);
       edtYanlisSayisi=(EditText)v.findViewById(R.id.ph_edtyanliscevap);
       tvYdsPuan=(TextView)v.findViewById(R.id.ph_tvYdsPuan);
       tvYdsHarfKarsiligi=(TextView)v.findViewById(R.id.ph_tvPuanHarfKarsiligi);
       tvGerekenPuan=(TextView)v.findViewById(R.id.ph_tvGerekenPuan);
       tvGerekenDogru=(TextView)v.findViewById(R.id.ph_tvGerekenDogruSayisi);

       btnHedefArtir.setOnClickListener(c->{
           if (edtHedef.getText().toString().matches(""))
               return;
           int x=Integer.valueOf(edtHedef.getText().toString());
           if (x>=100)
               x=100;
           else
               x=x+1;
           edtHedef.setText(String.valueOf(x));
       });
       btnHedefAzalt.setOnClickListener(c->{
           if (edtHedef.getText().toString().matches(""))
               return;
           int x=Integer.valueOf(edtHedef.getText().toString());
           if (x<=0)
               x=0;
           else
               x=x-1;
           edtHedef.setText(String.valueOf(x));
       });
       btnDogruArtir.setOnClickListener(c->{
           if (edtDogruSayisi.getText().toString().matches(""))
               return;
           int x=Integer.valueOf(edtDogruSayisi.getText().toString());
           if (x>=80)
               x=80;
           else
               x=x+1;
           edtDogruSayisi.setText(String.valueOf(x));
       });
       btnDogruAzalt.setOnClickListener(c->{
           if (edtDogruSayisi.getText().toString().matches(""))
               return;
           int x=Integer.valueOf(edtDogruSayisi.getText().toString());
           if (x<=0)
               x=0;
           else
               x=x-1;
           edtDogruSayisi.setText(String.valueOf(x));
       });
       btnYanlisArtir.setOnClickListener(c->{
           if (edtYanlisSayisi.getText().toString().matches(""))
               return;
           int x=Integer.valueOf(edtYanlisSayisi.getText().toString());
           if (x>=80)
               x=80;
           else
               x=x+1;
           edtYanlisSayisi.setText(String.valueOf(x));
       });
        btnYanlisAzalt.setOnClickListener(c->{
            if (edtYanlisSayisi.getText().toString().matches(""))
                return;
            int x=Integer.valueOf(edtYanlisSayisi.getText().toString());
            if (x<=0)
                x=0;
            else
                x=x-1;
            edtYanlisSayisi.setText(String.valueOf(x));
        });
        btnHesapla.setOnClickListener(c->{
            if (edtYanlisSayisi.getText().toString().matches("")||edtDogruSayisi.getText().toString().matches("")||edtHedef.getText().toString().matches(""))
            {
                new AlertDialog.Builder(v.getContext()).setTitle("Hata").setMessage("Boş geçilen alanlar var")
                        .setPositiveButton("Tamam",null).show();
                return;
            }
            int dogruSayisi=Integer.parseInt(edtDogruSayisi.getText().toString());
            int yanlisSayisi=Integer.parseInt(edtYanlisSayisi.getText().toString());
            float hedefPuan=Float.valueOf(edtHedef.getText().toString());

            if(dogruSayisi+yanlisSayisi>80)

            {
                new AlertDialog.Builder(v.getContext()).setTitle("Hata").setMessage("Dogru Sayisi ve Yanlış Sayısının Toplamı 80 olmalıdır.")
                        .setPositiveButton("Tamam", null).show();
                return;
            }
            float ydsPuani= (float) (dogruSayisi*1.25);
            if (hedefPuan-ydsPuani>0) {
                tvGerekenPuan.setText(String.valueOf(hedefPuan - ydsPuani));
                tvGerekenDogru.setText(String.valueOf((hedefPuan-ydsPuani)/1.25));
            }
            else
                tvGerekenPuan.setText("0");
            tvYdsPuan.setText(String.valueOf(ydsPuani));
            if (ydsPuani>=90&&ydsPuani<=100)
                tvYdsHarfKarsiligi.setText("A");
            if (ydsPuani>=80&&ydsPuani<=89)
                tvYdsHarfKarsiligi.setText("B");
            if (ydsPuani>=70&&ydsPuani<=79)
                tvYdsHarfKarsiligi.setText("C");
            if (ydsPuani>=60&&ydsPuani<=69)
                tvYdsHarfKarsiligi.setText("D");
            if (ydsPuani>=50&&ydsPuani<=59)
                tvYdsHarfKarsiligi.setText("E");


        });
   }
}
