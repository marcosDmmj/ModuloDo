package com.br.ksg.classesBasicas;

/*
 * Created by Marcos and Josias on 21/11/2014.
 */

public class Ingrediente {

    private int id_ingrediente ;
    private String nome ;
    private String quantidade ;


    public Ingrediente(){
    }

    public Ingrediente(int id_ingrediente,String nome ,String quantidade){
        this.id_ingrediente = id_ingrediente;
        this.nome = nome ;
        this.quantidade = quantidade ;
    }

    public int getId_ingrediente() {
        return id_ingrediente;
    }
    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}

