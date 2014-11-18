package com.br.ksg.telas;

import com.br.ksg.adapters.OrganizedAdapter;
import com.example.exempleswipetab.R;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

// lol eu consigo de novo??? De novo >.< sasahbsdahbasdkhbasdads
public class MainActivity extends FragmentActivity implements TabListener, OnPageChangeListener {

	ViewPager viewPager;
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle e) {
		super.onCreate(e);
		setContentView(R.layout.activity_main);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		
		viewPager.setAdapter(new OrganizedAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(this);
		
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
						
				
		ActionBar.Tab tab1 = actionBar.newTab();
		tab1.setText("SUGEST�ES");
		tab1.setTabListener(this);
		
		ActionBar.Tab tab2 = actionBar.newTab();
		tab2.setText("CATEGORIAS");
		tab2.setTabListener(this);
		
		ActionBar.Tab tab3 = actionBar.newTab();
		tab3.setText("SURVIVAL MODE");
		tab3.setTabListener(this);
		
		ActionBar.Tab tab4 = actionBar.newTab();
		tab4.setText("FAVORITOS");
		tab4.setTabListener(this);
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
		actionBar.addTab(tab4);
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
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
        case R.id.perfil:
        		Intent i = new Intent(this,PerfilUser.class);
    			startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
	public void usarToast(String texto) {
		Toast.makeText(getBaseContext(), texto, Toast.LENGTH_LONG).show();
	}
	
	public void verReceita(View v) {
		usarToast("Ainda n�o implementado! :'(");
		
		// Ser� usado em conjunto com a SS
		// Intent intent = new Intent(this,ReceitaActivity.class);
		// intent.putExtra("titulo", "receita inicial");
		// startActivity(intent);
	}

}

