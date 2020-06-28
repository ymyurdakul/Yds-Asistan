package com.osym.mustafa.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotlarAdapter extends RecyclerView.Adapter<NotlarViewHolder>{

    LayoutInflater inflater;
    Context context;
    ArrayList<Not>notes;

    public NotlarAdapter(Context context,ArrayList<Not>notes)
    {
        this.context=context;
        this.notes=notes;
        inflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public NotlarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.notlarrecitem,parent,false);
        return new NotlarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotlarViewHolder holder, int position) {
        holder.setData(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
class NotlarViewHolder extends RecyclerView.ViewHolder{

    public TextView tvTarih,tvNot;
    public NotlarViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTarih=itemView.findViewById(R.id.notlar_recitem_tarih_tv);
        tvNot=itemView.findViewById(R.id.notlar_recitem_not_tv);
    }

    public void setData(Not not){
        tvNot.setText(not.getNote());
        tvTarih.setText(not.getDate());

    }
}
