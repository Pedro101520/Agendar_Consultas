package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class ProcessaAgenda {
	
	private static List<String> nome = new ArrayList<>();
	private static List<String> especialidade = new ArrayList<>();
	private static List<String> unidade = new ArrayList<>();
	
	public ProcessaAgenda() {
	}

	Connection conexao;
	public boolean infoAgenda(String buscaEspecialidade, String buscaUnidade) {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "SELECT nome, especialidade, unidade FROM medicos WHERE especialidade = ? OR unidade = ?";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setString(1, buscaEspecialidade);
			pstm.setString(2, buscaUnidade);
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
			JOptionPane.showConfirmDialog(null, "ProcessaAgenda: " + erro);
			return false;
		}
	}
	
	public boolean selecionaPorNome(String buscaPorNome) {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "SELECT nome, especialidade, unidade FROM medicos WHERE nome = ?";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setString(1, buscaPorNome);
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
			JOptionPane.showConfirmDialog(null, "ProcessaAgenda: " + erro);
			return false;
		}
	}
	
	public List<String> getNome() {
		return ProcessaAgenda.nome;
	}
	public List<String> getEspecialidade() {
		return ProcessaAgenda.especialidade;
	}
	public List<String> getUnidade() {
		return ProcessaAgenda.unidade;
	}
}
