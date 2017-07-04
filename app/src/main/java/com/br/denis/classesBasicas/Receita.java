package com.br.denis.classesBasicas;

import java.util.List;

public class Receita extends ReceitaBasica {
	private String modo_preparo;
	private double rating;
	private String categoria;
	private String dificuldade;
	private List<String> ingredientes;

    public Receita(String title, String time, String qtd, String modo_preparo,
                   double rating, String categoria, String dificuldade,
                   List<String> ingredientes) {
        super(title, time, qtd);
        this.modo_preparo = modo_preparo;
        this.rating = rating;
        this.categoria = categoria;
        this.dificuldade = dificuldade;
        this.ingredientes = ingredientes;
    }

    public Receita(String id_receita, String nome, String tempo,String porcoes, String modo_preparo,
                   double rating, String categoria, String dificuldade) {

        super(id_receita, nome, tempo,porcoes);
        this.modo_preparo = modo_preparo;
        this.rating = rating;
        this.categoria = categoria;
        this.dificuldade = dificuldade;

    }

	public String getModo_preparo() {
		return modo_preparo;
	}

	public void setModo_preparo(String modo_preparo) {
		this.modo_preparo = modo_preparo;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}

	public List<String> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<String> ingredientes) {
		this.ingredientes = ingredientes;
	}
}
