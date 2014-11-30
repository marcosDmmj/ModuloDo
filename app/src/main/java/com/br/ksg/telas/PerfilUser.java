package com.br.ksg.telas;

import com.example.exempleswipetab.R;
import com.br.ksg.telas.PerfilEdit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class PerfilUser extends Activity {
    ImageButton btn_editar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);

        // TOast eexempocddf
        usarToast("Por enquanto vai dar erro se você clicar ali :P");
        btn_editar = (ImageButton) findViewById(R.id.imageButton2);
        btn_editar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clicarEditar();
            }
        });
    }
    public void clicarEditar(){
        usarToast("entrou no clicarEditar()");
        Intent i = new Intent(this,PerfilEdit.class);
        startActivity(i);
    }
    public void usarToast(String texto) {
        Toast.makeText(getBaseContext(), texto, Toast.LENGTH_SHORT).show();
    }
}
