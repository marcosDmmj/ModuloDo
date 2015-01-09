package com.br.ksg.telas;

import com.br.ksg.classesDAO.UsuarioDAO;
import com.example.exempleswipetab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PerfilUser extends Activity {
    ImageView btn_editar;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);

        btn_editar = (ImageView) findViewById(R.id.imageButton2);
        btn_editar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clicarEditar();
            }
        });

        TextView restricao = (TextView)findViewById(R.id.textViewModeracao);
        TextView alergia = (TextView)findViewById(R.id.textViewAlergias);
        TextView nivel = (TextView)findViewById(R.id.textViewNivel);
        TextView pontuacaoAtual = (TextView)findViewById(R.id.textViewPontuacaoAtual);
        TextView horasTotal = (TextView)findViewById(R.id.textViewHoras);
        TextView pontuacaoLimite = (TextView)findViewById(R.id.textViewMaiorPontuacao);
        ImageView progresso = (ImageView)findViewById(R.id.imageViewProgress);
        TextView pratosTotal = (TextView)findViewById(R.id.textViewTotalPratos);
        TextView pratosFaceis = (TextView)findViewById(R.id.textViewPratoFacil);
        TextView pratosMedianos = (TextView)findViewById(R.id.textViewPratoMedio);
        TextView pratosDificeis = (TextView)findViewById(R.id.textViewPratoDificil);

        UsuarioDAO u = new UsuarioDAO(this);
        ArrayList<String> info;
        float aux;

        info = u.getInfoUsuario();

        restricao.setText(info.get(0));
        alergia.setText(info.get(1));
        horasTotal.setText(info.get(2));
        pratosTotal.setText(info.get(3));
        pratosFaceis.setText(info.get(4));
        pratosMedianos.setText(info.get(5));
        pratosDificeis.setText(info.get(6));
        pontuacaoAtual.setText(info.get(7));
        nivel.setText(info.get(8));

        if(info.get(8).equals("Iniciante")) {
            pontuacaoLimite.setText("100");
            aux = Float.parseFloat(info.get(7))/100;
            if(aux == 0.0) progresso.setImageResource(R.drawable.progress_0);
            if(aux > 0.0 && aux <= 0.05) progresso.setImageResource(R.drawable.progress_5);
            if(aux > 0.05 && aux <= 0.1) progresso.setImageResource(R.drawable.progress_10);
            if(aux > 0.1 && aux <= 0.15) progresso.setImageResource(R.drawable.progress_15);
            if(aux > 0.15 && aux <= 0.2) progresso.setImageResource(R.drawable.progress_20);
            if(aux > 0.2 && aux <= 0.25) progresso.setImageResource(R.drawable.progress_25);
            if(aux > 0.25 && aux <= 0.3) progresso.setImageResource(R.drawable.progress_30);
            if(aux > 0.3 && aux <= 0.35) progresso.setImageResource(R.drawable.progress_35);
            if(aux > 0.35 && aux <= 0.4) progresso.setImageResource(R.drawable.progress_40);
            if(aux > 0.4 && aux <= 0.45) progresso.setImageResource(R.drawable.progress_45);
            if(aux > 0.45 && aux <= 0.5) progresso.setImageResource(R.drawable.progress_50);
            if(aux > 0.5 && aux <= 0.55) progresso.setImageResource(R.drawable.progress_55);
            if(aux > 0.55 && aux <= 0.6) progresso.setImageResource(R.drawable.progress_60);
            if(aux > 0.6 && aux <= 0.65) progresso.setImageResource(R.drawable.progress_65);
            if(aux > 0.65 && aux <= 0.7) progresso.setImageResource(R.drawable.progress_70);
            if(aux > 0.7 && aux <= 0.75) progresso.setImageResource(R.drawable.progress_75);
            if(aux > 0.75 && aux <= 0.8) progresso.setImageResource(R.drawable.progress_80);
            if(aux > 0.8 && aux <= 0.85) progresso.setImageResource(R.drawable.progress_85);
            if(aux > 0.85 && aux <= 0.9) progresso.setImageResource(R.drawable.progress_90);
            if(aux > 0.9 && aux <= 0.95) progresso.setImageResource(R.drawable.progress_95);
            if(aux > 0.95) progresso.setImageResource(R.drawable.progress_100);
        }
        if(info.get(8).equals("Novato")){
            pontuacaoLimite.setText("200");
            aux = Float.parseFloat(info.get(7))/200;
            if(aux == 0.0) progresso.setImageResource(R.drawable.progress_0);
            if(aux > 0.0 && aux <= 0.05) progresso.setImageResource(R.drawable.progress_5);
            if(aux > 0.05 && aux <= 0.1) progresso.setImageResource(R.drawable.progress_10);
            if(aux > 0.1 && aux <= 0.15) progresso.setImageResource(R.drawable.progress_15);
            if(aux > 0.15 && aux <= 0.2) progresso.setImageResource(R.drawable.progress_20);
            if(aux > 0.2 && aux <= 0.25) progresso.setImageResource(R.drawable.progress_25);
            if(aux > 0.25 && aux <= 0.3) progresso.setImageResource(R.drawable.progress_30);
            if(aux > 0.3 && aux <= 0.35) progresso.setImageResource(R.drawable.progress_35);
            if(aux > 0.35 && aux <= 0.4) progresso.setImageResource(R.drawable.progress_40);
            if(aux > 0.4 && aux <= 0.45) progresso.setImageResource(R.drawable.progress_45);
            if(aux > 0.45 && aux <= 0.5) progresso.setImageResource(R.drawable.progress_50);
            if(aux > 0.5 && aux <= 0.55) progresso.setImageResource(R.drawable.progress_55);
            if(aux > 0.55 && aux <= 0.6) progresso.setImageResource(R.drawable.progress_60);
            if(aux > 0.6 && aux <= 0.65) progresso.setImageResource(R.drawable.progress_65);
            if(aux > 0.65 && aux <= 0.7) progresso.setImageResource(R.drawable.progress_70);
            if(aux > 0.7 && aux <= 0.75) progresso.setImageResource(R.drawable.progress_75);
            if(aux > 0.75 && aux <= 0.8) progresso.setImageResource(R.drawable.progress_80);
            if(aux > 0.8 && aux <= 0.85) progresso.setImageResource(R.drawable.progress_85);
            if(aux > 0.85 && aux <= 0.9) progresso.setImageResource(R.drawable.progress_90);
            if(aux > 0.9 && aux <= 0.95) progresso.setImageResource(R.drawable.progress_95);
            if(aux > 0.95) progresso.setImageResource(R.drawable.progress_100);
        }
        if(info.get(8).equals("Mediano")){
            pontuacaoLimite.setText("500");
            aux = Float.parseFloat(info.get(7))/500;
            if(aux == 0.0) progresso.setImageResource(R.drawable.progress_0);
            if(aux > 0.0 && aux <= 0.05) progresso.setImageResource(R.drawable.progress_5);
            if(aux > 0.05 && aux <= 0.1) progresso.setImageResource(R.drawable.progress_10);
            if(aux > 0.1 && aux <= 0.15) progresso.setImageResource(R.drawable.progress_15);
            if(aux > 0.15 && aux <= 0.2) progresso.setImageResource(R.drawable.progress_20);
            if(aux > 0.2 && aux <= 0.25) progresso.setImageResource(R.drawable.progress_25);
            if(aux > 0.25 && aux <= 0.3) progresso.setImageResource(R.drawable.progress_30);
            if(aux > 0.3 && aux <= 0.35) progresso.setImageResource(R.drawable.progress_35);
            if(aux > 0.35 && aux <= 0.4) progresso.setImageResource(R.drawable.progress_40);
            if(aux > 0.4 && aux <= 0.45) progresso.setImageResource(R.drawable.progress_45);
            if(aux > 0.45 && aux <= 0.5) progresso.setImageResource(R.drawable.progress_50);
            if(aux > 0.5 && aux <= 0.55) progresso.setImageResource(R.drawable.progress_55);
            if(aux > 0.55 && aux <= 0.6) progresso.setImageResource(R.drawable.progress_60);
            if(aux > 0.6 && aux <= 0.65) progresso.setImageResource(R.drawable.progress_65);
            if(aux > 0.65 && aux <= 0.7) progresso.setImageResource(R.drawable.progress_70);
            if(aux > 0.7 && aux <= 0.75) progresso.setImageResource(R.drawable.progress_75);
            if(aux > 0.75 && aux <= 0.8) progresso.setImageResource(R.drawable.progress_80);
            if(aux > 0.8 && aux <= 0.85) progresso.setImageResource(R.drawable.progress_85);
            if(aux > 0.85 && aux <= 0.9) progresso.setImageResource(R.drawable.progress_90);
            if(aux > 0.9 && aux <= 0.95) progresso.setImageResource(R.drawable.progress_95);
            if(aux > 0.95) progresso.setImageResource(R.drawable.progress_100);
        }
        if(info.get(8).equals("Experiente")) {
            pontuacaoLimite.setText("750");
            aux = Float.parseFloat(info.get(7))/750;
            if (aux == 0.0) progresso.setImageResource(R.drawable.progress_0);
            if (aux > 0.0 && aux <= 0.05) progresso.setImageResource(R.drawable.progress_5);
            if (aux > 0.05 && aux <= 0.1) progresso.setImageResource(R.drawable.progress_10);
            if (aux > 0.1 && aux <= 0.15) progresso.setImageResource(R.drawable.progress_15);
            if (aux > 0.15 && aux <= 0.2) progresso.setImageResource(R.drawable.progress_20);
            if (aux > 0.2 && aux <= 0.25) progresso.setImageResource(R.drawable.progress_25);
            if (aux > 0.25 && aux <= 0.3) progresso.setImageResource(R.drawable.progress_30);
            if (aux > 0.3 && aux <= 0.35) progresso.setImageResource(R.drawable.progress_35);
            if (aux > 0.35 && aux <= 0.4) progresso.setImageResource(R.drawable.progress_40);
            if (aux > 0.4 && aux <= 0.45) progresso.setImageResource(R.drawable.progress_45);
            if (aux > 0.45 && aux <= 0.5) progresso.setImageResource(R.drawable.progress_50);
            if (aux > 0.5 && aux <= 0.55) progresso.setImageResource(R.drawable.progress_55);
            if (aux > 0.55 && aux <= 0.6) progresso.setImageResource(R.drawable.progress_60);
            if (aux > 0.6 && aux <= 0.65) progresso.setImageResource(R.drawable.progress_65);
            if (aux > 0.65 && aux <= 0.7) progresso.setImageResource(R.drawable.progress_70);
            if (aux > 0.7 && aux <= 0.75) progresso.setImageResource(R.drawable.progress_75);
            if (aux > 0.75 && aux <= 0.8) progresso.setImageResource(R.drawable.progress_80);
            if (aux > 0.8 && aux <= 0.85) progresso.setImageResource(R.drawable.progress_85);
            if (aux > 0.85 && aux <= 0.9) progresso.setImageResource(R.drawable.progress_90);
            if (aux > 0.9 && aux <= 0.95) progresso.setImageResource(R.drawable.progress_95);
            if (aux > 0.95) progresso.setImageResource(R.drawable.progress_100);
        }
        if(info.get(8).equals("Mestre Cucca")) {
            pontuacaoLimite.setText("9999");
            aux = Float.parseFloat(info.get(7))/ 9999;
            if (aux == 0.0) progresso.setImageResource(R.drawable.progress_0);
            if (aux > 0.0 && aux <= 0.05) progresso.setImageResource(R.drawable.progress_5);
            if (aux > 0.05 && aux <= 0.1) progresso.setImageResource(R.drawable.progress_10);
            if (aux > 0.1 && aux <= 0.15) progresso.setImageResource(R.drawable.progress_15);
            if (aux > 0.15 && aux <= 0.2) progresso.setImageResource(R.drawable.progress_20);
            if (aux > 0.2 && aux <= 0.25) progresso.setImageResource(R.drawable.progress_25);
            if (aux > 0.25 && aux <= 0.3) progresso.setImageResource(R.drawable.progress_30);
            if (aux > 0.3 && aux <= 0.35) progresso.setImageResource(R.drawable.progress_35);
            if (aux > 0.35 && aux <= 0.4) progresso.setImageResource(R.drawable.progress_40);
            if (aux > 0.4 && aux <= 0.45) progresso.setImageResource(R.drawable.progress_45);
            if (aux > 0.45 && aux <= 0.5) progresso.setImageResource(R.drawable.progress_50);
            if (aux > 0.5 && aux <= 0.55) progresso.setImageResource(R.drawable.progress_55);
            if (aux > 0.55 && aux <= 0.6) progresso.setImageResource(R.drawable.progress_60);
            if (aux > 0.6 && aux <= 0.65) progresso.setImageResource(R.drawable.progress_65);
            if (aux > 0.65 && aux <= 0.7) progresso.setImageResource(R.drawable.progress_70);
            if (aux > 0.7 && aux <= 0.75) progresso.setImageResource(R.drawable.progress_75);
            if (aux > 0.75 && aux <= 0.8) progresso.setImageResource(R.drawable.progress_80);
            if (aux > 0.8 && aux <= 0.85) progresso.setImageResource(R.drawable.progress_85);
            if (aux > 0.85 && aux <= 0.9) progresso.setImageResource(R.drawable.progress_90);
            if (aux > 0.9 && aux <= 0.95) progresso.setImageResource(R.drawable.progress_95);
            if (aux > 0.95) progresso.setImageResource(R.drawable.progress_100);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perfil_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView restricao = (TextView)findViewById(R.id.textViewModeracao);
        TextView alergia = (TextView)findViewById(R.id.textViewAlergias);
        TextView nivel = (TextView)findViewById(R.id.textViewNivel);
        TextView pontuacaoAtual = (TextView)findViewById(R.id.textViewPontuacaoAtual);
        TextView horasTotal = (TextView)findViewById(R.id.textViewHoras);
        TextView pontuacaoLimite = (TextView)findViewById(R.id.textViewMaiorPontuacao);
        ImageView progresso = (ImageView)findViewById(R.id.imageViewProgress);
        TextView pratosTotal = (TextView)findViewById(R.id.textViewTotalPratos);
        TextView pratosFaceis = (TextView)findViewById(R.id.textViewPratoFacil);
        TextView pratosMedianos = (TextView)findViewById(R.id.textViewPratoMedio);
        TextView pratosDificeis = (TextView)findViewById(R.id.textViewPratoDificil);

        UsuarioDAO u = new UsuarioDAO(this);
        ArrayList<String> info;
        float aux;

        info = u.getInfoUsuario();

        restricao.setText(info.get(0));
        alergia.setText(info.get(1));
        horasTotal.setText(info.get(2));
        pratosTotal.setText(info.get(3));
        pratosFaceis.setText(info.get(4));
        pratosMedianos.setText(info.get(5));
        pratosDificeis.setText(info.get(6));
        pontuacaoAtual.setText(info.get(7));
        nivel.setText(info.get(8));

    }

    public void clicarEditar(){
        Intent i = new Intent(this,PerfilEdit.class);
        startActivity(i);
    }
    public void usarToast(String texto) {
        Toast.makeText(getBaseContext(), texto, Toast.LENGTH_SHORT).show();
    }
}
