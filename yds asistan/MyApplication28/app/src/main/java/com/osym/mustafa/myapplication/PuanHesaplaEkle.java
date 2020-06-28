package com.osym.mustafa.myapplication;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.util.Calendar;

public class PuanHesaplaEkle extends Dialog {
    public int dateMain,monthMain,yearMain;
    Context context;
    @BindView(R.id.datePickerTimeline) DatePickerTimeline timeline;
    @BindView(R.id.phe_btnDogruArtir)Button btnDogruArtir;
    @BindView(R.id.phe_btnDogruAzalt)Button btnDogruAzalt;
    @BindView(R.id.phe_edtdogrucevap)EditText edtDogru;
    @BindView(R.id.phe_btnYanlisArtir)Button btnYanlisArtir;
    @BindView(R.id.phe_btnYanlisAzalt)Button btnYanlisAzalt;
    @BindView(R.id.phe_edtyanliscevap)EditText edtYanlis;
    @BindView(R.id.phe_edtpuan)EditText edtPuan;
    @BindView(R.id.phe_btnEkle)Button btnEkle;


    public PuanHesaplaEkle(@NonNull Context context) {
        super(context);
        this.context=context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_puan_hesapla_ekle);
        ButterKnife.bind(this);
        Calendar cal = Calendar.getInstance();

        dateMain = cal.get(Calendar.DATE);
         monthMain = cal.get(Calendar.MONTH) + 1; // in java month starts from 0 not from 1 so for december 11+1 = 12
         yearMain = cal.get(Calendar.YEAR);

        timeline.setInitialDate(yearMain,monthMain,dateMain);
        timeline.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int dayOfWeek) {
                dateMain=day;
                monthMain=month;
                yearMain=year;
                String s=year+"-"+month+"-"+day;
              //  Toast.makeText(context,s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDisabledDateSelected(int year, int month, int day, int dayOfWeek, boolean isDisabled) {

            }
        });

    }
    @OnClick(R.id.phe_btnDogruArtir)void click()
    {
        if (edtDogru.getText().toString().matches(""))
            return;
        int x=0;
        try {
            x=Integer.valueOf(edtDogru.getText().toString());
            if(x>=80)
                x=79;
            x++;
            edtDogru.setText(String.valueOf(x));
        }
        catch (Exception ex)
        {

        }
    }
    @OnClick(R.id.phe_btnDogruAzalt)void  onClick1()
    {
        if (edtDogru.getText().toString().matches(""))
            return;
        int x=0;
        try {
            x=Integer.valueOf(edtDogru.getText().toString());
            if(x<=0)
                x=1;
            x--;
            edtDogru.setText(String.valueOf(x));
        }
        catch (Exception ex)
        {

        }
    }
    @OnClick(R.id.phe_btnYanlisArtir)void onCLick()
    {
        if (edtYanlis.getText().toString().matches(""))
            return;
        int x=0;
        try {
            x=Integer.valueOf(edtYanlis.getText().toString());
            if(x>=80)
                x=79;
            x++;
            edtYanlis.setText(String.valueOf(x));
        }
        catch (Exception ex)
        {

        }

    }
    @OnClick(R.id.phe_btnYanlisAzalt)void  onClick()
    {
        if (edtYanlis.getText().toString().matches(""))
            return;
        int x=0;
        try {
            x=Integer.valueOf(edtYanlis.getText().toString());
            if(x<=0)
                x=1;
            x--;
            edtYanlis.setText(String.valueOf(x));
        }
        catch (Exception ex)
        {

        }
    }
    @OnTextChanged(R.id.phe_edtyanliscevap)void changeYanlis()
    {
        if (edtYanlis.getText().toString().matches("")) {
            edtYanlis.setText("0");
            return;
        }
        int x=Integer.valueOf(edtYanlis.getText().toString());
        if (x<0 || x>80)
            edtYanlis.setText("0");
    }
    @OnTextChanged(R.id.phe_edtdogrucevap)void change()
    {
        if (edtDogru.getText().toString().matches("")) {
            edtPuan.setText("0");
            return;
        }

        int y=Integer.valueOf(edtDogru.getText().toString());
        int x=Integer.valueOf(edtYanlis.getText().toString());
        if (y<0||y>80)
        {
            edtDogru.setText("80");
            edtPuan.setText("100");
            edtYanlis.setText("0");
        }
        if (x+y>80)
        {

            new androidx.appcompat.app.AlertDialog.Builder(getContext()).setTitle("Hata").setMessage("Doğru ve Yanlış Soru Sayı Toplamı 80 veya daha az olmalıdır.")
                .setPositiveButton("Tamam",null).show();
            edtPuan.setText("0");
            return;
        }

        puanHesapla(y);

    }
    public void puanHesapla(int dogruSayisi)
    {
        float puan=(float) (dogruSayisi*1.25);
        edtPuan.setText(Float.valueOf(puan).toString());

    }
    @OnClick(R.id.phe_btnEkle)void cx(){
        int y=Integer.valueOf(edtDogru.getText().toString());
        int x=Integer.valueOf(edtYanlis.getText().toString());
        double puagn=Double.valueOf(edtPuan.getText().toString());
        if (x+y>80) {
            new androidx.appcompat.app.AlertDialog.Builder(getContext()).setTitle("Hata").setMessage("Doğru ve Yanlış Soru Sayı Toplamı 80 veya daha az olmalıdır.")
                    .setPositiveButton("Tamam", null).show();
            return;
        }
        Deneme deneme=new Deneme(y,x,yearMain+"-"+monthMain+"-"+dateMain,puagn);
        MainPage.denemeDao.insertAll(deneme);
        //Toast.makeText(getContext(),deneme.toString(),Toast.LENGTH_SHORT).show();
        this.dismiss();
    }


}
