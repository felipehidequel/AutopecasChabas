package view;

import control.FuncionarioController;
import control.PecaController;
import model.entity.Funcionario;
import model.entity.Peca;
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
                System.out.println("2. Gerenciar estoque");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        gerenciarFuncionario(scanner);
                        break;
                    case 2:
                        gerenciarEstoque(scanner);
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
                                System.out.println("Caso queira voltar basta digitar 0");
                                System.out.println("Insira o nome completo: ");
                                nome = scanner.nextLine();

                                if (nome.equals("0")) {
                                    Logg.info("Retornando ao menu principal...");
                                    return;
                                }
                                
                                if (nome.trim().isEmpty()) {
                                    Logg.warning("O campo nome completo não pode estar vazio. Por favor, tente novamente.");
                                    return;
                                }

                                System.out.println("Insira o nome de usuário: ");
                                login = scanner.nextLine();

                                if (login.equals("0")) {
                                    Logg.info("Retornando ao menu principal...");
                                    return;
                                }

                                if (login.trim().isEmpty()) {
                                    Logg.warning("O campo nome de usuário não pode estar vazio. Por favor, tente novamente.");
                                    return;
                                }

                                System.out.println("Insira a senha: ");
                                senha = scanner.nextLine();
                                if (senha.equals("0")) {
                                    Logg.info("Retornando ao menu principal...");
                                    return;
                                }

                                if (senha.trim().isEmpty()) {
                                    Logg.warning("o compo senha não pode estar vazio. Por favor, tente novamente.");
                                    return;
                                }

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
                        menuGerente(scanner);
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
        boolean gerente, sair = false;
        do{
            Logg.info("=== Editar Funcionário ===");
            System.out.println("1. Editar Nome");
            System.out.println("2. Editar Login");
            System.out.println("3. Editar Cargo");
            System.out.println("4. Voltar Para Menu de Gerenciamento de Funcionário");
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
                        funci.setNome(nome);
                        break;
                    case 2:
                        Logg.info("Informe o novo login");
                        System.out.println("Novo nome de usuário: ");
                        login = scanner.nextLine();
                        funci.setLogin(login);
                        System.out.println("Nova senha: ");
                        senha = scanner.nextLine();
                        funci.setSenha(senha);
                        break;
                    case 3:
                        Logg.info("Alterar cargo de funcionário");
                        System.out.println("Deseja alterar cargo de [FUNCIONÁRIO ATIVO] para [GERENTE] (s/n)");
                        gerente = scanner.nextLine().equalsIgnoreCase("s");
                        funci.setGerente(gerente);
                        break;
                    case 4:
                        Logg.info("Saindo...");
                        gerenciarFuncionario(scanner);
                        sair = true;
                        break;
                    default:
                        Logg.warning("Opção inválida, tente novamente.");
                }

                if (opcao >= 1 && opcao <= 3) {
                    FuncionarioController.editaFuncionario(id, funci.getNome(), funci.getLogin(), funci.getSenha(), funci.getGerente(), opcao);
                    Logg.info("Funcinário editado com sucesso!");
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
        } while (!sair);
    }

