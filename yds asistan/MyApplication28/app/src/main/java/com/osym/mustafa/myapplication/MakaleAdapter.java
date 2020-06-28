package com.osym.mustafa.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import it.emperor.animatedcheckbox.AnimatedCheckBox;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MakaleAdapter extends RecyclerView.Adapter<MakaleViewHolder>{
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<Makale>makales;

    public  MakaleAdapter(ArrayList<Makale>makales,Context context)
    {
        this.makales=makales;
        this.layoutInflater=LayoutInflater.from(context);
        this.context=context;

    }


    @NonNull
    @Override
    public MakaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.makalerecitem,parent,false);
        return new MakaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakaleViewHolder holder, int position) {
        holder.setData(makales.get(position));
    }

    @Override
    public int getItemCount() {
        return makales.size();
    }
}
class MakaleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public static String MAKALE_EXTRA;
    TextView tvMakaleNo,tvKelimeSayisi;
    AnimatedCheckBox checkBox;
    Makale makale;

    public MakaleViewHolder(@NonNull View itemView) {
        super(itemView);
        tvMakaleNo=(TextView)itemView.findViewById(R.id.makale_recitem_tv_makaleno);
        tvKelimeSayisi=(TextView)itemView.findViewById(R.id.makale_recitem_tv_kelimesayisi);
        checkBox=(AnimatedCheckBox)itemView.findViewById(R.id.makale_recitem_checkbox_isread);
        itemView.setOnClickListener(this);
        checkBox.setEnabled(false);



    }

    public void setData(Makale makale) {
        this.makale=makale;
        tvMakaleNo.setText(String.valueOf(makale.getId()));
        tvKelimeSayisi.setText(String.valueOf(makale.getWordCount()));
       boolean isRead=MainPage.makaleSharedPreferences.getBoolean(String.valueOf(makale.getId()),false);
       makale.setRead(isRead);
       if (makale.isRead())
            checkBox.setChecked(true);
       else
           checkBox.setChecked(false);
    }

    @Override
    public void onClick(View v) {

        Intent intent=new Intent(v.getContext(),MakaleViewer.class);
        intent.putExtra(MAKALE_EXTRA,makale);
        v.getContext().startActivity(intent);
    }

}
