package model.dao;

import model.Peca;

import java.util.List;

public class DemoPecaDao {
    public static void main(String[] args) {
        Peca peca = new Peca(1, "coroa", "motomot", "teste", 50, 10);
        PecaDAO.criaPeca(peca);

        peca.setCategoria("Nova categoria");
        PecaDAO.editaPeca(peca);

        List<Peca> lista = PecaDAO.listaPecas();
        for (Peca peca1 : lista) {
            System.out.println(peca1);
        }
    }
}
