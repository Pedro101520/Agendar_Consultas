package Sistema;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class ConverteData {
	private String data;
	
	public ConverteData() {
	}
	
	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
		String dataBanco = "";
		String ano = this.data.substring(6, 10);
		String mes = this.data.substring(3, 5);
		String dia = this.data.substring(0, 2);
		dataBanco = ano + '-' + mes + '-' + dia;
		return dataBanco;
	}
	
	public boolean idade() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate data;
		data = LocalDate.parse(getData(), formatter);
		LocalDate dataAtual = LocalDate.now();
        if (data.isAfter(dataAtual)) {
			JOptionPane.showMessageDialog(null, "A data de nascimento é uma data futura, por favor informe uma data válida");
        	return false;
        }else {
        	return true;
        }
    }
}
