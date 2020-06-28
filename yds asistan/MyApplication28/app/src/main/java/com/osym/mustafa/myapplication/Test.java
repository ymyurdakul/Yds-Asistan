package com.osym.mustafa.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Test extends AppCompatActivity {

    PieChartView pieChartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

pieChartView=(PieChartView)findViewById(R.id.pie);
        List<SliceValue> pieData = new ArrayList<>();
        pieData.add(new SliceValue(65, Color.GREEN).setLabel("Dogru Sayısı"));
        pieData.add(new SliceValue(25, Color.RED).setLabel("Yanlış Sayısı $4"));
        pieData.add(new SliceValue(80, Color.BLUE).setLabel("Net Sayısı"));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true);
        pieChartData.setHasCenterCircle(true).setCenterText1("Sınav Sonucu").setCenterText1FontSize(10).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);


    }
}
