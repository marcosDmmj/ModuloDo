package com.br.ksg.telas;

import com.br.ksg.webService.DownloadImagemReceita;
import com.example.exempleswipetab.R;
import com.example.exempleswipetab.TextJustifyUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReceitaActivity extends Activity {
	
	private TextView txt_titulo;
	private TextView txt_modo_preparo;
	private TextView txt_tempo;
	private TextView txt_porcoes;
	private ImageView img_receita;           // lalasera?
    private ImageView star01, star02, star03, star04, star05;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final);

		Intent i = getIntent();
		Bundle receita = i.getBundleExtra("receita");

        if (receita.getInt("tamanho") == 0){
            usarToast(getString(R.string.verifica_conexao));
            finish();
        }
        else {

            setTitle("Receita: " + receita.getString("nome"));

            txt_titulo = (TextView) findViewById(R.id.txt_nome_receita);
            txt_titulo.setText(receita.getString("nome"));

            txt_modo_preparo = (TextView) findViewById(R.id.txt_modo_de_preparo_receita);
            txt_modo_preparo.setText(receita.getString("modo_preparo"));

            txt_modo_preparo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
            {
                boolean isJustified = false;

                @Override
                public boolean onPreDraw()
                {
                    if(!isJustified)
                    {
                        TextJustifyUtils.run(txt_modo_preparo,230);
                        isJustified = true;
                    }

                    return true;
                }

            });

            txt_tempo = (TextView) findViewById(R.id.txt_tempo_de_preparo);
            txt_tempo.setText("Tempo de preparo: " + receita.getString("tempo") + " min");

            txt_porcoes = (TextView) findViewById(R.id.txt_qtd_de_porcoes);
            txt_porcoes.setText(getString(R.string.porcoes) + ": " + receita.getString("porcoes"));

            img_receita = (ImageView) findViewById(R.id.img_receita);
            try {
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


        }
	}

    public void usarToast(String texto) {
        Toast.makeText(getBaseContext(), texto, Toast.LENGTH_LONG).show();
    }


}
