package com.br.denis.telas;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.telas.adapters.OrganizedAdapter;
import com.example.exempleswipetab.R;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements TabListener, OnPageChangeListener {

	ViewPager viewPager;
	ActionBar actionBar;
	private ProgressDialog dialog;
	public Integer status;
	private static final String PREF_ACCOUNT_NAME = "accountName";
	
	@Override
	protected void onCreate(Bundle e) {
		super.onCreate(e);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.pager);

		viewPager.setAdapter(new OrganizedAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(this);
		
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab tab2 = actionBar.newTab();
		tab2.setText("Calendário");
		tab2.setIcon(android.R.drawable.ic_menu_my_calendar);
		tab2.setTabListener(this);
		
		ActionBar.Tab tab3 = actionBar.newTab();
		tab3.setText("Próximos eventos");
		tab3.setTabListener(this);
		
		ActionBar.Tab tab4 = actionBar.newTab();
		tab4.setText("Solicitações pendentes");
		tab4.setTabListener(this);
		
		//actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
		actionBar.addTab(tab4);

		Intent intent = getIntent();
		status = intent.getIntExtra("status",-1);
    }

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		actionBar.setSelectedNavigationItem(arg0);
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main, menu);
		MenuItem item;
		if (status == 0) {
			item = menu.findItem(R.id.menu1);
		} else {
			item = menu.findItem (R.id.menu2);
		}
		item.setChecked(true);

		// Se tiver logado no Google não precisa mostrar pra logar!
		String accountName = getPreferences(Context.MODE_PRIVATE)
				.getString(PREF_ACCOUNT_NAME, null);
		item = menu.findItem(R.id.menu_google);
		item.setVisible(accountName == null);

        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
			case R.id.menu_google:
				Toast.makeText(this, "Falta implementar a conexão com o Google", Toast.LENGTH_SHORT).show();
				// TODO implementar a conexãoc om o Google Agenda!
				return true;
			case R.id.menu1:
				new setaStatusAsync(this).execute("http://ufam-automation.net/marcosmoura/setStatus.php?Status=0");
				if (item.isChecked()) item.setChecked(false);
				else item.setChecked(true);
				return true;
			case R.id.menu2:
				new setaStatusAsync(this).execute("http://ufam-automation.net/marcosmoura/setStatus.php?Status=1");
				if (item.isChecked()) item.setChecked(false);
				else item.setChecked(true);
				return true;
			case R.id.menu_atualizar:
				Intent intent = new Intent(this, SplashActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();

		}
        return super.onOptionsItemSelected(item);
    }

	private class setaStatusAsync extends AsyncTask<String, Void, Void> {
		Context c;
		HttpURLConnection urlConnection;

		public setaStatusAsync(Context c) {
			this.c = c;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(c, "Aguarde", "Atualizando status, Por favor aguarde!");
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			dialog.dismiss();
			Toast.makeText(c, "Status alterado!", Toast.LENGTH_SHORT).show();
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
			urlConnection.disconnect();
			Log.d("setaStatusAsync","Cancelou o bagulho?");
			dialog.cancel();
		}

		@Override
		protected Void doInBackground(String... strings) {

			try {
				URL url;
				url = new URL(strings[0]);

				urlConnection = (HttpURLConnection) url.openConnection();


				int responseCode = urlConnection.getResponseCode();

				if (responseCode == HttpURLConnection.HTTP_OK) {
					InputStream in = urlConnection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder result = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null) {
						result.append(line);
					}
					status = Integer.parseInt(result.toString());
					Log.d("setaStatusAsync","Pegou o resultado de boas! "+status);
				} else {
					Log.d("setaStatusAsync","A conexão não tá OK! code = "+responseCode);
				}
			}catch (Exception e) {
				Log.e("MainActivity","Erro Status.txt= "+e.getMessage());
			}

			return null;
		}
	}
}

