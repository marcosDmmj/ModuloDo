package com.br.ksg.telas;

import android.app.Activity;
import android.os.Bundle;
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

        UsuarioDAO u = new UsuarioDAO(this);
        String[] aux = u.getRestricoes();

        if(aux[0].equals("sim")) ((Switch)findViewById(R.id.switchVegetariano)).setChecked(true);
        if(aux[1].equals("sim")) ((Switch)findViewById(R.id.switchDiabetico)).setChecked(true);
        if(aux[2].equals("sim")) ((Switch)findViewById(R.id.switchHipertenso)).setChecked(true);
        if(aux[3].equals("sim")) ((CheckBox)findViewById(R.id.checkBoxFrutos)).setChecked(true);
        if(aux[4].equals("sim")) ((CheckBox)findViewById(R.id.checkBoxLeite)).setChecked(true);
        if(aux[5].equals("sim")) ((CheckBox)findViewById(R.id.checkBoxOvos)).setChecked(true);
        if(aux[6].equals("sim")) ((CheckBox)findViewById(R.id.checkBoxSoja)).setChecked(true);
        if(aux[7].equals("sim")) ((CheckBox)findViewById(R.id.checkBoxAmendoim)).setChecked(true);
        if(aux[8].equals("sim")) ((CheckBox)findViewById(R.id.checkBoxNozes)).setChecked(true);
        if(aux[9].equals("sim")) ((CheckBox)findViewById(R.id.checkBoxPeixes)).setChecked(true);
        if(aux[10].equals("sim")) ((CheckBox)findViewById(R.id.checkBoxTrigo)).setChecked(true);

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

        String str1;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12 = "";
        String str13 = "" ;
        if(status1==1){ str1 = "sim"; str12 += "Vegetariano  "; }else { str1 = "nao"; str12 += ""; }
        if(status2==1){ str2 = "sim"; str12 += "Diabético  "; }else { str2 = "nao"; str12 += ""; }
        if(status3==1){ str3 = "sim"; str12 += "Hipertenso  "; }else { str3 = "nao"; str12 += ""; }
        if(status4==1){ str4 = "sim"; str13 += "Frutos do Mar  "; }else { str4 = "nao"; str13 += ""; }
        if(status5==1){ str5 = "sim"; str13 += "Amendoim  "; }else { str5 = "nao"; str13 += ""; }
        if(status6==1){ str6 = "sim"; str13 += "Leite  "; }else { str6 = "nao"; str13 += ""; }
        if(status7==1){ str7 = "sim"; str13 += "Nozes  "; }else { str7 = "nao"; str13 += ""; }
        if(status8==1){ str8 = "sim"; str13 += "Ovos  "; }else { str8 = "nao"; str13 += ""; }
        if(status9==1){ str9 = "sim"; str13 += "Peixes  "; }else { str9 = "nao"; str13 += ""; }
        if(status10==1){ str10 = "sim"; str13 += "Soja  "; }else { str10 = "nao"; str13 += ""; }
        if(status11==1){ str11 = "sim"; str13 += "Trigo  "; }else { str11 = "nao"; str13 += ""; }
        if(str12.equals("")) str12 = "Nenhuma";
        if(str13.equals("")) str13 = "Nenhuma";

        u.setUsuarioCaracteristicas(str1, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13);

        finish();
    }
}
