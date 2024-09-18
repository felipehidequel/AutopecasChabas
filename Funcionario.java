public class Funcionario {
    private int idFuncionario;
    private String nome;
    private String login;
    private String senha;
    private boolean ehGerente;

    Funcionario(int id, String nome, String login, String senha, boolean ehGerente){
        this.idFuncionario = id;
        this.nome = nome;
        this.senha = senha;
        this.ehGerente = this.ehGerente;
    }

    public void setEhGerente(boolean ehGerente) {
        this.ehGerente = ehGerente;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void editaFuncionario(int id, String nome, String login, String senha, boolean ehGerente){
        setIdFuncionario(id);
        setEhGerente(ehGerente);
        setNome(nome);
        setLogin(login);
        setSenha(senha);
    }

    @Override
    public String toString() {
        String estadoGerencia = this.ehGerente ? "é gerente." : "não é gerente.";
        return String.format("%s, id:%d, %s", this.nome, this.idFuncionario, estadoGerencia);
    }
}
