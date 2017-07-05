package com.br.denis.telas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class SplashActivity extends AppCompatActivity {

    private ArrayList<Evento> eventos,eventosTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CarregaDadosAsync().execute();
    }

    private class CarregaDadosAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {super.onPreExecute();}

        @Override
        protected Void doInBackground(Void... arg0) {
            eventos = new ArrayList<>();
            eventosTemp = new ArrayList<>();
            try {
                URL url;
                HttpURLConnection urlConnection;
                url = new URL("http://ufam-automation.net/marcosmoura/getEventsTemp.php");// + date);

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
                Log.e("SplashActivity","Erro = "+e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            i.putExtra("eventos", eventos);
            i.putExtra("eventosTemp", eventosTemp);
            startActivity(i);
            finish();
        }
    }
}
