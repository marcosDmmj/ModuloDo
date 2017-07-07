package com.br.denis.webservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.br.denis.classesBasicas.Util;
import com.br.denis.classesBasicas.Evento;

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
 * Created by denis on 06/07/17.
 */

public class CarregaDadosAsync extends AsyncTask<Void, Void, Void> {
    Context c;
    Class class_final;
    private ProgressDialog dialog;
    private ArrayList<Evento> eventos,eventosTemp, eventosMonth;
    private Integer status;

    public CarregaDadosAsync(Context c, Class class_final) {
        this.c = c;
        this.class_final = class_final;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(c, "Aguarde", "Baixando dados, Por favor aguarde!");
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        eventos = new ArrayList<>();
        eventosTemp = new ArrayList<>();
        eventosMonth = new ArrayList<>();

        // Baixando Eventos ainda não vistos pelo prof!
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
                    eventosTemp.add(new Evento(evento.getString("Titulo"), evento.getString("Email"), evento.getString("Nome"), evento.getString("Data_inicio"), evento.getString("Data_fim")));
                    Log.d("SplashActivity","Evento = "+evento.toString());
                }
            }
        }catch (Exception e) {
            Log.e("SplashActivity","Erro getEventsTemp= "+e.getMessage());
        }

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
                        eventos.add(new Evento(evento.getString("Titulo"), evento.getString("Email"), evento.getString("Nome"), evento.getString("Data_inicio"), evento.getString("Data_fim")));
                        Log.d("SplashActivity","Evento = "+evento.toString());
                    }
                }
            } while (j == 3);
        }catch (Exception e) {
            Log.e("SplashActivity","Erro getEvents= "+e.getMessage());
        }

        // Baixando o Status do prof!
        try {
            URL url;
            HttpURLConnection urlConnection;
            url = new URL("http://ufam-automation.net/marcosmoura/Status.txt");

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
                status = Integer.parseInt(result.toString());
            }
        }catch (Exception e) {
            Log.e("SplashActivity","Erro Status.txt= "+e.getMessage());
        }

        // Baixando Eventos do mês!
        try {
            URL url;
            HttpURLConnection urlConnection;
            url = new URL("http://ufam-automation.net/marcosmoura/selectbyMonth.php?Data="+Util.dateToMonthyear(new Date()));

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
                    eventosMonth.add(new Evento(evento.getString("Titulo"), evento.getString("Email"), evento.getString("Nome"), evento.getString("Data_inicio"), evento.getString("Data_fim")));
                    Log.d("SplashActivity","Evento month = "+evento.toString());
                }
            }
        }catch (Exception e) {
            Log.e("SplashActivity","Erro getEventsTemp= "+e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        dialog.dismiss();
        Intent i = new Intent(c, class_final);
        i.putExtra("eventos", eventos);
        i.putExtra("eventosTemp", eventosTemp);
        i.putExtra("eventosMonth", eventosMonth);
        i.putExtra("status",status);
        c.startActivity(i);
        ((Activity)c).finish();
    }
}
