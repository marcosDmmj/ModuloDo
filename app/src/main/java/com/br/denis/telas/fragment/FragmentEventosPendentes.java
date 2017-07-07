package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.classesBasicas.OnTaskCompleted;
import com.br.denis.telas.adapters.CustomAdapterListEventsPendentes;
import com.br.denis.webservice.DownloadEventosPendentesAsync;
import com.denis.mdulodoprofessor.R;

import java.util.ArrayList;


public class FragmentEventosPendentes extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_fragment_eventos_pendentes,container, false);
	}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);

		ListView listView = (ListView) getActivity().findViewById(R.id.myListEventosPendentes);

        ArrayList<Evento> eventosTemp = getActivity().getIntent().getParcelableArrayListExtra("eventosTemp");
        final CustomAdapterListEventsPendentes eventosAdapter = new CustomAdapterListEventsPendentes(getActivity(),R.layout.item_evento, eventosTemp);
        listView.setAdapter(eventosAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Evento evento = eventosAdapter.getItem(i);

                // DialogFragment.show() will take care of adding the fragment
                // in a transaction.  We also want to remove any currently showing
                // dialog, so make our own transaction and take care of that here.
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                // Create and show the dialog.
                MyDialogFragment newFragment = MyDialogFragment.newInstance(evento);
                newFragment.setActionListener(new MyDialogFragment.ActionListener() {
                    @Override
                    public void onUpdate() {
                        new DownloadEventosPendentesAsync(getActivity(), new OnTaskCompleted() {
                            @Override
                            public void onTaskCompleted(ArrayList<Evento> eventoArrayList) {
                                eventosAdapter.clear();
                                eventosAdapter.addAll(eventoArrayList);
                                eventosAdapter.notifyDataSetChanged();
                            }
                        }).execute();
                    }
                });
                newFragment.show(ft, "dialog");

            }
        });
        final SwipeRefreshLayout swipeRefreshEventosPendentes = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipeRefreshEventosPendentes);
        swipeRefreshEventosPendentes.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new DownloadEventosPendentesAsync(getActivity(), new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(ArrayList<Evento> eventoArrayList) {
                        eventosAdapter.clear();
                        eventosAdapter.addAll(eventoArrayList);
                        eventosAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Eventos atualizados!", Toast.LENGTH_SHORT).show();
                        swipeRefreshEventosPendentes.setRefreshing(false);
                    }
                }).execute();
            }

        });
        TextView emptyView = (TextView) getActivity().findViewById(android.R.id.empty);
        listView.setEmptyView(emptyView);
	}
}
