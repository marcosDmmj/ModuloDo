package com.br.denis.classesBasicas;

public class Evento {
	private String titulo;
	private String nome;
	private String data_inicio;
	private String data_fim;
	private String email;

	public Evento(String titulo, String nome, String data_inicio,
				  String data_fim, String email) {
		super();
		this.titulo = titulo;
		this.nome = nome;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.email = email;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getdata_inicio() {
		return data_inicio;
	}

	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}

	public String getdata_fim() {
		return data_fim;
	}

	public void setdata_fim(String data_fim) {
		this.data_fim = data_fim;
	}

	public String getTitulo() {
		return titulo;
	}

	public void settitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
