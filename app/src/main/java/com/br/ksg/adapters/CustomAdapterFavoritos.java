package com.br.ksg.adapters;
import java.util.List;

import com.br.ksg.classesBasicas.ReceitaBasica;
import com.example.exempleswipetab.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapterFavoritos extends ArrayAdapter<ReceitaBasica> {
	/*
     * Used to instantiate layout XML file into its corresponding View objects
     */
    private final LayoutInflater inflater;
 
    /*
     * each list item layout ID
     */
    private final int resourceId;
 
    public CustomAdapterFavoritos(Context context, int resource, List<ReceitaBasica> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        //get the person from position
        ReceitaBasica item = getItem(position);
 
        // get a new View no matter recycling or ViewHolder FIXME
        convertView = inflater.inflate(resourceId, parent, false);
 
        //get all object from view
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView titulo = (TextView) convertView.findViewById(R.id.tv1);
        TextView tempo = (TextView) convertView.findViewById(R.id.tv2);
        TextView porcoes = (TextView) convertView.findViewById(R.id.tv3);
        
        //fill the view objects according values from person object
        titulo.setText(item.getNome());
        tempo.setText(item.getTempo());
        porcoes.setText(item.getPorcoes());


        //Aquiiiiiii
        //Carrega a imagem do SDcard
        try
        {
            String myPathInSd = "/sdcard/KSG/image_"+item.getNome()+".jpg"; //UPDATE WITH YOUR OWN JPG FILE
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeFile(myPathInSd, options);
            image.setImageBitmap(bitmap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //Toast.makeText( t, "Image could not be saved : Please ensure you have SD card installed " +
            //                                                                      "properly", Toast.LENGTH_LONG).show();
        }

        return convertView;
    }
}
