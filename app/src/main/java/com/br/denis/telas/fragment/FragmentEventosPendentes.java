package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exempleswipetab.R;


public class FragmentEventosPendentes extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_fragment_eventos_pendentes,container, false);
	}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);

	}
}
