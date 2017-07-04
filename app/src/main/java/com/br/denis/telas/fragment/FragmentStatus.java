package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.br.denis.adapters.CustomAdapterFavoritos;
import com.br.denis.classesBasicas.ReceitaBasica;
import com.example.exempleswipetab.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentStatus extends Fragment {

    ListView myListView;
    String nome;
    int j;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_status, container, false);
        final List<ReceitaBasica> item_l_f = new ArrayList<>();
        ArrayAdapter<ReceitaBasica> ad = new CustomAdapterFavoritos(getActivity(), R.layout.item, item_l_f);
        myListView = (ListView) rootView.findViewById(R.id.myListView);
        myListView.setAdapter(ad);

        myListView.setOnItemClickListener(new OnItemClickListener() {
            @SuppressWarnings({ "rawtypes" })
            public void onItemClick( AdapterView parent, View view,
                                     int position, long id) {
                nome = item_l_f.get(position).getNome();
                //acessa_a_receita();
            }
        });

        return rootView;
    }

}