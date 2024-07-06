package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class agendaConsulta {
	
	private static String nomeMedico;
	
	public agendaConsulta() {
	}

	Connection conexao;
	public boolean agendar(String medico, String hora, String data) {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = 
		            "SELECT m.nome, m.especialidade, m.unidade, u.nome, u.email, a.hora, a.data_consulta " +
		                    "FROM usuarios AS u, medicos AS m, agendamento AS a " +
		                    "WHERE m.nome = ? AND a.hora = ? AND a.data_consulta = ? " +
		                    "AND a.id_usuarios = u.id AND a.id_medicos = m.id";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setString(1, medico);
			pstm.setString(2, hora);
			pstm.setString(3, data);
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				nomeMedico = rs.getString("m.nome");
				JOptionPane.showConfirmDialog(null, "Foi encontrado");
				return true;
			}else {
				JOptionPane.showConfirmDialog(null, "Não foi encontrado");
				return false;
			}
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "UsuarioLogin: " + erro);
			return false;
		}
	}
	
	public String getNome() {
		return agendaConsulta.nomeMedico;
	}
}
