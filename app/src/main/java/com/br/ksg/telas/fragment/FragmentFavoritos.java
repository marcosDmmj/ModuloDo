package com.br.ksg.telas.fragment;

import java.util.ArrayList;
import java.util.List;

import com.br.ksg.adapters.CustomAdapterCategoria;
import com.br.ksg.classesBasicas.ReceitaBasica;
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
        List<ReceitaBasica> item_l_f = getExampleList();
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
       /* myListView = (ListView) rootView.findViewById(R.id.myListView);
        int[] to = { android.R.id.text1, android.R.id.text2};        
       
        String[] from = { "ExampleId", "ExampleName" };
		
        SimpleAdapter objAdapter =new SimpleAdapter(this.getActivity(), l, android.R.layout.simple_list_item_2, from, to);
		myListView.setAdapter(objAdapter);
        */
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

	private List<ReceitaBasica> getExampleList(){
        List<ReceitaBasica> p = new ArrayList<ReceitaBasica>();
        
        //Tentar implementar com SharedPreferences
        p.add(new ReceitaBasica("Musculo de Panela", "45 min", "5 porções"));
        p.add(new ReceitaBasica("Carne em tira com legumes", "30 min", "5 porções"));
        p.add(new ReceitaBasica("Carne de panela", "50 min", "5 porções"));
        p.add(new ReceitaBasica("Escondidinho de Picadinho", "45 min", "7 porções"));
        p.add(new ReceitaBasica("Polpetone", "60 min", "6 porções"));
        p.add(new ReceitaBasica("Yakissoba da casa", "40 min", "4 porções"));
        p.add(new ReceitaBasica("Bife a Milanesa", "40 min", "6 porções"));
        p.add(new ReceitaBasica("Almôndegas Recheadas", "30 min", "3 porções"));
        return p;
	}
	public void usarToast(String texto) {
		Toast.makeText(getActivity(), texto, Toast.LENGTH_LONG).show();
	}
	
}
