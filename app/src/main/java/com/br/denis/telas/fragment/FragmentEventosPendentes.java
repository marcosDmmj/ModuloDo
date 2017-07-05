package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.telas.MainActivity;
import com.br.denis.telas.adapters.CustomAdapterEventosPendentes;
import com.example.exempleswipetab.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentEventosPendentes extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_fragment_eventos_pendentes,container, false);
	}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);

		ListView listView = (ListView) getActivity().findViewById(R.id.myListEventosPendentes);
        final CustomAdapterEventosPendentes eventosAdapter = new CustomAdapterEventosPendentes(getActivity(),R.layout.item_evento, MainActivity.eventosTemp);
        listView.setAdapter(eventosAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Evento evento = eventosAdapter.getItem(i);
                Toast.makeText(getActivity(),"Pegou no item "+evento.getNome(),Toast.LENGTH_SHORT).show();
            }
        });
        TextView emptyView = (TextView) getActivity().findViewById(android.R.id.empty);
        listView.setEmptyView(emptyView);
	}
}
