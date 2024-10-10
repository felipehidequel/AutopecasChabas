package model.entity;

public class Cliente extends Pessoa {
    private String telefone;
    private String cpf;

    public Cliente(String nome, String telefone, String cpf) {
        super(nome);
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getTelefone(){
        return (telefone);
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return super.toString() + "Telfone: " + telefone + " CPF: " + cpf;
    }
}