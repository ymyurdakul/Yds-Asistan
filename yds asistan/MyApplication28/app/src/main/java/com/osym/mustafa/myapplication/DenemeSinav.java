package com.osym.mustafa.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.room.Ignore;

@IgnoreExtraProperties
public class DenemeSinav implements Serializable {
    private String yil;
    private String donem;
    private int soru_sayisi;

    public int getSoru_sayisi() {
        return soru_sayisi;
    }

    public void setSoru_sayisi(int soru_sayisi) {
        this.soru_sayisi = soru_sayisi;
    }

    @Ignore
    private ArrayList<Soru>sorular;

    public DenemeSinav()
    {

    }

    public String getYil() {
        return yil;
    }

    public void setYil(String yil) {
        this.yil = yil;
    }

    public String getDonem() {
        return donem;
    }

    public void setDonem(String donem) {
        this.donem = donem;
    }



    public ArrayList<Soru> getSorular() {
        return sorular;
    }

    public void setSorular(ArrayList<Soru> sorular) {
        this.sorular = sorular;
    }


}