private static void gerenciarEstoque(Scanner scanner) {
    int opcao = 0;
    do {
        Logg.info("=== Gerenciamento de Estoque ===");
        System.out.println("1. Cadastrar Peça");
        System.out.println("2. Editar Peça");
        System.out.println("3. Remover Peça");
        System.out.println("4. Listar Peças");
        System.out.println("5. Buscar Peça pelo Nome");
        System.out.println("6. Voltar Para o Menu de Gerente");
        System.out.print("Escolha uma opção: ");

        try {
            opcao = scanner.nextInt();
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Limpar o buffer
            }

            switch (opcao) {
                case 1:
                    // Cadastrar Peça
                    System.out.print("Informe o ID da peça: ");
                    int idPecaCadastrar = scanner.nextInt();
                    System.out.print("Informe o nome da peça: ");
                    String nomeCadastrar = scanner.next();
                    System.out.print("Informe a categoria: ");
                    String categoriaCadastrar = scanner.next();
                    System.out.print("Informe o fabricante: ");
                    String fabricanteCadastrar = scanner.next();
                    System.out.print("Informe o preço: ");
                    double precoCadastrar = scanner.nextDouble();
                    System.out.print("Informe a quantidade em estoque: ");
                    int quantidadeCadastrar = scanner.nextInt();

                    PecaController.criaPeca(idPecaCadastrar, nomeCadastrar, categoriaCadastrar, fabricanteCadastrar, precoCadastrar, quantidadeCadastrar);
                    Logg.info("Peça cadastrada com sucesso!");
                    break;

                case 2:
                    // Editar Peça
                    System.out.print("Informe o ID da peça que deseja editar: ");
                    Logg.info("=== Editar de Peça do Estoque ===");
                    System.out.println("1. Atualizar Nome da Peça");
                    System.out.println("2. Atualizar Categoria da Peça");
                    System.out.println("3. Atualizar Fabricante da Peça");
                    System.out.println("4. Atualizar Preço da Peças");
                    System.out.println("5. atualizar Quantidade de Peças");
                    System.out.println("6. Voltar Para o Menu de Gerente");
                    System.out.print("Escolha uma opção: ");


                    int idPecaEditar = scanner.nextInt();
                    Peca pecaEditar = PecaController.buscarPeca(idPecaEditar);

                    if (pecaEditar != null) {
                        System.out.print("Informe o novo nome (atual: " + pecaEditar.getNome() + "): ");
                        String novoNome = scanner.next();
                        System.out.print("Informe a nova categoria (atual: " + pecaEditar.getCategoria() + "): ");
                        String novaCategoria = scanner.next();
                        System.out.print("Informe o novo fabricante (atual: " + pecaEditar.getFabricante() + "): ");
                        String novoFabricante = scanner.next();
                        System.out.print("Informe o novo preço (atual: " + pecaEditar.getPreco() + "): ");
                        double novoPreco = scanner.nextDouble();
                        System.out.print("Informe a nova quantidade em estoque (atual: " + pecaEditar.getQuantidadeEstoque() + "): ");
                        int novaQuantidade = scanner.nextInt();

                        PecaController.editarPeca(idPecaEditar, novoNome, novaCategoria, novoFabricante, novoPreco, novaQuantidade);
                        Logg.info("Peça editada com sucesso!");
                    } else {
                        Logg.warning("Peça não encontrada!");
                    }
                    break;

                case 3:
                    // Remover Peça
                    System.out.print("Informe o ID da peça que deseja remover: ");
                    int idPecaRemover = scanner.nextInt();
                    if (PecaController.excluirPeca(idPecaRemover)) {
                        Logg.info("Peça removida com sucesso!");
                    } else {
                        Logg.warning("Peça não encontrada!");
                    }
                    break;

                case 4:
                    // Listar Peças
                    List<Peca> pecas = PecaController.listarPeca();
                    Logg.info("=== Lista de Peças ===");
                    if (pecas.isEmpty()) {
                        Logg.info("Nenhuma peça cadastrada.");
                    } else {
                        for (Peca peca : pecas) {
                            System.out.println(peca);
                        }
                    }
                    break;

                case 5:
                    // Buscar Peça pelo Nome
                    System.out.print("Informe o nome da peça que deseja buscar: ");
                    String nomeBuscar = scanner.next();
                    Peca pecaBuscar = PecaController.buscarPecaByNome(nomeBuscar);
                    
                    if (pecaBuscar != null) {
                        System.out.println(pecaBuscar);
                    } else {
                        Logg.warning("Peça não encontrada!");
                    }
                    break;

                case 6:
                    Logg.info("Retornando ao menu de gerenciamento de funcionários...");
                    break;

                default:
                    Logg.warning("Opção inválida, tente novamente.");
            }

        } catch (InputMismatchException e) {
            Logg.warning("Erro: informe um valor válido.");
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Limpar o buffer
            }
        } catch (Exception e) {
            Logg.warning(e.getMessage());
        }
    } while (opcao != 6);
  }
}

/*if(scanner.hasNextLine()){
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
break;*/
