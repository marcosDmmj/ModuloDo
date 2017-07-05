package com.br.denis.telas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.exempleswipetab.R;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Date;


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
		args.putBoolean(CaldroidFragment.ENABLE_SWIPE, false);
		args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);
        mCaldroidFragment.setArguments( args );

		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				Toast.makeText(getActivity().getApplicationContext(), "Clicou numa data em especifico! "+date,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onChangeMonth(int month, int year) {
				String text = "month: " + month + " year: " + year;
				Toast.makeText(getActivity().getApplicationContext(), text,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCaldroidViewCreated() {

			}

		};

		mCaldroidFragment.setCaldroidListener(listener);

        getActivity().getSupportFragmentManager().beginTransaction().replace( R.id.cal , mCaldroidFragment ).commit();

	}

}
