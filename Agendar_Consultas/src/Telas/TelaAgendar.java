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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaAgendar extends JFrame {

    dadosConsulta consulta = new dadosConsulta();
    ProcessaAgenda agenda = new ProcessaAgenda();

    private static JComboBox<String> cbEspecialidade;
    private static JComboBox<String> cbUnidade;
    private static JComboBox<String> cbMedico;
    private static List<String> nome = new ArrayList<>();
    private static List<String> especialidade = new ArrayList<>();
    private static List<String> unidade = new ArrayList<>();
    private static boolean verificaMedico = false;
    private static boolean verificaEspecialidade = false;
    private static boolean verificaUnidade = false;

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
        cbEspecialidade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cbEspecialidade.getSelectedIndex() == 0) {
                    System.out.println("Especialidade não selecionada");
                } else {
                    // Remover o ActionListener do cbUnidade temporariamente
                    cbUnidade.removeActionListener(cbUnidadeActionListener);

                    // Limpar e adicionar novos itens ao cbUnidade
                    cbUnidade.removeAllItems();
                    cbUnidade.addItem("Pedro");
                    // Aqui você pode adicionar lógica para preencher cbUnidade baseado na especialidade selecionada
                    // Exemplo: cbUnidade.addItem("Nova unidade");

                    // Adicionar o ActionListener de volta ao cbUnidade
                    cbUnidade.addActionListener(cbUnidadeActionListener);
                    verificaUnidade = true;
                    cbUnidade.insertItemAt("", 0);
                }
            }
        });

        cbMedico = new JComboBox<>();
        cbMedico.setBounds(150, 245, 180, 26);
        contentPane.add(cbMedico);
        cbMedico.addItem("");
        for (String nomeItem : nome) {
            cbMedico.addItem(nomeItem);
        }
        cbMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cbMedico.getSelectedIndex() == 0) {
                    System.out.println("Médico não selecionado");
                }
            }
        });

        cbUnidade = new JComboBox<>();
        cbUnidade.setBounds(150, 125, 180, 26);
        contentPane.add(cbUnidade);
        cbUnidade.addItem("");
        for (String unidadeItem : unidade) {
            cbUnidade.addItem(unidadeItem);
        }
        cbUnidade.addActionListener(cbUnidadeActionListener);

        JComboBox<String> cbHorario = new JComboBox<>();
        cbHorario.setBounds(150, 305, 180, 26);
        contentPane.add(cbHorario);
        cbHorario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para o horário, se necessário
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

    // ActionListener para cbUnidade
    private ActionListener cbUnidadeActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (cbUnidade.getSelectedIndex() == 0 && verificaUnidade) {
            	cbUnidade.insertItemAt("", 0);
                System.out.println("Unidade não selecionada");
                // Aqui você pode adicionar lógica para o que fazer se a unidade não estiver selecionada
                // Exemplo: cbUnidade.addItem("Nova unidade");
                verificaUnidade = false;
            }
        }
    };
}