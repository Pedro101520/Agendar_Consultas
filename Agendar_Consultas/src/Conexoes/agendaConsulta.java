package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class agendaConsulta {
    
    UsuarioLogin usuario = new UsuarioLogin();
    
    private static String nomeMedico;
    private static int idConsulta;
    private static List<String> horarioDisponivel = new ArrayList<>();
    
    public agendaConsulta() {
    }

    Connection conexao;

    public boolean verificaHorario(String medico, String data) {
        conexao = new Conexao().conexaoDB();

        try {
            String sql = 
                "SELECT a.hora " +
                "FROM medicos AS m, agendamento AS a " +
                "WHERE m.nome = ? AND a.data_consulta = ? " +
                "AND a.id_medicos = m.id";
            
            PreparedStatement pstm = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, medico);
            pstm.setString(2, data);
            ResultSet rs = pstm.executeQuery();

            horarioDisponivel.clear();

            rs.last();
            int qtde = rs.getRow();
            rs.beforeFirst();

            if (qtde > 0) {
                while (rs.next()) {
                    Time time = rs.getTime("hora");
                    horarioDisponivel.add(time.toString());
                }
                return true;
            } else {
                return false;
            }
            
        } catch(SQLException erro) {
            JOptionPane.showConfirmDialog(null, "Erro ao verificar horário: " + erro);
            return false;
        }
    }

    
    public void agendar(String horario, String data, int idMedico) {
        conexao = new Conexao().conexaoDB();
        
        try {
            String sql = "INSERT INTO agendamento (id_medicos, id_usuarios, hora, data_consulta) VALUES (?, ?, ?, ?)";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            
            pstm.setInt(1, idMedico);
            pstm.setInt(2, usuario.getIdUsuario());
            pstm.setString(3, horario);
            pstm.setString(4, data);
            pstm.executeUpdate();
        } catch(SQLException erro) {
            JOptionPane.showConfirmDialog(null, "Erro ao agendar consulta: " + erro);
        }
    }
    
    public String getNome() {
        return agendaConsulta.nomeMedico;
    }
    
    public int getId() {
    	return agendaConsulta.idConsulta;
    }
    
    public List<String> getHorario() {
        return agendaConsulta.horarioDisponivel;
    }
}
