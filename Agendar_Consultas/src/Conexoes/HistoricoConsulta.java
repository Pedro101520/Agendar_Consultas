package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class HistoricoConsulta {
	
	UsuarioLogin idUser = new UsuarioLogin();
	
	private static List<String> especialidade = new ArrayList<>();
	private static List<String> data = new ArrayList<>();	
	
	public HistoricoConsulta() {
	}

	Connection conexao;
	public boolean historicoInfo() {
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
			
			
			boolean verifica = true;
			while(verifica) {
				if(rs.next()) {
					especialidade.add(rs.getString("especialidade"));
					data.add(rs.getString("data_consulta_formatada"));
				}else {
					verifica = false;
				}
			}
			return true;
			
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "HistoricoConsulta: " + erro);
			return false;
		}
	}
	
	public List<String> getEspecialidade() {
		return HistoricoConsulta.especialidade;
	}
	
	public List<String> getData() {
		return HistoricoConsulta.data;
	}
}
