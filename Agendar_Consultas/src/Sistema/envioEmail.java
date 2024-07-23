package Sistema;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.DefaultAuthenticator;

public class envioEmail {
    public void email(String destinatario, String mensagem) {
        String meuEmail = "";
        String minhaSenha = "";
        String assunto = "Agendamento de consulta";

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
        email.setSSLOnConnect(true);
        email.setSSLCheckServerIdentity(true);
        email.setSocketTimeout(10000);
        email.setSocketConnectionTimeout(10000);
        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        

        try {
            email.setFrom(meuEmail);
            email.addTo(destinatario);
            email.setSubject(assunto);
            email.setMsg(mensagem);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
