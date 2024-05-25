package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class UsuarioLogin {

	Connection conexao;
	
	public ResultSet autenticacaoUsuario(Usuario objusuario) {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "SELECT email, senha FROM usuarios WHERE email = ? and senha = ?";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setString(1, objusuario.getEmail());
			pstm.setString(2, objusuario.getSenha());
			
			ResultSet rs = pstm.executeQuery();
			return rs;
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "UsuarioLogin: " + erro);
			return null;
		}
	}
	
}
