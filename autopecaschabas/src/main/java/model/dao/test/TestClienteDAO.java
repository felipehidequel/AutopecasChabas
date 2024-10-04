package model.dao.test;

import model.Cliente;
import model.dao.ClienteDAO;

import java.util.List;

public class TestClienteDAO implements TestDaoComponent {

    @Override
    public boolean teste() {
        try {
            System.out.println("\nTESTES CLIENTE DAO");
            Cliente cliente;

            System.out.println("Pegando os clientes no banco e inserindo numa lista");
            List<Cliente> lista = ClienteDAO.listaClientes();
            if (lista.isEmpty())
                cliente = new Cliente("Teste", "84996579714", "11111111111");
            else {
                cliente = lista.getFirst();
            }

            System.out.println("Inserindo cliente no banco:");
            ClienteDAO.criarCliente(cliente);

            System.out.println("Editando cliente:");
            cliente.setNome("João");
            ClienteDAO.editaCliente(cliente);

            System.out.println("Buscando cliente por cpf:");
            cliente = ClienteDAO.buscarClienteByCpf(cliente.getCpf());
            System.out.println(cliente);
            System.out.println("Cliente encontrado!");

            System.out.println("Buscando cliente por cpf:");
            cliente = ClienteDAO.buscarClienteById(cliente.getId());
            System.out.println(cliente);
            System.out.println("Cliente encontrado!");

            System.out.println("Deletar cliente:");
            ClienteDAO.excluirCliente(cliente);
            System.out.println("Cliente deletado com sucesso!");

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Falhou em ClienteDAO");
        }
        return false;
    }
}
