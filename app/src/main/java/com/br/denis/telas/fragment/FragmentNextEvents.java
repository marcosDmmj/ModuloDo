package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.telas.MainActivity;
import com.br.denis.telas.adapters.CustomAdapterNextEvent;
import com.example.exempleswipetab.R;

import java.util.ArrayList;
import java.util.List;

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
		final CustomAdapterNextEvent eventosAdapter = new CustomAdapterNextEvent(getActivity(),R.layout.item_evento, MainActivity.eventos);
		listView.setAdapter(eventosAdapter);
		TextView emptyView = (TextView) getActivity().findViewById(android.R.id.empty);
		listView.setEmptyView(emptyView);
	}

}
