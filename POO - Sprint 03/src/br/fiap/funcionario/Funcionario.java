package br.fiap.funcionario;

import java.sql.Date;
import java.time.LocalDateTime;

import br.fiap.departamento.Departamento;
import br.fiap.endereco.Endereco;

public class Funcionario {
	
	private int id;
	private int codigo;
	private String nome;
	private String email;
	private int telefone;
	private double salario;
	private LocalDateTime localDateTime = LocalDateTime.now();
	private Date sqlDate = Date.valueOf(localDateTime.toLocalDate());;
	private String sexo;
	private Endereco endereco;
	private Departamento departamento;
	private String nomeDoEndereco;
	private String nomeDoDepartamento;
	
	public Funcionario(int id, int codigo, String nome, String email, int telefone, double salario,
			String sexo, Endereco endereco, Departamento departamento) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.salario = salario;
		this.sexo = sexo;
		this.endereco = endereco;
		this.departamento = departamento;
	}

	public Funcionario(int id, int codigo, String nome, String email, int telefone, double salario, Date sqlDate,
			String sexo, String nomeDoEndereco, String nomeDoDepartamento) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.salario = salario;
		this.sqlDate = sqlDate;
		this.sexo = sexo;
		this.nomeDoEndereco = nomeDoEndereco;
		this.nomeDoDepartamento = nomeDoDepartamento;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public String getNomeDoEndereco() {
		return nomeDoEndereco;
	}

	public void setNomeDoEndereco(String nomeDoEndereco) {
		this.nomeDoEndereco = nomeDoEndereco;
	}

	public String getNomeDoDepartamento() {
		return nomeDoDepartamento;
	}

	public void setNomeDoDepartamento(String nomeDoDepartamento) {
		this.nomeDoDepartamento = nomeDoDepartamento;
	}

	public Date getsqlDate() {
		return sqlDate;
	}
	
}
