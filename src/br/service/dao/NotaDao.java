package br.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.service.config.BDconfig;
import br.service.entidade.Nota;

public class NotaDao {
	
	/*
	 * Buscando Todas as notas
	 */
	public List<Nota> listaNotas() throws Exception {
		// Declarando uma lista de notas
		List<Nota> lista = new ArrayList<>();
		// instanciando uma nova conexao com o BD
		Connection conexao = BDconfig.getconnection();
		// String para consulta no BD
		String sql = "SELECT * FROM tb_nota;";
		// preparando e executando consulta no BD
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		// percorendo o resultset e adiconando as notas retornadas do BD na
		// lista
		while (rs.next()) {
			Nota nota = new Nota();
			nota.setId(rs.getInt("id_note"));
			nota.setTitulo(rs.getString("titulo"));
			nota.setDescricao(rs.getString("descricao"));
			lista.add(nota);
		}
		return lista;
	}

	/*
	 * Buscando Nota por id
	 */
	public Nota buscarNotaPorId(int idNota) throws Exception {
		Nota nota = null;
		// instanciando uma nova conexao com o BD
		Connection conexao = BDconfig.getconnection();
		// String para consulta no BD
		String sql = "SELECT * FROM tb_nota where id_note = ?";
		// preparando e executando consulta no BD
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idNota);
		ResultSet rs = statement.executeQuery();
		// percorendo o resultset e adiconando as notas retornadas do BD na
		// lista
		if (rs.next()) {
			nota = new Nota();
			nota.setId(rs.getInt("id_note"));
			nota.setTitulo(rs.getString("titulo"));
			nota.setDescricao(rs.getString("descricao"));

		}
		return nota;
	}

	public void addNota(Nota nota) throws Exception {
		Connection conexao = BDconfig.getconnection();

		// String para persistir os dados da nota no BD
		String sql = "INSERT INTO tb_nota(titulo,descricao)values(?,?);";
		// passando os valores do objeto para inserir no BD
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.execute();

	}

	public void editarNota(Nota nota, int idNota) throws Exception {
		Connection conexao = BDconfig.getconnection();

		// String atualizar os dados
		String sql = "update tb_nota set titulo = ?, descricao = ? where id_note = ? ;";
		// passando os novos valores do objeto para atualizar os registros do BD
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.setInt(3, idNota);
		statement.execute();
	}

	public void removeNota(int idNota) throws Exception {
		Connection conexao = BDconfig.getconnection();

		// String com os comandos SQL para exclusao do registro
		String sql = "delete from tb_nota where id_note = ?;";
		// passando o id da nota que sera removida
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idNota);
		statement.execute();
	}
}
