package com.osym.mustafa.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NotlarFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fbtn;
    NotDao notDao=MainPage.notDao;
    NotlarAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_notlar, container, false);
        recyclerView=view.findViewById(R.id.notlar_recyclerview);
        fbtn=view.findViewById(R.id.notlar_fbtnadd);
        fbtn.setOnClickListener(click->{
            NotEkleDialog dialog=new NotEkleDialog(getActivity());
            dialog.show();
        });

        ArrayList<Not>notes=new ArrayList<>();
        notes.addAll(notDao.getAll());
        adapter=new NotlarAdapter(getActivity(),notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        notDao.getAllLive().observe(this, new Observer<List<Not>>() {
            @Override
            public void onChanged(List<Not> nots) {
                notes.clear();
                notes.addAll(nots);
                adapter=new NotlarAdapter(getActivity(),notes);
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
                Not note= notes.get(position);
                new AlertDialog.Builder(getActivity()).setTitle("Uyarı").setMessage("Sectiginiz notu silmek istiyormusunuz?")
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainPage.notDao.deleteAll(note);

                            }
                        }).setNegativeButton("Hayır",null).show();

            }
        }));
        return view;
    }


}
