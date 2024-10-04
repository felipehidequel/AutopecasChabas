package model.dao.test;

import model.Peca;
import model.dao.PecaDAO;

import java.util.List;

public class TestPecaDAO implements TestDaoComponent {
    @Override
    public boolean teste() {
        try {
            System.out.println("\nTESTES EM PecaDAO");
            // Criando uma nova peça
            Peca peca = new Peca(0, "Pneu", "Pneu", "Pirelli", 200.00, 10);
            PecaDAO.criaPeca(peca);
            System.out.println("Peça criada com sucesso!");

            // Editando a peça criada
            peca.setNome("Pneu de moto");
            PecaDAO.editaPeca(peca);
            System.out.println("Peça editada com sucesso!");

            // Listando todas as peças
            List<Peca> pecas = PecaDAO.listaPecas();
            // Buscando uma peça por ID
            System.out.println("Peça buscada por id:");
            peca = PecaDAO.buscarPecaById(1);
            System.out.println(peca);

            // Buscando uma peça por nome
            System.out.println("Peça buscada por nome:");
            peca = PecaDAO.vizualizarPeca("Pneu de moto");
            System.out.println(peca);

            // Excluindo a peça
            PecaDAO.excluirPeca(peca);

            System.out.println("Peça deletada com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Falhou em PecaDAO");
        }
        return false;
    }
}
