package model;

import java.util.Calendar;
import model.Funcionario;

public class Pedido {

    private int idPedido;
    private Calendar data;
    private String status;
    private Funcionario funcionario;
    private Cliente cliente;

    public Pedido(int idPedido, Calendar data, String status, Funcionario funcionario, Cliente cliente) {
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

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
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
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", data=" + data +
                ", status='" + status + '\'' +
                ", funcionario=" + funcionario +
                ", cliente=" + cliente +
                '}';
    }
}
