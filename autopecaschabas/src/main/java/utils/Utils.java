package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pause(Scanner scanner) {
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    public static String formatCPF(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String formatTelefone(String telefone) {
        if (telefone.length() == 11) {
            return telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        } else if (telefone.length() == 10) {
            return telefone.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        } else {
            return telefone;
        }
    }

}
