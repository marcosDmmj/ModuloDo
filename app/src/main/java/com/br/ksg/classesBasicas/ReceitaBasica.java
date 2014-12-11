package com.br.ksg.classesBasicas;

import android.graphics.drawable.Drawable;

public class ReceitaBasica {
	private String id_receita;
	private String nome;
	private String tempo;
	private String porcoes;
    private Drawable img;

	public ReceitaBasica(String title, String time, String qtd){
		this.setNome(title);
		this.setTempo(time);
		this.setPorcoes(qtd);
        img = null;
	}

	public ReceitaBasica(String id_receita, String nome, String tempo,
			String porcoes) {
		super();
		this.id_receita = id_receita;
		this.nome = nome;
		this.tempo = tempo;
		this.porcoes = porcoes;
        // TODO: Continuar apartir daqui!!!
        img = null;
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

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }
}
