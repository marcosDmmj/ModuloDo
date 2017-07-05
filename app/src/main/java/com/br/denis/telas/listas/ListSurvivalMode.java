package com.br.denis.telas.listas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.br.denis.telas.adapters.CustomAdapterCategoria;
import com.br.denis.classesBasicas.Evento;
//import com.br.denis.webService.DownloadReceitaPorId;
import com.example.exempleswipetab.R;

import java.util.ArrayList;
import java.util.List;

public class ListSurvivalMode extends Activity {
    private List<Evento> itemList;
    public static ArrayAdapter<Evento> ad;
  //  private List<DownloadImagemListaReceita2> imagens = new ArrayList<DownloadImagemListaReceita2> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_t);

        Intent i = getIntent();

        Bundle list = i.getBundleExtra("lista");
        if (list.getInt("tamanho") == 0){
            usarToast(getString(R.string.verifica_conexao));
            finish();
        }
        else {
            itemList = BundleToList(list);

            setTitle("Resultados");

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
        }
    }

    @Override
    protected void onResume() {
        int j;
        super.onResume();
        for (j = 0; j < itemList.size(); j++) {
            try {
                //imagens.add(j, new DownloadImagemListaReceita2(getApplication(), this, j));
                //imagens.get(j).execute("http://ksmapi.besaba.com/imagens/" + itemList.get(j).getId_receita() + ".jpg");
            } catch (Exception e){
                usarToast("Deu erro! "+j+" OnResume: "+e.getMessage());
            }
        }
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