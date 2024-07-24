package Telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexoes.CancelaConsulta;
import Conexoes.ProcessaAgenda;
import Conexoes.UsuarioCad;
import Conexoes.agendaConsulta;
import Conexoes.dadosConsulta;
import Sistema.envioEmail;

import com.toedter.calendar.JMonthChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class TelaAgendar extends JFrame {

    dadosConsulta consulta = new dadosConsulta();
    ProcessaAgenda agenda = new ProcessaAgenda();
	agendaConsulta confirmaAgendamento = new agendaConsulta();
	UsuarioCad dadosUsuario = new UsuarioCad();
	CancelaConsulta dados = new CancelaConsulta();

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
        cbHorario.addFocusListener(new FocusAdapter() {
        	public void focusGained(FocusEvent e) { 
            }
        });
        cbHorario.setBounds(150, 365, 180, 26);
        contentPane.add(cbHorario);
        cbHorario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });


        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {                
                try {
            		int id = agenda.getIdMedico(cbMedico.getSelectedItem().toString());
            		confirmaAgendamento.agendar(cbHorario.getSelectedItem().toString(), data, id);
                    TelaPrincipal principal = new TelaPrincipal();
                    envioEmail envio = new envioEmail();
                    
                    boolean hora = true;
                    if(cbHorario.getSelectedItem().toString() == "") {
                    	hora = false;
                    	JOptionPane.showMessageDialog(null, "Selecione todas as opções e tente novamente!");
                    }
                    
	                if(consulta.dadosEmail() && hora) {
	                    String dadosEmail = "Olá " + consulta.getNomeUser() + "\n" +
		                		"Sua consulta foi agendada com sucesso! Abaixo vou te fornecer mais informações: " + "\n" +
		                		"Nome do médico: " + consulta.getNomeMedico() + "\n" +
		                		"Especialidade: " + consulta.getEspecialidadeConsulta() + "\n" +
		                		"Unidade: " + consulta.getUnidadeConsulta() + "\n" +
		                		"Id da consulta: " + consulta.getId() + "\n" +
		                		"Obs: Caso deseje cancelar a consulta, é necessário que informe o id da consulta no sistema, na parte de cancelamento de consultas";	
	                    if(dadosUsuario.dadosUsuario()) {
	                        envio.email(dadosUsuario.getEmail(), dadosEmail);
	                    }
	                    
	            		JOptionPane.showMessageDialog(null, "Consulta Agendada! Enviei as informações de sua consulta no seu email!");
	                    principal.setVisible(true);
	                    dispose();
	
	                }
                }catch(Exception err) {
                	JOptionPane.showMessageDialog(null, "Selecione todas as opções e tente novamente!");
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
        dcData.setMinSelectableDate(new Date());
        dcData.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    String[] horarios = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
                    SimpleDateFormat formatacao = new SimpleDateFormat("yyyy-MM-dd");
                    data = formatacao.format(dcData.getDate());

                    Date hoje = new Date();
                    SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
                    String dataHoje = formatData.format(hoje);
                    
                    List<String> listHorario = new ArrayList<>(Arrays.asList(horarios));
                    
                    if(data.equals(dataHoje)) {
                        LocalTime horaAtual = LocalTime.now();
                        listHorario.removeIf(horario -> LocalTime.parse(horario).isBefore(horaAtual));
                    }

                    if(confirmaAgendamento.verificaHorario(cbMedico.getSelectedItem().toString(), data)) {
                        List<String> horaDisponivel = confirmaAgendamento.getHorario(); 

                        for(int i = 0; i < horaDisponivel.size(); i++) {
                            String hora = horaDisponivel.get(i);
                            horaDisponivel.set(i, hora.substring(0, 5));
                        }

                        listHorario.removeAll(horaDisponivel);
                    }
                    
                    cbHorario.removeAllItems();
                    cbHorario.addItem("");
                    for(String horaFiltrada : listHorario) {
                        cbHorario.addItem(horaFiltrada);
                    }
                }
            }
        });
        dcData.setBounds(150, 305, 180, 26);
	    dcData.getDateEditor().getUiComponent().addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	            e.consume();
	        }
	        public void keyPressed(KeyEvent e) {
	            e.consume();
	        }
	        public void keyReleased(KeyEvent e) {
	            e.consume();
	        }
	    });
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
                	
                	List<String> resultEspecialidade = agenda.getEspecialidade();
                	HashSet<String> setEspecialidade = new HashSet<>(resultEspecialidade);
                	List<String> listaSemDuplicados = new ArrayList<>(setEspecialidade);
                	           	
                    for (String bancoEspecialidade : listaSemDuplicados) {
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
                cbUnidade.removeActionListener(cbUnidadeActionListener);
                cbMedico.removeActionListener(cbMedicoListener);
                cbUnidade.removeAllItems();
                cbUnidade.insertItemAt("", 0);
                cbUnidade.setSelectedIndex(0);
                cbMedico.removeAllItems();
                cbMedico.insertItemAt("", 0);
                cbMedico.setSelectedIndex(0);
                if(agenda.selecionaPorEspecialidade(cbEspecialidade.getSelectedItem().toString())) {
                	
                	List<String> resultUnidade = agenda.getUnidade();
                	HashSet<String> setUnidade = new HashSet<>(resultUnidade);
                	List<String> listaSemDuplicados = new ArrayList<>(setUnidade);
                	
                    for (String bancoUnidade : listaSemDuplicados) {
                        cbUnidade.addItem(bancoUnidade);
                    }
                    
                    for (String bancoMedico : agenda.getNome()) {         	
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
}