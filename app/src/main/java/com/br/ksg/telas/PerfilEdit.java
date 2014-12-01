package com.br.ksg.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

import com.br.ksg.classesDAO.UsuarioDAO;
import com.example.exempleswipetab.R;

public class PerfilEdit extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_edit);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clicarSalvar(View view){



        UsuarioDAO u = new UsuarioDAO(this);
        int status1;
        int status2;
        int status3;
        int status4;
        int status5;
        int status6;
        int status7;
        int status8;
        int status9;
        int status10;
        int status11;

        if(((Switch)findViewById(R.id.switchVegetariano)).isChecked()) status1=1; else status1=0;
        if(((Switch)findViewById(R.id.switchDiabetico)).isChecked()) status2=1; else status2=0;
        if(((Switch)findViewById(R.id.switchHipertenso)).isChecked()) status3=1; else status3=0;
        if(((CheckBox)findViewById(R.id.checkBoxFrutos)).isChecked()) status4=1; else status4=0;
        if(((CheckBox)findViewById(R.id.checkBoxAmendoim)).isChecked()) status5=1; else status5=0;
        if(((CheckBox)findViewById(R.id.checkBoxLeite)).isChecked()) status6=1; else status6=0;
        if(((CheckBox)findViewById(R.id.checkBoxNozes)).isChecked()) status7=1; else status7=0;
        if(((CheckBox)findViewById(R.id.checkBoxOvos)).isChecked()) status8=1; else status8=0;
        if(((CheckBox)findViewById(R.id.checkBoxPeixes)).isChecked()) status9=1; else status9=0;
        if(((CheckBox)findViewById(R.id.checkBoxSoja)).isChecked()) status10=1; else status10=0;
        if(((CheckBox)findViewById(R.id.checkBoxTrigo)).isChecked()) status11=1; else status11=0;

        u.setUsuario(status1, status2, status3, status4, status5, status6, status7, status8, status9, status10, status11);

        Intent intent = new Intent(this, PerfilUser.class);
        startActivity(intent);
    }
}
