package model.dao.test;

import model.entity.Cliente;
import model.entity.Funcionario;
import model.entity.Pedido;
import model.dao.ClienteDAO;
import model.dao.FuncionarioDAO;
import model.dao.PedidoDAO;

import java.util.Date;
import java.util.List;

public class TestPedidoDAO implements TestDaoComponent {
    @Override
    public boolean teste() {

        try {
            System.out.println("TESTE EM TestPedidoDAO");

            Pedido pedido;
            Funcionario func = new Funcionario("TestePedido", "teste", "teste", false);
            FuncionarioDAO.criarFuncionario(func);

            Cliente cliente = new Cliente("Teste", "8394432", "fdefefe");
            ClienteDAO.criarCliente(cliente);

            List<Pedido> pedidos = PedidoDAO.listarPedido();
            if (pedidos.isEmpty())
                pedido = new Pedido(0, new Date(), "Pago", func, cliente);
            else {
                pedido = pedidos.getFirst();
            }

            PedidoDAO.criaPedido(pedido);
            System.out.println("Pedido criado: " + pedido);

            PedidoDAO.editaPedido(pedido);
            System.out.println("Pedido editado: " + pedido);

            Pedido pedidoBuscado = PedidoDAO.buscarPedidoById(pedido.getIdPedido());
            System.out.println("Pedido buscado por ID: " + pedidoBuscado);

            PedidoDAO.excluirPedido(pedido);
            System.out.println("Pedido excluído: " + pedido);

            PedidoDAO.editarStatus(1, "CONFIRMADO");
            System.out.println("Status do pedido com ID 1 atualizado para CONFIRMADO");

            for (Pedido p : PedidoDAO.listarPedido()) {
                System.out.println("Pedido listado: " + p);
            }


            FuncionarioDAO.excluirFuncionario(func);
            ClienteDAO.excluirCliente(cliente);

            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("Falhou em TestPedidoDAO");
        }
        return false;
    }
}
