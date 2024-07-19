package Telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Conexoes.CancelaConsulta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCancelar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextArea textInfo;
	
	CancelaConsulta cancela = new CancelaConsulta();

		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCancelar frame = new TelaCancelar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCancelar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 230, 201));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCancelarConsulta = new JLabel("Cancelar Consulta");
		lblCancelarConsulta.setBounds(35, 24, 278, 42);
		lblCancelarConsulta.setFont(new Font("Dialog", Font.BOLD, 30));
		contentPane.add(lblCancelarConsulta);
		
		JLabel lblInformeOId = new JLabel("Informe o ID da consulta:");
		lblInformeOId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblInformeOId.setBounds(35, 111, 208, 17);
		contentPane.add(lblInformeOId);
		
		txtID = new JTextField();
		txtID.setBounds(35, 140, 164, 31);
		contentPane.add(txtID);
		txtID.setColumns(10);
		txtID.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				exibeInfo();
			}

			public void removeUpdate(DocumentEvent e) {
				exibeInfo();
			}

			public void changedUpdate(DocumentEvent e) {
				exibeInfo();
			}

			private void exibeInfo() {
				String info;
				if(cancela.acessaConsulta(Integer.parseInt(txtID.getText()))) {
					textInfo.setText("");
					info = "Medico: " + cancela.getNome() + "\n" +
							"Especialidade: " + cancela.getEspecialidade() + "\n" +
							"Unidade: " + cancela.getUnidade() + "\n" +
							"Hora: " + cancela.getHora() + "\n" +
							"Data: " + cancela.getData();
				}else {
					info = "";
				}
				textInfo.append(info);
			}
		});
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cancela.cancelaConsulta(Integer.parseInt(txtID.getText()))) {
					JOptionPane.showMessageDialog(null, "Consulta cancelada com sucesso!");
	                TelaPrincipal voltar = new TelaPrincipal();
	                voltar.setVisible(true);
	                dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Verifique o ID da consulta e tente novamente!");				}
			}
		});
		btnConfirmar.setBounds(35, 385, 105, 27);
		contentPane.add(btnConfirmar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                TelaPrincipal voltar = new TelaPrincipal();
                voltar.setVisible(true);
                dispose();
			}
		});
		btnVoltar.setBounds(208, 385, 105, 27);
		contentPane.add(btnVoltar);
		
		textInfo = new JTextArea();
		textInfo.setBounds(35, 183, 278, 190);
		contentPane.add(textInfo);

	}
}
