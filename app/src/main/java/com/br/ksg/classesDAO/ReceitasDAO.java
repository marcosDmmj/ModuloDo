package com.br.ksg.classesDAO;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.br.ksg.bancoDeDados.BancoDeDados;
import com.br.ksg.classesBasicas.Receita;
import com.br.ksg.classesBasicas.ReceitaBasica;

/*
 * Created by Marcos and Josias on 22/11/2014.
 */

public class ReceitasDAO {

    static SQLiteDatabase bancoDeDados;

    public ReceitasDAO(Context context){
        if(bancoDeDados==null){
            bancoDeDados = (new BancoDeDados(context)).getWritableDatabase();
        }
    }



    public boolean addReceitas(Receita receita,List<String> ing,List<String> id_ing){
        int x;
        if(buscaReceita(receita.getNome())){
            Log.i("Adicionou", "não adedou");
            return true ;
        }
        else{
            try{
                x = Integer.parseInt(receita.getId_receita()) ;
                String comando = "INSERT INTO Receitas VALUES('"+x+"','"+receita.getNome()+"'," +
                        "'"+receita.getTempo()+"'," +
                        "'"+receita.getPorcoes()+"'," +
                        "'"+receita.getModo_preparo()+"'," +
                        "'"+receita.getRating()+"'," +
                        "'"+receita.getCategoria()+"'," +
                        "'"+receita.getDificuldade()+"'" +
                        ")";
                bancoDeDados.execSQL(comando);

                for (int i = 0;i<ing.size();i++) {
                    String comando2 = "INSERT INTO Receita_Ingrediente VALUES("+id_ing.get(i)+","+x+",'"+ing.get(i)+"')";
                    bancoDeDados.execSQL(comando2);
                }

                return true ;
            }
            catch(SQLException e){
                return false ;
            }
        }

    }

    public String removeReceitas(String nome){
        try{
            String comando = " DELETE FROM Receitas WHERE nome = "+"'"+nome+"'";
            bancoDeDados.execSQL(comando);
            return "Removeu dos favoritos!!";
        }
        catch(SQLException e){
            return "Erro: "+e.getLocalizedMessage() ;
        }
    }

    //Método que verifica se o registro já está presente no banco de dados
    public boolean buscaReceita(String receita){
        String novaReceita;
        try{
            String cmd = "SELECT nome FROM Receitas";
            Cursor cursor = bancoDeDados.rawQuery(cmd,null);
            while(cursor.moveToNext()){
                novaReceita = cursor.getString(0);
                if(pesquisa(receita,novaReceita)){
                    cursor.close();
                    return true;
                }
            }

        }
        catch(SQLException e){
            Log.i("APPBD", e.getMessage());
        }
        return false;

    }

    public boolean pesquisa(String receita,String novaReceita){
        return receita.equals(novaReceita);
    }

    public int quantIngredientes(int id_receita){
        try{
            String cmd = "SELECT * FROM Receita_Ingrediente WHERE id_receita = "+id_receita;
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
            Log.i("APPBD", e.getMessage());
        }
        return 0;
    }



    public Receita retornaReceita(String nome){
        try{
            String cmd = "SELECT * FROM Receitas WHERE nome='"+nome+"'";
            Cursor cursor = bancoDeDados.rawQuery(cmd,null);
            if(cursor.moveToNext()){
                return new Receita(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getDouble(5), cursor.getString(6),
                        cursor.getString(7));
            }
        }
        catch(SQLException e){
            Log.i("APPBD", e.getMessage());
        }
        return null;
    }



    public ArrayList<ReceitaBasica> getListaFavoritos(){
        ArrayList<ReceitaBasica> favoritos = new ArrayList<ReceitaBasica>() ;
        try{
            String cmd = "SELECT nome,tempo,porcoes FROM Receitas";
            Cursor cursor = bancoDeDados.rawQuery(cmd,null);
            while(cursor.moveToNext()){
                favoritos.add(new ReceitaBasica(cursor.getString(0), cursor.getString(1)+ "min", cursor.getString(2)+" prato(s)"));
            }
            cursor.close();
            return favoritos ;
        }
        catch(SQLException e){
            Log.i("APPBD", e.getMessage());
            return null;
        }
    }

    public List<String> getListaIngrediente(int id){
        List<String> favoritos = new ArrayList<String>() ;
        try{
            String cmd = "SELECT quantidade FROM Receita_Ingrediente";
            Cursor cursor = bancoDeDados.rawQuery(cmd,null);
            while(cursor.moveToNext()){
                favoritos.add(cursor.getString(0));
            }
            cursor.close();
            return favoritos ;
        }
        catch(SQLException e){
            Log.i("APPBD", e.getMessage());
            return null;
        }
    }

    public List<String> getListaIDIngrediente(int id){
        List<String> favoritos = new ArrayList<String>() ;
        try{
            String cmd = "SELECT id_ingrediente FROM Receita_Ingrediente";
            Cursor cursor = bancoDeDados.rawQuery(cmd,null);
            while(cursor.moveToNext()){
                favoritos.add(cursor.getString(0));
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