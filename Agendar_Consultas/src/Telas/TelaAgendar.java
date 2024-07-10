package Telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Conexoes.ProcessaAgenda;
import Conexoes.agendaConsulta;
import Conexoes.dadosConsulta;
import Sistema.ConverteData;

import com.toedter.calendar.JMonthChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class TelaAgendar extends JFrame {

    dadosConsulta consulta = new dadosConsulta();
    ProcessaAgenda agenda = new ProcessaAgenda();
	agendaConsulta confirmaAgendamento = new agendaConsulta();

    private static JComboBox<String> cbEspecialidade;
    private static JComboBox<String> cbUnidade;
    private static JComboBox<String> cbMedico;
    private static JDateChooser dcData;
    private static String data;
    private static List<String> nome = new ArrayList<>();
    private static List<String> especialidade = new ArrayList<>();
    private static List<String> unidade = new ArrayList<>();
    private static boolean verificaUnidade = true;
    private static boolean verificaEspecialidade = true;
    private static boolean verificaMedico = true;


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
        lblNewLabel_1.setBounds(30, 368, 82, 17);
        contentPane.add(lblNewLabel_1);
        
        
        if (consulta.acessaDadosMedico()) {
            nome = consulta.getNome();
            especialidade = consulta.getEspecialidade();
            unidade = consulta.getUnidade();
        } else {
            System.out.println("Elemento não encontrado");
        }

        cbEspecialidade = new JComboBox<>();
        cbEspecialidade.setBounds(150, 185, 180, 26);
        contentPane.add(cbEspecialidade);
        cbEspecialidade.addItem("");
        for (String especialidadeItem : especialidade) {
            cbEspecialidade.addItem(especialidadeItem);
        }
        cbEspecialidade.addActionListener(cbEspecialidadeListener);

        cbMedico = new JComboBox<>();
        cbMedico.setBounds(150, 245, 180, 26);
        contentPane.add(cbMedico);
        cbMedico.addItem("");
        for (String nomeItem : nome) {
            cbMedico.addItem(nomeItem);
        }
        cbMedico.addActionListener(cbMedicoListener);

        cbUnidade = new JComboBox<>();
        cbUnidade.setBounds(150, 125, 180, 26);
        contentPane.add(cbUnidade);
        cbUnidade.addItem("");
        for (String unidadeItem : unidade) {
            cbUnidade.addItem(unidadeItem);
        }
        cbUnidade.addActionListener(cbUnidadeActionListener);

        
        // Logica do Horario
        JComboBox<String> cbHorario = new JComboBox<>();
        cbHorario.setModel(new DefaultComboBoxModel(new String[] {"", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"}));
        cbHorario.setBounds(150, 365, 180, 26);
        contentPane.add(cbHorario);
        cbHorario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        
        
        
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

                int idMedico = agenda.getIdMedico(cbMedico.getSelectedItem().toString());
        		
        		if(confirmaAgendamento.verificaHorario(cbMedico.getSelectedItem().toString(), data)) {
        			JOptionPane.showMessageDialog(null, "Horário indisponivel");
        		}else {
        			JOptionPane.showMessageDialog(null, "Consulta Agendada");
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
					dispose();
        		}
        		
        	}
        });
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
        
        JLabel lblData = new JLabel("Data:");
        lblData.setFont(new Font("Dialog", Font.BOLD, 16));
        lblData.setBounds(30, 308, 60, 17);
        contentPane.add(lblData);
        
        dcData = new JDateChooser();
        dcData.addPropertyChangeListener(new PropertyChangeListener() {
        	public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    SimpleDateFormat formatacao = new SimpleDateFormat("yyyy-MM-dd");
                    data = formatacao.format(dcData.getDate());
                    }
                }
        });
        dcData.setBounds(150, 305, 180, 26);
        contentPane.add(dcData);
    }
    
    // Lógica unidade
    private ActionListener cbUnidadeActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (cbUnidade.getSelectedIndex() == 0) {
            	cbUnidade.removeAllItems();
            	cbEspecialidade.removeAllItems();
            	cbMedico.removeAllItems();
            	cbMedico.insertItemAt("", 0);
            	cbEspecialidade.insertItemAt("", 0);
            	cbEspecialidade.setSelectedIndex(0);
                for (String especialidadeItem : especialidade) {
                    cbEspecialidade.addItem(especialidadeItem);
                }
                for (String unidadeItem : unidade) {
                    cbUnidade.addItem(unidadeItem);
                }
                for (String medicoItem : nome) {
                    cbMedico.addItem(medicoItem);
                }
                agenda.getEspecialidade().clear();
                agenda.getNome().clear();
                verificaEspecialidade = true;
                verificaUnidade = true;
            }else if(cbUnidade.getSelectedIndex() > 0 && verificaUnidade){
                cbEspecialidade.removeActionListener(cbEspecialidadeListener);
                cbMedico.removeActionListener(cbMedicoListener);
                cbEspecialidade.removeAllItems();
                cbEspecialidade.insertItemAt("", 0);
                cbEspecialidade.setSelectedIndex(0);
                cbMedico.removeAllItems();
                cbMedico.insertItemAt("", 0);
                cbMedico.setSelectedIndex(0);
                if(agenda.selecionaPorUnidade(cbUnidade.getSelectedItem().toString())) {
                    for (String bancoEspecialidade : agenda.getEspecialidade()) {
                        cbEspecialidade.addItem(bancoEspecialidade);
                    }
                    
                    for (String bancoMedico : agenda.getNome()) {
                        cbMedico.addItem(bancoMedico);
                    }
                    
                    agenda.getEspecialidade().clear();
                    agenda.getNome().clear();
                }
                cbEspecialidade.addActionListener(cbEspecialidadeListener);
                cbMedico.addActionListener(cbMedicoListener);
                verificaEspecialidade = false;
                verificaMedico = false;
            }
        }
    };
    
    // Lógica Especialidade
    private ActionListener cbEspecialidadeListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (cbEspecialidade.getSelectedIndex() == 0) {
            	cbUnidade.removeAllItems();
            	cbUnidade.insertItemAt("", 0);
            	cbMedico.removeAllItems();
            	cbMedico.insertItemAt("", 0);
                for (String unidadeItem : unidade) {
                    cbUnidade.addItem(unidadeItem);
                }
                for (String especialidadeItem : especialidade) {
                    cbEspecialidade.addItem(especialidadeItem);
                }
                for (String medicoItem : nome) {
                	cbMedico.addItem(medicoItem);
                }
                agenda.getUnidade().clear();
                agenda.getNome().clear();
            	verificaUnidade = true;
            	verificaEspecialidade = true;
            } else if(cbEspecialidade.getSelectedIndex() > 0 && verificaEspecialidade){
            	
//            	for(String hora : confirmaAgendamento.getHorario()) {
//            		System.out.println(hora);
//            	}
            	
                cbUnidade.removeActionListener(cbUnidadeActionListener);
                cbMedico.removeActionListener(cbMedicoListener);
                cbUnidade.removeAllItems();
                cbUnidade.insertItemAt("", 0);
                cbUnidade.setSelectedIndex(0);
                cbMedico.removeAllItems();
                cbMedico.insertItemAt("", 0);
                cbMedico.setSelectedIndex(0);
                if(agenda.selecionaPorEspecialidade(cbEspecialidade.getSelectedItem().toString())) {
                    for (String bancoUnidade : agenda.getUnidade()) {
                        cbUnidade.addItem(bancoUnidade);
                    }
                    for (String bancoMedico : agenda.getNome()) {
//                    	System.out.println(bancoMedico);
                    	
                    	
                        cbMedico.addItem(bancoMedico);
                    }
                    
                    agenda.getUnidade().clear();
                    agenda.getNome().clear();
                }
                cbUnidade.addActionListener(cbUnidadeActionListener);
                cbMedico.addActionListener(cbMedicoListener);
                verificaUnidade = false;
                verificaMedico = false;
            }
        }
    };
    
    // Loǵica Médico
    private ActionListener cbMedicoListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (cbMedico.getSelectedIndex() == 0) {
            	cbMedico.removeAllItems();
            	cbMedico.insertItemAt("", 0);
            	cbMedico.setSelectedIndex(0);
            	cbEspecialidade.removeAllItems();
            	cbEspecialidade.insertItemAt("", 0);
            	cbEspecialidade.setSelectedIndex(0);
                for (String medicoItem : nome) {
                    cbMedico.addItem(medicoItem);
                }
                for (String unidadeItem : unidade) {
                    cbUnidade.addItem(unidadeItem);
                }
                for (String especialidadeItem : especialidade) {
                    cbEspecialidade.addItem(especialidadeItem);
                }
                agenda.getUnidade().clear();
                agenda.getEspecialidade().clear();
                verificaEspecialidade = true;
                verificaUnidade = true;
                verificaMedico = true;
            }else if(cbMedico.getSelectedIndex() > 0 && verificaMedico){
                cbEspecialidade.removeActionListener(cbEspecialidadeListener);
                cbUnidade.removeActionListener(cbUnidadeActionListener);
                cbEspecialidade.removeAllItems();
                cbEspecialidade.insertItemAt("", 0);
                cbEspecialidade.setSelectedIndex(0);
                cbUnidade.removeAllItems();
                cbUnidade.insertItemAt("", 0);
                cbUnidade.setSelectedIndex(0);
                if(agenda.selecionaPorNome(cbMedico.getSelectedItem().toString())) {
                	System.out.println("Teste");
                    for (String bancoEspecialidade : agenda.getEspecialidade()) {
                        cbEspecialidade.addItem(bancoEspecialidade);
                    }
                    
                    for (String bancoUnidade : agenda.getUnidade()) {
                        cbUnidade.addItem(bancoUnidade);
                    }
                    
                    agenda.getUnidade().clear();
                    agenda.getEspecialidade().clear();
                }
                cbEspecialidade.addActionListener(cbEspecialidadeListener);
                cbUnidade.addActionListener(cbUnidadeActionListener);
                verificaEspecialidade = false;
                verificaUnidade = false;
            }
        }
    };
    
    
    public String getData() {
    	return data;
    }
}