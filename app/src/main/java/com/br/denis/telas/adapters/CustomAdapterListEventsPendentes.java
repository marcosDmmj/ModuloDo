package com.br.denis.telas.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.classesBasicas.Util;
import com.denis.mdulodoprofessor.R;

import java.util.List;

public class CustomAdapterListEventsPendentes extends ArrayAdapter<Evento> {
	/*
     * Used to instantiate layout XML file into its corresponding View objects
     */
    private final LayoutInflater inflater;

    /*
     * each list item_evento layout ID
     */
    private final int resourceId;

    public CustomAdapterListEventsPendentes(Context context, int resource, List<Evento> objects) {
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
        TextView tvNome = (TextView) convertView.findViewById(R.id.tvNome);
        TextView tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
        TextView tvData = (TextView) convertView.findViewById(R.id.tvData);
        tvData.setText("Dia: "+ Util.dateToStringBR(Util.stringToDateComplete(item.getdata_inicio())));
        TextView tvDataHora = (TextView) convertView.findViewById(R.id.tvDataHora);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);

        //fill the view objects according values from person object
        tvNome.setText("Nome: "+item.getNome());
        tvTitulo.setText("Titulo: "+item.getTitulo());
        tvEmail.setText("Email: "+item.getEmail());
        tvDataHora.setText("Hora: "+Util.stringToDiffDate(item.getdata_inicio(),item.getdata_fim()));
        Log.d("CustomAdapterListEvents",item.toString());

        return convertView;
    }
}
