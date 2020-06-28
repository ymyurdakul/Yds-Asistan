package com.osym.mustafa.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@IgnoreExtraProperties
public class Makale implements Serializable {
    private String tr_text;
    private String en_text;

    private int id;
    @Ignore
    private int kelimeSayisi;
    @Ignore
    private boolean isRead;


    public Makale()
    {

    }

    public String getTr_text() {
        return tr_text;
    }

    public void setTr_text(String tr_text) {
        this.tr_text = tr_text;
    }

    public String getEn_text() {
        return en_text;
    }

    public void setEn_text(String en_text) {
        this.en_text = en_text;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  int getWordCount()
    {
       return getEn_text().split(" ").length;

    }
    public boolean isRead() {
        return isRead;
    }




    public int getKelimeSayisi() {
        return kelimeSayisi;
    }

    public void setKelimeSayisi(int kelimeSayisi) {
        this.kelimeSayisi = kelimeSayisi;
    }
}
