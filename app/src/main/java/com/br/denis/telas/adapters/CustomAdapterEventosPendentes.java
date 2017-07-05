package com.br.denis.telas.adapters;
import java.util.List;

import com.br.denis.classesBasicas.Evento;
import com.example.exempleswipetab.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterEventosPendentes extends ArrayAdapter<Evento> {
	/*
     * Used to instantiate layout XML file into its corresponding View objects
     */
    private final LayoutInflater inflater;
 
    /*
     * each list item_evento layout ID
     */
    private final int resourceId;
 
    public CustomAdapterEventosPendentes(Context context, int resource, List<Evento> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        //get the person from position
        Evento item = getItem(position);
 
        // get a new View no matter recycling or ViewHolder FIXME
        convertView = inflater.inflate(resourceId, parent, false);
 
        //get all object from view
        TextView titulo = (TextView) convertView.findViewById(R.id.tv1);
        TextView tempo = (TextView) convertView.findViewById(R.id.tv2);
        TextView porcoes = (TextView) convertView.findViewById(R.id.tv3);
        
        //fill the view objects according values from person object
        titulo.setText(item.getNome());
        tempo.setText(item.getTitulo());
        porcoes.setText(item.getEmail());

        return convertView;
    }
}
