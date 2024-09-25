package model;

public class NotaPedido {
    private int idNotaPedido;
    private int quantidadePeca;
    private Peca peca;
    private Pedido pedido;
    private double valorTotal;

    public NotaPedido (int idNotaPedido, int qntdPeca, Peca peca, Pedido pedido, double valor){
        this.idNotaPedido = idNotaPedido;
        this.peca = peca;
        this.pedido = pedido;
        this.quantidadePeca = qntdPeca;
        this.valorTotal = valorTotal;
    }

    public int getIdNotaPedido(){
        return idNotaPedido;
    }

    public int getQntPeca(){
        return quantidadePeca;
    }

    public Peca getPeca(){
        return peca;
    }

    public Pedido getPedido(){
        return pedido;
    }

    public double getValorTotal(){
        return valorTotal;
    }

    public void setIdNotaPedido(int idNotaPedido){
        this.idNotaPedido = idNotaPedido;
    }

    public void setQntPeca(int quantidadePeca){
        this.quantidadePeca = quantidadePeca;
    }

    public void setPeca(Peca peca){
        this.peca = peca;
    }

    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }

    public void setValorTotal(double valorTotal){
        this.valorTotal = valorTotal;
    }

    public String toString(){
        return "ID NOTA: " + idNotaPedido + "\n" + "Pedido: " + "\t" + pedido + "\t" + quantidadePeca + "\n" + "Valor Total: " + "\t" + valorTotal + "\n";
    }
}