package com.osym.mustafa.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mapzen.speakerbox.Speakerbox;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class KelimeFragment extends androidx.fragment.app.Fragment {

    @BindViews({R.id.btnAChar,R.id.btnBChar,R.id.btnCChar,R.id.btnDChar})
    List<Button>buttons;
    @BindView(R.id.btnNextWord)Button btnNext;
    @BindView(R.id.tvEnMean)TextView tvEnMean;
    @BindView(R.id.tvTrMean)TextView tvTrMean;
    @BindView(R.id.tvChar)TextView tvCharecter;
    ArrayList<Kelime>temp;
    ArrayList<Kelime>kelimeler;
    Context context;
    String selectedKey="-";
    int position=0;
    public KelimeFragment() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        kelimeler=new ArrayList<>();
        View view= inflater.inflate(R.layout.fragment_kelime, container, false);
        ButterKnife.bind(this,view);
        this.context=view.getContext();
        temp=new ArrayList<>();

        MainPage.
                fbDatabase.getReference().child("words").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot>dataSnapshotChildren=dataSnapshot.getChildren();
                for (DataSnapshot snapshot:dataSnapshotChildren)
                {
                    Kelime temp=snapshot.getValue(Kelime.class);
                    kelimeler.add(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        temp.addAll(kelimeler);
        return view;
    }
    public void refreshRec()
    {
        temp.clear();
        if(selectedKey.matches("-"))
            temp.addAll(kelimeler);
        else {
            for (Kelime kelime:kelimeler)
                if (kelime.getEn_mean().startsWith(selectedKey+""))
                    temp.add(kelime);
        }
        if (temp.size()==0);
       //  Toast.makeText(getView().getContext(),"...",Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.btnAChar,R.id.btnBChar,R.id.btnCChar,R.id.btnDChar})
    public  void click(Button btn)
    {
        position=0;
        Character character=btn.getText().toString().toLowerCase().charAt(0);
        selectedKey=character+"";
        refreshRec();
        tvCharecter.setText(btn.getText());
    //   Toast.makeText(getView().getContext(),temp.size()+"lasting size",Toast.LENGTH_LONG).show();

    }
    @OnClick(R.id.btnNextWord)
    public void click()
    {

      try {
          if (temp.size() <= 0) {
           //   Toast.makeText(getView().getContext(), "Kelimeler yükleniyor...", Toast.LENGTH_SHORT).show();
              refreshRec();

          }
          if (position == temp.size())
              return;
          else {
              position++;
              Kelime kelime = temp.get(position);
              if (kelime != null) {

                  tvEnMean.setText(kelime.getEn_mean());
                  tvTrMean.setText(kelime.getTr_mean());


              }
          }
      }
      catch (Exception ex)
      {}
    }
    @OnClick(R.id.btnPreviousWord)
    public void click2()
    {
        try {

            if (temp.size() <= 0) {
               // Toast.makeText(getView().getContext(), "Kelimeler yükleniyor...", Toast.LENGTH_SHORT).show();
                refreshRec();

            }
            if (position == 0)
                return;

            else {

                position--;
                Kelime kelime = temp.get(position);
                if (kelime != null) {

                    tvEnMean.setText(kelime.getEn_mean());
                    tvTrMean.setText(kelime.getTr_mean());


                }
                //  Toast.makeText(getView().getContext(),position+"pos değeri",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex)
        {}
    }
    @OnClick(R.id.btnWordSpeaker)
    public void click3()
    {
        if (temp.size()==0)
            return;
     Speakerbox speakerbox=  MainPage.speakerbox;
     speakerbox.unmute();
     speakerbox.play(temp.get(position).getEn_mean());
    }

}
