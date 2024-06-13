package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

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
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Latitude\\Desktop\\Cancelar copiar.png"));
		btnNewButton.setForeground(new Color(200, 230, 201));
		btnNewButton.setBackground(new Color(200, 230, 201));
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setOpaque(false);
        btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(50, 70, 86, 90);
		
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnNewButton.setContentAreaFilled(true);
                btnNewButton.setOpaque(true);
                btnNewButton.setBackground(new Color(150, 150, 150));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btnNewButton.setContentAreaFilled(false);
                btnNewButton.setOpaque(false);
                btnNewButton.setBackground(new Color(200, 230, 201));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton.setContentAreaFilled(true);
                btnNewButton.setOpaque(true);
                btnNewButton.setBackground(new Color(220, 220, 220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton.setContentAreaFilled(false);
                btnNewButton.setOpaque(false);
                btnNewButton.setBackground(new Color(200, 230, 201));
            }
        });

		
		contentPane.add(btnNewButton);
	}
}
