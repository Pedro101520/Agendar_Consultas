package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
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
		lblCadastro.setBounds(104, 63, 140, 44);
		contentPane.add(lblCadastro);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(240, 249, 242));
		panel.setBounds(57, 118, 222, 233);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 57, 49, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(56, 56, 156, 20);
		panel.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Data Nasc");
		lblNewLabel_1_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 88, 69, 14);
		panel.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(78, 87, 134, 20);
		panel.add(textField_1);
		
		JButton btnLogin = new JButton("Confirmar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(10, 199, 89, 23);
		panel.add(btnLogin);
		
		JButton btnCad = new JButton("Voltar");
		btnCad.setBounds(109, 199, 103, 23);
		panel.add(btnCad);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(56, 26, 156, 20);
		panel.add(textField_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nome:");
		lblNewLabel_1_1_1.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(10, 27, 49, 14);
		panel.add(lblNewLabel_1_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(56, 113, 156, 20);
		panel.add(textField_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("CPF:");
		lblNewLabel_1_2.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 114, 49, 14);
		panel.add(lblNewLabel_1_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(56, 144, 156, 20);
		panel.add(textField_4);
		
		JLabel lblNewLabel_1_3 = new JLabel("Senha:");
		lblNewLabel_1_3.setFont(new Font("Leelawadee", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(10, 145, 49, 14);
		panel.add(lblNewLabel_1_3);
	}
}
