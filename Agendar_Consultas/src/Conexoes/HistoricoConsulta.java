package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class HistoricoConsulta {
	
	UsuarioLogin idUser = new UsuarioLogin();
	
	private static String especialidade;
	private static String data;
	
	public HistoricoConsulta() {
	}

	Connection conexao;
	public boolean historico() {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "SELECT DATE_FORMAT(a.data_consulta, '%d/%m/%Y') AS data_consulta_formatada, m.especialidade "
					+ "FROM agendamento AS a, medicos AS m "
					+ "WHERE a.data_consulta < CURDATE() "
					+ "AND a.id_usuarios = ? "
					+ "AND a.id_medicos = m.id";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setInt(1, idUser.getIdUsuario());
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				especialidade = rs.getString("especialidade");
				data = rs.getString("data_consulta_formatada");
				return true;
			}else {
				return false;
			}
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "HistoricoConsulta: " + erro);
			return false;
		}
	}
	
	public String getEspecialidade() {
		return HistoricoConsulta.especialidade;
	}
	
	public String getData() {
		return HistoricoConsulta.data;
	}
}
