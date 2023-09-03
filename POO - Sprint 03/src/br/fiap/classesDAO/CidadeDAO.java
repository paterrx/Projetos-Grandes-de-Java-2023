package br.fiap.classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.fiap.cidade.Cidade;
import br.fiap.conexao.Conexao;

public class CidadeDAO {
	private PreparedStatement ps;
	private String sql;
	private Conexao conexao;
	private ResultSet rs;
	
	public CidadeDAO() {
		conexao = new Conexao();
	}
	
	public List<Cidade> listar () {
		List<Cidade> lista = new LinkedList<>();
		sql = "select * from cidade";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next() == true) {
				lista.add(new Cidade(rs.getInt("ID"), rs.getString("NOME")));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return lista;
	}
}
