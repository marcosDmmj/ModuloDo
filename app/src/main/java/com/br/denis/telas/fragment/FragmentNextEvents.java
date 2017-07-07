package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.classesBasicas.OnTaskCompleted;
import com.br.denis.telas.adapters.CustomAdapterListEvents;
import com.br.denis.telas.adapters.CustomAdapterListNextEvents;
import com.br.denis.webservice.DownloadEventosPendentesAsync;
import com.br.denis.webservice.DownloadNextEventsAsync;
import com.example.exempleswipetab.R;

import java.util.ArrayList;

public class FragmentNextEvents extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fragment_next_events,container, false);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
	}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);

		ListView listView = (ListView) getActivity().findViewById(R.id.myListNextEvents);
		ArrayList<Evento> eventos = getActivity().getIntent().getParcelableArrayListExtra("eventos");

		final CustomAdapterListNextEvents eventosAdapter = new CustomAdapterListNextEvents(getActivity(),R.layout.item_evento, eventos);
		listView.setAdapter(eventosAdapter);

		final SwipeRefreshLayout swipeRefreshNextEvents = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipeRefreshNextEvents);
		swipeRefreshNextEvents.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				new DownloadNextEventsAsync(getActivity(), new OnTaskCompleted() {
					@Override
					public void onTaskCompleted(ArrayList<Evento> eventoArrayList) {
						eventosAdapter.clear();
						eventosAdapter.addAll(eventoArrayList);
						eventosAdapter.notifyDataSetChanged();
						Toast.makeText(getActivity(), "Eventos atualizados!", Toast.LENGTH_SHORT).show();
						swipeRefreshNextEvents.setRefreshing(false);
					}
				}).execute();
			}

		});

		TextView emptyView = (TextView) getActivity().findViewById(android.R.id.empty);
		listView.setEmptyView(emptyView);
	}

}
