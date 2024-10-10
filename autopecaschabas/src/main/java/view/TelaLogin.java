package view;

import control.FuncionarioController;
import model.entity.Funcionario;
import utils.Logg;

import java.io.Console;
import java.util.Scanner;

public class TelaLogin {
    public static Funcionario login() {
        String nome, senha;
        Funcionario func = null;
        try (Scanner sc = new Scanner(System.in)) {
            boolean loginRealizado = false;
            do {
                try {
                    Logg.info("Insira seu nome de usuário: ");
                    nome = sc.nextLine();

                    Logg.info("Insira a senha: ");
                    senha = sc.nextLine();
                    func = FuncionarioController.realizarLogin(nome, senha);
                    if (func == null) {
                        Logg.warning("Nome de usuário ou senha incorretos. Por favor, tente novamente.");
                    } else {
                        loginRealizado = true;
                    }
                } catch (IllegalArgumentException e) {
                    Logg.warning("Erro: " + e.getMessage());
                    Logg.info("Por favor, tente novamente!");
                }
            } while (!loginRealizado); // Loop continua até login ser bem-sucedido
            return func;

        } catch (Exception e) {
            Logg.severe("Erro inesperado: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        TelaLogin.login();
    }
}


