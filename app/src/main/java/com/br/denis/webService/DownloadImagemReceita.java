package com.br.denis.webService;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.exempleswipetab.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Created by Marcos on 24/11/2014.
 */
public class DownloadImagemReceita extends AsyncTask<String, Void, Drawable> {
    Context c;
    Activity activity;

    public DownloadImagemReceita(Context c,Activity activity) {
        this.c = c;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Drawable doInBackground(String... params) {
        try {
            URL url;
            String date = params[0];
            HttpURLConnection urlConnection;
            url = new URL("http://ufam-automation.net/marcosmoura/selectbyDate.php?Data=" + date);
            Log.e("Date ", date);
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream instream = urlConnection.getInputStream();
                return Drawable.createFromStream(instream, "src");

            }
        }catch (Exception e) {
                return null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Drawable result) {
        super.onPostExecute(result);
    }
}
