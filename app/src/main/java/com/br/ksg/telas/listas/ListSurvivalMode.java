package com.br.ksg.telas.listas;

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

import com.br.ksg.adapters.CustomAdapterCategoria;
import com.br.ksg.classesBasicas.DoisInt;
import com.br.ksg.classesBasicas.ReceitaBasica;
import com.br.ksg.classesDAO.ReceitasDAO;
import com.br.ksg.webService.DownloadImagemListaReceita2;
import com.br.ksg.webService.DownloadReceitaPorId;
import com.example.exempleswipetab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSurvivalMode extends Activity {
    private List<ReceitaBasica> itemList;
    public static boolean verificaStatus;
    public static ArrayAdapter<ReceitaBasica> ad;
    private List<DownloadImagemListaReceita2> imagens = new ArrayList<DownloadImagemListaReceita2> ();
    boolean create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_t);

        Intent i = getIntent();

        verificaStatus = true;
        Bundle list = i.getBundleExtra("lista");
        if (list.getInt("tamanho") == 0){
            usarToast(getString(R.string.verifica_conexao));
            finish();
        }
        else {
            itemList = BundleToList(list);
            itemList = filtros(itemList);

            setTitle("Resultados");

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
        for (j = 0; j < itemList.size(); j++) {
            try {
                if (create) {
                    imagens.add(j, new DownloadImagemListaReceita2(getApplication(), this, j));
                    imagens.get(j).execute("http://ksmapi.besaba.com/imagens/" + itemList.get(j).getId_receita() + ".jpg");
                }
                else {
                    if (itemList.get(j).getImg() == null) {
                        imagens.remove(j);
                        imagens.add(j, new DownloadImagemListaReceita2(getApplication(), this, j));
                        imagens.get(j).execute("http://ksmapi.besaba.com/imagens/" + itemList.get(j).getId_receita() + ".jpg");
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
                imagens.get(j).cancel(true);
            } catch (Exception e){
                usarToast("Deu erro! "+e.getMessage());
            }
        }
    }

    public List<ReceitaBasica> filtros(List<ReceitaBasica> list){
        ReceitasDAO receitasDAO = new ReceitasDAO(this);

        List<DoisInt> minhaLista = new ArrayList<DoisInt>();

        for(int i = 0;i < list.size(); i++){
            minhaLista.add(new DoisInt(i,receitasDAO.contagem_pontos_Ing(Integer.parseInt(list.get(i).getId_receita()))));
        }

        Collections.sort(minhaLista);

        List<ReceitaBasica> result = new ArrayList<ReceitaBasica>();

        int tamanho = 30;
        if (list.size() < 30)
            tamanho = list.size();
        for(int i = 0;i < tamanho; i++){
            boolean ta = false;
            for (int j = 0; j < result.size(); j++) {
                if (list.get(minhaLista.get(i).getI()).getId_receita().equals(result.get(j).getId_receita())) {
                    ta = true;
                    break;
                }
            }
            if(!ta)
                result.add(list.get(minhaLista.get(i).getI()));
        }

        return result;
    }

    public void acessa_a_receita(String id){
        if (verificaConexao()) {
            try {
                for (int j = 0; j < itemList.size(); j++) {
                    try {
                        imagens.get(j).cancel(true);
                    } catch (Exception e){
                        usarToast("Deu erro! "+e.getMessage());
                    }
                }
                verificaStatus = false;
                new DownloadReceitaPorId(this).execute("http://ksmapi.besaba.com/sql/selectRec.php?id="+id);
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