package view;

import control.FuncionarioController;

import java.util.Scanner;

public class TelaLogin {
    public static void login() {
        //fazer a tela de login
        String nome, senha;
        Scanner sc = new Scanner(System.in);
        try {
            do {
                Logg.info("Insira seu nome de usuário");
                nome = sc.nextLine();
                Logg.info("Insira a senha");
                senha = sc.nextLine();
            }while (!FuncionarioController.realizarLogin(nome, senha));
        } catch (IllegalArgumentException e) {
            Logg.warning(e.getMessage());
        }
    }

    public static void main(String[] args) {
        TelaLogin.login();
    }
}


