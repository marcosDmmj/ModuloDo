package com.br.ksg.telas;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.br.ksg.classesBasicas.Receita;
import com.br.ksg.classesDAO.ReceitasDAO;
import com.br.ksg.classesDAO.UsuarioDAO;
import com.br.ksg.webService.DownloadImagemReceita;
import com.example.exempleswipetab.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
    ImageView star01, star02, star03, star04, star05,compartilhar,imagemSd;
    Bundle receita ;
    boolean verificaBD;
    int id_receita;
    boolean status ;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    ArrayList<String> ing = new ArrayList<String>();
    int controleEstrela = 0;
    int favorito = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent i = getIntent();
        receita = i.getBundleExtra("receita");
        verificaBD = false;

        if (receita == null){
            usarToast(getString(R.string.verifica_conexao)+"2");
            finish();
        }
        if (receita.getInt("tamanho") == 0){
            usarToast(getString(R.string.verifica_conexao));
            finish();
        }
        else {
            try {
                imagemSd = (ImageView) findViewById(R.id.img_receita);

                id_receita = Integer.parseInt(receita.getString("id"));

                // bla bla
                //setTitle("Receita: " + receita.getString("nome"));

                txt_titulo = (TextView) findViewById(R.id.txt_nome_receita);
                txt_titulo.setText(receita.getString("nome"));

                // Verificar se a receita já tem pontuacao
                final UsuarioDAO usuarioDAO = new UsuarioDAO(this);

                if (!usuarioDAO.receita_existe(id_receita)) {
                    Log.i("KSG","Vai vim aqui?");
                    List<String> id_ingredientes = new ArrayList<String>();
                    for (int j = 0; j < Integer.parseInt(receita.getString("quant")); j++) {
                        id_ingredientes.add(j, receita.getString("id_ing" + j));
                    }

                    usuarioDAO.addReceita(id_receita, id_ingredientes);
                }

                // List<String> id_ingredientes = new ArrayList<String>();
                ReceitasDAO receitasDAO = new ReceitasDAO(this);

                List<String> tes = receitasDAO.contagem_pontos(id_receita);
                Log.i("KSG","id = "+id_receita+ " size = " +tes.size());
                for (int j = 0; j < tes.size(); j++) {
                    Log.i("KSG", tes.get(j));
                }


                txt_ingredientes = (TextView) findViewById(R.id.txt_ingredientes_receita);
                String ingredientes = "";
                for (int j = 0; j < Integer.parseInt(receita.getString("quant")); j++) {
                    ingredientes += receita.getString("ingrediente" + j) + "\n";
                    ing.add(j, receita.getString("ingrediente" + j));
                }
                txt_ingredientes.setText(ingredientes);

                txt_modo_preparo = (TextView) findViewById(R.id.txt_modo_de_preparo_receita);
                txt_modo_preparo.setText(receita.getString("modo_preparo"));

                txt_tempo = (TextView) findViewById(R.id.txt_tempo_de_preparo);
                txt_tempo.setText("Tempo de preparo: " + receita.getString("tempo") + " min");

                txt_porcoes = (TextView) findViewById(R.id.txt_qtd_de_porcoes);
                txt_porcoes.setText(getString(R.string.porcoes) + ": " + receita.getString("porcoes"));

                if (receita.getInt("tamanho") != 2) {
                    try {
                        new DownloadImagemReceita(getApplication(), this).execute("http://ksmapi.besaba.com/imagens/" + receita.getString("id") + ".jpg");
                    } catch (Exception e) {
                        usarToast("Deu erro! " + e.getMessage());
                    }
                } else{
                    // TODO: Aqui que tem que fazer as coisas meu bem XD
                    // TODO: Vai setar nesse imageView
                    //Carrega oque houve a imagem do SDcard
                    try
                    {
                        String myPathInSd = "/sdcard/KSG/image_"+receita.getString("nome")+".jpg"; //UPDATE WITH YOUR OWN JPG FILE
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 2;
                        Bitmap bitmap = BitmapFactory.decodeFile(myPathInSd, options);
                        if(bitmap!=null){
                            imagemSd.setImageBitmap(bitmap);
                        }
                        else{
                            imagemSd.setImageResource(R.drawable.no_image2);
                        }

                    }
                    catch (Exception e)
                    {
                        imagemSd.setImageResource(R.drawable.no_image2);
                    }

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
                        controleEstrela = -3;
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
                        controleEstrela = -1;
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
                        controleEstrela = 1;
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
                        controleEstrela = 3;
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
                        controleEstrela = 5;
                    }
                });

                compartilhar = (ImageView) findViewById(R.id.btn_compartilhar);

                UsuarioDAO u = new UsuarioDAO(getBaseContext());
                ArrayList<String> id_ingredientes = new ArrayList<String>();
                int restricoes[] = {0, 0, 0};
                int alergias[] = {0, 0, 0, 0, 0, 0, 0, 0};
                int controleRestricoes = 0;

                for (int j = 0; j < Integer.parseInt(receita.getString("quant")); j++) {
                    id_ingredientes.add(j, receita.getString("id_ing" + j));

                    switch (Integer.parseInt(receita.getString("id_ing" + j))) {
                        case 1:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 2:
                            restricoes[2] = 1;
                            controleRestricoes = 1;
                            break;
                        case 8:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 12:
                            restricoes[2] = 1;
                            controleRestricoes = 1;
                            break;
                        case 13:
                            restricoes[2] = 1;
                            controleRestricoes = 1;
                            break;
                        case 14:
                            alergias[2] = 1;
                            controleRestricoes = 1;
                            break;
                        case 15:
                            alergias[7] = 1;
                            controleRestricoes = 1;
                            break;
                        case 16:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 19:
                            alergias[7] = 1;
                            controleRestricoes = 1;
                            break;
                        case 20:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 23:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 25:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 27:
                            alergias[2] = 1;
                            controleRestricoes = 1;
                            break;
                        case 30:
                            restricoes[1] = 1;
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 32:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 33:
                            alergias[7] = 1;
                            controleRestricoes = 1;
                            break;
                        case 34:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 35:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 37:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 40:
                            restricoes[0] = 1;
                            alergias[6] = 1;
                            controleRestricoes = 1;
                            break;
                        case 43:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 44:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 47:
                            alergias[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 48:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 49:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 56:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 58:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 60:
                            alergias[5] = 1;
                            controleRestricoes = 1;
                            break;
                        case 62:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 63:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 64:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 69:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 71:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 73:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 75:
                            alergias[7] = 1;
                            controleRestricoes = 1;
                            break;
                        case 76:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 77:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 79:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 81:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 86:
                            restricoes[1] = 1;
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 87:
                            restricoes[1] = 1;
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 89:
                            alergias[5] = 1;
                            controleRestricoes = 1;
                            break;
                        case 93:
                            alergias[2] = 1;
                            controleRestricoes = 1;
                            break;
                        case 96:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 100:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 109:
                            alergias[3] = 1;
                            controleRestricoes = 1;
                            break;
                        case 111:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 112:
                            alergias[2] = 1;
                            controleRestricoes = 1;
                            break;
                        case 113:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 114:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 115:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 119:
                            restricoes[0] = 1;
                            alergias[6] = 1;
                            controleRestricoes = 1;
                            break;
                        case 120:
                            alergias[7] = 1;
                            controleRestricoes = 1;
                            break;
                        case 121:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 122:
                            restricoes[1] = 1;
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 128:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 139:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 147:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 148:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 149:
                            alergias[4] = 1;
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 150:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 152:
                            alergias[7] = 1;
                            controleRestricoes = 1;
                            break;
                        case 160:
                            alergias[7] = 1;
                            controleRestricoes = 1;
                            break;
                        case 163:
                            alergias[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 164:
                            alergias[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 168:
                            restricoes[0] = 1;
                            alergias[6] = 1;
                            controleRestricoes = 1;
                            break;
                        case 169:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 173:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 174:
                            restricoes[2] = 1;
                            controleRestricoes = 1;
                            break;
                        case 177:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 178:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 179:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 180:
                            restricoes[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 182:
                            restricoes[2] = 1;
                            controleRestricoes = 1;
                            break;
                        case 183:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 196:
                            restricoes[0] = 1;
                            alergias[6] = 1;
                            controleRestricoes = 1;
                            break;
                        case 201:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 214:
                            restricoes[0] = 1;
                            alergias[6] = 1;
                            controleRestricoes = 1;
                            break;
                        case 215:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 216:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 221:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 222:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 225:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 228:
                            restricoes[0] = 1;
                            controleRestricoes = 1;
                            break;
                        case 230:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        case 236:
                            alergias[1] = 1;
                            controleRestricoes = 1;
                            break;
                        default:
                            break;
                    }
                }

                if (controleRestricoes == 1) {

                    String[] aux = u.getRestricoes();
                    String msgRestricao = "";
                    String msgAlergias = "";

                    for (int k = 0; k < 3; k++) {
                        if (aux[k].equals("nao")) restricoes[k] = 0;
                    }

                    for (int k = 0; k < 8; k++) {
                        if (aux[k].equals("nao")) alergias[k] = 0;
                    }

                    if (restricoes[0] == 1) { msgRestricao = msgRestricao + "- Vegetariano \n"; }
                    if (restricoes[1] == 1) { msgRestricao = msgRestricao + "- Diabete \n"; }
                    if (restricoes[2] == 1) { msgRestricao = msgRestricao + "- Hipertenso "; }
                    if (alergias[0] == 1) { msgAlergias = msgAlergias + "- Frutos do Mar \n"; }
                    if (alergias[1] == 1) { msgAlergias = msgAlergias + "- Leite \n"; }
                    if (alergias[2] == 1) { msgAlergias = msgAlergias + "- Ovos \n"; }
                    if (alergias[3] == 1) { msgAlergias = msgAlergias + "- Soja \n"; }
                    if (alergias[4] == 1) { msgAlergias = msgAlergias + "- Amendoins \n"; }
                    if (alergias[5] == 1) { msgAlergias = msgAlergias + "- Nozes \n"; }
                    if (alergias[6] == 1) { msgAlergias = msgAlergias + "- Peixes \n"; }
                    if (alergias[7] == 1) { msgAlergias = msgAlergias + "- Trigo "; }


                    AlertDialog.Builder bld = new AlertDialog.Builder(this);
                    String msgNivel = "";

                    if(u.getNivelUsuario().equals("Iniciante")){

                        if(Integer.parseInt(receita.getString("tempo")) > 180){
                            msgNivel = "É aconselhado que tente essa receita no nível: Mestre Cucca \n\n";
                        }
                        if(Integer.parseInt(receita.getString("tempo")) > 120 && Integer.parseInt(receita.getString("tempo")) <= 180){
                            msgNivel = "É aconselhado que tente essa receita no nível: Experiente \n\n";
                        }
                        if(Integer.parseInt(receita.getString("tempo")) > 90 && Integer.parseInt(receita.getString("tempo")) <= 120){
                            msgNivel = "É aconselhado que tente essa receita no nível: Mediano \n\n";
                        }
                        if(Integer.parseInt(receita.getString("tempo")) > 30  && Integer.parseInt(receita.getString("tempo")) <= 90){
                            msgNivel = "É aconselhado que tente essa receita no nível: Novato \n\n";
                        }
                    }

                    if(u.getNivelUsuario().equals("Novato")){
                        if(Integer.parseInt(receita.getString("tempo")) > 180){
                            msgNivel = "É aconselhado que tente essa receita no nível: Mestre Cucca \n\n";
                        }
                        if(Integer.parseInt(receita.getString("tempo")) > 120 && Integer.parseInt(receita.getString("tempo")) <= 180){
                            msgNivel = "É aconselhado que tente essa receita no nível: Experiente \n\n";
                        }
                        if(Integer.parseInt(receita.getString("tempo")) > 90 && Integer.parseInt(receita.getString("tempo")) <= 120){
                            msgNivel = "É aconselhado que tente essa receita no nível: Mediano \n\n";
                        }
                    }

                    if(u.getNivelUsuario().equals("Mediano")){
                        if(Integer.parseInt(receita.getString("tempo")) > 180){
                            msgNivel = "É aconselhado que tente essa receita no nível: Mestre Cucca \n\n";
                        }
                        if(Integer.parseInt(receita.getString("tempo")) > 120 && Integer.parseInt(receita.getString("tempo")) <= 180){
                            msgNivel = "É aconselhado que tente essa receita no nível: Experiente \n\n";
                        }
                    }

                    if(u.getNivelUsuario().equals("Experiente")){
                        if(Integer.parseInt(receita.getString("tempo")) > 180){
                            msgNivel = "É aconselhado que tente essa receita no nível: Mestre Cucca \n\n";
                        }
                    }

                    if(!(msgAlergias.equals("") && msgRestricao.equals(""))) {

                    if ((restricoes[0] == 1 || restricoes[1] == 1 || restricoes[2] == 1) && (alergias[0] == 1 || alergias[1] == 1 || alergias[2] == 1 || alergias[3] == 1 || alergias[4] == 1 || alergias[5] == 1 || alergias[6] == 1 || alergias[7] == 1)) {
                        bld.setMessage(msgNivel + "Essa receita contém ingredientes que vão contra suas seguintes restrições: \n\n" + msgRestricao + "\n\nTambém possui ingredientes que podem afetar suas seguintes alergias: \n\n" + msgAlergias);
                    } else if (restricoes[0] == 1 || restricoes[1] == 1 || restricoes[2] == 1) {
                        bld.setMessage(msgNivel + "Essa receita contém ingredientes que vão contra suas seguintes restrições: \n\n" + msgRestricao);
                    } else if (alergias[0] == 1 || alergias[1] == 1 || alergias[2] == 1 || alergias[3] == 1 || alergias[4] == 1 || alergias[5] == 1 || alergias[6] == 1 || alergias[7] == 1) {
                        bld.setMessage(msgNivel + "Essa receita contém ingredientes que podem afetar suas seguintes alergias: \n\n" + msgAlergias);
                    }



                        bld.setTitle("Aviso");

                        bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        bld.show();
                    }
            }

                compartilhar.setOnClickListener(new View.OnClickListener() {
                    // private Uri fileUri;




                    @Override
                    public void onClick(View v) {

                        UsuarioDAO u = new UsuarioDAO(getBaseContext());

                        ArrayList<String> id_ingredientes = new ArrayList<String>();
                        for (int j = 0; j < Integer.parseInt(receita.getString("quant")); j++){
                            id_ingredientes.add(j, receita.getString("id_ing"+j));
                        }

                        u.update_experiencia(receita.getString("tempo"));
                        u.update_pontos(id_ingredientes, controleEstrela);

                        //TODO: SEM QUERER DELETAMOS OS COMANDOS PRA TIRAR FOTO D:
                        usarToast("Clicou em foto :) E supostamente up os pontos!");

                    }
                });
            } catch (Exception e) {
                usarToast("ERRORRRRRR!!! "+e.getLocalizedMessage());
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
    public boolean onOptionsItemSelected(final MenuItem item) {
        //switch (item.getItemId()) {

        ReceitasDAO receitaPesquisa = new ReceitasDAO(this.getBaseContext());
        verificaBD = receitaPesquisa.buscaReceita(receita.getString("nome")) ;

        if(verificaBD){

            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setMessage("Deseja remover dos Favoritos");
            bld.setTitle("Aviso");

            bld.setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    status = true ;
                    ReceitasDAO receitaDAO = new ReceitasDAO(getBaseContext());
                    usarToast(receitaDAO.removeReceitas(receita.getString("nome")));
                    item.setIcon(R.drawable.favorito);
                }
            });



            bld.setNegativeButton(getString(R.string.nao),new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    favorito  = 1;
                }
            });

            bld.show();

        }

        if(verificaBD == false){

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

                item.setIcon(R.drawable.favorito_selected);

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
                usarToast("bla Cancelou");
            } else {
                // Image capture failed, advise user
                usarToast("bla falhou");
            }
        }
    }

    public void add(){
        Toast toast = Toast.makeText(this, "Receita adicionada aos Favoritos!", Toast.LENGTH_LONG);
        toast.show();

        imagemSd = (ImageView) findViewById(R.id.img_receita);
        imagemSd.getDrawable();


        BitmapDrawable btmpDr = (BitmapDrawable) imagemSd.getDrawable();
        Bitmap bmp = btmpDr.getBitmap();

        //Salva imagem no SDcard
        try
        {
            File sdCardDirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "KSG");
            sdCardDirectory.mkdirs();

            String imageNameForSDCard = "image_"  + receita.getString("nome") + ".jpg";

            File image = new File(sdCardDirectory, imageNameForSDCard);
            FileOutputStream outStream;

            outStream = new FileOutputStream(image);
                        /* 100 to keep full quality of the image */
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            //Refreshing SD card
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //Toast.makeText( t, "Image could not be saved : Please ensure you have SD card installed " +
            //                                                                      "properly", Toast.LENGTH_LONG).show();
        }


    }
}
