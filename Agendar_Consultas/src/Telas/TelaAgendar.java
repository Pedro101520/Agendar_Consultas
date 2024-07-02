package Telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexoes.ProcessaAgenda;
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
	ProcessaAgenda agenda = new ProcessaAgenda();
	
	private static JComboBox<String> cbEspecialidade = new JComboBox<>();
	private static JComboBox<String> cbUnidade = new JComboBox<>();
	private static JComboBox<String> cbMedico = new JComboBox<>();
    private static List<String> nome = new ArrayList<String>();
    private static List<String> especialidade = new ArrayList<String>();
    private static List<String> unidade = new ArrayList<String>();
    private static boolean verificaMedico = true;
    private static boolean verificaEspecialidade = true;
    private static boolean verificaUnidade = true;

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
		lblUnidade.setBounds(30, 128, 82, 23);
		contentPane.add(lblUnidade);
		
		JLabel lblNewLabel = new JLabel("Especialidade:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(30, 188, 113, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Horário:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBounds(30, 308, 82, 17);
		contentPane.add(lblNewLabel_1);
		
		if(consulta.acessaDadosMedico()) {
			nome = consulta.getNome();
			especialidade = consulta.getEspecialidade();
			unidade = consulta.getUnidade();
		}else {
			System.out.println("Elemento não encontrado");
		}
		
		cbEspecialidade = new JComboBox();
		cbEspecialidade.setBounds(150, 185, 180, 26);
		contentPane.add(cbEspecialidade);
		cbEspecialidade.addItem("");
		cbEspecialidade.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (verificaEspecialidade) {
		            verificaMedico = false;
		            verificaUnidade = false;
		            
		            if (cbEspecialidade.getSelectedIndex() > 0 && agenda.selecionaPorEspecialidade(cbEspecialidade.getSelectedItem().toString())) {
		                System.out.println("If especialidade");
		                cbMedico.removeAllItems();
		                cbUnidade.removeAllItems();
		                cbMedico.addItem("");
		                cbUnidade.addItem("");
		                                    
		                for (String medico : agenda.getNome()) {
		                    cbMedico.addItem(medico);
		                }
		                for (String unidade : agenda.getUnidade()) {
		                    cbUnidade.addItem(unidade);
		                }
		            } else if (cbEspecialidade.getSelectedIndex() == 0) {
		                System.out.println("Else if especialidade");
		                cbMedico.removeAllItems();
		                cbUnidade.removeAllItems();
		                cbMedico.addItem("");
		                cbUnidade.addItem("");

		                for (String nomeItem : nome) {
		                    cbMedico.addItem(nomeItem);
		                }
		                for (String unidadeItem : unidade) {
		                    cbUnidade.addItem(unidadeItem);
		                }
		            }
		            
		            verificaMedico = true;
		            verificaUnidade = true;
		        }
		    }
		});
        for (String especialidadeItem : especialidade) {
            cbEspecialidade.addItem(especialidadeItem);
        }
		
		cbMedico = new JComboBox();
		cbMedico.setBounds(150, 245, 180, 26);
		contentPane.add(cbMedico);
		cbMedico.addItem("");
		cbMedico.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (verificaMedico) {
		            verificaEspecialidade = false;
		            verificaUnidade = false;
		            
		            if (cbMedico.getSelectedIndex() > 0 && agenda.selecionaPorNome(cbMedico.getSelectedItem().toString())) {
		                System.out.println("If medico");
		                cbEspecialidade.removeAllItems();
		                cbUnidade.removeAllItems();
		                cbEspecialidade.addItem("");
		                cbUnidade.addItem("");
		                                    
		                for (String especialidade : agenda.getEspecialidade()) {
		                    cbEspecialidade.addItem(especialidade);
		                }
		                for (String unidade : agenda.getUnidade()) {
		                    cbUnidade.addItem(unidade);
		                }
		            } else if (cbMedico.getSelectedIndex() == 0) {
		                System.out.println("Else if medico");
		                cbEspecialidade.removeAllItems();
		                cbUnidade.removeAllItems();
		                cbEspecialidade.addItem("");
		                cbUnidade.addItem("");
		            }
		            
		            verificaEspecialidade = true;
		            verificaUnidade = true;
		        }
		    }
		});
		for (String nomeItem: nome) {
			cbMedico.addItem(nomeItem);
		}
		
		cbUnidade = new JComboBox();
		cbUnidade.setBounds(150, 125, 180, 26);
		contentPane.add(cbUnidade);
		cbUnidade.addItem("");
		cbUnidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		for (String unidadeItem : unidade) {
			cbUnidade.addItem(unidadeItem);
		}
		
		JComboBox cbHorario = new JComboBox();
		cbHorario.setBounds(150, 305, 180, 26);
		contentPane.add(cbHorario);
		cbHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
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
		
		JLabel lblNewLabel_2 = new JLabel("Médico:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2.setBounds(30, 248, 92, 17);
		contentPane.add(lblNewLabel_2);
	}
}
