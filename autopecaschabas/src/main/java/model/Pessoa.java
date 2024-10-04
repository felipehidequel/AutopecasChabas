package model;

public class Pessoa{
    private int id;
    private String nome;

    public Pessoa(String nome){
        this.id = 0; // o id será criado no banco de dados e setado posteriomente
        this.nome = nome;
    }


    public int getId(){
        return (id);
    } 
    public void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return (nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " " + "ID: " + id + " ";
    }

}