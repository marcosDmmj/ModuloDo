package com.br.ksg.telas.fragment;

import java.util.ArrayList;
import java.util.List;

import com.br.ksg.adapters.CustomAdapterCategoria;
import com.br.ksg.classesBasicas.Receita;
import com.br.ksg.classesBasicas.ReceitaBasica;
import com.br.ksg.classesDAO.ReceitasDAO;
import com.br.ksg.telas.ReceitaActivity;
import com.example.exempleswipetab.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentFavoritos extends Fragment {

    ListView myListView;
    String nome;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main_categoria, container, false);
        final List<ReceitaBasica> item_l_f = listaCheia();
        ArrayAdapter<ReceitaBasica> ad = new CustomAdapterCategoria(getActivity(), R.layout.item, item_l_f);
        myListView = (ListView) rootView.findViewById(R.id.myListView);
        myListView.setAdapter(ad);

        myListView.setOnItemClickListener(new OnItemClickListener() {
            @SuppressWarnings({ "rawtypes" })
            public void onItemClick( AdapterView parent, View view,
                                     int position, long id) {
                nome = item_l_f.get(position).getNome();
                acessa_a_receita();
            }
        });

        return rootView;
    }
    public void acessa_a_receita(){
        try{
            Intent intent = new Intent(getActivity(),ReceitaActivity.class);
            Bundle b = new Bundle();

            ReceitasDAO receitaFav = new ReceitasDAO(getActivity());
            Receita receita = receitaFav.retornaReceita(nome);

            b.putString("id", receita.getId_receita());
            String nome = receita.getNome();
            b.putString("nome", nome.substring(0,1).toUpperCase()+nome.substring(1));
            b.putString("modo_preparo", receita.getModo_preparo());
            b.putString("porcoes", receita.getPorcoes());
            b.putString("categoria", receita.getCategoria());
            b.putString("tempo", receita.getTempo());
            b.putString("dificuldade", receita.getDificuldade());
            b.putDouble("rating", receita.getRating());
            int q = receitaFav.quantIngredientes(Integer.parseInt(receita.getId_receita()));
            b.putString("quant", ""+q);

            List <String> ing = receitaFav.getListaIngrediente(Integer.parseInt(receita.getId_receita()));
            List <String> id_ing = receitaFav.getListaIDIngrediente(Integer.parseInt(receita.getId_receita()));
            for (int j = 0; j < q; j++) {
                b.putString("ingrediente"+j, ing.get(j));
                b.putString("id_ing"+j, id_ing.get(j));
            }

            b.putInt("tamanho", 2);

            intent.putExtra("receita", b);


            startActivity(intent);
        }catch (Exception e) {
            Log.i("KSG", "Chore aqui ToT "+e.getMessage());
        }
    }

    public ArrayList<ReceitaBasica> listaCheia(){
        ReceitasDAO receitaBasicaDAO = new ReceitasDAO(getActivity());
        return receitaBasicaDAO.getListaFavoritos();
    }

}