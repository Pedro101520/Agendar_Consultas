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
    private static List<String> horarioDisponivel = new ArrayList<>();
    
    public agendaConsulta() {
    }

    Connection conexao;

    public boolean verificaHorario(String medico, String data) {
        conexao = new Conexao().conexaoDB();
//        SimpleDateFormat formatacao = new SimpleDateFormat("yyyy-MM-dd");
//        String data = formatacao.format(dcData.getDate());
//        int idMedico = agenda.getIdMedico(cbMedico.getSelectedItem().toString());
        
        try {
            String sql = 
                "SELECT a.hora " +
                "FROM medicos AS m, agendamento AS a " +
                "WHERE m.nome = ? AND a.data_consulta = ? " +
                "AND a.id_medicos = m.id";
            
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, medico);
            pstm.setString(2, data);
            ResultSet rs = pstm.executeQuery();
            
            int qtde = 0;
            while(rs.next()) {
                Time time = rs.getTime("hora");
                horarioDisponivel.add(time.toString());
                qtde++;
            }
            
            if(qtde > 0) {
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
    
    public List<String> getHorario() {
        return agendaConsulta.horarioDisponivel;
    }
}
