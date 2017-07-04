package com.br.denis.telas.listas;

import java.util.ArrayList;
import java.util.List;

import com.br.denis.adapters.CustomAdapterCategoria;
import com.br.denis.classesBasicas.ReceitaBasica;
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
	private List<ReceitaBasica> itemList;
    public static boolean verificaStatus;
    public static ArrayAdapter<ReceitaBasica> ad;
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
                    acessa_a_receita(itemList.get(position).getId_receita());
                }
            });

            create = true;
        }
	}

    @Override
    protected void onResume() {
        int j;
        super.onResume();
        if (itemList != null)
        for (j = 0; j < itemList.size(); j++) {
            try {
                if (create) {
                    //imagens.add(j, new DownloadImagemListaReceita(getApplication(), this, j));
                    //imagens.get(j).execute("http://ksmapi.besaba.com/imagens/" + itemList.get(j).getId_receita() + ".jpg");
                }
                else {
                    if (itemList.get(j).getImg() == null) {
                        //imagens.remove(j);
                        //imagens.add(j, new DownloadImagemListaReceita(getApplication(), this, j));
                        //imagens.get(j).execute("http://ksmapi.besaba.com/imagens/" + itemList.get(j).getId_receita() + ".jpg");
                    }
                }
            } catch (Exception e){
                usarToast("Deu erro! "+j+" OnResume: "+e.getMessage());
            }
        }
        create = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (itemList != null)
        for (int j = 0; j < itemList.size(); j++) {
            try {
                //imagens.get(j).cancel(true);
            } catch (Exception e){
                usarToast("Deu erro! "+e.getMessage());
            }
        }
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

			return (netInfo != null) && (netInfo.isConnected());
		} else {
			return false;
		}
	}
	
	public void usarToast(String texto) {
		Toast.makeText(getBaseContext(), texto, Toast.LENGTH_LONG).show();
	}

}
