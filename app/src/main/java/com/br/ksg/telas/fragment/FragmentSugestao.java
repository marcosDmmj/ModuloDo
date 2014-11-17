package com.br.ksg.telas.fragment;

import java.util.Calendar;

import com.example.exempleswipetab.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentSugestao extends Fragment {
	private int mHour;
	private TextView txtSaudacao, txtDica;
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_fragment_sugestao,container, false);
		
	}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);
		
		txtSaudacao = (TextView) getActivity().findViewById(R.id.txt_parte_do_dia);
		txtDica = (TextView) getActivity().findViewById(R.id.txt_comentario);
		
		
		final Calendar c = Calendar.getInstance();
		mHour = c.get(Calendar.HOUR_OF_DAY);
		
		if( (mHour >= 3) && (mHour < 12) )
			txtSaudacao.setText("Bom Dia!");
		else if( (mHour >= 12) && (mHour < 18) )
			txtSaudacao.setText("Boa Tarde!");
		else
			txtSaudacao.setText("Boa Noite!");
		
		txtDica.setText("Que tal experimentar a seguinte receita...");
	}	
}
