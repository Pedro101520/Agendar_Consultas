package Telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Conexoes.HistoricoConsulta;

import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaHistorico extends JFrame {
	
	HistoricoConsulta historico = new HistoricoConsulta();

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
	
	public void executaInfo() {
		String[] columnNames = {"Especialidade", "Data da Consulta"};
        List<String> infoEspecialidade = new ArrayList<>();
        List<String> infoData = new ArrayList<>();
		
		if(historico.historicoInfo()) {
			System.out.println("Pedro");
	        infoEspecialidade = historico.getEspecialidade();
	        infoData = historico.getData();
	        
	        Object[][] infoConsulta = new Object[infoEspecialidade.size()][2];
	        
	        for (int i = 0; i < infoEspecialidade.size(); i++) {
	            infoConsulta[i][0] = infoEspecialidade.get(i);
	            infoConsulta[i][1] = infoData.get(i);
	        }
	        
			table = new JTable(new DefaultTableModel(infoConsulta, columnNames));
			table.setBounds(76, 100, 300, 300);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(28, 100, 300, 300);
			contentPane.add(scrollPane);
		}else {
			System.out.println("Não há informações");
		}
	}
}
