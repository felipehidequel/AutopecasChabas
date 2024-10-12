package model.entity;

import java.util.Date;

public class Pedido {

    private int idPedido;
    private Date data;
    private String status;
    private Funcionario funcionario;
    private Cliente cliente;

    public static final String andamento = "EM ANDAMENTO";
    public static final String completo = "COMPLETO";
    public static final String problema = "CANCELADO";
    public static final String processando = "EM PROCESSAMENTO";

    public Pedido(int idPedido, Date data, String status, Funcionario funcionario, Cliente cliente) {
        this.idPedido = idPedido;
        this.data = data;
        this.status = status;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        String res = "\nID PEDIDO: "+ idPedido;
        res += "\nDATA: " + data;
        res += "\nSTATUS: "+ status;
        res += "\nFUNCIONARIO: "+funcionario.getNome();
        res+= "\nCLIENTE: "+cliente.getNome() + " CPF: "+cliente.getCpf();

        return res;
    }
}
