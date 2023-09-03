package br.fiap.departamento;

public class Departamento {

	private int id;
	private int codigo;
	private String nome;
	private int qtd_funcionarios;
	
	public Departamento(int id, int codigo, String nome, int qtd_funcionarios) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.qtd_funcionarios = qtd_funcionarios;
	}

	public Departamento(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtd_funcionarios() {
		return qtd_funcionarios;
	}

	public void setQtd_funcionarios(int qtd_funcionarios) {
		this.qtd_funcionarios = qtd_funcionarios;
	}
	
}
