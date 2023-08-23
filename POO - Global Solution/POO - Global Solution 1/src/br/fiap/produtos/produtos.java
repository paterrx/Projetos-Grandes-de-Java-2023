package br.fiap.produtos;

public class produtos {
	private int id_produto;
	private String nome;
	private double peso;
	
	public produtos(int id_produto, String nome, double peso) {
		this.id_produto = id_produto;
		this.nome = nome;
		this.peso = peso;
	}

	public int getId_produto() {
		return id_produto;
	}

	public String getNome() {
		return nome;
	}

	public double getPeso() {
		return peso;
	}
	
	@Override
	public String toString() {
		String aux = "";
		aux += "ID do Produto: " + getId_produto() + "\n";
		aux += "Nome do produto: " + getNome() + "\n";
		aux += "Peso do Produto: " + getPeso() + " kg \n";
		return aux;
	}
	
}
