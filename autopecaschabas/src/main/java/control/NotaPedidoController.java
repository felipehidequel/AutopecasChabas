package control;

import java.util.List;

import model.NotaPedido;
import model.Peca;
import model.Pedido;
import model.dao.NotaPedidoDAO;

public class NotaPedidoController {
    public static NotaPedido criaNotaPedido(int idNotaPedido, int qntdPeca, Peca peca, Pedido pedido, double valorTotal){
        NotaPedido nota = new NotaPedido(idNotaPedido, qntdPeca, peca, pedido, valorTotal);
        NotaPedidoDAO.criaNotaPedido(nota);
        return nota;
    }

    public static NotaPedido editarNotaPedido(int idNotaPedido, int qntdPeca, Peca peca, Pedido pedido, double valorTotal){
        var notaPedido = new NotaPedido(idNotaPedido, qntdPeca, peca, pedido, valorTotal);
        NotaPedidoDAO.editaNotaPedido(notaPedido);
        return notaPedido;
    }

    public static List<NotaPedido> listarNotaPedidos(){
        return NotaPedidoDAO.buscarNotaPedidos();
    }

    public static buscarNotaPedido(Pedido id_pedido){
        return NotaPedidoDAO.buscarNotaPedido(id_pedido);
    }

    public static NotaPedido excluirNotaPedido(int id_nota){
        NotaPedido nota = NotaPedidoDAO.buscarNotaPedido(id_nota);
        if(nota == null){
            return false;
        }
        NotaPedidoDAO.excluirNotaPedido(nota);
        return true;
    }
}
