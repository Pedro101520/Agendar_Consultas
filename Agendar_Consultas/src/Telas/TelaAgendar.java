package Telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexoes.dadosConsulta;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaAgendar extends JFrame {
	
	dadosConsulta consulta = new dadosConsulta();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAgendar frame = new TelaAgendar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAgendar() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(200, 230, 201));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAgendarConsulta = new JLabel("Agendar Consulta");
		lblAgendarConsulta.setFont(new Font("Dialog", Font.BOLD, 30));
		lblAgendarConsulta.setBounds(38, 45, 286, 42);
		contentPane.add(lblAgendarConsulta);
		
		JLabel lblUnidade = new JLabel("Unidade:");
		lblUnidade.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUnidade.setBounds(38, 188, 82, 23);
		contentPane.add(lblUnidade);
		
		JLabel lblNewLabel = new JLabel("Especialidade:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(38, 128, 113, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Horário:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBounds(38, 248, 82, 17);
		contentPane.add(lblNewLabel_1);
		
		List<String> nome = new ArrayList<String>();
		String especialidade = "";
		String unidade = "";
		if(consulta.autenticacaoUsuario()) {
			nome = consulta.getNome();
			especialidade = consulta.getEspecialidade();
			unidade = consulta.getUnidade();
			System.out.println(nome.size());
		}else {
			System.out.println("Elemento não encontrado");
		}
		
		JComboBox cbEspecialidade = new JComboBox();
		cbEspecialidade.setBounds(169, 125, 155, 26);
		contentPane.add(cbEspecialidade);
        for (String nomeItem : nome) {
            cbEspecialidade.addItem(nomeItem + " - " + especialidade);
        }
		
		JComboBox cbUnidade = new JComboBox();
		cbUnidade.setModel(new DefaultComboBoxModel(new String[] {unidade}));
		cbUnidade.setBounds(169, 188, 155, 26);
		contentPane.add(cbUnidade);
		
		JComboBox cbHorario = new JComboBox();
		cbHorario.setBounds(169, 245, 155, 26);
		contentPane.add(cbHorario);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(38, 411, 105, 27);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(219, 411, 105, 27);
		contentPane.add(btnCancelar);
	}
}
