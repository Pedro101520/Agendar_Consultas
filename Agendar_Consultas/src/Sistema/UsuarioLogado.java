package Sistema;

import javax.swing.JLabel;

import Conexoes.UsuarioLogin;

public class UsuarioLogado {
	
	UsuarioLogin usuario = new UsuarioLogin();
	
	public UsuarioLogado() {
	}
	
	public void nomeLabel(JLabel label) {
		label.setText(usuario.getUsuarioLogado());
	}
	
}
