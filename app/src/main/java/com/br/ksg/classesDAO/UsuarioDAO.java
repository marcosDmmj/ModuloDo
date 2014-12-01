package com.br.ksg.classesDAO;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.br.ksg.bancoDeDados.BancoDeDados;
import com.br.ksg.classesBasicas.Ingrediente;
import com.br.ksg.classesBasicas.Receita;

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

    public boolean setUsuario(int status1,int status2,int status3,int status4,int status5,int status6,int status7,int status8,int status9,int status10,int status11){
        try{
            String comando = "INSERT INTO Usuario VALUES ("+ status1 +","+ status2 +","+ status3 +","+ status3 +","+ status4 +","+status5+","+ status6 +","+ status7 + ","+ status8 +","+ status9 +","+ status10 +","+ status11 +");";
            bancoDeDados.execSQL(comando);
            System.out.println("Funcionou !!!");
            return true ;
        }
        catch(SQLException e){
            return false ;
        }
    }


}
