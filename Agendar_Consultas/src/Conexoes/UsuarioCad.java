package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import Sistema.Usuario;

public class UsuarioCad {

    UsuarioLogin idUser = new UsuarioLogin();
    
    private static String nome;
    private static String email;
    private static String cep;
    private static String rua;
    private static String bairro;
    private static String cidade;
    private static String estado;
    private static String senha;
    private static String data;
    private static String cpf;
    private static Date dataNascimento;
    
    Connection conexao;
    
    public boolean cadastrarUsuario(Usuario objcadastra) {
        conexao = new Conexao().conexaoDB();
        
        try { 
            String sql = "INSERT INTO usuarios (nome, email, senha, data_nascimento, cpf, rua, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            
            pstm.setString(1, objcadastra.getNome());
            pstm.setString(2, objcadastra.getEmail());
            pstm.setString(3, objcadastra.getSenha());
            pstm.setString(4, objcadastra.getNascimento());
            pstm.setString(5, objcadastra.getCpf());
            pstm.setString(6, objcadastra.getRua());
            pstm.setString(7, objcadastra.getBairro());
            pstm.setString(8, objcadastra.getCidade());
            pstm.setString(9, objcadastra.getEstado());
            pstm.setString(10, objcadastra.getCEP());
            
            pstm.executeUpdate();
            return true;
        } catch(SQLException erro) {
            JOptionPane.showConfirmDialog(null, "UsuarioLogin: " + erro);
            return false;
        }
    }
    
    public boolean dadosUsuario() {
        conexao = new Conexao().conexaoDB();
        
        try { 
            String sql = "SELECT nome, email, cep, rua, bairro, cidade, estado, senha, DATE_FORMAT(data_nascimento, '%d/%m/%Y') as data_nascimento, cpf FROM usuarios WHERE id = ?";
            
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, idUser.getIdUsuario());
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()) {
                nome = rs.getString("nome");
                email = rs.getString("email");
                cep = rs.getString("cep");
                rua = rs.getString("rua");
                bairro = rs.getString("bairro");
                cidade = rs.getString("cidade");
                estado = rs.getString("estado");
                senha = rs.getString("senha");
                data = rs.getString("data_nascimento");
                cpf = rs.getString("cpf");
               
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataNascimento = sdf.parse(data);
            }

            return true;
        } catch(SQLException erro) {
            JOptionPane.showConfirmDialog(null, "UsuarioLogin: " + erro);
            return false;
        } catch(ParseException e) {
            JOptionPane.showConfirmDialog(null, "Erro ao converter data: " + e);
            return false;
        }
    }
    
    public String getNome() {
        return UsuarioCad.nome;
    }
    public String getEmail() {
        return UsuarioCad.email;
    }
    public String getCep() {
        return UsuarioCad.cep;
    }
    public String getRua() {
        return UsuarioCad.rua;
    }
    public String getBairro() {
        return UsuarioCad.bairro;
    }
    public String getCidade() {
        return UsuarioCad.cidade;
    }
    public String getEstado() {
        return UsuarioCad.estado;
    }
    public String getSenha() {
        return UsuarioCad.senha;
    }
    public Date getDataNascimento() {
        return UsuarioCad.dataNascimento;
    }
    public String getCPF() {
        return UsuarioCad.cpf;
    }
}
