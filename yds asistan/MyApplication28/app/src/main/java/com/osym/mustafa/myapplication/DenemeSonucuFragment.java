package com.osym.mustafa.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class DenemeSonucuFragment extends androidx.fragment.app.Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    FloatingActionButton fbtn;
     DenemeAdapter adapter;
    public DenemeSonucuFragment() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<Deneme> denemes = new ArrayList<>();
        denemes.addAll(MainPage.denemeDao.getAll());


        View view = inflater.inflate(R.layout.activity_fragment_denemesonucu, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.denemesonucu_recyclerview);
        fbtn = (FloatingActionButton) view.findViewById(R.id.denemesonucu_fbtnadd);
        recyclerView.setOnClickListener(this);

        fbtn.setOnClickListener(this);
         adapter= new DenemeAdapter(getActivity(), denemes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        MainPage.denemeDao.getAllLive().observe(this, new Observer<List<Deneme>>() {
            @Override
            public void onChanged(List<Deneme> denemeler) {
                denemes.clear();
                denemes.addAll(denemeler);
                adapter=new DenemeAdapter(getActivity(),denemes);
                recyclerView.setAdapter(adapter);
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                onItemLongClick(view,position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Deneme deneme= denemes.get(position);
                new AlertDialog.Builder(getActivity()).setTitle("Uyarı").setMessage("Sectiginiz denemeye silmek istiyormusunuz?")
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainPage.denemeDao.deleteAll(deneme);

                            }
                        }).setNegativeButton("Hayır",null).show();
                Toast.makeText(getContext(),deneme.toString(),Toast.LENGTH_SHORT).show();
            }
        }));
        return view;
    }
    @Override
    public void onClick(View v) {
        PuanHesaplaEkle ekle=new PuanHesaplaEkle(v.getContext());
        ekle.show();
    }


}
