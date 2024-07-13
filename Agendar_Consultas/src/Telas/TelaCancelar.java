package Telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaCancelar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;

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
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setBounds(35, 192, 278, 171);
		contentPane.add(lblInfo);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(35, 385, 105, 27);
		contentPane.add(btnConfirmar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(208, 385, 105, 27);
		contentPane.add(btnVoltar);

	}
}
