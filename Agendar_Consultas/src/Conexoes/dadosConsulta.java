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
	private static List<String> nome = new ArrayList<>();
	private static List<String> especialidade = new ArrayList<>();
	private static List<String> unidade = new ArrayList<>();
	
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
