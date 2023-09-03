package br.fiap.classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.fiap.conexao.Conexao;
import br.fiap.departamento.Departamento;

public class DepartamentoDAO {
	private PreparedStatement ps;
	private String sql;
	private Conexao conexao;
	private ResultSet rs;
	
	public DepartamentoDAO() {
		conexao = new Conexao();
	}
	
	public List<Departamento> listar () {
		List<Departamento> lista = new LinkedList<>();
		sql = "select * from departamento";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next() == true) {
				lista.add(new Departamento(rs.getInt("ID"), rs.getInt("CODIGO"), rs.getString("NOME"), rs.getInt("QTD_FUNCIONARIOS")));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return lista;
	}
}
