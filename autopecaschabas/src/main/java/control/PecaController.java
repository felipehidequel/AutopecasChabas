package control;
import java.util.ArrayList;
import java.util.List;

import model.entity.Peca;
import model.dao.PecaDAO;

public class PecaController{
    public static Peca criaPeca(int idpeca, String nome, String categoria, String fabricante, double preco, int quantidadeEstoque){

        if(nome == null){
            throw new IllegalArgumentException("Nome da peça não pode ser nulo ou vazio.\n");
        }

        if(categoria == null){
            throw new IllegalArgumentException("Categoria da peça não pode ser nula ou vazia.\n");
        }

        if(fabricante == null){
            throw new IllegalArgumentException("Fabricante não pode ser nulo ou vazio.\n");
        }

        if(preco < 0){
            throw new IllegalArgumentException("Preço não pode ser negativo.\n");
        }

        if(quantidadeEstoque < 0){
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.\n");
        }

        Peca peca = new Peca(idpeca, nome, categoria, fabricante, preco, quantidadeEstoque);
        PecaDAO.criaPeca(peca);
        return(peca);
    }

    public static Peca editarPeca(int idpeca, String nome, String categoria, String fabricante, double preco, int quantidadeEstoque){
        if(idpeca <= 0){
            throw new IllegalArgumentException("ID da peça deve ser maior que zero.");
        }
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome da peça não pode ser vazio.");
        }
        if(categoria == null || categoria.isEmpty()){
            throw new IllegalArgumentException("Categoria não pode ser vazia.");
        }
        if(fabricante == null || fabricante.isEmpty()){
            throw new IllegalArgumentException("Fabricante não pode ser vazio.");
        }
        if(preco < 0){
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        if(quantidadeEstoque < 0){
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.");
        }

        var pe = new Peca(idpeca, nome, categoria, fabricante, preco, quantidadeEstoque);
        PecaDAO.editaPeca(pe);
        return(pe);
    }

    public static boolean excluirPeca(int idpeca) {
        Peca peca = PecaDAO.buscarPecaById(idpeca);
        if(peca == null){
            return(false);
        }

        try{
            PecaDAO.excluirPeca(peca);
            return (true); 
        }catch(Exception e){
            System.err.println("Erro ao excluir a peça: " + e.getMessage());
            return(false);
        }
    }

    public static List<Peca> listarPeca(){
        try{
            List<Peca> pecas = PecaDAO.listaPecas();
            return pecas != null ? pecas : new ArrayList<>();
        
        }catch(Exception e){
            System.out.println("Erro ao listar as peças:" + e.getMessage());
            return(new ArrayList<>());
        }
    }

    public static Peca buscarPeca(int idpeca){
        if(idpeca <= 0){
            throw new IllegalArgumentException("ID da peca deve ser maior que zero");
        }
        try{
            Peca peca = PecaDAO.buscarPecaById(idpeca);
            if(peca == null){
                System.out.println("peça não encontrada");
            }
            return (peca);
        }catch(Exception e){
            System.out.println("Erro ao buscar a peça:" + e.getMessage());
            return (null);
        }
    }
}
