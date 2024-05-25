package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexoes.UsuarioLogin;
import Sistema.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtData;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtSenha;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;

	Usuario cadastroUsuario = new Usuario();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 230, 201));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setFont(new Font("Leelawadee", Font.BOLD, 30));
		lblCadastro.setBounds(98, 11, 140, 44);
		contentPane.add(lblCadastro);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(240, 249, 242));
		panel.setBounds(35, 56, 266, 332);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 42, 49, 14);
		panel.add(lblNewLabel_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(100, 41, 156, 20);
		panel.add(txtEmail);
		
		JLabel lblNewLabel_1_1 = new JLabel("Data Nasc:");
		lblNewLabel_1_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 73, 80, 14);
		panel.add(lblNewLabel_1_1);
		
		txtData = new JTextField();
		txtData.setColumns(10);
		txtData.setBounds(100, 72, 156, 20);
		panel.add(txtData);
		
		JButton btnLogin = new JButton("Confirmar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(10, 302, 103, 23);
		panel.add(btnLogin);
		
		JButton btnCad = new JButton("Voltar");
		btnCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String email_usuario, senha_usuario;
				
					email_usuario = txtEmail.getText();
					senha_usuario = txtSenha.getText();
				
					loginUsuario.setNome(email_usuario);
					loginUsuario.setSenha(senha_usuario);
					
					UsuarioLogin objusuariologin = new UsuarioLogin();
					ResultSet rsusuario = objusuariologin.autenticacaoUsuario(loginUsuario);
					
					if(rsusuario.next()) {
						TelaPrincipal principal = new TelaPrincipal();
						principal.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Usuário ou senha inválido");
					}
				}catch(SQLException erro) {
					JOptionPane.showMessageDialog(null, "Login" + erro);
				}
			}
		});
		btnLogin.setBounds(10, 199, 89, 23);
		panel.add(btnLogin);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(100, 11, 156, 20);
		panel.add(txtNome);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nome:");
		lblNewLabel_1_1_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(10, 12, 49, 14);
		panel.add(lblNewLabel_1_1_1);
		
		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(100, 103, 156, 20);
		panel.add(txtCPF);
		
		JLabel lblNewLabel_1_2 = new JLabel("CPF:");
		lblNewLabel_1_2.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 104, 49, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Rua:");
		lblNewLabel_1_3_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_1.setBounds(10, 135, 49, 14);
		panel.add(lblNewLabel_1_3_1);
		
		txtRua = new JTextField();
		txtRua.setColumns(10);
		txtRua.setBounds(100, 134, 156, 20);
		panel.add(txtRua);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Bairro:");
		lblNewLabel_1_3_2.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_2.setBounds(10, 167, 49, 14);
		panel.add(lblNewLabel_1_3_2);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(100, 166, 156, 20);
		panel.add(txtBairro);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Cidade:");
		lblNewLabel_1_3_3.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_3.setBounds(10, 198, 69, 14);
		panel.add(lblNewLabel_1_3_3);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(100, 197, 156, 20);
		panel.add(txtCidade);
		
		JLabel lblNewLabel_1_3_4 = new JLabel("Estado:");
		lblNewLabel_1_3_4.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_4.setBounds(10, 229, 49, 14);
		panel.add(lblNewLabel_1_3_4);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(100, 228, 156, 20);
		panel.add(txtEstado);
		
		JLabel lblNewLabel_1_3 = new JLabel("Senha:");
		lblNewLabel_1_3.setBounds(10, 257, 49, 14);
		panel.add(lblNewLabel_1_3);
		lblNewLabel_1_3.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		
		txtSenha = new JTextField();
		txtSenha.setBounds(100, 256, 156, 20);
		panel.add(txtSenha);
		txtSenha.setColumns(10);
	}
}
