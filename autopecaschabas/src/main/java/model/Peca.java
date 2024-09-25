package model;

public class Peca {
    private int idPeca;
    private String nome;
    private String categoria;
    private String fabricante;
    private double preco;
    private int quantidadeEstoque;

    public Peca(int idPeca, String nome, String categoria,String fabricante, double preco, int quantidadeEstoque) {
        this.idPeca = idPeca;
        this.nome = nome;
        this.categoria = categoria;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        return "Peca{" +
                "idPeca=" + idPeca +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", preco=" + preco +
                ", quantidadeEstoque=" + quantidadeEstoque +
                '}';
    }
}
