package view;

import control.FuncionarioController;
import control.PecaController;
import model.entity.Funcionario;
import utils.Logg;

import java.lang.ModuleLayer.Controller;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

import utils.Utils;

public class TelaGerente {

    public static void menuGerente(Scanner scanner) {
        Funcionario func = null;
        boolean sair = false, gerente, funcionarioCadastrado = false;
        int id, quantidade;
        int opcao = 0;

        try {
            do {
                Logg.info("\n-------------- Menu do Gerente --------------");
                Logg.info("1. Cadastrar Novo Funcionário");
                Logg.info("2. Listar Funcionários");
                Logg.info("3. Remover Funcionário");
                Logg.info("4. Atualizar estoque");
                Logg.info("5. Sair");

                Logg.info("Informe uma opção");
                System.out.print("Opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        while (!funcionarioCadastrado) {
                            try {
                                if (scanner.hasNextLine()) {
                                    scanner.nextLine();
                                }
                                Logg.info(
                                        "<><><><> Preencha as informações para cadastrar um novo funcionario <><><><>");
                                Logg.info("Insira o nome completo: ");
                                String nome = scanner.nextLine();

                                Logg.info("Insira o nome de usuário: ");
                                String login = scanner.nextLine();

                                Logg.info("Insira a senha: ");
                                String senha = scanner.nextLine();

                                Logg.info("O funcionário é gerente? (s/n): ");
                                gerente = scanner.nextLine().equalsIgnoreCase("s");

                                FuncionarioController.criaFuncionario(nome, login, senha, gerente);
                                Logg.info("Funcionário cadastrado com sucesso!");
                                funcionarioCadastrado = true;
                            } catch (IllegalArgumentException e) {
                                Logg.warning("Erro: " + e.getMessage());
                                Logg.info("Por favor, tente novamente.");
                            }
                        }
                        break;
                    case 2:
                        Logg.info("<><><><> Listagem de funcionários ativos <><><><>");
                        List<Funcionario> funcionarios = FuncionarioController.listarFuncionarios();
                        if (funcionarios.isEmpty()) {
                            Logg.info("Nenhum funcionário cadastrado.");
                        } else {
                            for (Funcionario funcionario : funcionarios) {
                                Logg.info("Nome: " + funcionario.getNome() + ", Login: " + funcionario.getLogin()
                                        + ", Cargo: " + (funcionario.getGerente() ? "Gerente" : "Funcionário ativo")); // mudar
                                                                                                                       // o
                                                                                                                       // cargo
                                                                                                                       // caso
                                                                                                                       // não
                                                                                                                       // seja
                                                                                                                       // gerente
                            }
                        }
                        break;
                    case 3:
                        List<Funcionario> verificaFuncionario = FuncionarioController.listarFuncionarios();
                        if (verificaFuncionario.isEmpty()) {
                            Logg.info("Não há funcionários cadastrados para remover.");
                        } else {
                            Logg.info("Digite o ID do funcionário a ser removido:");
                            id = scanner.nextInt();
                            try {
                                FuncionarioController.excluirFuncionario(id);
                                Logg.info("Funcionário removido com sucesso!");
                            } catch (IllegalArgumentException e) {
                                Logg.warning("Erro ao remover funcionário: " + e.getMessage());
                            }
                        }
                        break;
                    case 4:
                        if(scanner.hasNextLine()){
                            scanner.nextLine();
                        }
                        Logg.info("<><><><> Atualização de estoque <><><><>");
                        PecaController.listarPecasPorCategoria();
                        Logg.info("Digite o ID da peca que deseja atualizar:");
                        System.out.print("ID: ");
                        id = scanner.nextInt();

                        Logg.info("Informe a quantidade que será adicionada ao estoque:");
                        quantidade = scanner.nextInt();

                        try {
                            PecaController.atualizarEstoqueByIdPeca(quantidade, id);
                            Logg.info("Estoque da peça de ID " + id + " atualizado com sucesso!");
                        } catch (IllegalArgumentException e) {
                            Logg.warning("Erro ao atualizar estoque: " + e.getMessage());
                        }
                        break;
                    case 5:
                        Logg.info("Saindo do menu do gerente.");
                        sair = true;
                        break;
                    default:
                        Logg.warning("Opção inválida! Tente novamente.");
                        break;
                }
            } while (!sair);

        } catch (IllegalArgumentException e) {
            Logg.warning("Erro: " + e.getMessage());
            Logg.info("Por Favor, tente novamente.");
        } catch (InputMismatchException e) {
            Logg.warning("Erro: informe um valor válido.");
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        } catch (Exception e) {
            Logg.severe("Erro inesperado: " + e.getMessage());
        }
    }
}