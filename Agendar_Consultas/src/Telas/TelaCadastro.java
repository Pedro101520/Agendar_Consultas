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
				try {
				    data.setData(txtDataFormatted.getText());

					cadastroUsuario.setNome(txtNome.getText());
					if(regEmail.emailDB(txtEmail.getText())) {
						cadastroUsuario.setEmail(txtEmail.getText());
					}else {
						return;
					}
					cadastroUsuario.setNascimento(data.getData());
					if(valida.valida(txtCPFFormatted.getText())) {
						cadastroUsuario.setCpf(txtCPFFormatted.getText());
					}else {
						return;
					}
					cadastroUsuario.setCEP(txtCEPFormatted.getText());
					cadastroUsuario.setRua(txtRua.getText());
					cadastroUsuario.setBairro(txtBairro.getText());
					cadastroUsuario.setCidade(txtCidade.getText());
					cadastroUsuario.setEstado(txtEstado.getText());
					cadastroUsuario.setSenha(new String(psSenha.getPassword()));
									
					UsuarioCad objusuarioCadastra = new UsuarioCad();
					boolean rsCadastro = objusuarioCadastra.cadastrarUsuario(cadastroUsuario);
										
					if(rsCadastro) {
						Login telaLogin = new Login();
						JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
						telaLogin.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Verifique as informações e tente novamente!");
					}
				}catch(Exception erro) {
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
	    lblCepErro.setBounds(103, 160, 49, 14);
	    panel.add(lblCepErro);
    	lblCepErro.setVisible(false);
		
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
		lblNewLabel_1_3_2.setBounds(10, 211, 49, 14);
		panel.add(lblNewLabel_1_3_2);
		
		txtBairro = new JTextField();
		txtBairro.setDocument(new LimitarCaracteres(30, LimitarCaracteres.TipoEntrada.TEXTO));
		txtBairro.setColumns(10);
		txtBairro.setBounds(100, 210, 156, 20);
		panel.add(txtBairro);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Cidade:");
		lblNewLabel_1_3_3.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_3.setBounds(10, 242, 69, 14);
		panel.add(lblNewLabel_1_3_3);
		
		txtCidade = new JTextField();
		txtCidade.setDocument(new LimitarCaracteres(30, LimitarCaracteres.TipoEntrada.TEXTO));
		txtCidade.setColumns(10);
		txtCidade.setBounds(100, 241, 156, 20);
		panel.add(txtCidade);
		
		JLabel lblNewLabel_1_3_4 = new JLabel("Estado:");
		lblNewLabel_1_3_4.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_4.setBounds(10, 273, 49, 14);
		panel.add(lblNewLabel_1_3_4);
		
		txtEstado = new JTextField();
		txtEstado.setDocument(new LimitarCaracteres(20, LimitarCaracteres.TipoEntrada.TEXTO));
		txtEstado.setColumns(20);
		txtEstado.setBounds(100, 272, 156, 20);
		panel.add(txtEstado);
		
		JLabel lblNewLabel_1_3 = new JLabel("Senha:");
		lblNewLabel_1_3.setBounds(10, 301, 49, 14);
		panel.add(lblNewLabel_1_3);
		lblNewLabel_1_3.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		
		//Dar um limite de 8 caracteres
		psSenha = new JPasswordField();
		psSenha.setBounds(100, 300, 156, 20);
		panel.add(psSenha);
		
		txtRua = new JTextField();
		txtRua.setColumns(10);
		txtRua.setBounds(100, 181, 156, 20);
		panel.add(txtRua);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Rua:");
		lblNewLabel_1_3_1_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3_1_1.setBounds(10, 182, 49, 14);
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
                }catch(Exception erro) {
                	lblCepErro.setVisible(true);
                }
            }
        });	
	}
}
