package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class dadosConsulta {
	agendaConsulta consulta = new agendaConsulta();
	UsuarioLogin idUser = new UsuarioLogin();
	
	
	private static List<String> nome = new ArrayList<>();
	private static List<String> especialidade = new ArrayList<>();
	private static List<String> unidade = new ArrayList<>();
	private static String nomeMedico, nomeUser, especialidadeConsulta, unidadeConsulta, hora, data;
	private static int idConsulta;
	
	public dadosConsulta() {
	}

	Connection conexao;
    public boolean acessaDadosMedico() {
        conexao = new Conexao().conexaoDB();

        try{ 
            String sql = "SELECT nome, especialidade, unidade FROM medicos";
            
            PreparedStatement pstm = conexao.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            // Utilizando Set para evitar duplicações
            Set<String> especialidadeSet = new HashSet<>();
            Set<String> unidadeSet = new HashSet<>();
            
            while(rs.next()) {
                nome.add(rs.getString("nome"));
                especialidadeSet.add(rs.getString("especialidade"));
                unidadeSet.add(rs.getString("unidade"));
            }
            
            especialidade.addAll(especialidadeSet);
            unidade.addAll(unidadeSet);

            return true;

        } catch(SQLException erro) {
            JOptionPane.showConfirmDialog(null, "dadosConsulta: " + erro);
            return false;
        }
    }
    
    public boolean dadosEmail() {
        conexao = new Conexao().conexaoDB();

        try { 
            String sql = "SELECT a.id, m.nome AS nome_medico, u.nome AS nome_usuario, m.especialidade, m.unidade, a.hora, a.data_consulta " +
                         "FROM medicos AS m " +
                         "JOIN agendamento AS a ON a.id_medicos = m.id " +
                         "JOIN usuarios AS u ON a.id_usuarios = u.id " +
                         "WHERE a.id_usuarios = ? " +
                         "ORDER BY a.id DESC " +
                         "LIMIT 1";
            
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, idUser.getIdUsuario());
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
            	idConsulta = rs.getInt("id");
                nomeMedico = rs.getString("nome_medico");
                nomeUser = rs.getString("nome_usuario");
                especialidadeConsulta = rs.getString("especialidade");
                unidadeConsulta = rs.getString("unidade");
                hora = rs.getString("hora");
                data = rs.getString("data_consulta");
                return true;
            } else {
                return false;
            }
        } catch(SQLException erro) {
            JOptionPane.showConfirmDialog(null, "dadosEmail: " + erro);
            return false;
        }
    }
	
    public static int getId() {
    	return idConsulta;
    }
    
	public static String getNomeMedico() {
		return nomeMedico;
	}

	public static String getNomeUser() {
		return nomeUser;
	}

	public static String getEspecialidadeConsulta() {
		return especialidadeConsulta;
	}


	public static String getUnidadeConsulta() {
		return unidadeConsulta;
	}


	public static String getHora() {
		return hora;
	}


	public static String getData() {
		return data;
	}

	public List<String> getNome() {
		return dadosConsulta.nome;
	}
	public List<String> getEspecialidade() {
		return dadosConsulta.especialidade;
	}
	public List<String> getUnidade() {
		return dadosConsulta.unidade;
	}
	
	
}
