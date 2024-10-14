package view;

import control.FuncionarioController;
import model.entity.Funcionario;
import utils.Logg;

import java.util.Scanner;

import utils.Utils;

public class TelaLogin {
    public static Funcionario login(Scanner scanner) {
        String nome, senha;
        Funcionario func = null;
        try {
            Logg.info("==LOGIN==");
            boolean loginRealizado = false;
            do {
                try {
                    Logg.info("Insira seu nome de usuário: ");
                    System.out.print("nome de usuario: ");
                    nome = scanner.nextLine();

                    Logg.info("Insira a senha: ");
                    System.out.print("senha: ");
                    senha = scanner.nextLine();
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
            } while (!loginRealizado);
            return func;

        } catch (Exception e) {
            Logg.severe("Erro inesperado: " + e.getMessage());
            return null;
        }
    }
}
