package control.test;


import control.FuncionarioController;
import model.entity.Funcionario;

public class TestControllerFuncionario implements TestControllerComponent {

    @Override
    public boolean teste() {
        try {
            System.out.println("TESTES FuncionarioController");

            String nome = "Felipe da Silva", login = "felipehidequel", senha = "umadificil12";
            boolean gerente = true;

            Funcionario funci;

            FuncionarioController.criaFuncionario(nome, login, senha, gerente);
            funci = FuncionarioController.buscaFuncionario(nome);
            funci = FuncionarioController.buscaFuncionarioById(funci.getId());
            funci = FuncionarioController.buscaFuncionarioByLogin(login);

            FuncionarioController.realizarLogin(login, senha);
            FuncionarioController.editaFuncionario(funci.getId(), nome, "Novologin", senha, gerente, 3);

            for (Funcionario f :FuncionarioController.listarFuncionarios())
                System.out.println(f);

            FuncionarioController.excluirFuncionario(funci.getId());

            System.out.println("TUDO OK!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


        return false;
    }
}
