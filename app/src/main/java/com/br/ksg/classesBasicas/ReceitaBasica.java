package com.br.ksg.classesBasicas;

public class ReceitaBasica {
	private String id_receita;
	private String nome;
	private String tempo;
	private String porcoes;	
	
	public ReceitaBasica(String title, String time, String qtd){
		this.setNome(title);
		this.setTempo(time);
		this.setPorcoes(qtd);		
	}	

	public ReceitaBasica(String id_receita, String nome, String tempo,
			String porcoes) {
		super();
		this.id_receita = id_receita;
		this.nome = nome;
		this.tempo = tempo;
		this.porcoes = porcoes;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public String getPorcoes() {
		return porcoes;
	}

	public void setPorcoes(String porcoes) {
		this.porcoes = porcoes;
	}

	public String getId_receita() {
		return id_receita;
	}

	public void setId_receita(String id_receita) {
		this.id_receita = id_receita;
	}
}
