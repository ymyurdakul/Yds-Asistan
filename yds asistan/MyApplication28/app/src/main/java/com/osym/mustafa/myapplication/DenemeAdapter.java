package com.osym.mustafa.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DenemeAdapter extends RecyclerView.Adapter<DenemeViewHolder> {
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<Deneme>denemes;

    public DenemeAdapter(Context context, ArrayList<Deneme>denemes)
    {

        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        this.denemes=denemes;

    }

    @NonNull
    @Override
    public DenemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


       View view= layoutInflater.inflate(R.layout.denemesonucu_recitem,parent,false);
        return  new DenemeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DenemeViewHolder holder, int position) {

        holder.setData(denemes.get(position));
    }

    @Override
    public int getItemCount() {


        return denemes.size();
    }


}
class DenemeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    TextView dogruSayisi,yanlisSayisi,puan,tarih,numara;
    public DenemeViewHolder(@NonNull View itemView) {
        super(itemView);
        dogruSayisi=(TextView)itemView.findViewById(R.id.denemesonucu_recitem_dogrusayısı);
        yanlisSayisi=(TextView)itemView.findViewById(R.id.denemesonucu_recitem_yanlissayisi);
        puan=(TextView)itemView.findViewById(R.id.denemesonuc_recitem_puan);
        tarih=(TextView)itemView.findViewById(R.id.denemesonuc_recitem_tarih);
        numara=(TextView)itemView.findViewById(R.id.denemesonucu_recitem_denemeno);

    }

    public void setData(Deneme deneme) {
        dogruSayisi.setText(String.valueOf(deneme.getDogruSayisi()));
        yanlisSayisi.setText(String.valueOf(deneme.getYanlisSayisi()));
        puan.setText(String.valueOf(deneme.getPuan()));
        tarih.setText(deneme.getTarih());
        numara.setText(String.valueOf(deneme.getId()));
    }


    @Override
    public void onClick(View v) {
        Log.d("dsds","dsdsdsd");
    }
}
