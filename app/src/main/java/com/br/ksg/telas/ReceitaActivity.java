package com.br.ksg.telas;

import com.br.ksg.webService.DownloadImagemReceita;
import com.example.exempleswipetab.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReceitaActivity extends Activity {
	
	private TextView txt_titulo;
	private TextView txt_modo_preparo;
	private TextView txt_tempo;
	private TextView txt_porcoes;
	private ImageView img_receita;
	
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

            txt_tempo = (TextView) findViewById(R.id.txt_tempo_de_preparo);
            txt_tempo.setText("Tempo de preparo: " + receita.getString("tempo") + " min");

            txt_porcoes = (TextView) findViewById(R.id.txt_qtd_de_porcoes);
            txt_porcoes.setText(getString(R.string.porcoes) + ": " + receita.getString("porcoes"));

            img_receita = (ImageView) findViewById(R.id.img_receita);
            try {
                new DownloadImagemReceita(getApplication(),this).execute("http://ksmapi.besaba.com/imagens/"+receita.getString("id")+".jpg");
            } catch (Exception e){

            }
        }
	}

    public void usarToast(String texto) {
        Toast.makeText(getBaseContext(), texto, Toast.LENGTH_LONG).show();
    }


}
