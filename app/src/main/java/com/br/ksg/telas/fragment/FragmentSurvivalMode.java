package com.br.ksg.telas.fragment;

import java.util.ArrayList;

import com.br.ksg.classesBasicas.Ingrediente;
import com.br.ksg.classesDAO.IngredienteDAO;
import com.br.ksg.webService.DownloadReceitaPorSurvivalMode;
import com.example.exempleswipetab.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class FragmentSurvivalMode extends Fragment {

	private AutoCompleteTextView autoComplete ;
    private ListView listView ;
	ArrayList<String> listaAutoComplete ;
	ArrayList<String> listaElementos ;
	Button buttonPlus,buttonSearch ;
	ArrayAdapter<String> adapter,adapterListView;
	
	private int pos;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fragment_survivalmode,container, false);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
	}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);
		listaAutoComplete = new ArrayList<String>();
		listaElementos = new ArrayList<String>();

        listaAutoComplete = listaCheia();
		
		adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,listaAutoComplete);
		autoComplete = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompleteTextView1); 
		autoComplete.setAdapter(adapter);
		
		buttonPlus = (Button) getActivity().findViewById(R.id.buttonAdiciona);		
		
		
		buttonPlus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                if ((listaElementos.size() < 5)) {
			    if ((!(autoComplete.getText().toString().equals("")))&&(!listaElementos.contains(autoComplete.getText().toString().toLowerCase()))) {
                IngredienteDAO ingredienteDAO = new IngredienteDAO(getActivity());
                Ingrediente ingrediente = ingredienteDAO.getIngrediente(autoComplete.getText().toString().toLowerCase());

                if (ingrediente != null) {
                    listaElementos.add(autoComplete.getText().toString());
                    adapterListView = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaElementos);
                    listView = (ListView) getActivity().findViewById(R.id.listView1);
                    listView.setAdapter(adapterListView);
                    listView.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            pos = position;
                            AlertDialog.Builder bld = new AlertDialog.Builder(getActivity());
                            bld.setMessage("Deseja excluir este item?");
                            bld.setTitle("Aviso");

                            bld.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    adapterListView.remove(listaElementos.get(pos));
                                    adapterListView.notifyDataSetChanged();
                                    listView.setAdapter(adapterListView);
                                    Toast toast = Toast.makeText(getActivity(), "Item removido!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            });
                            bld.setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            bld.show();
                        }
                    });
                    autoComplete.setText("");
                } else {
                    usarToast("Ingrediente não cadastrado!");
                }
			    }
                } else {
                    usarToast("Limite máximo de 5 ingredientes!");
                }
			}
		});												
		
		buttonSearch = (Button) getActivity().findViewById(R.id.buttonPesquisar);
		buttonSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!listaElementos.isEmpty()){
                    if (verificaConexao()) {
                        try {

                            new DownloadReceitaPorSurvivalMode(getActivity()).execute("http://ksmapi.besaba.com/sql/selectRecByIng.php?id=10");
                        } catch (Exception e) {
                            usarToast("Erro: "+e.getMessage());
                        }
                    } else {
                        usarToast(getString(R.string.verifica_conexao));
                    }
				} else {
                    usarToast("Adicione pelo menos 1 ingrediente!");
                }
			}
		});
		
		
	}

    public ArrayList<String> listaCheia(){
        IngredienteDAO ingDAO = new IngredienteDAO(getActivity());
        return ingDAO.getListaIngredientes();

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
