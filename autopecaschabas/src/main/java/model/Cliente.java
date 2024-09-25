class Cliente extends Pessoa{
    private String telefone;
    private String cpf;

    public Cliente(String id, String nome, String telefone, String cpf){
        super(id, nome);
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getTelefone(){
        return (telefone);
    } 
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getNome(){
        return (nome);
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return();
    }

}