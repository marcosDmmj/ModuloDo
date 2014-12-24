package com.br.ksg.classesDAO;

import java.util.ArrayList;

import com.br.ksg.bancoDeDados.BancoDeDados;
import com.br.ksg.classesBasicas.Ingrediente;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*
 * Created by Marcos and Josias on 21/11/2014.
 */

public class IngredienteDAO {

    static SQLiteDatabase bancoDeDados;

    public IngredienteDAO(Context context){
        if(bancoDeDados==null){
            bancoDeDados = (new BancoDeDados(context)).getWritableDatabase();
        }
    }


    public boolean addIngredientes(int id , String ingrediente){
        try{
            String comando = "INSERT INTO Ingredientes VALUES ("+id+",'"+ingrediente+"'"+");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public ArrayList<String> getListaIngredientes(){
        ArrayList<String> ingredientes = new ArrayList<String>() ;
        try{
            String cmd = "SELECT INGREDIENTE FROM Ingredientes";
            Cursor cursor = bancoDeDados.rawQuery(cmd,null);
            while(cursor.moveToNext()){
                ingredientes.add(cursor.getString(0));
            }
            cursor.close();
            return ingredientes ;
        }
        catch(SQLException e){
            Log.i("APPBD", e.getMessage());
            return null;
        }

    }

    public Ingrediente getIngrediente(String nome){
        Ingrediente ingrediente = new Ingrediente();
        try{
            String cmd = "SELECT * FROM Ingredientes WHERE INGREDIENTE ='"+nome+"'";
            Cursor cursor = bancoDeDados.rawQuery(cmd,null);
            if (cursor.moveToNext()){
                ingrediente.setId_ingrediente(cursor.getInt(0));
                ingrediente.setNome(cursor.getString(1));
            }
            cursor.close();
            return ingrediente;
        }
        catch(SQLException e){
            Log.i("APPBD", e.getMessage());
            return null;
        }

    }

    public static int sizeBD(){
        try{
            String cmd = "SELECT INGREDIENTE FROM Ingredientes";
            Cursor cursor = bancoDeDados.rawQuery(cmd,null);
            int result;
            if (cursor != null)
                 result = cursor.getCount();
            else
                return 0;
            cursor.close();
            return result;
        }
        catch(SQLException e){
            Log.i("APPBD bla bla bla", e.getMessage());
            return -1;
        }
    }
}