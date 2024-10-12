import java.util.Scanner;

import utils.Utils;

import model.entity.Funcionario;
import utils.Logg;
import view.TelaFuncionario;
import view.TelaGerente;
import view.TelaLogin;

public class App {
    public static void main(String[] args) {
        Funcionario user;
        Scanner scanner = new Scanner(System.in);

        user = TelaLogin.login(scanner);

        if (user != null) {
            Logg.info("Login bem sucedido!");
            if (user.getGerente()) {
                TelaGerente.menuGerente(scanner); // substituir por menu
            } else {
                TelaFuncionario.menuFuncionario(user, scanner); // substituir por menu
            }

        }

        scanner.close();
    }
}
