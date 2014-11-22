package com.br.ksg.telas;

import com.example.exempleswipetab.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceitaActivity extends Activity {
	
	private TextView txt_titulo;
	private TextView txt_modo_preparo;
	private TextView txt_tempo;
	private TextView txt_porcoes;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final);
		
		Intent i = getIntent();
		Bundle receita = i.getBundleExtra("receita");
		
		setTitle("Receita: "+receita.getString("nome"));
		
		txt_titulo = (TextView) findViewById(R.id.txt_nome_receita);
		txt_titulo.setText(receita.getString("nome"));
		
		txt_modo_preparo = (TextView) findViewById(R.id.txt_modo_de_preparo_receita);
		txt_modo_preparo.setText(receita.getString("modo_preparo"));
		
		txt_tempo = (TextView) findViewById(R.id.txt_tempo_de_preparo);
		txt_tempo.setText("Tempo de preparo: "+receita.getString("tempo")+" min");
		
		txt_porcoes = (TextView) findViewById(R.id.txt_qtd_de_porcoes);
		txt_porcoes.setText(getString(R.string.porcoes)+": "+receita.getString("porcoes"));
	}
}
