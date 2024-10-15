import java.util.Scanner;

import control.FuncionarioController;
import utils.Utils;

import model.entity.Funcionario;
import utils.Logg;
import view.TelaFuncionario;
import view.TelaGerente;
import view.TelaLogin;
import java.util.InputMismatchException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        menu(scanner);

        scanner.close();
    }

    public static void menu(Scanner scanner) {
        int opcao = 0;

        List<Funcionario> funcionarios = FuncionarioController.listarFuncionarios();
        if (funcionarios.isEmpty()) {
            FuncionarioController.criaFuncionario("admin", "admin123", "admin123", true);
        }

        do {
            Utils.clearScreen();
            Logg.info("=== Menu Principal ===");
            System.out.println("1. Login");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                if (scanner.hasNextLine())
                    scanner.nextLine();
                switch (opcao) {
                    case 1:
                        Funcionario user = TelaLogin.login(scanner);
                        if (user != null) {
                            Logg.info("Login bem sucedido!");
                            Utils.pause(scanner);
                            Utils.clearScreen();
                            if (user.getGerente()) {
                                TelaGerente.menuGerente(scanner);
                            } else {
                                TelaFuncionario.menuFuncionario(user, scanner);
                            }
                        }
                        break;
                    case 2:
                        Logg.info("Saindo...");
                        break;
                    default:
                        Logg.warning("Opção inválida, tente novamente.");
                }

            } catch (NumberFormatException e) {
                Logg.warning("Informe somente números para a opção.");
            } catch (InputMismatchException e) {
                Logg.warning("Erro: informe um valor válido.");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            } catch (Exception e) {
                Logg.severe("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 2);
    }
}
