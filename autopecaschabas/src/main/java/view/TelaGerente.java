package view;

import control.FuncionarioController;
import control.PecaController;
import model.entity.Funcionario;
import model.entity.Peca;
import utils.Logg;

import java.util.List;
import java.util.Scanner;

import java.util.InputMismatchException;

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
                    case 1: // gerenciar funcionarios
                        gerenciarFuncionario(scanner);
                        break;
                    case 2: // gerenciar estoque
                        gerenciarEstoque(scanner);
                        break;
                    case 3: // sair
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

        do {
            Logg.info("=== Menu de gerenciamento de funcionários ===");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Remover Funcionário do Sistema");
            System.out.println("4. Atualizar Funcionário");
            System.out.println("5. Voltar para Menu do gerente");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }

                switch (opcao) {
                    case 1: // cadastrar funcionario
                        while (!funcionarioCadastrado) {
                            try {
                                Logg.info("=== Preencha as informações para cadastrar um novo funcionario ===");
                                System.out.print("Insira o nome completo: ");
                                nome = scanner.nextLine();

                                System.out.print("Insira o nome de usuário: ");
                                login = scanner.nextLine();

                                System.out.print("Insira a senha: ");
                                senha = scanner.nextLine();

                                System.out.print("O funcionário é gerente? (s/n): ");
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
                    case 2: // listar funcionarios
                        int contagem = 0;
                        Logg.info("=== Listagem de funcionários ativos ===");
                        List<Funcionario> funcionarios = FuncionarioController.listarFuncionarios();
                        if (funcionarios.isEmpty()) {
                            Logg.info("Nenhum funcionário cadastrado.");
                        } else {
                            for (Funcionario funcionario : funcionarios) {
                                System.out.println(contagem + "| Nome: " + funcionario.getNome() + ", Login: "
                                        + funcionario.getLogin()
                                        + ", Cargo: " + (funcionario.getGerente() ? "Gerente" : "Funcionário ativo"));
                                contagem++;
                            }
                        }
                        break;
                    case 3: // remover funcionario
                        List<Funcionario> verificaFuncionario = FuncionarioController.listarFuncionarios();
                        if (verificaFuncionario.isEmpty()) {
                            Logg.info("Não há funcionários cadastrados para remover.");
                        } else {
                            verificaFuncionario.forEach(System.out::println);
                            Logg.info("=== Remover Funcionário do Sistema ===");
                            System.out.print("Digite o ID do funcionário a ser removido: ");
                            id = scanner.nextInt();
                            try {
                                FuncionarioController.excluirFuncionario(id);
                                Logg.info("Funcionário removido com sucesso!");
                            } catch (IllegalArgumentException e) {
                                Logg.warning("Erro ao remover funcionário: " + e.getMessage());
                            }
                        }
                        break;
                    case 4: // atualizar funcionario
                        // if (scanner.hasNextLine())
                        // scanner.nextLine();
                        List<Funcionario> func = FuncionarioController.listarFuncionarios();
                        if (func.isEmpty()) {
                            Logg.warning("Nenhum Funcionário cadastrado.");
                            break;
                        }
                        Logg.info("TODOS OS FUNCIONÁRIOS ATIVOS");
                        func.forEach(System.out::println);

                        Logg.info("Informe o ID do funcionário");
                        System.out.print("ID: ");
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
                    case 5: // voltar
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
        } while (opcao != 5);
    }

    private static void editarFuncionario(int id, Funcionario funci, Scanner scanner) {
        int opcao = 0;
        String nome, login, senha;
        boolean gerente;
        do {
            Logg.info("=== Editar Funcionário ===");
            System.out.println("1. Editar Nome");
            System.out.println("2. Editar Login");
            System.out.println("3. Editar Cargo");
            System.out.println("4. Voltar para Menu do gerente");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                if (scanner.hasNextLine())
                    scanner.nextLine();

                switch (opcao) {
                    case 1: // editar nome
                        Logg.info("Informe o novo nome");
                        System.out.print("Nome completo: ");
                        nome = scanner.nextLine();
                        funci.setNome(nome);
                        break;
                    case 2: // editar login
                        Logg.info("Informe o novo login");
                        System.out.print("Novo nome de usuário: ");
                        login = scanner.nextLine();
                        System.out.print("Nova senha: ");
                        senha = scanner.nextLine();
                        funci.setLogin(login);
                        funci.setSenha(senha);
                        break;
                    case 3: // editar cargo
                        Logg.info("Alterar cargo de funcionário");
                        System.out.print("Deseja alterar cargo de [FUNCIONÁRIO ATIVO] para [GERENTE] (s/n) ");
                        gerente = scanner.nextLine().equalsIgnoreCase("s");
                        funci.setGerente(gerente);
                        break;
                    case 4: // voltar
                        Logg.info("Saindo...");
                        break;
                    default:
                        Logg.warning("Opção inválida, tente novamente.");
                }

                if (opcao >= 1 && opcao <= 3) {
                    FuncionarioController.editaFuncionario(id, funci.getNome(), funci.getLogin(), funci.getSenha(),
                            funci.getGerente(), opcao);
                    Logg.info("Funcionario editado com sucesso!");
                }

            } catch (NumberFormatException e) {
                Logg.warning("Informe somente números para a opção.");
            } catch (InputMismatchException e) {
                Logg.warning("Erro: Informe um valor válido.");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            } catch (Exception e) {
                Logg.warning(e.getMessage());
            }
        } while (opcao != 4);
    }

    private static void gerenciarEstoque(Scanner scanner) {
        int opcao = 0, id, quantidade;
        boolean sair = false;
        String nome, categoria, fabricante;
        double preco;

        do {
            Logg.info("=== Menu de gerenciamento de estoque de peças ===");
            System.out.println("1. Cadastrar Peça");
            System.out.println("2. Listar Peças");
            System.out.println("3. Remover Peça do Estoque");
            System.out.println("4. Atualizar Peça");
            System.out.println("5. Voltar para o Menu anterior");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }

                switch (opcao) {
                    case 1: // cadastrar peça
                        Logg.info("=== Preencha as informações para cadastrar uma nova peça ===");
                        System.out.print("Nome da peça: ");
                        nome = scanner.nextLine();

                        System.out.print("Categoria da peça: ");
                        categoria = scanner.nextLine();

                        System.out.print("Fabricante da peça: ");
                        fabricante = scanner.nextLine();

                        System.out.print("Preço: ");
                        preco = scanner.nextDouble();

                        System.out.print("Quantidade em estoque: ");
                        quantidade = scanner.nextInt();

                        PecaController.criaPeca(0, nome, categoria, fabricante, preco, quantidade);
                        Logg.info("Peça cadastrada com sucesso!");
                        break;

                    case 2: // listar peças
                        Logg.info("=== Listagem de peças em estoque ===");
                        PecaController.listarPecasPorCategoria();
                        utils.Utils.pause(scanner);
                        break;

                    case 3: // remover peça
                        List<Peca> verificaPeca = PecaController.listarPeca();
                        if (verificaPeca.isEmpty()) {
                            Logg.info("Não há peças cadastradas para remover.");
                        } else {
                            PecaController.listarPecasPorCategoria();
                            Logg.info("=== Remover peça do estoque ===");
                            System.out.print("Digite o ID da peça a ser removida: ");
                            id = scanner.nextInt();
                            boolean removida = PecaController.excluirPeca(id);
                            if (removida) {
                                Logg.info("Peça removida com sucesso!");
                            } else {
                                Logg.warning("Erro ao remover peça. Verifique o ID e tente novamente.");
                            }
                        }
                        break;

                    case 4: // atualizar peça
                        if (PecaController.listarPeca().isEmpty()) {
                            Logg.warning("Nenhuma peça cadastrada.");
                            break;
                        }
                        Logg.info("=== Atualizar informações da peça ===");
                        PecaController.listarPecasPorCategoria();
                        System.out.print("Digite o ID da peça a ser atualizada: ");
                        id = scanner.nextInt();

                        Peca peca = PecaController.buscarPeca(id);
                        if (peca != null) {
                            Logg.info("O que deseja alterar?");
                            System.out.println("1. Nome");
                            System.out.println("2. Categoria");
                            System.out.println("3. Fabricante");
                            System.out.println("4. Preço");
                            System.out.println("5. Quantidade em estoque");
                            System.out.print("Escolha uma opção: ");

                            int flag = scanner.nextInt();
                            if (scanner.hasNextLine()) {
                                scanner.nextLine();
                            }

                            switch (flag) {
                                case 1:
                                    Logg.info("Informe o novo nome da peça: ");
                                    System.out.print("Nome: ");
                                    peca.setNome(scanner.nextLine());
                                    break;
                                case 2:
                                    Logg.info("Informe a nova categoria da peça: ");
                                    System.out.print("Categoria: ");
                                    peca.setCategoria(scanner.nextLine());
                                    break;
                                case 3:
                                    Logg.info("Informe o novo fabricante da peça: ");
                                    System.out.print("Fabricante: ");
                                    peca.setFabricante(scanner.nextLine());
                                    break;
                                case 4:
                                    Logg.info("Informe o novo preço da peça: ");
                                    System.out.print("Preço: ");
                                    peca.setPreco(scanner.nextDouble());
                                    break;
                                case 5:
                                    Logg.info("Informe a nova quantidade em estoque: ");
                                    System.out.print("Quantidade em estoque: ");
                                    peca.setQuantidadeEstoque(scanner.nextInt());
                                    break;
                                default:
                                    Logg.warning("Opção inválida, tente novamente.");
                            }
                            PecaController.editarPeca(id, peca.getNome(), peca.getCategoria(), peca.getFabricante(),
                                    peca.getPreco(), peca.getQuantidadeEstoque());
                        } else {
                            Logg.warning("Peça não encontrada.");
                        }
                        break;

                    case 5:
                        Logg.info("Saindo...");
                        sair = true;
                        break;

                    default:
                        Logg.warning("Opção inválida, tente novamente.");
                }

            } catch (InputMismatchException e) {
                Logg.warning("Erro: Informe um valor válido.");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            } catch (Exception e) {
                Logg.severe("Erro inesperado: " + e.getMessage());
            }
        } while (!sair);
    }
}