package control;

import java.util.Date;
import java.util.List;

import model.Cliente;
import model.Funcionario;
import model.Pedido;
import model.dao.PedidoDAO;

public class PedidoController {
    public static Pedido criaPedido(int idPedido, Date data, String status, Funcionario funcionario, Cliente cliente){
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nulo.\n");
        }

        Date dataAtual = new Date();
        if (data.after(dataAtual)) {
            throw new IllegalArgumentException("Data inválida.\n");
        }

        if (status == null) {
            throw new IllegalArgumentException("Status do pedido não pode ser nulo.\n");
        }

        if (!status.equals(Pedido.andamento) && !status.equals(Pedido.completo) && !status.equals(Pedido.problema) && !status.equals(Pedido.processando)){
            throw new IllegalArgumentException("Status de pedido inválido.");
        }

        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo.\n");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.\n");
        }

        Pedido pedido = new Pedido(idPedido, data, status, funcionario, cliente);
        PedidoDAO.criaPedido(pedido);
        return pedido;
    }

    public static Pedido editarPedido(int idPedido, Date data, String status, Funcionario funcionario, Cliente cliente){
        var p = new Pedido(idPedido, data, status, funcionario, cliente);
        PedidoDAO.editaPedido(p);
        return p;
    }

    public static boolean excluirPedido(int id_pedido){
        Pedido pedido = PedidoDAO.buscarPedidoById(id_pedido);
        if(pedido == null){
            return false;
        }
        PedidoDAO.excluirPedido(pedido);
        return true;
    }

    public static List<Pedido> listarPedidos(){
        return PedidoDAO.listarPedido();
    }

    public static Pedido buscarPedido(int id_pedido){
        return PedidoDAO.buscarPedidoById(id_pedido);
    }
}
