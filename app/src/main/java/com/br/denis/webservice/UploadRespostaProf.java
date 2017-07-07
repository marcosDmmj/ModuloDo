package com.br.denis.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.classesBasicas.OnTaskCompletedUpload;

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

public class UploadRespostaProf extends AsyncTask<String, Void, Integer> {
    Context c;
    private ProgressDialog dialog;
    public OnTaskCompletedUpload onTaskCompleted = null;

    public UploadRespostaProf(Context c, OnTaskCompletedUpload onTaskCompleted) {
        this.c = c;
        this.onTaskCompleted = onTaskCompleted;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(c, "Aguarde", "Enviando dados, Por favor aguarde!");
    }

    @Override
    protected Integer doInBackground(String... params) {
        Integer result = -1;
        // Fazendo o upload da resposta do professor!
        try {
            URL url;
            HttpURLConnection urlConnection;
            url = new URL(params[0]);

            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder resultStringBuilder = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    resultStringBuilder.append(line);
                }
                Log.d("UploadRespostaProf","resultStringBuilder = "+resultStringBuilder.toString());
                result = Integer.parseInt(resultStringBuilder.toString());
                Log.d("UploadRespostaProf","result = "+result);
            }
        }catch (Exception e) {
            Log.e("UploadRespostaProf","Erro "+e.getMessage());
        }

        Log.d("UploadRespostaProf","Dismiss here!");
        dialog.dismiss();
        return result;
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        onTaskCompleted.onTaskCompleted(integer);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        dialog.dismiss();
    }

}
