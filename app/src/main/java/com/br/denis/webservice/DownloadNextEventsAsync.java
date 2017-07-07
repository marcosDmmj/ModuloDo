package com.br.denis.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.classesBasicas.OnTaskCompleted;
import com.br.denis.classesBasicas.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by denis on 07/07/17.
 */

public class DownloadNextEventsAsync extends AsyncTask<String, Void, ArrayList<Evento>> {
    Context c;
    private ProgressDialog dialog;
    public OnTaskCompleted taskCompleted = null;

    public DownloadNextEventsAsync(Context context, OnTaskCompleted taskCompleted) {
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

        // Baixando próximos eventos!
        try {
            URL url;
            HttpURLConnection urlConnection;
            Date proximosEventos = new Date();
            int j = 0;
            do {
                Calendar cal = Calendar.getInstance();
                cal.setTime(proximosEventos);
                cal.add(Calendar.DAY_OF_MONTH, j);
                j++;
                proximosEventos = cal.getTime();
                url = new URL("http://ufam-automation.net/marcosmoura/selectbyDate.php?Data="+ Util.dateToString(proximosEventos));

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
                        resultEvents.add(new Evento(evento.getString("Evento_id"),evento.getString("Titulo"), evento.getString("Email"), evento.getString("Nome"), evento.getString("Data_inicio"), evento.getString("Data_fim")));
                        Log.d("SplashActivity","Evento = "+evento.toString());
                    }
                }
            } while (j == 3);
        }catch (Exception e) {
            Log.e("SplashActivity","Erro getEvents= "+e.getMessage());
        }

        return resultEvents;

    }

    @Override
    protected void onPostExecute(ArrayList<Evento> eventoArrayList) {
        super.onPostExecute(eventoArrayList);
        taskCompleted.onTaskCompleted(eventoArrayList);
    }
}
