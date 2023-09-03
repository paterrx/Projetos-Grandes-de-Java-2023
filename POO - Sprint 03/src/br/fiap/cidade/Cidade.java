package br.fiap.cidade;

public class Cidade {
	
	private int id;
	private String nome;
	
	public Cidade(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Cidade (int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
