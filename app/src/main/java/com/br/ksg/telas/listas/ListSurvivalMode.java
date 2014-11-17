package com.br.ksg.telas.listas;

import java.util.ArrayList;

import com.example.exempleswipetab.R;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class ListSurvivalMode extends Activity {

	private ArrayList<String> listaIngredientes;
	private ArrayAdapter<String> adapter;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abre_do_pesquisa);
		Intent intent = getIntent(); 
		listaIngredientes = intent.getStringArrayListExtra("listaIngredientes");
		
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaIngredientes);
		listView = (ListView) findViewById(R.id.listViewAbreDoPesquisa);
		listView.setAdapter(adapter);
		
	}

	
}
