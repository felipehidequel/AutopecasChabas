package control;

import model.entity.Cliente;
import model.dao.ClienteDAO;

import java.util.List;

public class ClienteController {
    public static Cliente cadastrarCliente(String nome, String telefone, String cpf) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo.\n");
        }
        nome = nome.toUpperCase();

        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        nome = nome.toUpperCase();

        if (telefone == null) {
            throw new IllegalArgumentException("Telefone não pode ser nulo.\n");
        }

        if (telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio.\n");
        }

        if (!telefone.matches("\\d+")) {
            throw new IllegalArgumentException("Telefone não pode conter letras.\n");
        }

        if (telefone.length() > 11) {
            throw new IllegalArgumentException("Digite apenas 11 números.");
        }

        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo.\n");
        }

        if (cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.\n");
        }

        if (cpf.length() != 11) {
            throw new IllegalArgumentException("Digite apenas 11 números para CPF.\n");
        }

        if (!cpf.matches("\\d+")) {
            throw new IllegalArgumentException("CPF não pode conter letras.");
        }

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