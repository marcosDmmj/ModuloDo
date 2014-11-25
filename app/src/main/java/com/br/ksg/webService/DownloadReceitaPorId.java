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

import com.br.ksg.telas.ReceitaActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class DownloadReceitaPorId extends AsyncTask<String, Void, Bundle> {
    ProgressDialog dialog;
    Context c;
	

	public DownloadReceitaPorId(Context c) {
		this.c = c;
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

                return getReceita(json);
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
		
		dialog.dismiss();
		Intent i = new Intent(c, ReceitaActivity.class);
		i.putExtra("receita", result);
		c.startActivity(i);
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
	
	private Bundle getReceita(String json) {
		Bundle b = new Bundle();
		try {
			JSONArray receitasL = new JSONArray(json);
			JSONObject receita;
			for (int i = 0; i < receitasL.length(); i++) {
				receita = new JSONObject(receitasL.getString(i));
				
				b.putString("id", receita.getString("id_receita"));
				b.putString("nome", receita.getString("nome"));
				b.putString("modo_preparo", receita.getString("modo_preparo"));
				b.putString("porcoes", receita.getString("porcoes"));
				b.putString("categoria", receita.getString("categoria"));
				b.putString("tempo", receita.getString("tempo_preparo"));
				b.putString("dificuldade", receita.getString("dificuldade"));
				b.putString("rating", receita.getString("rating"));
				b.putString("tamanho", receita.getString("quant"));
				
				for (int j = 0; j < receita.getInt("quant"); j++) {
					b.putString("ingrediente"+i, receita.getString("ingrediente"+(i+1)));
				}

			}
            b.putInt("tamanho", 1);
			return b;
		} catch (Exception e) {
            b.putInt("tamanho", 0);
            return b;
		}
	}	
}
