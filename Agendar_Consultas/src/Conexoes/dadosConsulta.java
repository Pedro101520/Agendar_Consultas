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
	private static String especialidade;
	private static String unidade;
	
	public dadosConsulta() {
	}

	Connection conexao;
	public boolean autenticacaoUsuario() {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "SELECT * FROM medicos";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				nome.add(rs.getString("nome"));
				especialidade = rs.getString("especialidade");
				unidade = rs.getString("unidade");
				return true;
			}else {
				return false;
			}
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "UsuarioLogin: " + erro);
			return false;
		}
	}
	
	public List<String> getNome() {
		return dadosConsulta.nome;
	}
	public String getEspecialidade() {
		return dadosConsulta.especialidade;
	}
	public String getUnidade() {
		return dadosConsulta.unidade;
	}
}
