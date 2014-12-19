package com.br.ksg.bancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Created by Marcos and Josias bla on 21/11/2014.
 */

public class BancoDeDados extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "KSG";

    private static final String SQL_CREATE_TABLE_INGREDIENTES = "CREATE TABLE Ingredientes(" +
            "id integer," +
            " INGREDIENTE TEXT)";
    private static final String SQL_CREATE_TABLE_RECEITAS = "CREATE TABLE Receitas(" +
            "id_receita integer," +
            "nome TEXT, " +
            "tempo TEXT," +
            "porcoes TEXT," +
            "modo_preparo TEXT," +
            "rating integer," +
            "categoria TEXT," +
            "dificuldade TEXT)";

    private static final String SQL_CREATE_TABLE_RECEITA_INGREDIENTES = "CREATE TABLE Receita_Ingrediente(" +
            "id_ingrediente integer," +
            "id_receita integer," +
            "quantidade TEXT)";

    private static final String SQL_CREATE_TABLE_USUARIOINFO = "CREATE TABLE UsuarioInfo(" +
            "id TEXT," +
            "vegetariano TEXT, " +
            "diabetico TEXT, " +
            "hipertenso TEXT, " +
            "frutos TEXT, " +
            "amendoim TEXT, " +
            "leite TEXT, " +
            "nozes TEXT, " +
            "ovos TEXT, " +
            "peixes TEXT, " +
            "soja TEXT, " +
            "trigo TEXT, " +
            "nivel TEXT, " +
            "restricao TEXT, " +
            "alergia TEXT, " +
            "experiencia TEXT, " +
            "horastotal TEXT, " +
            "pratostotal TEXT, " +
            "pratosfaceis TEXT, " +
            "pratosmedianos TEXT, " +
            "pratosdificeis TEXT)";

    private static final String SQL_DELETE_INGREDIENTES ="DROP TABLE IF EXISTS Ingredientes" ;
    private static final String SQL_DELETE_RECEITAS ="DROP TABLE IF EXISTS Receitas" ;
    private static final String SQL_DELETE_TABLE_RECEITA_INGREDIENTES ="DROP TABLE IF EXISTS Receita_Ingrediente" ;
    private static final String SQL_DELETE_TABLE_USUARIOINFO ="DROP TABLE IF EXISTS UsuarioInfo" ;
    private static final String SQL_DELETE_TABLE_SCORE ="DROP TABLE IF EXISTS Score" ;

    public BancoDeDados(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_INGREDIENTES);

        db.execSQL(SQL_CREATE_TABLE_RECEITAS);

        db.execSQL(SQL_CREATE_TABLE_RECEITA_INGREDIENTES);

        db.execSQL(SQL_CREATE_TABLE_USUARIOINFO);
        db.execSQL("INSERT INTO UsuarioInfo VAlUES('1','nao','nao','nao','nao','nao','nao','nao','nao','nao','nao','nao','Iniciante','Nenhuma','Nenhuma','0','0','0','0','0','0')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_INGREDIENTES);
        db.execSQL(SQL_DELETE_RECEITAS);
        db.execSQL(SQL_DELETE_TABLE_RECEITA_INGREDIENTES);
        db.execSQL(SQL_DELETE_TABLE_USUARIOINFO);
        db.execSQL(SQL_DELETE_TABLE_SCORE);
        onCreate(db);
    }

}
