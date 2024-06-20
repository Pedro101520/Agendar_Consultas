package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class UsuarioLogin {
	
	private static String nome;
	
	public UsuarioLogin() {
	}

	Connection conexao;
	public boolean autenticacaoUsuario(Usuario objusuario) {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "SELECT email, senha, nome FROM usuarios WHERE email = ? and senha = ?";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setString(1, objusuario.getEmail());
			pstm.setString(2, objusuario.getSenha());
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				nome = rs.getString("nome");
//				nomeUsuario.setNome(nome);
//				System.out.println("Nome: " + nome);
//				this.nomeUsuario = rs.getString("nome");
				return true;
			}else {
				return false;
			}
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "UsuarioLogin: " + erro);
			return false;
		}
	}
	
	public String getNome() {
		return UsuarioLogin.nome;
	}
}
