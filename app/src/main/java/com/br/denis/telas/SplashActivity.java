package com.br.denis.telas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.br.denis.classesBasicas.Evento;
import com.example.exempleswipetab.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SplashActivity extends FragmentActivity {

    private ArrayList<Evento> eventos,eventosTemp;
    private Integer status;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CarregaDadosAsync(this).execute();
    }

    private class CarregaDadosAsync extends AsyncTask<Void, Void, Void> {
        Context c;

        public CarregaDadosAsync(Context c) {
            this.c = c;
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
                    }
                }
            }catch (Exception e) {
                Log.e("SplashActivity","Erro getEventsTemp= "+e.getMessage());
            }

            // Baixando próximos eventos!
            try {
                URL url;
                HttpURLConnection urlConnection;
                url = new URL("http://ufam-automation.net/marcosmoura/getEvents.php");

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
                    }
                }
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


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            dialog.dismiss();
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            i.putExtra("eventos", eventos);
            i.putExtra("eventosTemp", eventosTemp);
            i.putExtra("status",status);
            startActivity(i);
            finish();
        }
    }
}
