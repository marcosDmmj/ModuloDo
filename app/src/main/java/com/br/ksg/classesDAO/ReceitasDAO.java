package com.br.ksg.classesDAO;

        import java.util.ArrayList;
        import java.util.List;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.util.Log;

        import com.br.ksg.bancoDeDados.BancoDeDados;
        import com.br.ksg.classesBasicas.Ingrediente;
        import com.br.ksg.classesBasicas.Receita;
        import com.br.ksg.classesBasicas.ReceitaBasica;

/**
 * Created by Marcos and Josias on 22/11/2014.
 */

public class ReceitasDAO {

    static SQLiteDatabase bancoDeDados;

    public ReceitasDAO(Context context){
        if(bancoDeDados==null){
            bancoDeDados = (new BancoDeDados(context)).getWritableDatabase();
        }
    }

    public boolean addReceitas(Receita receita , List<Ingrediente> ingredientes){
        try{
            //String comando = "INSERT INTO Ingredientes VALUES ("++",'"++"'"+");";
            //bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }


    public ArrayList<ReceitaBasica> getListaFavoritos(){
        ArrayList<ReceitaBasica> favoritos = new ArrayList<ReceitaBasica>() ;
        try{
            String cmd = "SELECT nome,tempo,porcoes FROM Receitas";
            Cursor cursor = bancoDeDados.rawQuery(cmd,null);
            while(cursor.moveToNext()){
                favoritos.add( new ReceitaBasica(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
            cursor.close();
            return favoritos ;
        }
        catch(SQLException e){
            Log.i("APPBD", e.getMessage());
            return null;
        }

    }



}

