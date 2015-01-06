package com.br.ksg.classesDAO;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.br.ksg.bancoDeDados.BancoDeDados;
import com.br.ksg.classesBasicas.Ingrediente;
import com.br.ksg.classesBasicas.Receita;

import java.util.ArrayList;
import java.util.List;

/**
/*
 * Created by metal on 29/11/14.
 */
public class UsuarioDAO {

    static SQLiteDatabase bancoDeDados;

    public UsuarioDAO(Context context){
        if(bancoDeDados==null){
            bancoDeDados = (new BancoDeDados(context)).getWritableDatabase();
        }
    }

    public boolean setUsuarioCaracteristicas(String status1,String status2,String status3,String status4,String status5,String status6,String status7,String status8,String status9,String status10,String status11, String status12, String status13){
        try{
            String comando = "UPDATE UsuarioInfo SET vegetariano='"+status1+"', diabetico='"+status2+"', hipertenso='"+status3+"', frutos='"+status4+"', amendoim='"+status5+"'" +
                             ", leite='"+status6+"', nozes='"+status7+"', ovos='"+status8+"', peixes='"+status9+"', soja='"+status10+"', trigo='"+status11+"', restricao='"+status12+"', alergia='"+status13+"' WHERE id='1'";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public String calculaProgresso(String horas){

        int aux=99999;

        if(Integer.parseInt(horas) <= 100){ aux = Integer.parseInt(horas); }
        else if(Integer.parseInt(horas) > 100 && Integer.parseInt(horas) <= 300){ aux = Integer.parseInt(horas) - 100;}
        else if(Integer.parseInt(horas) > 300 && Integer.parseInt(horas) <= 800){ aux = Integer.parseInt(horas) - 300;}
        else if(Integer.parseInt(horas) > 800 && Integer.parseInt(horas) <= 1550){ aux = Integer.parseInt(horas) - 800;}
        else if(Integer.parseInt(horas) > 1550){ aux = Integer.parseInt(horas) - 1550;}

        return Integer.toString(aux);
        }

    public String calculaNivel(String horas){

        String aux = "";

        if(Integer.parseInt(horas) <= 100){ aux = "Iniciante"; }
        else if(Integer.parseInt(horas) > 100 && Integer.parseInt(horas) <= 300){ aux = "Novato";}
        else if(Integer.parseInt(horas) > 300 && Integer.parseInt(horas) <= 800){ aux = "Mediano";}
        else if(Integer.parseInt(horas) > 800 && Integer.parseInt(horas) <= 1550){ aux = "Experiente";}
        else if(Integer.parseInt(horas) > 1550){ aux = "Mestre Cucca";}

        return aux;
    }


    public ArrayList<String> getInfoUsuario(){

        ArrayList<String> info = new ArrayList<String>();

        String sqlQuery = "SELECT restricao, alergia, horastotal, pratostotal, pratosfaceis, pratosmedianos, pratosdificeis FROM UsuarioInfo WHERE id='1'";
        Cursor cursor = bancoDeDados.rawQuery(sqlQuery, null);

        if(cursor.moveToNext()){
            info.add(cursor.getString(0));
            info.add(cursor.getString(1));
            info.add(cursor.getString(2));
            info.add(cursor.getString(3));
            info.add(cursor.getString(4));
            info.add(cursor.getString(5));
            info.add(cursor.getString(6));
        }

        info.add(calculaProgresso(info.get(2)));
        info.add(calculaNivel(info.get(2)));

      cursor.close();
      return info;

    }

    public boolean receita_existe(int id){
        String sqlQuery = "SELECT * FROM Pontuacao WHERE id_pratos = '"+id+"'";
        Cursor cursor = bancoDeDados.rawQuery(sqlQuery, null);

        if(cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

    public void addReceita(int id_receita,List<String> id_ingredientes){

        for(int i=0; i < id_ingredientes.size(); i++) {
            String sqlQuery2 = "SELECT pontos FROM Pontuacao WHERE id_ingredientes ='"+id_ingredientes.get(i)+"'";
            Cursor cursor2 = bancoDeDados.rawQuery(sqlQuery2, null);

            if (cursor2.moveToNext()) {
                String sqlQuery1 = "INSERT INTO Pontuacao VALUE('" + id_receita + "', '"+id_ingredientes.get(i)+"', '"+Integer.parseInt(cursor2.getString(0))+"')";
                Cursor cursor = bancoDeDados.rawQuery(sqlQuery1, null);
                cursor.close();
            } else {
                String sqlQuery3 = "INSERT INTO Pontuacao VALUE('" + id_receita + "', '"+id_ingredientes.get(i)+"', '0')";
                Cursor cursor = bancoDeDados.rawQuery(sqlQuery3, null);
                cursor.close();
            }
            cursor2.close();
        }
    }


    public void update_experiencia(String horas){
        ArrayList<String> info = new ArrayList<String>();
        String horastotal = "";
        String pratostotal = "";
        String dificuldadePrato = "";

        // TODO: Dificuldades dos pratos
        // if()

        String sqlQuery = "SELECT horastotal, pratostotal, pratosfaceis, pratosmedianos, pratosdificeis FROM UsuarioInfo WHERE id='1'";
        Cursor cursor = bancoDeDados.rawQuery(sqlQuery, null);

        if(cursor.moveToNext()){
            info.add(cursor.getString(0));
            info.add(cursor.getString(1));
            info.add(cursor.getString(2));
            info.add(cursor.getString(3));
            info.add(cursor.getString(4));
        }

        horastotal = Integer.toString(Integer.parseInt(info.get(0)) + Integer.parseInt(horas));
        pratostotal = Integer.toString(Integer.parseInt(info.get(1)) + 1);

        cursor.close();

        try{
            String comando = "UPDATE UsuarioInfo SET horastotal='"+horastotal+"', pratostotal='"+pratostotal+"' WHERE id='1'";
            bancoDeDados.execSQL(comando);
        }
        catch(SQLException e){}
    }

    public void update_pontos(ArrayList<String> lista, int ponto){

        int aux = 0;
        for(int i=0; i < lista.size(); i++){
            String comando1 = "SELECT pontos FROM Pontuacao WHERE id_ingredientes ='"+lista.get(i)+"'";
            Cursor cursor = bancoDeDados.rawQuery(comando1, null);

            if(cursor.moveToNext()){
                aux = Integer.parseInt(cursor.getString(0)) + ponto;
                String comando2 = "UPDATE Pontuacao SET pontos='"+ aux +"' WHERE id_ingredientes='"+lista.get(i)+"'";
            }
        }
    }

    public int contagem_pontos(int id_receita){
        String sqlQuery = "SELECT pontos FROM Pontuacao id_pratos = '"+id_receita+"'";
        Cursor cursor = bancoDeDados.rawQuery(sqlQuery, null);

        int result = 0;
        while(cursor.moveToNext()){
            result+= Integer.parseInt(cursor.getString(0));
        }

        return result;
    }

}
