package com.br.ksg.adapters;

import com.br.ksg.telas.fragment.FragmentCategorias;
import com.br.ksg.telas.fragment.FragmentFavoritos;
import com.br.ksg.telas.fragment.FragmentSugestao;
import com.br.ksg.telas.fragment.FragmentSurvivalMode;

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
			return new FragmentSugestao();
		}
		if(arg0==1){
			return new FragmentCategorias();
		}
		if(arg0==2){
			return new FragmentSurvivalMode();
		}
		if(arg0==3){
			return new FragmentFavoritos();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 4;
	}	
}