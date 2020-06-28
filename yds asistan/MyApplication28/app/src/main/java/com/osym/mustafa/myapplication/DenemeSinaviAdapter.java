package com.osym.mustafa.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class DenemeSinaviAdapter extends RecyclerView.Adapter<DenemeSinaviViewHolder> {

    LayoutInflater inflater;
    Context context;
    ArrayList<DenemeSinav>sinavlar;
    public DenemeSinaviAdapter(Context context, ArrayList<DenemeSinav>sinavlar)
    {
        inflater=LayoutInflater.from(context);
        this.context=context;
        this.sinavlar=sinavlar;

    }
    @NonNull
    @Override
    public DenemeSinaviViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= inflater.inflate(R.layout.denemesinavrecitem,parent,false);
        return  new DenemeSinaviViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DenemeSinaviViewHolder holder, int position) {
        holder.setData(sinavlar.get(position));
    }

    @Override
    public int getItemCount() {
        return sinavlar.size();
    }


}
class DenemeSinaviViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static String CIKMIS_SORU_DENEMESINAV_EXTRA;

    TextView tvSinavYili;
    TextView tvSinavDonem;
    TextView tvSoruSayisi;
    View view;
    DenemeSinav sinav;

    public DenemeSinaviViewHolder(@NonNull View itemView) {
        super(itemView);
        tvSinavDonem=(TextView)itemView.findViewById(R.id.denemesinavrecitem_tvDonem);
        tvSinavYili=(TextView)itemView.findViewById(R.id.denemesinavrecitem_tvYil);
        tvSoruSayisi=(TextView)itemView.findViewById(R.id.denemesinavrecitem_tvSoruSayisi);
        view=itemView;

        itemView.setOnClickListener(this);
    }
    public void setData(DenemeSinav sinav)
    {
        this.sinav=sinav;
        tvSoruSayisi.setText(String.valueOf(sinav.getSoru_sayisi()));
        tvSinavYili.setText(String.valueOf(sinav.getYil()));
        tvSinavDonem.setText(String.valueOf(sinav.getDonem()));

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(v.getContext(),SoruViewer.class);
       // v.getContext().startActivity(intent);
     //  Toast.makeText(view.getContext(),sinav.getDonem().toString(),Toast.LENGTH_LONG).show();
     //  Toast.makeText(view.getContext(),sinav.getSorular().size()+" sizeof",Toast.LENGTH_SHORT).show();
    //   Toast.makeText(view.getContext(),sinav.getSorular().get(0).getQuestion(),Toast.LENGTH_SHORT).show();
       //for (Soru soru:sinav.getSorular())
         if (sinav.getSorular().size()==0)
           Toast.makeText(view.getContext(),"Bos soruşar",Toast.LENGTH_SHORT).show();
     //   Toast.makeText(view.getContext(),sinav.getSorular().size()+"dsds",Toast.LENGTH_SHORT).show();
    //    Toast.makeText(view.getContext(),sinav.getSorular().get(2).getQuestion(),Toast.LENGTH_SHORT).show();
        intent.putExtra("extra",sinav);
        v.getContext().startActivity(intent);
    }
}
