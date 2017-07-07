package com.br.denis.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.br.denis.classesBasicas.Evento;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by denis on 06/07/17.
 */

public class DownloadDiaAsync extends AsyncTask<String, Void, ArrayList<Evento>> {
    Context c;
    private ProgressDialog dialog;

    public DownloadDiaAsync(Context c) {
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //dialog = ProgressDialog.show(c, "Aguarde", "Baixando dados, Por favor aguarde!");
    }
    @Override
    protected ArrayList<Evento> doInBackground(String... params) {
        ArrayList<Evento> resultEvents = new ArrayList<>();
        // Baixando Eventos de um dia específico!
        try {
            URL url;
            HttpURLConnection urlConnection;
            url = new URL("http://ufam-automation.net/marcosmoura/selectbyDate.php?Data="+params[0]);

            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    result.append(line);
                }
                JSONArray eventosL = new JSONArray(result.toString());
                JSONObject evento;

                for (int i = 0; i < eventosL.length(); i++) {
                    evento = eventosL.getJSONObject(i);
                    resultEvents.add(new Evento(evento.getString("Titulo"), evento.getString("Email"), evento.getString("Nome"), evento.getString("Data_inicio"), evento.getString("Data_fim")));
                    Log.d("SplashActivity","Evento = "+evento.toString());
                }
            }
        }catch (Exception e) {
            Log.e("DownloadDiaAsync","Erro "+e.getMessage());
        }

        //dialog.dismiss();
        return resultEvents;
    }
}
