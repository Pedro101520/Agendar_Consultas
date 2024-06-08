package Sistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class consultaCEP {
    public static void main(String[] args) {
        try {
            // CEP que você quer consultar
            String cep = "08565440";
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            // Criação da conexão HTTP
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            // Lendo a resposta
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // Convertendo a resposta para um objeto JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Extraindo os campos desejados
            String cidade = jsonResponse.getString("localidade");
            String estado = jsonResponse.getString("uf");
            String rua = jsonResponse.getString("logradouro");
            String bairro = jsonResponse.getString("bairro");

            System.out.println("Cidade: " + cidade);
            System.out.println("Estado: " + estado);
            System.out.println("Rua: " + rua);
            System.out.println("Bairro: " + bairro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

