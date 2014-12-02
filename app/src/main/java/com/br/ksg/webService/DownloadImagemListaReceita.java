package com.br.ksg.webService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.exempleswipetab.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

/**
 * Created by Marcos on 02/12/2014.
 */
public class DownloadImagemListaReceita extends AsyncTask<String, Void, Drawable> {
    Context c;
    Activity activity;
    int position;

    public DownloadImagemListaReceita(Context c,Activity activity,int position) {
        this.c = c;
        this.activity = activity;
        this.position = position;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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

        ListView lv = (ListView) activity.findViewById(R.id.listdel);
        ImageView imageView = (ImageView) lv.getChildAt(position).findViewById(R.id.image);
        if (result != null)
            imageView.setImageDrawable(result);
    }
}
