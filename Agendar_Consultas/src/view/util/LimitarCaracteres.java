package view.util;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitarCaracteres extends PlainDocument {
    public enum TipoEntrada {
        TEXTO, EMAIL, SENHA;
    }

    private int qtdeCaracteres;
    private TipoEntrada tpEntrada;

    public LimitarCaracteres(int qtdeCaracteres, TipoEntrada tpEntrada) {
        this.qtdeCaracteres = qtdeCaracteres;
        this.tpEntrada = tpEntrada;
    }

    public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }

        if (tpEntrada == TipoEntrada.TEXTO) {
            str = str.replaceAll("[^\\p{L} ]", "");
        }else if(tpEntrada == TipoEntrada.EMAIL) {
            str = str.replaceAll("[^\\p{L}\\d@._-]", "");
        }else if(tpEntrada == TipoEntrada.SENHA) {
        	str = str.replaceAll("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?]+", "");
        }

        if ((getLength() + str.length()) <= qtdeCaracteres) {
            super.insertString(offs, str, a);
        } else {
            String nova = str.substring(0, qtdeCaracteres - getLength());
            super.insertString(offs, nova, a);
        }
    }
}
