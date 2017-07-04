package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exempleswipetab.R;
import com.roomorama.caldroid.CaldroidFragment;


public class FragmentCalendario extends Fragment {
	int mHour;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fragment_calendario,container, false);
	}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);

        CaldroidFragment mCaldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        args.putInt( CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY );
        mCaldroidFragment.setArguments( args );
        getActivity().getSupportFragmentManager().beginTransaction().replace( R.id.cal , mCaldroidFragment ).commit();

	}

}
