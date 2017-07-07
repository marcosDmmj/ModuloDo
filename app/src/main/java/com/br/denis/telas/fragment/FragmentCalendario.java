package com.br.denis.telas.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.classesBasicas.Util;
import com.br.denis.telas.ListEvents;
import com.br.denis.telas.MainActivity;
import com.br.denis.webservice.DownloadDiaAsync;
import com.example.exempleswipetab.R;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;


public class FragmentCalendario extends Fragment {

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fragment_calendario,container, false);
	}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);

        CaldroidFragment mCaldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
		args.putBoolean(CaldroidFragment.ENABLE_SWIPE, false);
		args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);
        mCaldroidFragment.setArguments( args );

		try {
			Map<Date, Drawable> dates = new HashMap<>();
			ColorDrawable colorBackground = new ColorDrawable(Color.parseColor("#FF8C00"));
			ArrayList<Evento> eventosMonth = getActivity().getIntent().getParcelableArrayListExtra("eventosMonth");
			for (int i = 0; i < eventosMonth.size(); i++) {
                dates.put(Util.stringToDateComplete(eventosMonth.get(i).getdata_inicio()), colorBackground);
            }

			mCaldroidFragment.setBackgroundDrawableForDates(dates);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("FragmentCalendario","Deu erro! "+e.getMessage());
		}
		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				Intent intent = new Intent(getActivity(), ListEvents.class);
				try {
					ArrayList<Evento> eventosDoDia = new DownloadDiaAsync(getActivity()).execute(Util.dateToString(date)).get();
					intent.putParcelableArrayListExtra("eventosDoDia",eventosDoDia);
					intent.putExtra("dia",Util.datetoDayAndMonth(date));
					getActivity().startActivity(intent);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Log.e("FragmentCalendario","Erro = "+e.getMessage());
				} catch (ExecutionException e) {
					e.printStackTrace();
					Log.e("FragmentCalendario","Erro = "+e.getMessage());
				}
			}

			@Override
			public void onChangeMonth(int month, int year) {
				//String text = "month: " + month + " year: " + year;
				//Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCaldroidViewCreated() {

			}

		};

		mCaldroidFragment.setCaldroidListener(listener);

        getActivity().getSupportFragmentManager().beginTransaction().replace( R.id.cal , mCaldroidFragment ).commit();

	}

}
