package com.br.denis.telas.listas;

import java.util.ArrayList;
import java.util.List;

import com.br.denis.telas.adapters.CustomAdapterCategoria;
import com.br.denis.classesBasicas.Evento;
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
import android.view.View;

public class ListCategoria extends Activity {
	private List<Evento> itemList;
    public static boolean verificaStatus;
    public static ArrayAdapter<Evento> ad;
    //private List<DownloadImagemListaReceita> imagens = new ArrayList<DownloadImagemListaReceita> ();
    boolean create;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_t);		
		
		Intent i = getIntent();

        verificaStatus = true;
		String que_categoria = i.getStringExtra("categoria");
		Bundle list = i.getBundleExtra("lista");
        if (list.getInt("tamanho") == 0){
            usarToast(getString(R.string.verifica_conexao));
            finish();
        }
        else {
            itemList = BundleToList(list);

            setTitle(que_categoria.substring(0,1).toUpperCase() + que_categoria.substring(1).toLowerCase());

            ad = new CustomAdapterCategoria(this, R.layout.item, itemList);
            ListView lv = (ListView) findViewById(R.id.listdel);
            lv.setAdapter(ad);

            lv.setOnItemClickListener(new OnItemClickListener() {
                @SuppressWarnings({"rawtypes"})
                public void onItemClick(AdapterView parent, View view,
                                        int position, long id) {
                    //acessa_a_receita(itemList.get(position).getId_receita());
                }
            });

            create = true;
        }
	}

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
     }

    public void acessa_a_receita(String id){
		if (verificaConexao()) {					
			try {
                for (int j = 0; j < itemList.size(); j++) {
                    try {
                        //imagens.get(j).cancel(true);
                    } catch (Exception e){
                        usarToast("Deu erro! "+e.getMessage());
                    }
                }
                verificaStatus = false;
                //new DownloadReceitaPorId(this).execute("http://ksmapi.besaba.com/sql/selectRec.php?id="+id);
			} catch (Exception e) {
				usarToast(getString(R.string.nao)+" funfou :'("+e.getMessage());
			}
		} else {
			usarToast(getString(R.string.verifica_conexao));
		}
	}	
	
	private List<Evento> BundleToList(Bundle l){
		List<Evento> lista = new ArrayList<Evento>();
		
		for (int i = 0; i < l.getInt("tamanho"); i++) {
			lista.add(new Evento(l.getString("id"+i), l.getString("nome"+i), l.getString("tempo"+i)+" min", l.getString("porcoes"+i)+" "+getString(R.string.porcoes), ""));
		}	
		
		return lista;
	}

	public boolean verificaConexao() {
		ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

			return (netInfo != null) && (netInfo.isConnected());
		} else {
			return false;
		}
	}
	
	public void usarToast(String texto) {
		Toast.makeText(getBaseContext(), texto, Toast.LENGTH_LONG).show();
	}

}
