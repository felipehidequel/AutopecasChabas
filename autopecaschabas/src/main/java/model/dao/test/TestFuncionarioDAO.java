package model.dao.test;

import model.Funcionario;
import model.dao.FuncionarioDAO;

import java.util.List;

public class TestFuncionarioDAO implements TestDaoComponent {

    @Override
    public boolean teste() {

        try {
            System.out.println("TESTES EM FuncionarioDAO");

            System.out.println("Pegando os funcionarios do banco: ");
            List<Funcionario> funcs = FuncionarioDAO.listaFuncionarios();
            Funcionario func;
            if (funcs.isEmpty()){
                func = new Funcionario("Gerente Doutor", "hehehe", "eusouocara10", true);
            }
            else {
                func = funcs.getFirst();
            }

            System.out.println("Adicionando novo funcionario no banco:");
            FuncionarioDAO.criarFuncionario(func);
            System.out.println(func);
            System.out.println("Funcionario adicionado com sucesso!");

            System.out.println("Atualiza informção funcionario");
            func.setNome("Doutor Gerente");
            FuncionarioDAO.editaFuncario(func);
            System.out.println("Funcionario atualizado!");

            System.out.println("Buscando funcionario por nome:");
            func = FuncionarioDAO.buscaFuncionario("Doutor Gerente");
            System.out.println("Funcionario encontrando");

            System.out.println("Buscando funcionario por id:");
            FuncionarioDAO.buscarFuncionarioById(1);
            System.out.println("Funcionario encontrando");

            System.out.println("Funcionarios:");
            for (Funcionario f : FuncionarioDAO.listaFuncionarios())
                System.out.println(f);

            System.out.println("Deletando um funcionario:");
            FuncionarioDAO.excluirFuncionario(func);
            System.out.println("Funcionario deletado com sucesso!");

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Falhou em FuncionarioDAO");
        }

        return false;
    }
}
