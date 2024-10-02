package control;

import model.Cliente;
import model.dao.ClienteDAO;

import java.util.List;

public class ClienteController {
    public static Cliente cadastrarCliente(String nome, String telefone, String cpf) {
        Cliente c = new Cliente(nome, telefone, cpf);
        ClienteDAO.criarCliente(c);
        return c;
    }

    public static Cliente atualizarCliente(String nome, String telefone, String cpf) {
        var cliente = new Cliente(nome, telefone, cpf);
        ClienteDAO.editaCliente(cliente);
        return cliente;
    }

    public static Boolean deletarCliente(String cpf) {
        Cliente c = ClienteDAO.buscarClienteByCpf(cpf);
        if (c == null) {
            return false;
        }

        ClienteDAO.excluirCliente(c);
        return true;
    }

    public static Cliente buscarCliente(String cpf) {
        return ClienteDAO.buscarClienteByCpf(cpf);
    }

    public static List<Cliente> listarClientes() {
        return ClienteDAO.listaClientes();
    }

}