package com.br.denis.telas.adapters;

import com.br.denis.telas.fragment.FragmentEventosPendentes;
import com.br.denis.telas.fragment.FragmentCalendario;
import com.br.denis.telas.fragment.FragmentNextEvents;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class OrganizedAdapter extends FragmentPagerAdapter{

	public OrganizedAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		if(arg0==0){
			return new FragmentCalendario();
		}
		if(arg0==1){
			return new FragmentNextEvents();
		}
		if(arg0==2){
			return new FragmentEventosPendentes();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}	
}