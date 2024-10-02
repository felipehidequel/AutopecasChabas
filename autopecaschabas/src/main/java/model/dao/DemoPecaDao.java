package model.dao;

import model.*;

import java.util.Date;
import java.util.List;

public class DemoPecaDao {
    public static void main(String[] args) {
        Peca peca = new Peca(1, "coroa", "motomot", "teste", 50, 10);
        Cliente cliente = new Cliente(1, "Xulipa", "84996579714", "843848934034");
        Funcionario funcioario = new Funcionario(1, "Kenner", "kenys", "ktd123", true);
        Pedido pedido = new Pedido(1,new Date(2001,12,2), "Pago", funcioario, cliente);
        NotaPedido notaPedido = new NotaPedido(1,4,peca,pedido, 50.0);

        PecaDAO.criaPeca(peca);
        ClienteDAO.criarCliente(cliente);
        FuncionarioDAO.criarFuncionario(funcioario);
        NotaPedidoDAO.criaNotaPedido(notaPedido);



        peca.setCategoria("Nova categoria");
        PecaDAO.editaPeca(peca);

        List<Peca> lista = PecaDAO.listaPecas();
        for (Peca peca1 : lista) {
            System.out.println(peca1);
        }

        System.out.println(PecaDAO.vizualizarPeca("coroa"));




    }
}
