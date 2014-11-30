package com.br.ksg.classesDAO;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.br.ksg.bancoDeDados.BancoDeDados;
import com.br.ksg.classesBasicas.Ingrediente;
import com.br.ksg.classesBasicas.Receita;
import com.br.ksg.classesBasicas.Usuario;

import java.util.List;

/**
 * Created by metal on 29/11/14.
 */
public class UsuarioDAO {

    static SQLiteDatabase bancoDeDados;

    public UsuarioDAO(Context context){
        if(bancoDeDados==null){
            bancoDeDados = (new BancoDeDados(context)).getWritableDatabase();
        }
    }

    public boolean setRestricaoVegetariano(int status){
        try{
            String comando = "INSERT INTO Usuario (vegetariano) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public boolean setRestricaoDiabetico(int status){
        try{
            String comando = "INSERT INTO Usuario (diabetico) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public boolean setRestricaoHipertenso(int status){
        try{
            String comando = "INSERT INTO Usuario (hipertenso) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public boolean setAlergiaFruto(int status){
        try{
            String comando = "INSERT INTO Usuario (alergia_fruto) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public boolean setAlergiaAmendoim(int status){
        try{
            String comando = "INSERT INTO Usuario (alergia_amendoim) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public boolean setAlergiaLeite(int status){
        try{
            String comando = "INSERT INTO Usuario (alergia_leite) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public boolean setAlergiaNozes(int status){
        try{
            String comando = "INSERT INTO Usuario (alergia_nozes) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public boolean setAlergiaOvos(int status){
        try{
            String comando = "INSERT INTO Usuario (alergia_ovos) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public boolean setAlergiaPeixes(int status){
        try{
            String comando = "INSERT INTO Usuario (alergia_peixes) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public boolean setAlergiaSoja(int status){
        try{
            String comando = "INSERT INTO Usuario (alergia_soja) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

    public boolean setAlergiaTrigo(int status){
        try{
            String comando = "INSERT INTO Usuario (alergia_trigo) VALUES (" + status +");";
            bancoDeDados.execSQL(comando);
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }

}
