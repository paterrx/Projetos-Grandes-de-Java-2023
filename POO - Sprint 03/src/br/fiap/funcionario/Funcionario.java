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
	private long telefone;
	private double salario;
	private LocalDateTime localDateTime = LocalDateTime.now();
	private Date sqlDate = Date.valueOf(localDateTime.toLocalDate());;
	private String sexo;
	private Endereco endereco;
	private Departamento departamento;
	private int idDoEndereco;
	private int idDoDepartamento;
	
	public Funcionario(int id, int codigo, String nome, String email, long telefone, double salario,
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

	public Funcionario(int id, int codigo, String nome, String email, long telefone, double salario, Date sqlDate,
			String sexo, int idDoEndereco, int idDoDepartamento) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.salario = salario;
		this.sqlDate = sqlDate;
		this.sexo = sexo;
		this.idDoEndereco = idDoEndereco;
		this.idDoDepartamento = idDoDepartamento;
	}

	public Funcionario(int id, String nome, double salario) {
		this.id = id;
		this.nome = nome;
		this.salario = salario;
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

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
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

	public int getIdDoEndereco() {
		return idDoEndereco;
	}

	public void setIdDoEndereco(int idDoEndereco) {
		this.idDoEndereco = idDoEndereco;
	}

	public int getIdDoDepartamento() {
		return idDoDepartamento;
	}

	public void setIdDoDepartamento(int idDoDepartamento) {
		this.idDoDepartamento = idDoDepartamento;
	}

	public Date getsqlDate() {
		return sqlDate;
	}
	
}
