package Telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaHistorico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaHistorico frame = new TelaHistorico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaHistorico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 230, 201));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] columnNames = {"Especialidade", "Data da Consulta"};
		Object[][] data = {
			{"Brasil", "São Paulo"},
			{"Inglaterra", "Londres"},
			{"Japão", "Tóquio"}
		};
		
		table = new JTable(new DefaultTableModel(data, columnNames));
		table.setBounds(76, 100, 300, 300);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(28, 100, 300, 300);
		contentPane.add(scrollPane);
		
		JLabel lblHistricoDeConsultas = new JLabel("Histórico de Consultas");
		lblHistricoDeConsultas.setFont(new Font("Dialog", Font.BOLD, 20));
		lblHistricoDeConsultas.setBounds(65, 48, 230, 17);
		contentPane.add(lblHistricoDeConsultas);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                TelaPrincipal voltar = new TelaPrincipal();
                voltar.setVisible(true);
                dispose();
			}
		});
		btnVoltar.setBounds(125, 410, 105, 27);
		contentPane.add(btnVoltar);
	}
}
