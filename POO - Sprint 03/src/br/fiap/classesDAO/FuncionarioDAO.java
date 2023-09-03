package br.fiap.classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.fiap.funcionario.Funcionario;
import br.fiap.conexao.Conexao;

public class FuncionarioDAO {
	
	private PreparedStatement ps;
	private String sql;
	private Conexao conexao;
	private ResultSet rs;
	
	public FuncionarioDAO() {
		conexao = new Conexao();
	}

	public void inserir(Funcionario funcionario) {
		sql = "insert into funcionario values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, funcionario.getId());
			ps.setInt(2, funcionario.getCodigo());
			ps.setString(3, funcionario.getNome());
			ps.setString(4, funcionario.getEmail());
			ps.setLong(5, funcionario.getTelefone());
			ps.setDouble(6, funcionario.getSalario());
			ps.setDate(7, funcionario.getsqlDate());
			ps.setString(8, funcionario.getSexo());
			ps.setInt(9, funcionario.getEndereco().getId_endereco());
			ps.setInt(10, funcionario.getDepartamento().getId());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*private int id;
	private int codigo;
	private String nome;
	private String email;
	private int telefone;
	private double salario;
	private Date sqlDate;
	private String sexo;
	private Endereco endereco;
	private Departamento departamento;*/
	
	public String pesquisar(int id) {
		String aux = null;
		sql = "select * from funcionario where id = ?";

		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				aux = "";
				aux += "ID: " 		+ rs.getInt("ID") + "\n";
				aux += "Codigo: " 	+ rs.getInt("CODIGO") + "\n";
				aux += "Nome: " 	+ rs.getString("NOME") + "\n";
				aux += "Email: " 	+ rs.getString("EMAIL") + "\n";
				aux += "Telefone: " + rs.getLong("TELEFONE") + "\n";
				aux += "Salario: " 	+ rs.getDouble("SALARIO") + "\n";
				aux += "Data da CTT: " 	+ rs.getDate("DATA_CONTRATACAO") + "\n";
				aux += "Sexo: " 	+ rs.getString("SEXO") + "\n";
				aux += "ID do End: " 	+ rs.getInt("ENDERECO") + "\n";
				aux += "ID do Dep: " 	+ rs.getInt("DEPARTAMENTO") + "\n";
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar empregado\n + e");
			e.printStackTrace();
		}
		
		return aux;
	}
	
	public String pesquisarCodigo(int codigo) {
		String aux = null;
		sql = "select * from funcionario where codigo = ?";

		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, codigo);
			rs = ps.executeQuery();
			if(rs.next()) {
				aux = "";
				aux += "ID: " 		+ rs.getInt("ID") + "\n";
				aux += "Codigo: " 	+ rs.getInt("CODIGO") + "\n";
				aux += "Nome: " 	+ rs.getString("NOME") + "\n";
				aux += "Email: " 	+ rs.getString("EMAIL") + "\n";
				aux += "Telefone: " + rs.getLong("TELEFONE") + "\n";
				aux += "Salario: " 	+ rs.getDouble("SALARIO") + "\n";
				aux += "Data da CTT: " 	+ rs.getDate("DATA_CONTRATACAO") + "\n";
				aux += "Sexo: " 	+ rs.getString("SEXO") + "\n";
				aux += "ID do End: " 	+ rs.getInt("ENDERECO") + "\n";
				aux += "ID do Dep: " 	+ rs.getInt("DEPARTAMENTO") + "\n";
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar empregado\n + e");
			e.printStackTrace();
		}
		
		return aux;
	}
	
	public List<Funcionario> listar () {
		List<Funcionario> lista = new LinkedList<>();
		sql = "select * from funcionario";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next() == true) {
				lista.add(new Funcionario(rs.getInt("ID"), rs.getInt("CODIGO"), rs.getString("NOME"), rs.getString("EMAIL"), 
				rs.getLong("TELEFONE"), rs.getDouble("SALARIO"), rs.getDate("DATA_CONTRATACAO"), rs.getString("SEXO"), 
				rs.getInt("ENDERECO"), rs.getInt("DEPARTAMENTO")));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return lista;
	}
	
	public void excluir(int id) {
		sql = "delete from funcionario where id = ?";
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar empregado\n + e");
		}
	}
	
	public void atualizar(Funcionario funcionario) {
		sql = "update funcionario set nome = ?, salario = ? where id = ?";
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setString(1, funcionario.getNome());
			ps.setDouble(2, funcionario.getSalario());
			ps.setInt(3, funcionario.getId());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar empregado\n + e");
		}
	}
	
}
