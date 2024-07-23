package Conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
	public Connection conexaoDB() {
		Connection conexao = null;
			
		try {
			String url = "jdbc:mysql://localhost:3306/consultas?user=pedro&password=12345";
			conexao = DriverManager.getConnection(url);
		}catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, erro.getMessage());
		}
		return conexao;
	}
	
	public static void desconectar(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			}catch (SQLException erro) {
				System.out.println("Não foi possível fechar a conexão");
				erro.printStackTrace();
			}
		}
	}
}
