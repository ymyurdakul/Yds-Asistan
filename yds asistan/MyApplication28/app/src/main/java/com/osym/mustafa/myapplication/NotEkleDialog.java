package com.osym.mustafa.myapplication;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.util.Calendar;

public class NotEkleDialog extends Dialog {
    public int dateMain,monthMain,yearMain;
    public String fullDate;
    Context context;
    @BindView(R.id.notdialog_datePickerTimeline) DatePickerTimeline timeline;
    @BindView(R.id.notdialog_btnadd)Button btnAdd;
    @BindView(R.id.notdialog_edtnot)EditText edtNot;
    public NotEkleDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekle_dialog);
        ButterKnife.bind(this);
        Calendar cal = Calendar.getInstance();

        dateMain = cal.get(Calendar.DATE);
        monthMain = cal.get(Calendar.MONTH) + 1; // in java month starts from 0 not from 1 so for december 11+1 = 12
        yearMain = cal.get(Calendar.YEAR);
          fullDate=yearMain+"-"+monthMain+"-"+dateMain;
        timeline.setInitialDate(yearMain,monthMain,dateMain);
        timeline.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int dayOfWeek) {
                dateMain=day;
                monthMain=month;
                yearMain=year;
                fullDate=year+"-"+month+"-"+day;
                //  Toast.makeText(context,s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDisabledDateSelected(int year, int month, int day, int dayOfWeek, boolean isDisabled) {

            }
        });
    }
    @OnClick(R.id.notdialog_btnadd)void  click()
    {
        if (edtNot.getText().toString().matches(""))
        {
            Toast.makeText(getContext(),"Not ksımını bos bırakamazsınız.",Toast.LENGTH_LONG).show();
            return;

        }
        Not not=new Not(fullDate,edtNot.getText().toString());
        MainPage.notDao.insertAll(not);
        MainPage.fbDatabase.getReference("notes").child("myyur").setValue(not);
        this.dismiss();
    }
}
