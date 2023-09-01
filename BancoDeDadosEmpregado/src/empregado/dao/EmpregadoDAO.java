package empregado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import empregado.conexao.Conexao;
import empregado.entidade.Departamento;
import empregado.entidade.Empregado;

public class EmpregadoDAO {
	private PreparedStatement ps;
	private String sql;
	private Conexao conexao;
	private ResultSet rs;
	
	public EmpregadoDAO() {
		conexao = new Conexao();
	}
	
	// metodo para inserir um empregado no banco de dados
	public void inserir(Empregado empregado) {
		sql = "insert into java_empregado values(?, ?, ?, ?)";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, empregado.getId());
			ps.setString(2, empregado.getNome());
			ps.setDouble(3, empregado.getSalario());
			ps.setInt(4, empregado.getDepartamento().getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	// metodo para pesquisar um empregado pelo ID
	public String pesquisar(int id) {
		String aux = null;
		sql = "select * from java_empregado where id = ?";

		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				aux = "";
				aux += rs.getString("NOME") + "\n";
				aux += rs.getString("SALARIO") + "\n";
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar empregado\n + e");
		}
		
		return aux;
	}
	
	public List<Empregado> listar () {
		List<Empregado> lista = new LinkedList<>();
		sql = "select e.nome, e.salario, d.nome as departamento\r\n"
				+ "from java_empregado e INNER JOIN\r\n"
				+ "java_departamento d on e.id = d.id;";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next() == true) {
				String nome = rs.getString("nome");
				double salario = rs.getDouble("salario");
				String nomeDep = rs.getString("departamento");
				Departamento departamento = new Departamento(nomeDep);
				Empregado empregado = new Empregado(nome, salario, departamento);
				lista.add(empregado);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar empregado\n");
		}
		
		return lista;
	}
	
	// metodo para remover um empregado pelo ID
	public void remover(int id) {
		sql = "delete from java_empregado where id = ?";
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
	
	public void atualizar(Empregado empregado) {
		sql = "update java_empregado set nome = ?, salario = ? where id = ?";
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setString(1, empregado.getNome());
			ps.setDouble(2, empregado.getSalario());
			ps.setInt(3, empregado.getId());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar empregado\n + e");
		}
	}
	
}
