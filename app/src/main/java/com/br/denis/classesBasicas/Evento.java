package com.br.denis.classesBasicas;

import android.os.Parcel;
import android.os.Parcelable;

public class Evento implements Parcelable{
	private String titulo;
	private String nome;
	private String data_inicio;
	private String data_fim;
	private String email;

	public Evento(String titulo, String email, String nome, String data_inicio,
				  String data_fim) {
		super();
		this.titulo = titulo;
		this.nome = nome;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.email = email;
	}


	protected Evento(Parcel in) {
		titulo = in.readString();
		nome = in.readString();
		data_inicio = in.readString();
		data_fim = in.readString();
		email = in.readString();
	}

	public static final Creator<Evento> CREATOR = new Creator<Evento>() {
		@Override
		public Evento createFromParcel(Parcel in) {
			return new Evento(in);
		}

		@Override
		public Evento[] newArray(int size) {
			return new Evento[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(titulo);
		parcel.writeString(nome);
		parcel.writeString(data_fim);
		parcel.writeString(data_inicio);
		parcel.writeString(email);
	}
}
