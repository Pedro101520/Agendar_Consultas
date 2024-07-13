package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexoes.UsuarioLogin;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Choice;
import javax.swing.JDesktopPane;

public class TelaPrincipal extends JFrame {
	
	UsuarioLogin user = new UsuarioLogin();
	
	private void styleButton(JButton button) {
		button.setForeground(new Color(200, 230, 201));
		button.setBackground(new Color(200, 230, 201));
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setBorderPainted(false);
		
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setContentAreaFilled(true);
                button.setOpaque(true);
                button.setBackground(new Color(150, 150, 150));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setContentAreaFilled(false);
                button.setOpaque(false);
                button.setBackground(new Color(200, 230, 201));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setContentAreaFilled(true);
                button.setOpaque(true);
                button.setBackground(new Color(220, 220, 220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setContentAreaFilled(false);
                button.setOpaque(false);
                button.setBackground(new Color(200, 230, 201));
            }
        });
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 230, 201));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                TelaCancelar cancelar = new TelaCancelar();
                cancelar.setVisible(true);
                dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Cancelar.png")));
		btnCancelar.setBounds(206, 94, 86, 90);	
		styleButton(btnCancelar);
		contentPane.add(btnCancelar);
		
		JButton btnHistorico = new JButton("");
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHistorico.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Historico.png")));
		btnHistorico.setBounds(44, 240, 86, 77);
		styleButton(btnHistorico);
		contentPane.add(btnHistorico);
		
		JButton btnAgendar = new JButton("");
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAgendar agendar = new TelaAgendar();
				agendar.setVisible(true);
				dispose();
			}
		});
		btnAgendar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Agendar.png")));
		btnAgendar.setBounds(44, 95, 89, 77);
		styleButton(btnAgendar);
		contentPane.add(btnAgendar);
		
		JButton btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAtualizar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/atualizar.png")));
		btnAtualizar.setBounds(203, 245, 89, 72);
		styleButton(btnAtualizar);
		contentPane.add(btnAtualizar);
		
		JButton btnVoltar = new JButton("Sair");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(123, 400, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("Agendar \r\nConsulta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(33, 170, 119, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cancelar Consulta");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(198, 180, 119, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Histórico de Consultas");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(22, 326, 140, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Atualizar Dados");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(200, 326, 102, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblOla = new JLabel("");
		lblOla.setHorizontalAlignment(SwingConstants.CENTER);
		lblOla.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOla.setBounds(0, 32, 336, 40);
		String msgOla = user.getNome();
		lblOla.setText("Olá " + msgOla + " Seja bem vindo(a)");
		contentPane.add(lblOla);
	}
}
