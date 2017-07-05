package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.exempleswipetab.R;

import java.util.ArrayList;

public class FragmentNextEvents extends Fragment {

	ArrayList<String> listaAutoComplete ;
	ArrayList<String> listaElementos ;
	ArrayAdapter<String> adapter,adapterListView;

	
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
		listaAutoComplete = new ArrayList<>();
		listaElementos = new ArrayList<>();

		
		adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,listaAutoComplete);
	}
}
