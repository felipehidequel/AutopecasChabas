package control;

import java.util.Date;
import java.util.List;

import model.Cliente;
import model.Funcionario;
import model.Pedido;
import model.dao.PedidoDAO;

public class PedidoController {
    public static Pedido criaPedido(int idPedido, Date data, String status, Funcionario funcionario, Cliente cliente){
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
