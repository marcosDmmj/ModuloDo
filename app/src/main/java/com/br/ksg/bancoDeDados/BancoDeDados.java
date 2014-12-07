package com.br.ksg.bancoDeDados;

        import java.util.List;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marcos and Josias on 21/11/2014.
 */

public class BancoDeDados extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "KSG";

    private static final String SQL_CREATE_TABLE_INGREDIENTES = "CREATE TABLE Ingredientes(id integer, INGREDIENTE TEXT)";
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

    public BancoDeDados(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_INGREDIENTES);
        db.execSQL("INSERT INTO Ingredientes VALUES(1,'sal')");
        db.execSQL("INSERT INTO Ingredientes VALUES(2,'salinho')");
        db.execSQL("INSERT INTO Ingredientes VALUES(3,'salgadinho')");
        db.execSQL("INSERT INTO Ingredientes VALUES(4,'salrum')");
        db.execSQL("INSERT INTO Ingredientes VALUES(5,'salome')");
        db.execSQL("INSERT INTO Ingredientes VALUES(6,'salem')");
        db.execSQL("INSERT INTO Ingredientes VALUES(7,'salomao')");
        db.execSQL("INSERT INTO Ingredientes VALUES(8,'salmao')");
        db.execSQL("INSERT INTO Ingredientes VALUES(9,'abacaxi')");
        db.execSQL("INSERT INTO Ingredientes VALUES(10,'abacate')");
        db.execSQL("INSERT INTO Ingredientes VALUES(11,'abacatada')");
        db.execSQL("INSERT INTO Ingredientes VALUES(12,'abaca')");
        db.execSQL("INSERT INTO Ingredientes VALUES(13,'aba')");

        db.execSQL(SQL_CREATE_TABLE_RECEITAS);
        db.execSQL("INSERT INTO Receitas VALUES(1,'pao segura marido','10','2','bla bla bla',3,'la la la','fácil')");
        db.execSQL("INSERT INTO Receitas VALUES(2,'pao segura esposa','5','3','bla bla bla 2',6,'ta ta ta','difícil')");

        db.execSQL(SQL_CREATE_TABLE_RECEITA_INGREDIENTES);
        db.execSQL("INSERT INTO Receita_Ingrediente VALUES(1,1,'3 ml')");
        db.execSQL("INSERT INTO Receita_Ingrediente VALUES(10,1,'5 ml')");
        db.execSQL("INSERT INTO Receita_Ingrediente VALUES(3,2,'10 ml')");
        db.execSQL("INSERT INTO Receita_Ingrediente VALUES(5,2,'502 ml')");

        db.execSQL(SQL_CREATE_TABLE_USUARIOINFO);
        db.execSQL("INSERT INTO UsuarioInfo VAlUES('1','nao','nao','nao','nao','nao','nao','nao','nao','nao','nao','nao','Iniciante','Nenhuma','Nenhuma','0','385','23','10','9','4')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_INGREDIENTES);
        db.execSQL(SQL_DELETE_RECEITAS);
        db.execSQL(SQL_DELETE_TABLE_RECEITA_INGREDIENTES);
        db.execSQL(SQL_DELETE_TABLE_USUARIOINFO);
        onCreate(db);
    }

}
