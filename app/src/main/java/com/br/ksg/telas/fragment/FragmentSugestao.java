package com.br.ksg.telas.fragment;

import java.util.Calendar;

import com.br.ksg.webService.DownloadImagemReceita;
import com.example.exempleswipetab.R;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentSugestao extends Fragment {
	int mHour;
	TextView txtSaudacao, txtDica;
    ImageView imageView;
    Button btnVerReceita;

    @Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fragment_sugestao,container, false);
	}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);
		
		txtSaudacao = (TextView) getActivity().findViewById(R.id.txt_parte_do_dia);
		txtDica = (TextView) getActivity().findViewById(R.id.txt_comentario);
        imageView = (ImageView) getActivity().findViewById(R.id.img_opcao);
        btnVerReceita = (Button) getActivity().findViewById(R.id.btn_ver_receita);
		
		final Calendar c = Calendar.getInstance();
		mHour = c.get(Calendar.HOUR_OF_DAY);
		
		if( (mHour >= 3) && (mHour < 12) )
			txtSaudacao.setText("Bom Dia!");
		else if( (mHour >= 12) && (mHour < 18) )
			txtSaudacao.setText("Boa Tarde!");
		else
			txtSaudacao.setText("Boa Noite!");
		
		txtDica.setText("Que tal experimentar a seguinte receita...");

        btnVerReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usarToast("Ainda "+getString(R.string.nao)+" implementado! :'(");
                try {
                    //DownloadImagemReceita b = (DownloadImagemReceita) new DownloadImagemReceita(getActivity()).execute("http://ksmapi.besaba.com/imagens/130.jpg");
                    //Drawable result = b.get();
                    //if (result != null)
                      //  imageView.setImageDrawable(result);
                } catch (Exception e){
                    usarToast("Deu um erro considerado! " + e.getMessage());
                }
            }
        });
	}

    public void usarToast(String texto) {
        Toast.makeText(getActivity(), texto, Toast.LENGTH_LONG).show();
    }


}
