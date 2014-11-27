package com.br.ksg.webService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.br.ksg.telas.listas.ListCategoria;
import com.example.exempleswipetab.R;

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
import java.net.URL;

/**
 * Created by Marcos on 24/11/2014.
 */
public class DownloadImagemReceita extends AsyncTask<String, Void, Drawable> {
    ProgressDialog dialog;
    Context c;
    Activity activity;

    public DownloadImagemReceita(Context c,Activity activity) {
        this.c = c;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // dialog = ProgressDialog.show(c, "Aguarde", "Baixando dados, Por favor aguarde!");
    }


    @Override
    protected Drawable doInBackground(String... params) {
        String urlString = params[0];
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(urlString);

        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            if(entity != null) {
                InputStream instream = entity.getContent();
                Drawable d = Drawable.createFromStream(instream, "src");
                return d;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Drawable result) {
        super.onPostExecute(result);

        ImageView imageView = (ImageView) activity.findViewById(R.id.img_receita);
        if (result != null)
            imageView.setImageDrawable(result);

        // dialog.dismiss();
    }
}
