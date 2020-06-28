package com.osym.mustafa.myapplication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "denemeler")
public class Deneme {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "dogru_sayisi")
    private int dogruSayisi;
    @ColumnInfo(name = "yanlis_sayisi")
    private int yanlisSayisi;
    @ColumnInfo(name = "tarih")
    private  String tarih;
    @ColumnInfo(name = "puan")
    private double puan;

    public Deneme(int dogruSayisi, int yanlisSayisi, String tarih, double puan) {
        this.dogruSayisi = dogruSayisi;
        this.yanlisSayisi = yanlisSayisi;
        this.tarih = tarih;
        this.puan = puan;
    }

    public static   double puanHesapla(int dogruSayisi)
    {
        return (dogruSayisi/80)*100;

    }
    public int getDogruSayisi() {
        return dogruSayisi;
    }

    public void setDogruSayisi(int dogruSayisi) {
        this.dogruSayisi = dogruSayisi;
    }

    public int getYanlisSayisi() {
        return yanlisSayisi;
    }

    public void setYanlisSayisi(int yanlisSayisi) {
        this.yanlisSayisi = yanlisSayisi;
    }



    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public double getPuan() {
        return puan;
    }

    public void setPuan(double puan) {
        this.puan = puan;
    }

    @NonNull
    @Override
    public String toString() {
        return getDogruSayisi()+"----"+getYanlisSayisi()+"---"+getPuan()+"---"+getTarih();
    }
}
