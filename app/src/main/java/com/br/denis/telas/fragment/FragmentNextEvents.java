package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.br.denis.telas.MainActivity;
import com.br.denis.telas.adapters.CustomAdapterListEvents;
import com.example.exempleswipetab.R;

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
		final CustomAdapterListEvents eventosAdapter = new CustomAdapterListEvents(getActivity(),R.layout.item_evento, MainActivity.eventos);
		listView.setAdapter(eventosAdapter);
		TextView emptyView = (TextView) getActivity().findViewById(android.R.id.empty);
		listView.setEmptyView(emptyView);
	}

}
