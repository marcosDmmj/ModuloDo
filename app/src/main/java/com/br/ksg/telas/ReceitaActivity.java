package com.br.ksg.telas;

import com.br.ksg.webService.DownloadImagemReceita;
import com.example.exempleswipetab.R;

import android.app.Activity;
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
    boolean verificaBD;
    int id_receita;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final);

		Intent i = getIntent();
		Bundle receita = i.getBundleExtra("receita");
        verificaBD = false;

        if (receita.getInt("tamanho") == 0){
            usarToast(getString(R.string.verifica_conexao));
            finish();
        }
        else {
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

            /*

                        txt_ingredientes.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
            {
                boolean isJustified = false;

                @Override
                public boolean onPreDraw()
                {
                    if(!isJustified)
                    {
                        TextJustifyUtils.run(txt_modo_preparo,240);
                        isJustified = true;
                    }

                    return true;
                }

            });

             */
            txt_modo_preparo = (TextView) findViewById(R.id.txt_modo_de_preparo_receita);
            txt_modo_preparo.setText(receita.getString("modo_preparo"));

            // txt_modo_preparo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
            //{
              //  boolean isJustified = false;

                //@Override
                //public boolean onPreDraw()
                //{
                   // if(!isJustified)
                    //{
                        // TextJustifyUtils.run(txt_modo_preparo,230);
                      //  isJustified = true;
                    //}

                    //return true;
                //}

            //});

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

            // TODO: Ver abaixo!
            /*
            Usar esse metodo pra saber se a receita havia se favoritado anteriormente para saber que imagem mostrar
            public ArrayList<ReceitaBasica> listaCheia(){
                ReceitasDAO receitaBasicaDAO = new ReceitasDAO(getActivity());
                return receitaBasicaDAO.getListaFavoritos();
            }
            favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //se a imagem ainda nao tiver sido favoritada poderei realizar o seguinte procedimento...
                    favorito.setImageResource(R.drawable.favorito_selected);
                    usarToast("Adicionado aos favoritos! -sqn");
                }
            });

            */

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
                    // i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                    // fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                    i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
                    startActivityForResult(i,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }
            });
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
        switch (item.getItemId()) {
            case R.id.action_favorito:
                verificaBD = !verificaBD;
                if (verificaBD) {
                    item.setIcon(R.drawable.favorito_selected);
                    Toast.makeText(this, "Add aos favoritos! -sqn", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    item.setIcon(R.drawable.favorito);
                    Toast.makeText(this, "Removido dos favoritos! -sqn", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
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
}
