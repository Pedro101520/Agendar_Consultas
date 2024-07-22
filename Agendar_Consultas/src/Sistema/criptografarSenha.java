package Sistema;

import java.math.BigInteger;
import java.security.MessageDigest;

public class criptografarSenha {

    public static String md5(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            return hash.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}