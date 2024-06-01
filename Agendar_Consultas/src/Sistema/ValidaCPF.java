package Sistema;

import javax.swing.JOptionPane;

public class ValidaCPF {
	private String CPF;
	
	public ValidaCPF() {
	}
	
	public void setCPF(String CPF) {
		if (this.valida(CPF)) {
			this.CPF = CPF;
		}else {
			JOptionPane.showMessageDialog(null, "CPF inválido!");
		}
	}
	public String getCPF() {
		return CPF;
	}
	public boolean valida(String CPF) {
		String cpf = CPF.replace('-', ' ');
		return true;
	}
}
