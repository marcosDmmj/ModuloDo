package com.br.denis.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.classesBasicas.OnTaskCompleted;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by denis on 07/07/17.
 */

public class DownloadEventosPendentesAsync extends AsyncTask<String, Void, ArrayList<Evento>> {
    Context c;
    private ProgressDialog dialog;
    public OnTaskCompleted taskCompleted = null;

    public DownloadEventosPendentesAsync(Context context, OnTaskCompleted taskCompleted) {
        this.c = context;
        this.taskCompleted = taskCompleted;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Evento> doInBackground(String... params) {
        ArrayList<Evento> resultEvents = new ArrayList<>();
        // Baixando Eventos pendentes!
        Log.d("DownloadEventosPendents","Baixando eventos");
        try {
            URL url;
            HttpURLConnection urlConnection;
            url = new URL("http://ufam-automation.net/marcosmoura/getEventsTemp.php");

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
                    resultEvents.add(new Evento(evento.getString("Evento_id"), evento.getString("Titulo"), evento.getString("Email"), evento.getString("Nome"), evento.getString("Data_inicio"), evento.getString("Data_fim")));
                    Log.d("DownloadDiaAsync","Evento = "+evento.toString());
                }
            }
        }catch (Exception e) {
            Log.e("DownloadDiaAsync","Erro "+e.getMessage());
        }

        return resultEvents;

    }

    @Override
    protected void onPostExecute(ArrayList<Evento> eventoArrayList) {
        super.onPostExecute(eventoArrayList);
        taskCompleted.onTaskCompleted(eventoArrayList);
    }
}
