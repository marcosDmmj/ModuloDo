package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.br.denis.classesBasicas.Evento;
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
        final CustomAdapterEventosPendentes eventosAdapter = new CustomAdapterEventosPendentes(getActivity(),R.layout.item_evento,gerarEventos());
        listView.setAdapter(eventosAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Evento evento = eventosAdapter.getItem(i);
                Toast.makeText(getActivity(),"Pegou no item "+evento.getNome(),Toast.LENGTH_SHORT).show();
            }
        });
	}

    private List<Evento> gerarEventos() {
        List<Evento> Eventos = new ArrayList<Evento>();
        Eventos.add(new Evento("Shane", "33", "R.drawable.Evento_um","R.drawable.Evento_um","R.drawable.Evento_um"));
        Eventos.add(new Evento("Hershel", "233", "R.drawable.Evento_dois","R.drawable.Evento_dois","R.drawable.Evento_dois"));
        Eventos.add(new Evento("Glen", "2339", "R.drawable.Evento_tres","R.drawable.Evento_tres","R.drawable.Evento_tres"));

        return Eventos;
    }
}
