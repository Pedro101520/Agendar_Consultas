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
		conexao = new ConexaoLogin().conexaoDB();
		
		try{
			String sql = "SELECT * FROM usuario WHERE nome_usuario = ? and senha = ?";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setString(1, objusuario.getNome());
			pstm.setString(2, objusuario.getSenha());
			
			ResultSet rs = pstm.executeQuery();
			return rs;
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "UsuarioLogin: " + erro);
			return null;
		}
	}
	
}
