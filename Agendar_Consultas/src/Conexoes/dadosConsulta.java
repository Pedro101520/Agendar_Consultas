package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class dadosConsulta {
	private static List<String> nome = new ArrayList<>();
	private static List<String> especialidade = new ArrayList<>();
	private static List<String> unidade = new ArrayList<>();
	
	public dadosConsulta() {
	}

	Connection conexao;
	public boolean acessaDadosMedico() {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "SELECT * FROM medicos";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			boolean verifica = true;
			while(verifica) {
				if (rs.next()) {
					nome.add(rs.getString("nome"));
					especialidade.add(rs.getString("especialidade"));
					unidade.add(rs.getString("unidade"));
				}else {
					verifica = false;
				}
			}
			return true;
			
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "dadosConsulta: " + erro);
			return false;
		}
	}
	
	public List<String> getNome() {
		return dadosConsulta.nome;
	}
	public List<String> getEspecialidade() {
		return dadosConsulta.especialidade;
	}
	public List<String> getUnidade() {
		return dadosConsulta.unidade;
	}
}
