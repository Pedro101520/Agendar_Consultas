package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class UsuarioCad {

	Connection conexao;
	
	public boolean cadastrarUsuario(Usuario objcadastra) {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "INSERT INTO usuarios (nome, email, senha, data_nascimento, cpf, rua, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstm = conexao.prepareStatement(sql);
			
			pstm.setString(1, objcadastra.getNome());
			pstm.setString(2, objcadastra.getEmail());
			pstm.setString(3, objcadastra.getSenha());
			pstm.setString(4, objcadastra.getNascimento());
			pstm.setString(5, objcadastra.getCpf());
			pstm.setString(6, objcadastra.getRua());
			pstm.setString(7, objcadastra.getBairro());
			pstm.setString(8, objcadastra.getCidade());
			pstm.setString(9, objcadastra.getEstado());
			pstm.setString(10, objcadastra.getCEP());
			
			pstm.executeUpdate();
			return true;
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "UsuarioLogin: " + erro);
			return false;
		}
	}
}
