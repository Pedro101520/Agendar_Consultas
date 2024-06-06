package Sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Conexoes.Conexao;

public class registraEmail {
	private String email;
	
	public registraEmail() {
	}
	
	Connection conexao;
	private ResultSet autenticacaoUsuario() {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "SELECT email FROM usuarios WHERE email = ?";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setString(1, this.email);
			
			ResultSet rs = pstm.executeQuery();
			return rs;
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "Registra Email: " + erro);
			return null;
		}
	}
	
	
	public boolean emailDB(String email) {
		this.email = email;
        ResultSet rs = autenticacaoUsuario();
        try {
        	if (rs.next() && rs != null) {
        		JOptionPane.showMessageDialog(null,"Email Já esta em uso, tente fazer o login!");
                return false;
        	}else {
        		return true;
            }
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Erro ao registrar Email: " + e);
            return false;
        }
	}
}
