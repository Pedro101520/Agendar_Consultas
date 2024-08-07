package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class atualizaDados {
	
	public atualizaDados() {
	}

	Connection conexao;
	public boolean atualiza(String nome, String email, String senha, String cpf, String cep, String rua, String bairro, String cidade, String estado, String dataNasc) {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "UPDATE usuarios SET "
					+ "nome = ?, "
					+ "email = ?, "
					+ "senha = ?, "
					+ "data_nascimento = ?, "
					+ "cpf = ?, "
					+ "cep = ?, "
					+ "rua = ?, "
					+ "bairro = ?, "
					+ "cidade = ?, "
					+ "estado = ? "
					+ "WHERE id = 1";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setString(1, nome);
			pstm.setString(2, email);
			pstm.setString(3, senha);
			pstm.setString(4, dataNasc);
			pstm.setString(5, cpf);
			pstm.setString(6, cep);
			pstm.setString(7, rua);
			pstm.setString(8, bairro);
			pstm.setString(9, cidade);
			pstm.setString(10, estado);
			
			int rowsAffected = pstm.executeUpdate();
			
			if (rowsAffected > 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "UsuarioLogin: " + erro);
			return false;
		}
	}

}
