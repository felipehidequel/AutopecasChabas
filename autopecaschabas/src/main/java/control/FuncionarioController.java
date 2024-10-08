package control;

import java.util.List;

import model.Funcionario;
import model.dao.FuncionarioDAO;

public class FuncionarioController {
    public static void criaFuncionario(String nome, String login, String senha, boolean gerente) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo.\n");
        }
        nome = nome.toUpperCase();

        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        nome = nome.toUpperCase();

        if (login == null) {
            throw new IllegalArgumentException("Login não pode ser nulo.\n");
        }

        if (login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login não pode ser vazio.\n");
        }

        if (!login.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Login deve conter apenas letras e números.\n");
        }

        if (senha == null || senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 6 caracteres.\n");
        }
        if (!senha.matches(".*[A-Za-z].*") || !senha.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra e um número.");
        }

        if(FuncionarioDAO.buscaFuncionarioByLogin(login) != null)
            throw new IllegalArgumentException("Login já existente!");

        Funcionario novoFuncionario = new Funcionario(nome, login.toLowerCase(), senha, gerente);
        FuncionarioDAO.criarFuncionario(novoFuncionario);
    }

    public static void editaFuncionario(int id, String nome, String login, String senha, boolean gerente) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        nome = nome.toUpperCase();

        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login não pode ser nulo ou vazio.");
        }
        if (!login.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Login deve conter apenas letras e números.");
        }

        if (senha == null || senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 6 caracteres.");
        }
        if (!senha.matches(".*[A-Za-z].*") || !senha.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra e um número.");
        }

        Funcionario funcionario = FuncionarioDAO.buscarFuncionarioById(id);
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        funcionario.setNome(nome);
        funcionario.setLogin(login.toLowerCase());
        funcionario.setSenha(senha);
        funcionario.setGerente(gerente);

        FuncionarioDAO.editaFuncario(funcionario);
    }

    public static void excluirFuncionario(int id) {
        Funcionario funcionario = FuncionarioDAO.buscarFuncionarioById(id);
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        FuncionarioDAO.excluirFuncionario(funcionario);
    }

    public static Funcionario buscaFuncionario(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        nome = nome.toUpperCase();

        Funcionario funcionario = FuncionarioDAO.buscaFuncionario(nome);
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        return funcionario;
    }

    public static Funcionario buscaFuncionarioByLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login não pode ser nulo ou vazio.");
        }

        Funcionario funcionario = FuncionarioDAO.buscaFuncionarioByLogin(login);
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        return funcionario;
    }

    public static Funcionario buscaFuncionarioById(int id) {
        Funcionario funcionario = FuncionarioDAO.buscarFuncionarioById(id);
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        return funcionario;
    }

    public static List<Funcionario> listarFuncionarios() {
        return FuncionarioDAO.listaFuncionarios();
    }
    
    public static boolean realizarLogin(String login, String senha){
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login não pode ser nulo ou vazio.");
        }

        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia.");
        }

        Funcionario funcionario = FuncionarioDAO.buscaFuncionarioByLogin(login);
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }

        return funcionario.getSenha().equals(senha);
    }

}
