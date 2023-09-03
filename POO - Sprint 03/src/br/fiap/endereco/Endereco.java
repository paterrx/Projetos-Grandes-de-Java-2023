package br.fiap.endereco;

import br.fiap.cidade.Cidade;

public class Endereco {
	
	private int id_endereco;
	private String nome_endereco;
	private Cidade cidade;
	private int id_cidade;
	
	public Endereco(int id_endereco, String nome_endereco, Cidade cidade) {
		this.id_endereco = id_endereco;
		this.nome_endereco = nome_endereco;
		this.cidade = cidade;
	}
	
	public Endereco(int id_endereco, String nome_endereco, int id_cidade) {
		this.id_endereco = id_endereco;
		this.nome_endereco = nome_endereco;
		this.id_cidade = id_cidade;
	}
	
	public Endereco(int id_endereco, Cidade cidade) {
		this.id_endereco = id_endereco;
		this.cidade = cidade;
	}

	public int getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(int id_endereco) {
		this.id_endereco = id_endereco;
	}

	public String getNome_endereco() {
		return nome_endereco;
	}

	public void setNome_endereco(String nome_endereco) {
		this.nome_endereco = nome_endereco;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public int getId_cidade() {
		return id_cidade;
	}

	public void setId_cidade(int id_cidade) {
		this.id_cidade = id_cidade;
	}
	
}
