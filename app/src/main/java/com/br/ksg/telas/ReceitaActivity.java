package com.br.ksg.telas;

import java.util.ArrayList;
import java.util.List;

import com.br.ksg.classesBasicas.Receita;
import com.br.ksg.classesDAO.ReceitasDAO;
import com.br.ksg.webService.DownloadImagemReceita;
import com.example.exempleswipetab.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class ReceitaActivity extends Activity {

    TextView txt_titulo;
    TextView txt_modo_preparo;
    TextView txt_tempo;
    TextView txt_porcoes;
    TextView txt_ingredientes;
    ImageView star01, star02, star03, star04, star05,compartilhar;
    Bundle receita ;
    boolean verificaBD;
    int id_receita;
    MenuItem item ;
    boolean status ;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent i = getIntent();
        receita = i.getBundleExtra("receita");
        verificaBD = false;

        if (receita.getInt("tamanho") == 0){
            usarToast(getString(R.string.verifica_conexao));
            finish();
        }
        else {
            try {
                id_receita = Integer.parseInt(receita.getString("id"));

                setTitle("Receita: " + receita.getString("nome"));

                txt_titulo = (TextView) findViewById(R.id.txt_nome_receita);
                txt_titulo.setText(receita.getString("nome"));

                txt_ingredientes = (TextView) findViewById(R.id.txt_ingredientes_receita);
                String ingredientes = "";
                for (int j = 0; j < Integer.parseInt(receita.getString("quant")); j++){
                    ingredientes += receita.getString("ingrediente"+j)+"\n";
                }
                txt_ingredientes.setText(ingredientes);

                txt_modo_preparo = (TextView) findViewById(R.id.txt_modo_de_preparo_receita);
                txt_modo_preparo.setText(receita.getString("modo_preparo"));

                txt_tempo = (TextView) findViewById(R.id.txt_tempo_de_preparo);
                txt_tempo.setText("Tempo de preparo: " + receita.getString("tempo") + " min");

                txt_porcoes = (TextView) findViewById(R.id.txt_qtd_de_porcoes);
                txt_porcoes.setText(getString(R.string.porcoes) + ": " + receita.getString("porcoes"));

                try {
                    // if (receita.get("img") == null)
                    new DownloadImagemReceita(getApplication(),this).execute("http://ksmapi.besaba.com/imagens/"+receita.getString("id")+".jpg");
                } catch (Exception e){
                    usarToast("Deu erro! "+e.getMessage());
                }

                star01 = (ImageView) findViewById(R.id.star01);
                star02 = (ImageView) findViewById(R.id.star02);
                star03 = (ImageView) findViewById(R.id.star03);
                star04 = (ImageView) findViewById(R.id.star04);
                star05 = (ImageView) findViewById(R.id.star05);

                star01.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        star01.setImageResource(R.drawable.cookie_star_icon_select);
                        star02.setImageResource(R.drawable.cookie_star_icon);
                        star03.setImageResource(R.drawable.cookie_star_icon);
                        star04.setImageResource(R.drawable.cookie_star_icon);
                        star05.setImageResource(R.drawable.cookie_star_icon);
                    }
                });
                star02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        star01.setImageResource(R.drawable.cookie_star_icon_select);
                        star02.setImageResource(R.drawable.cookie_star_icon_select);
                        star03.setImageResource(R.drawable.cookie_star_icon);
                        star04.setImageResource(R.drawable.cookie_star_icon);
                        star05.setImageResource(R.drawable.cookie_star_icon);
                    }
                });
                star03.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        star01.setImageResource(R.drawable.cookie_star_icon_select);
                        star02.setImageResource(R.drawable.cookie_star_icon_select);
                        star03.setImageResource(R.drawable.cookie_star_icon_select);
                        star04.setImageResource(R.drawable.cookie_star_icon);
                        star05.setImageResource(R.drawable.cookie_star_icon);
                    }
                });
                star04.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        star01.setImageResource(R.drawable.cookie_star_icon_select);
                        star02.setImageResource(R.drawable.cookie_star_icon_select);
                        star03.setImageResource(R.drawable.cookie_star_icon_select);
                        star04.setImageResource(R.drawable.cookie_star_icon_select);
                        star05.setImageResource(R.drawable.cookie_star_icon);
                    }
                });
                star05.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        star01.setImageResource(R.drawable.cookie_star_icon_select);
                        star02.setImageResource(R.drawable.cookie_star_icon_select);
                        star03.setImageResource(R.drawable.cookie_star_icon_select);
                        star04.setImageResource(R.drawable.cookie_star_icon_select);
                        star05.setImageResource(R.drawable.cookie_star_icon_select);
                    }
                });

                compartilhar = (ImageView) findViewById(R.id.btn_compartilhar);
                compartilhar.setOnClickListener(new View.OnClickListener() {
                    private Uri fileUri;

                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
                        startActivityForResult(i,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                    }
                });
            } catch (Exception e) {
                usarToast("ERRORRRRRR!!! "+e.getLocalizedMessage());
                e.printStackTrace();
                finish();
            }

        }
    }

    public void usarToast(String texto) {
        Toast.makeText(getBaseContext(), texto, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_receita_final, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //switch (item.getItemId()) {

        ReceitasDAO receitaPesquisa = new ReceitasDAO(this.getBaseContext());
        verificaBD = receitaPesquisa.buscaReceita(receita.getString("nome")) ;

        if(verificaBD){
            item.setIcon(R.drawable.favorito_selected);
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setMessage("Deseja remover dos Favoritos");
            bld.setTitle("Aviso");

            bld.setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    status = true ;
                    ReceitasDAO receitaDAO = new ReceitasDAO(getBaseContext());
                    usarToast(receitaDAO.removeReceitas(receita.getString("nome")));

                }
            });



            bld.setNegativeButton(getString(R.string.nao),new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            });

            bld.show();

        }


        if(!verificaBD){
            item.setIcon(R.drawable.favorito);
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setMessage("Deseja adicionar  aos Favoritos?");
            bld.setTitle("Aviso");

            bld.setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //Adiciona
                    status = true ;
                    add();


                    Receita receitaFav = new Receita(
                            ""+id_receita,
                            receita.getString("nome"),
                            receita.getString("tempo"),
                            receita.getString("porcoes"),
                            receita.getString("modo_preparo"),
                            receita.getDouble("rating"),
                            receita.getString("categoria"),
                            receita.getString("dificuldade"));
                    ReceitasDAO receitaDAO = new ReceitasDAO(getBaseContext());

                    List<String> id_ingredientes = new ArrayList<String>();
                    for (int j = 0; j < Integer.parseInt(receita.getString("quant")); j++){
                        id_ingredientes.add(receita.getString("id_ing"+j));
                    }

                    List<String> ingredientes = new ArrayList<String>();
                    for (int j = 0; j < Integer.parseInt(receita.getString("quant")); j++){
                        ingredientes.add(receita.getString("ingrediente"+j));
                    }

                    receitaDAO.addReceitas(receitaFav,ingredientes,id_ingredientes);

                }
            });


            bld.setNegativeButton(getString(R.string.nao),new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            });
            bld.show();

        }



        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Image saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
                // compartilhar.setImageDrawable();
                compartilhar.setImageURI(data.getData());
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }

    public void remove(){
        Toast toast = Toast.makeText(this, "Receita removida dos Favoritos!", Toast.LENGTH_LONG);
        //item.setIcon(R.drawable.favorito);
        toast.show();
    }

    public void add(){
        Toast toast = Toast.makeText(this, "Receita adicionada aos Favoritos!", Toast.LENGTH_LONG);
        //item.setIcon(R.drawable.favorito_selected);
        toast.show();
    }
}
