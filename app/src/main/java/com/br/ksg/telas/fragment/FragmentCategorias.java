package com.br.ksg.telas.fragment;


import com.br.ksg.webService.DownloadReceitaPorCategoria;
import com.example.exempleswipetab.R;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class FragmentCategorias extends Fragment {
	ImageView img01, img02, img03, img04, img05, img06, img07, img08, img09, img10, img11, img12;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_fragment_categorias,container, false);
	}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);
		
		img01 = (ImageView) getActivity().findViewById(R.id.tx1);
		img02 = (ImageView) getActivity().findViewById(R.id.tx2);
		img03 = (ImageView) getActivity().findViewById(R.id.tx3);
		img04 = (ImageView) getActivity().findViewById(R.id.tx4);
		img05 = (ImageView) getActivity().findViewById(R.id.tx5);
		img06 = (ImageView) getActivity().findViewById(R.id.tx6);
		img07 = (ImageView) getActivity().findViewById(R.id.tx7);
		img08 = (ImageView) getActivity().findViewById(R.id.tx8);
		img09 = (ImageView) getActivity().findViewById(R.id.tx9);
		img10 = (ImageView) getActivity().findViewById(R.id.tx10);
		img11 = (ImageView) getActivity().findViewById(R.id.tx11);
		img12 = (ImageView) getActivity().findViewById(R.id.tx12);
		
		img01.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"carne").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=carne");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});
		
		img02.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"ave").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=ave");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});
		
		img03.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"peixe").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=peixe");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});

		img04.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"refeicoes").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=refei");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});

		img05.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"salada").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=salada");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});

		img06.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"massa").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=massa");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});

		img07.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"acompanhamento").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=acompanhamento");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});

		img08.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"aperitivo").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=aperitivo");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});

		img09.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"cafe da manha").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=caf");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});
		
		img10.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {
					try {
						new DownloadReceitaPorCategoria(getActivity(),"lanche")
								.execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=lanche");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});
		
		img11.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"doce").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=doce");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});

		img12.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if (verificaConexao()) {					
					try {
						new DownloadReceitaPorCategoria(getActivity(),"salgado").execute("http://ksmapi.besaba.com/sql/selectRecByCat.php?id=salgado");
					} catch (Exception e) {
					}
				} else {
					usarToast(getString(R.string.verifica_conexao));
				}
			}
		});

	}
	
	public boolean verificaConexao() {
		ConnectivityManager connectivity = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

			if (netInfo == null) {
				return false;
			}

			int netType = netInfo.getType();

			if (netType == ConnectivityManager.TYPE_WIFI
					|| netType == ConnectivityManager.TYPE_MOBILE) {
				return netInfo.isConnected();

			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void usarToast(String texto) {
		Toast.makeText(getActivity(), texto, Toast.LENGTH_LONG).show();
	}
}
