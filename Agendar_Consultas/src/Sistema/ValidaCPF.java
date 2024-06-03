package Sistema;

import javax.swing.JOptionPane;

public class ValidaCPF {
	private String CPF;
	
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
	
	public boolean valida(String CPF) {
		String cpf = CPF.replace("-", "").replace(".", "");
		String semDigito = cpf.substring(0, 9);
		int num1 = this.priDig(semDigito);
		int num2 = this.segDig(semDigito, num1);
		String cpfCalculado = semDigito + num1 + num2;
		if(cpf.equals(cpfCalculado)) {
			System.out.println("Pedro");
			return true;
		}
		JOptionPane.showMessageDialog(null,"CPF inválido");
		return false ;
	}
}
