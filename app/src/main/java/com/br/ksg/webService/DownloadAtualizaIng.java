package com.br.ksg.webService;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.br.ksg.classesDAO.IngredienteDAO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * Created by Marcos on 19/12/2014.
 */
public class DownloadAtualizaIng extends AsyncTask<String, Void, Void> {
    ProgressDialog dialog;
    Context c;

    public DownloadAtualizaIng(Context c) {
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(c, "Aguarde", "Baixando dados, Por favor aguarde!");
    }


    @Override
    protected Void doInBackground(String... params) {
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

                getReceitasBasicas(json);
            }
        } catch (Exception e) {
            Log.i("KSG","erro doIn"+e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        try {
            dialog.dismiss();
        } catch (Exception e) {
            Log.i("KSG","Preocupante! "+e.getMessage());
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

    private void getReceitasBasicas(String json) {
        try {
            JSONArray IngL = new JSONArray(json);
            JSONObject ing;
            for (int i = 0; i < IngL.length(); i++) {
                ing = new JSONObject(IngL.getString(i));

                IngredienteDAO ingredienteDAO = new IngredienteDAO(c);
                ingredienteDAO.addIngredientes(ing.getInt("id"),ing.getString("nome"));

            }
        } catch (Exception e) {
            Log.i("KSG","erro get "+e.getMessage());
        }
    }
}