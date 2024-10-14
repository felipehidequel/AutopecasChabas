package view;

import control.*;
import model.entity.*;
import utils.Logg;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TelaFuncionario {

    public static void menuFuncionario(Funcionario func, Scanner scanner) {
        int opcao = 0;

        do {
            Logg.info("=== Menu do Funcionário ===");
            System.out.println("1. Gerenciar Cliente");
            System.out.println("2. Gerar Pedido");
            System.out.println("3. Consultar Pedido");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        menuCliente(scanner);
                        break;
                    case 2:
                        if (scanner.hasNextLine())
                            scanner.nextLine();

                        Logg.info("Informe o CPF do cliente ou digite 0 para volar ao menu principal");
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        if (cpf.equals("0")) {
                            Logg.info("Voltando ao menu principal...");
                            break;
                        }
                        Cliente cli = ClienteController.buscarCliente(cpf);
                        if (cli != null) {
                            Logg.info("Cliente encontrado!");
                            System.out.println(cli);
                            gerarPedido(func, cli, scanner);
                        } else {
                            Logg.warning("Cliente não encontrado.");
                        }
                        break;
                    case 3:
                        consultarPedido(scanner);
                        break;
                    case 4:
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

    private static void menuCliente(Scanner scanner) {
        int opcao = 0;

        do {
            Logg.info("=== Menu do Cliente ===");
            System.out.println("1. Criar Cliente");
            System.out.println("2. Editar Cliente");
            System.out.println("3. Excluir Cliente");
            System.out.println("4. Consultar Cliente");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        cadastrarCliente(scanner);
                        break;
                    case 2:
                        if (scanner.hasNextLine())
                            scanner.nextLine();
                        List<Cliente> c = ClienteController.listarClientes();
                        if (c.isEmpty()) {
                            Logg.warning("Nenhum cliente cadastrado.");
                            break;
                        }
                        Logg.info("TODOS OS CLIENTES");
                        c.forEach(System.out::println);

                        Logg.info("Informe o CPF do cliente");
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        Cliente cli = ClienteController.buscarCliente(cpf);

                        if (cli != null) {
                            Logg.info("Cliente encontrado!");
                            System.out.println(cli);
                            editarCliente(cli.getId(), cli, scanner);
                        } else {
                            Logg.warning("Cliente não encontrado.");
                        }
                        break;
                    case 3:
                        if (scanner.hasNextLine())
                            scanner.nextLine();
                        List<Cliente> cl = ClienteController.listarClientes();
                        Logg.info("TODOS OS CLIENTES");
                        cl.forEach(System.out::println);

                        Logg.info("Informe o CPF do cliente");
                        System.out.print("CPF: ");
                        String cp = scanner.nextLine();
                        Cliente clie = ClienteController.buscarCliente(cp);
                        Logg.info("Cliente encontrado!");
                        System.out.println(clie);

                        if (clie != null) {
                            Logg.warning("Deseja realmente excluir o cliente? (S/n)");
                            System.out.print("Resposta: ");
                            String resposta = scanner.nextLine().toUpperCase();
                            if (!resposta.equals("S")) {
                                Logg.info("Operação cancelada.");
                                break;
                            }
                            ClienteController.deletarCliente(clie.getCpf());
                            Logg.info("Cliente excluído com sucesso!");
                        } else {
                            Logg.warning("Cliente não encontrado.");
                        }
                        break;
                    case 4:
                        if (scanner.hasNextLine())
                            scanner.nextLine();
                        List<Cliente> clien = ClienteController.listarClientes();
                        Logg.info("TODOS OS CLIENTES");
                        clien.forEach(System.out::println);

                        Logg.info("Informe o CPF do cliente");
                        System.out.print("CPF: ");
                        String cpfBuscado = scanner.nextLine();
                        Cliente clienteBusca = ClienteController.buscarCliente(cpfBuscado);
                        if (clienteBusca == null) {
                            Logg.warning("Cliente não encontrado.");
                            break;
                        }
                        Logg.info("Cliente encontrado!");
                        System.out.println(clienteBusca);
                        NotaPedidoController.listarNotaByCliente(clienteBusca.getId());
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
        } while (opcao != 5);
    }

    private static void cadastrarCliente(Scanner scanner) {
        String nome, cpf, telefone;
        try {
            if (scanner.hasNextLine())
                scanner.nextLine();

            Logg.info("===Cadastro de Cliente===");
            System.out.print("Nome: ");
            nome = scanner.nextLine();
            System.out.print("Telefone: ");
            telefone = scanner.nextLine();
            System.out.print("CPF: ");
            cpf = scanner.nextLine();

            ClienteController.cadastrarCliente(nome, telefone, cpf);
            Logg.info("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            Logg.warning(e.getMessage());
        }
    }

    private static void gerarPedido(Funcionario func, Cliente cli, Scanner scanner) {
        String nome;
        int quantidade;
        Peca peca;
        try {
            // if(scanner.hasNextLine())
            // scanner.nextLine();

            Logg.info("===Gerar Pedido===");
            Pedido pedido = PedidoController.criaPedido(0, new Date(), Pedido.andamento, func, cli);
            Logg.info("Estoque");
            PecaController.listarPecasPorCategoria();
            Logg.info("Informe o nome da produto");
            System.out.print("produto: ");
            nome = scanner.nextLine();
            peca = PecaController.buscarPecaByNome(nome);
            if (peca == null) {
                Logg.info("Peca não encontrada!");
                PedidoController.excluirPedido(pedido.getIdPedido());
            } else {
                Logg.info("Informe a quantidade");
                System.out.print("Quantidade: ");
                quantidade = Integer.parseInt(scanner.nextLine());

                if (quantidade > peca.getQuantidadeEstoque()) {
                    Logg.info("Quantidade informada não disponivel em estoque!");
                    PedidoController.excluirPedido(pedido.getIdPedido());
                } else {
                    NotaPedido n = NotaPedidoController.criaNotaPedido(0, quantidade, peca, pedido);
                    Logg.info("Seu pedido: ");
                    System.out.println("ID: " + n.getIdNotaPedido());
                    System.out.println("NOME: " + n.getPeca().getNome());
                    System.out.println("FABRICANTE: " + n.getPeca().getFabricante());
                    System.out.println("QUANTIDADE: " + n.getQntPeca());
                    System.out.println("VALOR TOTAL: R$ " + n.getValorTotal());

                    Logg.info("Confirmar compra?(S/n)");
                    System.out.print("Resposta: ");
                    String resposta = scanner.nextLine().toUpperCase();

                    if (resposta.equals("S")) {
                        peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() - quantidade);
                        PecaController.editarPeca(peca.getIdPeca(), peca.getNome(), peca.getCategoria(),
                                peca.getFabricante(), peca.getPreco(), peca.getQuantidadeEstoque());

                        n.getPedido().setStatus(Pedido.completo);
                        Pedido p = n.getPedido();
                        PedidoController.editarPedido(p.getIdPedido(), p.getData(), p.getStatus(), p.getFuncionario(),
                                p.getCliente());

                        Logg.info("Pedido gerado com Sucesso!");
                    } else {
                        Logg.info("Pedido Cancelado!");
                        n.getPedido().setStatus(Pedido.problema);
                        Pedido p = n.getPedido();
                        PedidoController.editarPedido(p.getIdPedido(), p.getData(), p.getStatus(), p.getFuncionario(),
                                p.getCliente());
                    }
                }
            }
        } catch (InputMismatchException e) {
            Logg.warning("Erro: informe um valor válido.");
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consultarPedido(Scanner scanner) {
        List<Pedido> pedidos = PedidoController.listarPedidos();
        int id;
        if (pedidos.isEmpty()) {
            Logg.warning("Nenhum pedido cadastrado.");
            return;
        }
        Logg.info("TODOS OS PEDIDOS");
        pedidos.forEach(System.out::println);

        if (scanner.hasNextLine())
            scanner.nextLine();

        Logg.info("Informe o ID do pedido a ser consultado");
        System.out.print("ID: ");
        id = scanner.nextInt();
        NotaPedido n = NotaPedidoController.buscarNotaPedido(id);
        if (n == null) {
            Logg.info("Pedido não encontrado!");
        } else {
            System.out.println(n);
        }
    }

    private static void editarCliente(int id, Cliente cli, Scanner scanner) {
        int opcao = 0;
        do {
            Logg.info("=== Editar Cliente ===");
            System.out.println("1. Editar Nome");
            System.out.println("2. Editar Telefone");
            System.out.println("3. Editar CPF");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                if (scanner.hasNextLine())
                    scanner.nextLine();

                switch (opcao) {
                    case 1:
                        Logg.info("Informe o novo nome");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        cli.setNome(nome);
                        break;
                    case 2:
                        Logg.info("Informe o novo telefone");
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        cli.setTelefone(telefone);
                        break;
                    case 3:
                        Logg.info("Informe o novo CPF");
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        cli.setCpf(cpf);
                        break;
                    case 4:
                        Logg.info("Saindo...");
                        break;
                    default:
                        Logg.warning("Opção inválida, tente novamente.");
                }

                if (opcao >= 1 && opcao <= 3) {
                    ClienteController.atualizarCliente(id, cli.getNome(), cli.getTelefone(), cli.getCpf());
                    Logg.info("Cliente editado com sucesso!");
                }

            } catch (NumberFormatException e) {
                Logg.warning("Informe somente números para a opção.");
            } catch (InputMismatchException e) {
                Logg.warning("Erro: informe um valor válido.");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            } catch (Exception e) {
                Logg.warning(e.getMessage());
            }
        } while (opcao != 4);
    }

    public static void main(String[] args) {
        menuFuncionario(FuncionarioController.buscaFuncionarioById(90), new Scanner(System.in));
    }
}
