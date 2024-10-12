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
                        cadastrarCliente();
                        break;
                    case 2:
                        Logg.info("Informe o CPF do cliente");
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        Cliente cli = ClienteController.buscarCliente(cpf);

                        if (cli != null) {
                            gerarPedido(func, cli);
                        } else {
                            Logg.warning("Cliente não encontrado.");
                        }
                        break;
                    case 3:
                        consultarPedido();
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

    private static void cadastrarCliente() {
        String nome, cpf, telefone;
        Scanner sc = new Scanner(System.in);
        try {
            Logg.info("===Cadastro de Cliente===");
            System.out.print("Nome: ");
            nome = sc.nextLine();
            System.out.print("Telefone: ");
            telefone = sc.nextLine();
            System.out.print("CPF: ");
            cpf = sc.nextLine();

            ClienteController.cadastrarCliente(nome, telefone, cpf);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }

    private static void gerarPedido(Funcionario func, Cliente cli) {
        String nome;
        int quantidade;
        Peca peca;
        Scanner sc = new Scanner(System.in);
        try {
            Logg.info("===Gerar Pedido===");
            Pedido pedido = PedidoController.criaPedido(0, new Date(), Pedido.andamento, func, cli);
            Logg.info("Estoque");
            PecaController.listarPecasPorCategoria();
            Logg.info("Informe o nome da produto");
            System.out.print("produto: ");
            nome = sc.nextLine();
            peca = PecaController.buscarPecaByNome(nome);
            if (peca == null) {
                Logg.info("Peca não encontrada!");
                PedidoController.excluirPedido(pedido.getIdPedido());
            } else {
                Logg.info("Informe a quantidade");
                System.out.print("Quantidade: ");
                quantidade = Integer.parseInt(sc.nextLine());

                if (quantidade > peca.getQuantidadeEstoque()) {
                    Logg.info("Quantidade informada não disponivel em estoque!");
                    PedidoController.excluirPedido(pedido.getIdPedido());
                } else {
                    NotaPedidoController.criaNotaPedido(0, quantidade, peca, pedido);
                    peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() - quantidade);
                    PecaController.editarPeca(peca.getIdPeca(), peca.getNome(), peca.getCategoria(), peca.getFabricante(), peca.getPreco(), peca.getQuantidadeEstoque());
                    Logg.info("Pedido gerado com Sucesso!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para consultar pedido
    private static void consultarPedido() {
        List<Pedido> pedidos = PedidoController.listarPedidos();
        int id;
        Scanner sc = new Scanner(System.in);
        Logg.info("TODOS OS PEDIDOS");
        pedidos.forEach(System.out::println);

        Logg.info("Informe o ID do pedido a ser consultado");
        System.out.print("ID: ");
        id = Integer.parseInt(sc.nextLine());
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
