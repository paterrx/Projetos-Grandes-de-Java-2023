package br.fiap.endereco;

import br.fiap.cidade.Cidade;

public class Endereco {
	
	private int id_endereco;
	private Cidade cidade;
	
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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
}
