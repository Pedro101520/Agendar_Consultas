package Sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Conexoes.Conexao;

public class ValidaCPF {
	private String CPF;
	private String cpfValidado;
	
	//Teste
	public ValidaCPF() {
	}
	public String getCPF() {	
		return CPF;
	}
	private int priDig(String semDigito) {
		int num, soma = 0, mult = 1, count = 10, priNum;
		for(int i = 0; i < semDigito.length(); i++) {
			num = Character.getNumericValue(semDigito.charAt(i));
			mult = num * count;
			soma += mult;
			count--;
		}
		priNum = 11 - (soma % 11);
		priNum = priNum > 9 ? 0 : priNum;
		return priNum;
	}
	
	private int segDig(String semDigito, int num1) {
		int num, soma = 0, mult = 1, count = 11, segNum;
		for(int i = 0; i < semDigito.length(); i++) {
			num = Character.getNumericValue(semDigito.charAt(i));
			mult = num * count;
			soma += mult;
			count--;	
		}
		segNum = 11 - ((soma + (num1 * 2)) % 11);
		segNum = segNum > 9 ? 0 : segNum;
		return segNum;
	}
	
	Connection conexao;
	private ResultSet autenticacaoUsuario() {
		conexao = new Conexao().conexaoDB();
		
		try{ 
			String sql = "SELECT cpf FROM usuarios WHERE cpf = ?";
			
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setString(1, this.cpfValidado);
			
			ResultSet rs = pstm.executeQuery();
			return rs;
		} catch(SQLException erro) {
			JOptionPane.showConfirmDialog(null, "ValidaCPF: " + erro);
			return null;
		}
	}
	
	public boolean valida(String CPF) {
		String cpf = CPF.replace("-", "").replace(".", "");
		String semDigito = cpf.substring(0, 9);
		int num1 = this.priDig(semDigito);
		int num2 = this.segDig(semDigito, num1);
		String cpfCalculado = semDigito + num1 + num2;
		if(cpf.equals(cpfCalculado)) {
			this.cpfValidado = CPF;
            ResultSet rs = autenticacaoUsuario();
            try {
                if (rs.next() && rs != null) {
        			JOptionPane.showMessageDialog(null,"CPF Já esta em uso, tente fazer o login!");
                    return false;
                }else if (cpf.chars().distinct().count() == 1) {
                    JOptionPane.showMessageDialog(null, "CPF inválido: todos os dígitos são iguais");
                    return false;
                }else {
                	return true;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao validar CPF: " + e);
                return false;
            }
		}else {
//			JOptionPane.showMessageDialog(null,"CPF inválido");
			return false ;
		}
	}
}
