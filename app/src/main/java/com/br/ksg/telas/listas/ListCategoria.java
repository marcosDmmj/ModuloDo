package com.br.ksg.telas.listas;

import java.util.ArrayList;
import java.util.List;

import com.br.ksg.adapters.CustomAdapterCategoria;
import com.br.ksg.classesBasicas.ReceitaBasica;
import com.br.ksg.webService.DownloadReceitaPorId;
import com.example.exempleswipetab.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;;

public class ListCategoria extends Activity {
	List<ReceitaBasica> itemList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_t);		
		
		Intent i = getIntent();
		
		String que_categoria = i.getStringExtra("categoria");
		Bundle list = i.getBundleExtra("lista");  
		itemList = BundleToList(list);
		
		setTitle("Resultados para: "+que_categoria);
				
        ArrayAdapter<ReceitaBasica> ad = new CustomAdapterCategoria(this, R.layout.item, itemList);
        ListView lv = (ListView) findViewById(R.id.listdel);
        lv.setAdapter(ad);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
        	@SuppressWarnings({ "rawtypes" })
            public void onItemClick(AdapterView parent, View view,
                    int position, long id) {
            	acessa_a_receita(itemList.get(position).getId_receita());
            }
        });                
	}
	
	public void acessa_a_receita(String id){
		if (verificaConexao()) {					
			try {
				new DownloadReceitaPorId(this).execute("http://ksmapi.besaba.com/sql/selectRec.php?id="+id);
			} catch (Exception e) {
				usarToast(getString(R.string.nao)+" funfou :'("+e.getMessage());
			}
		} else {
			usarToast(getString(R.string.verifica_conexao));
		}

		// Intent i = new Intent(this,ReceitaActivity.class);
		// i.putExtra("titulo", rct);
		// startActivity(i);
	}	
	
	private List<ReceitaBasica> BundleToList(Bundle l){
		List<ReceitaBasica> lista = new ArrayList<ReceitaBasica>();				
		
		for (int i = 0; i < l.getInt("tamanho"); i++) {
			lista.add(new ReceitaBasica(l.getString("id"+i), l.getString("nome"+i), l.getString("tempo"+i)+" min", l.getString("porcoes"+i)+" "+getString(R.string.porcoes)));
		}	
		
		return lista;
	}

	public boolean verificaConexao() {
		ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

			if (netInfo == null) {
				return false;
			}

			int netType = netInfo.getType();

			if (netType == ConnectivityManager.TYPE_WIFI
					|| netType == ConnectivityManager.TYPE_MOBILE) {
				return netInfo.isConnected();

			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void usarToast(String texto) {
		Toast.makeText(getBaseContext(), texto, Toast.LENGTH_LONG).show();
	}

}
