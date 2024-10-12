package view;

import control.*;
import model.dao.NotaPedidoDAO;
import model.dao.PecaDAO;
import model.entity.*;
import utils.Logg;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.LongFunction;

public class TelaFuncionario {

    public static void menuFuncionario(Funcionario func, Scanner scanner) {
        int opcao = 0;

        do {
            Logg.info("=== Menu do Funcionário ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Gerar Pedido");
            System.out.println("3. Consultar Pedido");
            System.out.println("4. Sair");
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

                        Logg.info("Informe o CPF do cliente");
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        Cliente cli = ClienteController.buscarCliente(cpf);
                        Logg.info("Cliente encontrado!");
                        System.out.println(cli);

                        if (cli != null) {
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
            } catch (Exception e) {
                Logg.severe("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 4);
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
//            if(scanner.hasNextLine())
//                scanner.nextLine();

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
                        PecaController.editarPeca(peca.getIdPeca(), peca.getNome(), peca.getCategoria(), peca.getFabricante(), peca.getPreco(), peca.getQuantidadeEstoque());

                        n.getPedido().setStatus(Pedido.completo);
                        Pedido p = n.getPedido();
                        PedidoController.editarPedido(p.getIdPedido(), p.getData(), p.getStatus(), p.getFuncionario(), p.getCliente());

                        Logg.info("Pedido gerado com Sucesso!");
                    } else {
                        Logg.info("Pedido Cancelado!");
                        n.getPedido().setStatus(Pedido.problema);
                        Pedido p = n.getPedido();
                        PedidoController.editarPedido(p.getIdPedido(), p.getData(), p.getStatus(), p.getFuncionario(), p.getCliente());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para consultar pedido
    private static void consultarPedido(Scanner scanner) {
        List<Pedido> pedidos = PedidoController.listarPedidos();
        int id;
        Logg.info("TODOS OS PEDIDOS");
        pedidos.forEach(System.out::println);

        if(scanner.hasNextLine())
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

    public static void main(String[] args) {
        menuFuncionario(FuncionarioController.buscaFuncionarioById(90), new Scanner(System.in));
    }
}
