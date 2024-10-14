package control;

import java.util.List;

import model.entity.NotaPedido;
import model.entity.Peca;
import model.entity.Pedido;
import utils.Logg;
import model.dao.NotaPedidoDAO;

public class NotaPedidoController {
    public static NotaPedido criaNotaPedido(int idNotaPedido, int qntdPeca, Peca peca, Pedido pedido) {
        if (qntdPeca < 0) {
            throw new IllegalArgumentException("A quantidade de peças não pode ser negativa.");
        }

        if (peca == null) {
            throw new IllegalArgumentException("A peça não pode ser nula ou vazia.");
        }

        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo ou vazio.");
        }

        NotaPedido nota = new NotaPedido(idNotaPedido, qntdPeca, peca, pedido);
        NotaPedidoDAO.criaNotaPedido(nota);
        return nota;
    }

    public static NotaPedido editarNotaPedido(int idNotaPedido, int qntdPeca, Peca peca, Pedido pedido) {
        var notaPedido = new NotaPedido(idNotaPedido, qntdPeca, peca, pedido);

        if (qntdPeca < 0) {
            throw new IllegalArgumentException("A quantidade de peças não pode ser negativa.");
        }

        if (peca == null) {
            throw new IllegalArgumentException("A peça não pode ser nula ou vazia.");
        }

        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo ou vazio.");
        }

        NotaPedidoDAO.editaNotaPedido(notaPedido);
        return notaPedido;
    }

    public static List<NotaPedido> listarNotaPedidos() {
        return NotaPedidoDAO.listarNotasPedidos();
    }

    public static NotaPedido buscarNotaPedido(int id_pedido) {
        return NotaPedidoDAO.buscarNotaPedidoByIdPedido(id_pedido);
    }

    public static boolean excluirNotaPedido(NotaPedido id_nota) {
        NotaPedido nota = NotaPedidoDAO.buscarNotaPedido(id_nota);
        if (nota == null) {
            return false;
        }
        NotaPedidoDAO.excluirNotaPedido(nota);
        return true;
    }
    public static void listarNotaByCliente(int id_cliente) {
        List<NotaPedido> notas = NotaPedidoDAO.listarNotasPedidos();
        Logg.info("Notas do cliente: ");
        if(notas.isEmpty()){
            Logg.info("Não há notas cadastradas.");
            return;
        }

        for (NotaPedido nota : notas) {
            if (nota.getPedido().getCliente().getId() == id_cliente) {
                System.out.println(nota);
            }
        }
    }
}

