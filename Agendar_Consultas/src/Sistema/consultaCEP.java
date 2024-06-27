package Sistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;


public class consultaCEP {
	
	private String rua;
	private String estado;
	private String cidade;
	private String bairro;
	
	public consultaCEP() {
	}
	
	public void exibeInfo(String cep) {
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            JSONObject jsonResponse = new JSONObject(response.toString());

            String cidade = jsonResponse.getString("localidade");
            String estado = jsonResponse.getString("uf");
            String rua = jsonResponse.getString("logradouro");
            String bairro = jsonResponse.getString("bairro");
            
            
            this.setCidade(cidade);
            this.setEstado(estado);
            this.setRua(rua);
            this.setBairro(bairro);
            
        } catch (IOException e) {
            e.printStackTrace();
          }
	}
	
	public String getRua() {
		return this.rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getEstado() {
		return this.estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCidade() {
		return this.cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getBairro() {
		return this.bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}

