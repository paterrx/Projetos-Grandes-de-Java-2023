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
			ps.setInt(5, funcionario.getTelefone());
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
				aux += rs.getInt("ID") + "\n";
				aux += rs.getInt("CODIGO") + "\n";
				aux += rs.getString("NOME") + "\n";
				aux += rs.getString("EMAIL") + "\n";
				aux += rs.getInt("TELEFONE") + "\n";
				aux += rs.getDouble("SALARIO") + "\n";
				aux += rs.getDate("DATA_CONTRATACAO") + "\n";
				aux += rs.getString("SEXO") + "\n";
				aux += rs.getString("ENDERECO") + "\n";
				aux += rs.getString("DEPARTAMENTO") + "\n";
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
		sql = "select * from funcionario where id = ?";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next() == true) {
				lista.add(new Funcionario(rs.getInt("ID"), rs.getInt("CODIGO"), rs.getString("NOME"), rs.getString("EMAIL"), 
				rs.getInt("TELEFONE"), rs.getDouble("SALARIO"), rs.getDate("DATA_CONTRATACAO"), rs.getString("SEXO"), 
				rs.getString("ENDERECO"), rs.getString("DEPARTAMENTO")));
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
	
}
