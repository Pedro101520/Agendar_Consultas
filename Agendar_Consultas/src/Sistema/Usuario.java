package Sistema;

public class Usuario {
//	private int id_usuario;
	private String nome;
	private String email;
	private String senha; 
	private String nascimento;
	private String cpf;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;

	public Usuario() {
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return this.nome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
	
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public String getNascimento() {
		return this.nascimento;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCpf() {
		return this.cpf;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getRua() {
		return this.rua;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getBairro() {
		return this.bairro;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCidade() {
		return this.cidade;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstado() {
		return this.estado;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenha() {
		return this.senha;
	}
	
}
