package control;

import java.util.List;

import model.entity.Peca;
import model.dao.PecaDAO;

public class PecaController {
    public static Peca criaPeca(int idpeca, String nome, String categoria, String fabricante, double preco, int quantidadeEstoque) {

        if (nome == null) {
            throw new IllegalArgumentException("Nome da peça não pode ser nulo ou vazio.\n");
        }

        if (categoria == null) {
            throw new IllegalArgumentException("Categoria da peça não pode ser nula ou vazia.\n");
        }

        if (fabricante == null) {
            throw new IllegalArgumentException("Fabricante não pode ser nulo ou vazio.\n");
        }

        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.\n");
        }

        if (quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.\n");
        }

        Peca peca = new Peca(idpeca, nome, categoria, fabricante, preco, quantidadeEstoque);
        PecaDAO.criaPeca(peca);
        return (peca);
    }

    public static Peca editarPeca(int idpeca, String nome, String categoria, String fabricante, double preco, int quantidadeEstoque) {
        var pe = new Peca(idpeca, nome, categoria, fabricante, preco, quantidadeEstoque);
        PecaDAO.editaPeca(pe);
        return (pe);
    }

    public static boolean excluirPeca(int idpeca) {
        Peca peca = PecaDAO.buscarPecaById(idpeca);
        if (peca == null) {
            return (false);
        }
        PecaDAO.excluirPeca(peca);
        return (true);
    }

    public static List<Peca> listarPeca() {
        return PecaDAO.listaPecas();
    }

    public static Peca buscarPeca(int idpeca) {
        return (PecaDAO.buscarPecaById(idpeca));
    }
}
