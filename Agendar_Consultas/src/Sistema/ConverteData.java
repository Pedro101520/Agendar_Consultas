package Sistema;

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
}
