package control;

import java.util.List;

import model.entity.Funcionario;
import model.dao.FuncionarioDAO;

public class FuncionarioController {
    public static void criaFuncionario(String nome, String login, String senha, boolean gerente) {
        Funcionario funcionario = FuncionarioDAO.buscaFuncionarioByLogin(login);
        if (funcionario != null) {
            throw new IllegalArgumentException("Login já existente.");
        }

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
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.\n");
        }
        if (!senha.matches(".*[A-Za-z].*") || !senha.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra e um número.");
        }

        if (FuncionarioDAO.buscaFuncionarioByLogin(login) != null)
            throw new IllegalArgumentException("Login já existente!");

        Funcionario novoFuncionario = new Funcionario(nome, login.toUpperCase(), senha, gerente);
        FuncionarioDAO.criarFuncionario(novoFuncionario);
    }
// FLAGS
//1. Editar Nome
//2. Editar Login
//3. Editar Cargo
    public static void editaFuncionario(int id, String nome, String login, String senha, boolean gerente, int flag) {
        switch (flag) {
            case 1:
                if (nome == null || nome.trim().isEmpty()) {
                    throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
                }
                nome = nome.toUpperCase();
                break;
            case 2:
                if (FuncionarioDAO.buscaFuncionarioByLogin(login) != null) {
                    throw new IllegalArgumentException("Login existente!");
                }

                if (login == null || login.trim().isEmpty()) {
                    throw new IllegalArgumentException("Login não pode ser nulo ou vazio.");
                }
                if (!login.matches("[a-zA-Z0-9]+")) {
                    throw new IllegalArgumentException("Login deve conter apenas letras e números.");
                }

                if (senha == null || senha.length() < 8) {
                    throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
                }
                if (!senha.matches(".*[A-Za-z].*") || !senha.matches(".*[0-9].*")) {
                    throw new IllegalArgumentException("A senha deve conter pelo menos uma letra e um número.");
                }
                break;
        }

        Funcionario funcionario = buscaFuncionarioById(id);

        funcionario.setNome(nome);
        funcionario.setLogin(login.toUpperCase());
        funcionario.setSenha(senha);
        funcionario.setGerente(gerente);

        FuncionarioDAO.editaFuncioario(funcionario);
    }

    public static void excluirFuncionario(int id) {
        Funcionario funcionario = buscaFuncionarioById(id);
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

    /*Se a senha estiver errada retorne null, se o login estiver errado retorne null*/
    public static Funcionario realizarLogin(String login, String senha) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login não pode ser nulo ou vazio.");
        }

        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia.");
        }

        Funcionario funcionario = FuncionarioDAO.buscaFuncionarioByLogin(login.toUpperCase());
        if (funcionario == null) {
            return null;
        }
        if (funcionario.getSenha().equals(senha)) {
            return funcionario;
        }
        return null;
    }

}
