package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class CancelaConsulta {
	
	private static String nome, especialidade, unidade, hora, data;	
	
	public CancelaConsulta() {
	}

	Connection conexao;
	public boolean acessaConsulta(int idConsulta) {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "SELECT m.nome, m.especialidade, m.unidade, a.hora, a.data_consulta "
					+ "FROM medicos AS m, agendamento AS a "
					+ "WHERE a.id = ? "
					+ "AND a.id_medicos = m.id";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setInt(1, idConsulta);
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				nome = rs.getString("nome");
				especialidade = rs.getString("especialidade");
				unidade = rs.getString("unidade");
				hora = rs.getString("hora");
				data = rs.getString("data_consulta");
				
				return true;
			}else {
				return false;
			}
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "Cancela consulta: " + erro);
			return false;
		}
	}
	
	public boolean cancelaConsulta(int idConsulta) {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "DELETE FROM agendamento WHERE id = ?";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setInt(1, idConsulta);
			
			// Aqui serve para capturar o numero de linhas afetadas
			int rowsAffected = pstm.executeUpdate();
			
			if (rowsAffected > 0) {		
				return true;
			}else {
				return false;
			}
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "Cancela consulta: " + erro);
			return false;
		}
	}
	
	public String getNome() {
		return CancelaConsulta.nome;
	}
	public String getEspecialidade() {
		return CancelaConsulta.especialidade;
	}
	public String getUnidade() {
		return CancelaConsulta.unidade;
	}
	public String getHora() {
		return CancelaConsulta.hora;
	}
	public String getData() {
		return CancelaConsulta.data;
	}
}
	
	
