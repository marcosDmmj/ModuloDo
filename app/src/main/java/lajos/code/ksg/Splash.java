package lajos.code.ksg;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.br.ksg.classesDAO.IngredienteDAO;
import com.br.ksg.telas.MainActivity;
import com.br.ksg.webService.DownloadAtualizaIng;
import com.example.exempleswipetab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class Splash extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread = new Thread() {

            public void run() {
                try {
                    if (verificaConexao()) {
                        try {
                            new DownloadAtualizaIng(getApplication()).execute("http://ksmapi.besaba.com/sql/selectIngW.php?id=" + IngredienteDAO.sizeBD());
                        } catch (Exception e) {
                            Log.i("KSG", "Deu um erro ! AtualizaIng" + e.getMessage());
                        }
                        sleep(1000);
                        startActivity(new Intent(getApplicationContext(),
                                MainFacebook.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    } else {
                        Toast.makeText(getApplicationContext(),getString(R.string.verifica_conexao),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                } catch (Exception e) {
                    // e.printStackTrace();
                    startActivity(new Intent(getApplicationContext(),
                            MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }

        };
        thread.start();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    public boolean verificaConexao() {
        ConnectivityManager connectivity = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

            return (netInfo != null) && (netInfo.isConnected());
        } else {
            return false;
        }
    }

}
