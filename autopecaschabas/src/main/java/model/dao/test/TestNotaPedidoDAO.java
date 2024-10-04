package model.dao.test;

import model.*;
import model.dao.*;

import java.util.Date;
import java.util.List;

public class TestNotaPedidoDAO implements TestDaoComponent {

    @Override
    public boolean teste() {


        try {
            System.out.println("\nTESTE EM NotaPedidoDAO");

            NotaPedido nota;
            Funcionario func = new Funcionario("TestePedido", "teste", "teste", false);
            Cliente cliente = new Cliente("Teste", "8394432", "fdefefe");
            Peca peca = new Peca(0, "Pneu", "Pneu", "Pirelli", 200.00, 10);
            Pedido pedido = new Pedido(0, new Date(), "PAGO", func, cliente);

            FuncionarioDAO.criarFuncionario(func);
            ClienteDAO.criarCliente(cliente);
            PecaDAO.criaPeca(peca);
            PedidoDAO.criaPedido(pedido);


            List<NotaPedido> notas = NotaPedidoDAO.listarNotasPedidos();
            if (notas.isEmpty()) {
                nota = new NotaPedido(0, 10, peca, pedido);
            } else {
                nota = notas.getFirst();
            }

            System.out.println("Criando NotaPedido no banco");
            NotaPedidoDAO.criaNotaPedido(nota);

            System.out.println("Buscando NotaPedido no banco");
            nota = NotaPedidoDAO.buscarNotaPedido(nota);
            if(nota!=null)
                System.out.println("Nota encontrada com sucesso!");

            System.out.println("Notas no banco:");
            NotaPedidoDAO.listarNotasPedidos().forEach(System.out::println);

            System.out.println("Excluindo nota: ");
            NotaPedidoDAO.excluirNotaPedido(nota);


            PedidoDAO.excluirPedido(pedido);
            PecaDAO.excluirPeca(peca);
            FuncionarioDAO.excluirFuncionario(func);
            ClienteDAO.excluirCliente(cliente);

            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro em NotaPedidoDAO");
        }

        return false;
    }
}
