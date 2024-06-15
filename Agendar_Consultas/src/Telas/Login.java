package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexoes.UsuarioLogin;
import Sistema.Usuario;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.security.auth.login.FailedLoginException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;

	Usuario loginUsuario = new Usuario();
	private JPasswordField psSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Biblioteca\\Downloads\\cadeado.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 230, 201));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Leelawadee", Font.BOLD, 30));
		lblNewLabel.setBounds(104, 45, 140, 44);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(240, 249, 242));
		panel.setBounds(57, 100, 222, 233);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 57, 49, 14);
		panel.add(lblNewLabel_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(56, 56, 156, 20);
		panel.add(txtEmail);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 112, 49, 14);
		panel.add(lblNewLabel_1_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String email_usuario, senha_usuario;
				
					email_usuario = txtEmail.getText();
					senha_usuario = new String(psSenha.getPassword());
				
					loginUsuario.setEmail(email_usuario);
					loginUsuario.setSenha(senha_usuario);
					
					UsuarioLogin objusuariologin = new UsuarioLogin();
					
					if(objusuariologin.autenticacaoUsuario(loginUsuario)) {
						TelaPrincipal principal = new TelaPrincipal();
						principal.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Usuário ou senha inválido");
					}
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(null, "Login" + erro);
				}
			}
		});
		btnLogin.setBounds(10, 199, 89, 23);
		panel.add(btnLogin);
		
		JButton btnCad = new JButton("Cadastrar");
		btnCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastro cadastro = new TelaCadastro();
				cadastro.setVisible(true);
				dispose();			
			}
		});
		btnCad.setBounds(109, 199, 103, 23);
		panel.add(btnCad);
		
		psSenha = new JPasswordField();
		psSenha.setBounds(56, 111, 156, 20);
		panel.add(psSenha);
	}
}
