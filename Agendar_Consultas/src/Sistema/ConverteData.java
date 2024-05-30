package Sistema;

public class ConverteData {
	private String data;
	
	public ConverteData() {
	}
	
	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
        String dataFormatada = this.data.replace('/', '-');
        String[] partes = dataFormatada.split("-");
        String dataBancodeDados = partes[2] + "-" + partes[1] + "-" + partes[0];
        System.out.println(dataBancodeDados);
		return dataBancodeDados;
	}
}
