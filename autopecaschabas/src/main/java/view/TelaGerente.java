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
                Logg.info("=== Menu do Gerente ===");
                System.out.println("1. Gerenciar funcionários");
                System.out.println("2. Gerenciar estoque de peças");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        gerenciarFuncionario(scanner);
                        break;
                    case 2:
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
                    case 3:
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

    private static void gerenciarFuncionario(Scanner scanner) {
        int opcao = 0, id, quantidade;
        boolean sair = false, gerente, funcionarioCadastrado = false;
        String nome, login, senha;

        do{
            Logg.info("=== Menu de gerenciamento de funcionários ===");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Remover Funcionário do Sistema");
            System.out.println("4. Atualizar Funcionário");
            System.out.println("5. Voltar para Menu do gerente");
            System.out.println("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
            if(scanner.hasNextLine()){
                scanner.nextLine();
            }

                switch (opcao) {
                    case 1:
                        while (!funcionarioCadastrado) {
                            try {
                                Logg.info("=== Preencha as informações para cadastrar um novo funcionario ===");
                                System.out.println("Insira o nome completo: ");
                                nome = scanner.nextLine();

                                System.out.println("Insira o nome de usuário: ");
                                login = scanner.nextLine();

                                System.out.println("Insira a senha: ");
                                senha = scanner.nextLine();

                                System.out.println("O funcionário é gerente? (s/n): ");
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
                        Logg.info("=== Listagem de funcionários ativos ===");
                        List<Funcionario> funcionarios = FuncionarioController.listarFuncionarios();
                        if (funcionarios.isEmpty()) {
                            Logg.info("Nenhum funcionário cadastrado.");
                        } else {
                            for (Funcionario funcionario : funcionarios) {
                                System.out.println("Nome: " + funcionario.getNome() + ", Login: " + funcionario.getLogin()
                                        + ", Cargo: " + (funcionario.getGerente() ? "Gerente" : "Funcionário ativo"));
                            }
                        }
                        break;
                    case 3:
                        List<Funcionario> verificaFuncionario = FuncionarioController.listarFuncionarios();
                        if (verificaFuncionario.isEmpty()) {
                            Logg.info("Não há funcionários cadastrados para remover.");
                        } else {
                            Logg.info("=== Remover Funcionário do Sistema ===");
                            System.out.println("Digite o ID do funcionário a ser removido:");
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
                        if (scanner.hasNextLine())
                            scanner.nextLine();
                        List<Funcionario> func = FuncionarioController.listarFuncionarios();
                        if (func.isEmpty()){
                            Logg.warning("Nenhum Funcionário cadastrado.");
                            break;
                        }
                        Logg.info("TODOS OS FUNCIONÁRIOS ATIVOS");
                        func.forEach(System.out::println);

                        Logg.info("Informe o ID do funcionário");
                        System.out.println("ID: ");
                        id = scanner.nextInt();
                        Funcionario funci = FuncionarioController.buscaFuncionarioById(id);

                        if (funci != null) {
                            Logg.info("Funcionário encontrado!");
                            System.out.println(funci);
                            editarFuncionario(funci.getId(), funci, scanner);
                        } else {
                            Logg.warning("Funcionário não encontrado.");
                        }
                        break;
                    case 5:
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
        } while (opcao != 4);
    }

    private static void editarFuncionario(int id, Funcionario funci, Scanner scanner) {
        int opcao = 0;
        String nome, login, senha;
        boolean gerente;
        do{
            Logg.info("=== Editar Funcionário ===");
            System.out.println("1. Editar Nome");
            System.out.println("2. Editar Login");
            System.out.println("3. Editar Cargo");
            System.out.println("4. Voltar para Menu do gerente");
            System.out.println("Escolha uma opção: ");

            try{
                opcao = scanner.nextInt();
                if(scanner.hasNextLine())
                    scanner.nextLine();

                switch (opcao) {
                    case 1:
                        Logg.info("Informe o novo nome");
                        System.out.println("Nome completo: ");
                        nome = scanner.nextLine();
                        break;
                    case 2:
                        Logg.info("Informe o novo login");
                        System.out.println("Novo nome de usuário: ");
                        login = scanner.nextLine();
                        System.out.println("Nova senha: ");
                        senha = scanner.nextLine();
                        break;
                    case 3:
                        Logg.info("Alterar cargo de funcionário");
                        System.out.println("Deseja alterar cargo de [FUNCIONÁRIO ATIVO] para [GERENTE] (s/n)");
                        gerente = scanner.nextLine().equalsIgnoreCase("s");
                        break;
                    case 4:
                        Logg.info("Saindo...");
                        break;
                    default:
                        Logg.warning("Opção inválida, tente novamente.");
                }

                if (opcao >= 1 && opcao <= 3) {
                    FuncionarioController.editaFuncionario(id, funci.getNome(), funci.getLogin(), funci.getSenha(), funci.getGerente(), opcao);
                    Logg.info("Cliente editado com sucesso!");
                }

            } catch (NumberFormatException e) {
                Logg.warning("Informe somente números para a opção.");
            } catch (InputMismatchException e){
                Logg.warning("Erro: Informe um valor válido.");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            } catch (Exception e) {
                Logg.warning(e.getMessage());
            }
        } while (opcao != 4);
    }
}