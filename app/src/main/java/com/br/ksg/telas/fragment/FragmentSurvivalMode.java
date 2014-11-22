package com.br.ksg.telas.fragment;

import java.util.ArrayList;

import com.br.ksg.classesDAO.IngredienteDAO;
import com.br.ksg.telas.listas.ListSurvivalMode;
import com.example.exempleswipetab.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
	private ArrayList<String> listaAutoComplete ;
	private ArrayList<String> listaElementos ;
	private ListView listView ;
	private Button buttonPlus,buttonSearch ;
	private ArrayAdapter<String> adapter,adapterListView;
	
	private int pos;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
			if((autoComplete.getText().toString().equals(""))){
				return;
			}
			else{
				listaElementos.add(autoComplete.getText().toString());
				adapterListView = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listaElementos);
				listView = (ListView) getActivity().findViewById(R.id.listView1);
				listView.setAdapter(adapterListView);
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
						pos = position ;
						AlertDialog.Builder bld = new AlertDialog.Builder(getActivity());
						bld.setMessage("Deseja excluir este item?");
						bld.setTitle("Aviso");
						
						bld.setPositiveButton("Sim",new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								adapterListView.remove(listaElementos.get(pos));
								//listaElementos.remove(pos);
								adapterListView.notifyDataSetChanged();
								listView.setAdapter(adapterListView);
								Toast toast = Toast.makeText(getActivity(), "Excluiu", Toast.LENGTH_SHORT);
								toast.show();
							}
						});
						bld.setNegativeButton(getString(R.string.nao),new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								return ;							}
						});
						bld.show();
					}
				});
				autoComplete.setText("");
			}
			}
		});												
		
		buttonSearch = (Button) getActivity().findViewById(R.id.buttonPesquisar);
		buttonSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listaElementos.isEmpty()){
					return ;
				}
				else{
					Intent intent = new Intent(getActivity(),ListSurvivalMode.class);
					intent.putStringArrayListExtra("listaIngredientes",listaElementos);
					startActivity(intent);
				}
			}
		});
		
		
	}

    public ArrayList<String> listaCheia(){
        IngredienteDAO ingDAO = new IngredienteDAO(getActivity());
        return ingDAO.getListaIngredientes();

    }

}
