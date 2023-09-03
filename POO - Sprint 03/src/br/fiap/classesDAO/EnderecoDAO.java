package br.fiap.classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.fiap.conexao.Conexao;
import br.fiap.endereco.Endereco;

public class EnderecoDAO {
	private PreparedStatement ps;
	private String sql;
	private Conexao conexao;
	private ResultSet rs;
	
	public EnderecoDAO() {
		conexao = new Conexao();
	}
	
	public List<Endereco> listar () {
		List<Endereco> lista = new LinkedList<>();
		sql = "select * from endereco";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next() == true) {
				lista.add(new Endereco(rs.getInt("ID"), rs.getString("NOME"), rs.getInt("CIDADE")));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return lista;
	}
}
