package com.br.ksg.telas.fragment;

import java.util.ArrayList;
import java.util.List;

import com.br.ksg.adapters.CustomAdapterCategoria;
import com.br.ksg.classesBasicas.ReceitaBasica;
import com.br.ksg.classesDAO.ReceitasDAO;
import com.example.exempleswipetab.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentFavoritos extends Fragment {
	
private ListView myListView;	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 		
        View rootView = inflater.inflate(R.layout.activity_main_categoria, container, false); 
        List<ReceitaBasica> item_l_f = listaCheia();
        ArrayAdapter<ReceitaBasica> ad = new CustomAdapterCategoria(getActivity(), R.layout.item, item_l_f);
        myListView = (ListView) rootView.findViewById(R.id.myListView);
        myListView.setAdapter(ad);
        
        myListView.setOnItemClickListener(new OnItemClickListener() {
            @SuppressWarnings({ "rawtypes" })            
			public void onItemClick( AdapterView parent, View view,
                    int position, long id) {
            	acessa_a_receita();
            }
        });

        return rootView;
    }
	public void acessa_a_receita(){
		usarToast("metodo acessa_a_receita");
		/*
		Intent i = new Intent(getActivity(),FinalActivity.class);
		i.putExtra("titulo", rct);
		startActivity(i);
		*/
	}

	public void usarToast(String texto) {
		Toast.makeText(getActivity(), texto, Toast.LENGTH_LONG).show();
	}

    public ArrayList<ReceitaBasica> listaCheia(){
        ReceitasDAO receitaBasicaDAO = new ReceitasDAO(getActivity());
        return receitaBasicaDAO.getListaFavoritos();
    }

}
