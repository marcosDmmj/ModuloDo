package com.br.ksg.webService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.br.ksg.telas.listas.ListCategoria;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class DownloadReceitaPorCategoria extends AsyncTask<String, Void, Bundle>{
    ProgressDialog dialog;
    String categoria;
    Context c;

	public DownloadReceitaPorCategoria(Context c,String categoria) {
		this.c = c;
		this.categoria = categoria;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(c, "Aguarde", "Baixando dados, Por favor aguarde!");
	}


	@Override
	protected Bundle doInBackground(String... params) {
		String urlString = params[0];
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(urlString);
		try {
            HttpResponse response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();

            if(entity != null){
                InputStream instream = entity.getContent();

                String json = toString(instream);
                
                instream.close();

                return getReceitasBasicas(json);
            }
		} catch (Exception e) {

        }
        Bundle b = new Bundle();
        b.putInt("tamanho", 0);
        return b;
    }

	@Override
	protected void onPostExecute(Bundle result) {
		super.onPostExecute(result);
		try {
            dialog.dismiss();
            Intent receitas = new Intent(c,ListCategoria.class);

            receitas.putExtra("lista", result);
            receitas.putExtra("categoria", categoria);
            
            c.startActivity(receitas);
        } catch (Exception e) {

        }
		
	}

	private String toString(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
             baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }
	
	private Bundle getReceitasBasicas(String json) {
		Bundle b = new Bundle();
		try {
			JSONArray receitasL = new JSONArray(json);
			JSONObject receita;
			for (int i = 0; i < receitasL.length(); i++) {
				receita = new JSONObject(receitasL.getString(i));
				
				b.putString("id"+i, receita.getString("id_receita"));
				b.putString("nome"+i, receita.getString("nome"));
				b.putString("tempo"+i, receita.getString("tempo_preparo"));
				b.putString("porcoes"+i, receita.getString("porcoes"));

			}
			b.putInt("tamanho", receitasL.length());
			return b;
        } catch (Exception e) {
            b.putInt("tamanho", 0);
            return b;
        }
	}	
}
