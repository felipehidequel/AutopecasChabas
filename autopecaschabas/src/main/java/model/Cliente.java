package model;

class Cliente extends Pessoa {
    private String telefone;
    private String cpf;
    private String nome;

    public Cliente(String id, String nome, String telefone, String cpf) {
        super(id, nome);
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getTelefone() {
        return (telefone);
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return (this.nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "";
    }
}