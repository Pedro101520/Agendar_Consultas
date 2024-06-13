package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import Conexoes.UsuarioCad;
import Conexoes.UsuarioLogin;
import Sistema.ConverteData;
import Sistema.Usuario;
import Sistema.ValidaCPF;
import Sistema.consultaCEP;
import Sistema.registraEmail;
import view.util.LimitarCaracteres;

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
import javax.swing.JEditorPane;
import javax.swing.DropMode;
import java.awt.TextField;
import java.awt.Window;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtNome;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;

	ValidaCPF valida = new ValidaCPF();
	Usuario cadastroUsuario = new Usuario();
	UsuarioCad cadastra = new UsuarioCad();
	ConverteData data = new ConverteData();
	registraEmail regEmail = new registraEmail();
	consultaCEP consulta = new consultaCEP();

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

	private JFormattedTextField txtDataFormatted;
	private JFormattedTextField txtCPFFormatted;
	private JFormattedTextField txtCEPFormatted;
	private JPasswordField psSenha;
	private JTextField txtRua;
	private static JLabel lblEmailVazio;
	private static JLabel lblNomeVazio;
	private static JLabel lblDataVazio;
	private static JLabel lblCPFVazio;
	private static JLabel lblRuaVazio;
	private static JLabel lblBairroVazio;
	private static JLabel lblCidadeVazio;
	private static JLabel lblEstadoVazio;
	private static JLabel lblSenhaVazio;

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
		lblCadastro.setBounds(98, 0, 140, 44);
		contentPane.add(lblCadastro);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(240, 249, 242));
		panel.setBounds(35, 40, 266, 365);
		contentPane.add(panel);

		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 49, 49, 14);
		panel.add(lblNewLabel_1);

		txtEmail = new JTextField();
		txtEmail.setDocument(new LimitarCaracteres(30, LimitarCaracteres.TipoEntrada.EMAIL));
		txtEmail.setColumns(10);
		txtEmail.setBounds(100, 48, 156, 20);
		panel.add(txtEmail);

		JLabel lblNewLabel_1_1 = new JLabel("Data Nasc:");
		lblNewLabel_1_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 80, 80, 14);
		panel.add(lblNewLabel_1_1);

		try {
			MaskFormatter mask = new MaskFormatter("##/##/####");
			txtDataFormatted = new JFormattedTextField(mask);
			txtDataFormatted.setBounds(100, 79, 156, 20);
			panel.add(txtDataFormatted);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			MaskFormatter mask = new MaskFormatter("###.###.###-##");
			txtCPFFormatted = new JFormattedTextField(mask);
			txtCPFFormatted.setBounds(100, 110, 156, 20);
			panel.add(txtCPFFormatted);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JButton btnCad = new JButton("Confirmar");
		btnCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validaFormulario = true;
				try {
					if(txtDataFormatted.getText().length() > 0) {
						lblDataVazio.setVisible(false);
						data.setData(txtDataFormatted.getText());
					}else {
						lblDataVazio.setVisible(true);
						validaFormulario = false;
					}
					if(txtNome.getText().length() > 0) {
						lblNomeVazio.setVisible(false);
						cadastroUsuario.setNome(txtNome.getText());
					}else {
						lblNomeVazio.setVisible(true);
						validaFormulario = false;
					}
					if (regEmail.emailDB(txtEmail.getText()) && txtEmail.getText().length() > 0) {
						lblEmailVazio.setVisible(false);
						cadastroUsuario.setEmail(txtEmail.getText());
					} else {
						lblEmailVazio.setVisible(true);
						validaFormulario = false;
					}
					if(txtDataFormatted.getText().length() > 0) {
						lblDataVazio.setVisible(false);
						cadastroUsuario.setNascimento(data.getData());	
					}else {
						lblDataVazio.setVisible(true);
						validaFormulario = false;					
					}
					if (valida.valida(txtCPFFormatted.getText()) && txtCPFFormatted.getText().length() > 0) {
						lblCPFVazio.setVisible(false);
						cadastroUsuario.setCpf(txtCPFFormatted.getText());
					} else {
						lblCPFVazio.setVisible(true);
						validaFormulario = false;
					}
					cadastroUsuario.setCEP(txtCEPFormatted.getText());
					if (txtRua.getText().length() > 0) {
						lblRuaVazio.setVisible(false);
						cadastroUsuario.setRua(txtRua.getText());
					} else {
						lblRuaVazio.setVisible(true);
						validaFormulario = false;
					}
					if (txtBairro.getText().length() > 0) {
						lblBairroVazio.setVisible(false);
						cadastroUsuario.setBairro(txtBairro.getText());
					} else {
						lblBairroVazio.setVisible(true);
						validaFormulario = false;
					}
					if (txtCidade.getText().length() > 0) {
						lblCidadeVazio.setVisible(false);
						cadastroUsuario.setCidade(txtCidade.getText());
					} else {
						lblCidadeVazio.setVisible(true);
						validaFormulario = false;
					}
					if (txtEstado.getText().length() > 0) {
						lblEstadoVazio.setVisible(false);
						cadastroUsuario.setEstado(txtEstado.getText());
					} else {
						lblEstadoVazio.setVisible(true);
						validaFormulario = false;
					}
					if (psSenha.getPassword().length > 0) {
						lblSenhaVazio.setVisible(false);
						cadastroUsuario.setSenha(new String(psSenha.getPassword()));
					} else {
						lblSenhaVazio.setVisible(true);
						validaFormulario = false;
					}

					if(validaFormulario) {
						UsuarioCad objusuarioCadastra = new UsuarioCad();
						boolean rsCadastro = objusuarioCadastra.cadastrarUsuario(cadastroUsuario);
						if (rsCadastro) {
							Login telaLogin = new Login();
							JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
							telaLogin.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Verifique as informações e tente novamente!");
						}
					}else {
						return;
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Cadastro" + erro);
				}
			}
		});
		btnCad.setBounds(10, 331, 103, 23);
		panel.add(btnCad);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(167, 331, 89, 23);
		panel.add(btnVoltar);

		txtNome = new JTextField();
		txtNome.setDocument(new LimitarCaracteres(40, LimitarCaracteres.TipoEntrada.TEXTO));
		txtNome.setColumns(10);
		txtNome.setBounds(100, 17, 156, 20);
		panel.add(txtNome);

		JLabel lblNewLabel_1_1_1 = new JLabel("Nome:");
		lblNewLabel_1_1_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(10, 18, 49, 14);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblCepErro = new JLabel("CEP inválido");
		lblCepErro.setForeground(new Color(255, 0, 0));
		lblCepErro.setBackground(new Color(255, 0, 0));
		lblCepErro.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblCepErro.setBounds(100, 159, 49, 14);
		panel.add(lblCepErro);
		lblCepErro.setVisible(false);

		lblNomeVazio = new JLabel("Informe um Nome");
		lblNomeVazio.setForeground(new Color(255, 0, 0));
		lblNomeVazio.setBackground(new Color(255, 0, 0));
		lblNomeVazio.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNomeVazio.setBounds(100, 35, 69, 14);
		panel.add(lblNomeVazio);
		lblNomeVazio.setVisible(false);

		lblEmailVazio = new JLabel("Informe um Email");
		lblEmailVazio.setForeground(new Color(255, 0, 0));
		lblEmailVazio.setBackground(new Color(255, 0, 0));
		lblEmailVazio.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblEmailVazio.setBounds(100, 66, 69, 14);
		panel.add(lblEmailVazio);
		lblEmailVazio.setVisible(false);

		lblDataVazio = new JLabel("Informe uma data");
		lblDataVazio.setForeground(new Color(255, 0, 0));
		lblDataVazio.setBackground(new Color(255, 0, 0));
		lblDataVazio.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblDataVazio.setBounds(100, 98, 69, 14);
		panel.add(lblDataVazio);
		lblDataVazio.setVisible(false);

		lblCPFVazio = new JLabel("Informe um CPF");
		lblCPFVazio.setForeground(new Color(255, 0, 0));
		lblCPFVazio.setBackground(new Color(255, 0, 0));
		lblCPFVazio.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblCPFVazio.setBounds(100, 129, 69, 14);
		panel.add(lblCPFVazio);
		lblCPFVazio.setVisible(false);

		lblRuaVazio = new JLabel("Informe uma Rua");
		lblRuaVazio.setForeground(new Color(255, 0, 0));
		lblRuaVazio.setBackground(new Color(255, 0, 0));
		lblRuaVazio.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblRuaVazio.setBounds(100, 190, 69, 14);
		panel.add(lblRuaVazio);
		lblRuaVazio.setVisible(false);

		lblBairroVazio = new JLabel("Informe um Bairro");
		lblBairroVazio.setForeground(new Color(255, 0, 0));
		lblBairroVazio.setBackground(new Color(255, 0, 0));
		lblBairroVazio.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblBairroVazio.setBounds(100, 220, 69, 14);
		panel.add(lblBairroVazio);
		lblBairroVazio.setVisible(false);

		lblCidadeVazio = new JLabel("Informe uma Cidade");
		lblCidadeVazio.setForeground(new Color(255, 0, 0));
		lblCidadeVazio.setBackground(new Color(255, 0, 0));
		lblCidadeVazio.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblCidadeVazio.setBounds(100, 252, 89, 14);
		panel.add(lblCidadeVazio);
		lblCidadeVazio.setVisible(false);

		lblEstadoVazio = new JLabel("Informe um Estado");
		lblEstadoVazio.setForeground(new Color(255, 0, 0));
		lblEstadoVazio.setBackground(new Color(255, 0, 0));
		lblEstadoVazio.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblEstadoVazio.setBounds(100, 285, 80, 14);
		panel.add(lblEstadoVazio);
		lblEstadoVazio.setVisible(false);

		lblSenhaVazio = new JLabel("Informe uma Senha");
		lblSenhaVazio.setForeground(new Color(255, 0, 0));
		lblSenhaVazio.setBackground(new Color(255, 0, 0));
		lblSenhaVazio.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblSenhaVazio.setBounds(100, 318, 80, 14);
		panel.add(lblSenhaVazio);
		lblSenhaVazio.setVisible(false);

		JLabel lblNewLabel_1_2 = new JLabel("CPF:");
		lblNewLabel_1_2.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 111, 49, 14);
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3_1 = new JLabel("CEP:");
		lblNewLabel_1_3_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_1.setBounds(10, 142, 49, 14);
		panel.add(lblNewLabel_1_3_1);

		JLabel lblNewLabel_1_3_2 = new JLabel("Bairro:");
		lblNewLabel_1_3_2.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_2.setBounds(10, 204, 49, 14);
		panel.add(lblNewLabel_1_3_2);

		txtBairro = new JTextField();
		txtBairro.setDocument(new LimitarCaracteres(30, LimitarCaracteres.TipoEntrada.TEXTO));
		txtBairro.setColumns(10);
		txtBairro.setBounds(100, 203, 156, 20);
		panel.add(txtBairro);

		JLabel lblNewLabel_1_3_3 = new JLabel("Cidade:");
		lblNewLabel_1_3_3.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_3.setBounds(10, 235, 69, 14);
		panel.add(lblNewLabel_1_3_3);

		txtCidade = new JTextField();
		txtCidade.setDocument(new LimitarCaracteres(30, LimitarCaracteres.TipoEntrada.TEXTO));
		txtCidade.setColumns(10);
		txtCidade.setBounds(100, 234, 156, 20);
		panel.add(txtCidade);

		JLabel lblNewLabel_1_3_4 = new JLabel("Estado:");
		lblNewLabel_1_3_4.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_4.setBounds(10, 266, 49, 14);
		panel.add(lblNewLabel_1_3_4);

		txtEstado = new JTextField();
		txtEstado.setDocument(new LimitarCaracteres(20, LimitarCaracteres.TipoEntrada.TEXTO));
		txtEstado.setColumns(20);
		txtEstado.setBounds(100, 265, 156, 20);
		panel.add(txtEstado);

		JLabel lblNewLabel_1_3 = new JLabel("Senha:");
		lblNewLabel_1_3.setBounds(10, 300, 49, 14);
		panel.add(lblNewLabel_1_3);
		lblNewLabel_1_3.setFont(new Font("Leelawadee", Font.PLAIN, 15));

		// Dar um limite de 8 caracteres
		psSenha = new JPasswordField();
		psSenha.setBounds(100, 299, 156, 20);
		panel.add(psSenha);

		txtRua = new JTextField();
		txtRua.setColumns(10);
		txtRua.setBounds(100, 173, 156, 20);
		panel.add(txtRua);

		JLabel lblNewLabel_1_3_1_1 = new JLabel("Rua:");
		lblNewLabel_1_3_1_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_1_1.setBounds(10, 173, 49, 14);
		panel.add(lblNewLabel_1_3_1_1);

		try {
			MaskFormatter mask = new MaskFormatter("#####-###");
			txtCEPFormatted = new JFormattedTextField(mask);
			txtCEPFormatted.setBounds(100, 141, 156, 20);
			panel.add(txtCEPFormatted);
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtCEPFormatted.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				verificarCEP();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				verificarCEP();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				verificarCEP();
			}

			private void verificarCEP() {
				String cep = txtCEPFormatted.getText().replaceAll("-", "").replaceAll(" ", "");
				String verificaCEP = "";
				try {
					if (cep.length() == 8) {
						verificaCEP += cep;
						consulta.exibeInfo(verificaCEP);
						txtRua.setText(consulta.getRua());
						txtBairro.setText(consulta.getBairro());
						txtCidade.setText(consulta.getCidade());
						txtEstado.setText(consulta.getEstado());
						lblCepErro.setVisible(false);
					}
				} catch (Exception erro) {
					lblCepErro.setVisible(true);
					txtRua.setText("");
					txtCidade.setText("");
					txtBairro.setText("");
					txtEstado.setText("");
				}
			}
		});
	}
}