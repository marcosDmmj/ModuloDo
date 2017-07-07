package com.br.denis.telas.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.br.denis.webservice.CarregaDadosAsync;
import com.example.exempleswipetab.R;

public class SplashActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CarregaDadosAsync(this,MainActivity.class).execute();
    }

}
