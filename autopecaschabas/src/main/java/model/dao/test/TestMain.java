package model.dao.test;

public class TestMain {
    public static void main(String[] args) {
        TestDaoComposite testes;
        testes = new TestDaoComposite(new TestPecaDAO(), new TestClienteDAO(), new TestFuncionarioDAO(), new TestPedidoDAO(), new TestNotaPedidoDAO());


        testes.teste();
    }
}
