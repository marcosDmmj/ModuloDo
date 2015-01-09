package com.br.ksg.webService;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.br.ksg.classesDAO.IngredienteDAO;
import com.br.ksg.classesDAO.UsuarioDAO;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcos Denis on 08/01/2015.
 */
public class DownloadPontuaTudo extends AsyncTask<String, Void, Void> {
    ProgressDialog dialog;
    Context c;

    public DownloadPontuaTudo(Context c) {
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(c, "Aguarde", "Baixando dados... Por favor aguarde!");
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
            Log.i("KSG", "Erro no DownPontuaTudo" + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        try {
            dialog.dismiss();
        } catch (Exception e) {
            Log.i("KSG","Preocupante! DPT"+e.getMessage());
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
            JSONArray RecL = new JSONArray(json);
            JSONObject Rec;
            for (int i = 0; i < RecL.length(); i++) {
                Rec = new JSONObject(RecL.getString(i));

                List<String> id_ings = new ArrayList<String>();
                // JSONArray Ling = new JSONArray(Rec.getJSONArray("ingredientes"));
                // JSONObject ing;

                // for (int j = 0; j < Ling.length(); j++) {
                    // TODO: Aqui Man >.<
                    // ing = new JSONObject(Ling.getString("id_ing"+(i+1)));
                // }

                // UsuarioDAO usuarioDAO = new UsuarioDAO(c);
                // usuarioDAO.addReceita(Rec.getInt("id_rec"),);
            }
        } catch (Exception e) {
            Log.i("KSG","erro get "+e.getMessage());
        }
    }
}