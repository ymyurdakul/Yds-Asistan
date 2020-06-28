package com.osym.mustafa.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
@IgnoreExtraProperties
public class Kelime implements Serializable {
    private int id;
    private String tr_mean;
    private String en_mean;

    public Kelime(String tr_mean, String en_mean) {
        this.tr_mean = tr_mean;
        this.en_mean = en_mean;
    }
    public Kelime()
    {}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTr_mean() {
        return tr_mean;
    }

    public void setTr_mean(String tr_mean) {
        this.tr_mean = tr_mean;
    }

    public String getEn_mean() {
        return en_mean;
    }

    public void setEn_mean(String en_mean) {
        this.en_mean = en_mean;
    }
}
