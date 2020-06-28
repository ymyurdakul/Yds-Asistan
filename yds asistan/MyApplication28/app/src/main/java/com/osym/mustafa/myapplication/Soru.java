package com.osym.mustafa.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Ignore;
@IgnoreExtraProperties
public class Soru implements Serializable  {
    private String a_choice;
    private String b_choice;
    private String c_choice;
    private String d_choice;
    private String e_choice;
    private String true_choice;
    private String question;
    private int soru_no;

    public int getSoru_no() {
        return soru_no;
    }

    public void setSoru_no(int soru_no) {
        this.soru_no = soru_no;
    }

    @Ignore
    boolean isMultibleQuestion;
    private String topdescriptiveparagraph;

    public Soru(String a_choice, String b_choice, String c_choice, String d_choice, String e_choice, String true_choice, String question, boolean isMultibleQuestion, String topDescriptiveParagraph) {
        this.a_choice = a_choice;
        this.b_choice = b_choice;
        this.c_choice = c_choice;
        this.d_choice = d_choice;
        this.e_choice = e_choice;
        this.true_choice = true_choice;
        this.question = question;
        this.isMultibleQuestion = isMultibleQuestion;
        this.topdescriptiveparagraph = topDescriptiveParagraph;
    }

    public Soru() {

    }

    public String getA_choice() {
        return a_choice;
    }

    public void setA_choice(String a_choice) {
        this.a_choice = a_choice;
    }

    public String getB_choice() {
        return b_choice;
    }

    public void setB_choice(String b_choice) {
        this.b_choice = b_choice;
    }

    public String getC_choice() {
        return c_choice;
    }

    public void setC_choice(String c_choice) {
        this.c_choice = c_choice;
    }

    public String getD_choice() {
        return d_choice;
    }

    public void setD_choice(String d_choice) {
        this.d_choice = d_choice;
    }

    public String getE_choice() {
        return e_choice;
    }

    public void setE_choice(String e_choice) {
        this.e_choice = e_choice;
    }

    public String getTrue_choice() {
        return true_choice;
    }

    public void setTrue_choice(String true_choice) {
        this.true_choice = true_choice;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isMultibleQuestion() {
        return isMultibleQuestion;
    }

    public void setMultibleQuestion(boolean multibleQuestion) {
        isMultibleQuestion = multibleQuestion;
    }

    public String getTopDescriptiveParagraph() {
        return topdescriptiveparagraph;
    }

    public void setTopDescriptiveParagraph(String topDescriptiveParagraph) {
        this.topdescriptiveparagraph = topDescriptiveParagraph;
    }
    public boolean  isTrueAnswered(String caseRes)
    {
        return caseRes.matches(getTrue_choice().toString());
    }

    @NonNull
    @Override
    public String toString() {

        return getTopDescriptiveParagraph()+"\n"+getA_choice()+"\n"+getB_choice();
    }


}
