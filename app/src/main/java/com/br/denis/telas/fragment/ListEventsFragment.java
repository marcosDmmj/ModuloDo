package com.br.denis.telas.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.telas.adapters.CustomAdapterListEvents;
import com.example.exempleswipetab.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListEventsFragment extends Fragment {

    public ListEventsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_events, container, false);
    }

    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        getActivity().getActionBar().setTitle("Eventos do dia: "+getActivity().getIntent().getStringExtra("dia"));
        ListView listView = (ListView) getActivity().findViewById(R.id.myListNextEvents);
        ArrayList<Evento> eventos = getActivity().getIntent().getParcelableArrayListExtra("eventosDoDia");
        final CustomAdapterListEvents eventosAdapter = new CustomAdapterListEvents(getActivity(),R.layout.item_evento_sem_data, eventos);
        listView.setAdapter(eventosAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Evento evento = eventosAdapter.getItem(i);
                Toast.makeText(getActivity(),"Pegou no item "+evento.getdata_inicio() +" -> "+evento.getdata_fim(),Toast.LENGTH_SHORT).show();
            }
        });
        TextView emptyView = (TextView) getActivity().findViewById(android.R.id.empty);
        listView.setEmptyView(emptyView);
    }
}
