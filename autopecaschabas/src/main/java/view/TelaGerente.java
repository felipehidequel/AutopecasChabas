package view;

import control.FuncionarioController;
import control.PecaController;
import model.entity.Funcionario;
import model.entity.Peca;
import utils.Logg;

import java.util.List;
import java.util.Scanner;

public class TelaGerente {

    public static void menuGerente() {
        Funcionario func = null;
        String nome, login, senha;
        boolean sair = false, gerente, funcionarioCadastrado = false;
        int id, quantidade;

        try(Scanner sc = new Scanner(System.in)) {
            do {
                Logg.info("\n-------------- Menu do Gerente --------------");
                Logg.info("1. Cadastrar Novo Funcionário");
                Logg.info("2. Listar Funcionários");
                Logg.info("3. Remover Funcionário");
                Logg.info("4. Atualizar estoque");
                Logg.info("5. Sair");

                int opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        while (!funcionarioCadastrado) {
                            try {
                                Logg.info("<><><><> Preencha as informações para cadastrar um novo funcionario <><><><>");
                                Logg.info("Insira o nome completo: ");
                                nome = sc.nextLine();

                                Logg.info("Insira o nome de usuário: ");
                                login = sc.nextLine();

                                Logg.info("Insira a senha: ");
                                senha = sc.nextLine();

                                Logg.info("O funcionário é gerente? (s/n): ");
                                gerente = sc.nextLine().equalsIgnoreCase("s");

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
                                Logg.info("Nome: " + funcionario.getNome() + ", Login: " + funcionario.getLogin() + ", Cargo: " + (funcionario.getGerente() ? "Gerente" : "Funcionário ativo")); //mudar o cargo caso não seja gerente
                            }
                        }
                        break;
                    case 3:
                        List<Funcionario> verificaFuncionario = FuncionarioController.listarFuncionarios();
                        if (verificaFuncionario.isEmpty()) {
                            Logg.info("Não há funcionários cadastrados para remover.");
                        } else {
                            Logg.info("Digite o ID do funcionário a ser removido:");
                            id = sc.nextInt();
                            sc.nextLine();
                            try {
                                FuncionarioController.excluirFuncionario(id);
                                Logg.info("Funcionário removido com sucesso!");
                            } catch (IllegalArgumentException e) {
                                Logg.warning("Erro ao remover funcionário: " + e.getMessage());
                            }
                        }
                        break;
                    case 4:
                        Logg.info("Digite o ID do pedido que deseja atualizar:");
                        id = sc.nextInt();
                        sc.nextLine();

                        Logg.info("Informe a quantidade que será adicionada ao estoque:");
                        quantidade = sc.nextInt();
                        sc.nextLine();

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
            } while(!sair);

        } catch(IllegalArgumentException e){
            Logg.warning("Erro: " + e.getMessage());
            Logg.info("Por Favor, tente novamente.");
        }
    }

    public static void main(String[] args) {
        TelaGerente.menuGerente();
    }
}