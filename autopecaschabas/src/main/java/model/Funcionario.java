package model;

class Funcionario extends Pessoa{
    private String login;
    private String senha;
    private Boolean gerente;

    public Funcionario(String id, String nome, String login, String senha, Boolean gerente){
        super(id, nome);
        this.login = login;
        this.senha = senha;
        this.gerente = gerente;
    }

    public String getLogin(){
        return (login);
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getSenha(){
        return (senha);
    }

    public void setSenha(String senha){
        this.login = login;
    }

    public boolean getGerente(){
        return gerente;
    }

    public void setGerente(Boolean gerente){
        this.gerente = gerente;
    }

    @Override
    public String toString(){
        return "";
    }

}