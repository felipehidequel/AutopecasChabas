package model.dao;

import model.Peca;
import model.db.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecaDAO {

    public static void criaPeca(Peca peca) {
        String sql = "INSERT INTO peca(nome, categoria, fabricante, preco, quantidade_estoque) VALUES (?,?,?,?,?);";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, peca.getNome());
                pstmt.setString(2, peca.getCategoria());
                pstmt.setString(3, peca.getFabricante());
                pstmt.setDouble(4, peca.getPreco());
                pstmt.setInt(5, peca.getQuantidadeEstoque());

                int insertedRow = pstmt.executeUpdate();
                if (insertedRow > 0) {
                    ResultSet generatedKeys = pstmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        peca.setIdPeca(generatedKeys.getInt(1)); // Atualiza o ID da peça com o valor gerado
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editaPeca(Peca peca) {
        String sql = "UPDATE peca SET nome = ?, categoria = ?, fabricante = ?, preco = ?, quantidade_estoque = ? WHERE id_peca = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, peca.getNome());
                pstmt.setString(2, peca.getCategoria());
                pstmt.setString(3, peca.getFabricante());
                pstmt.setDouble(4, peca.getPreco());
                pstmt.setInt(5, peca.getQuantidadeEstoque());
                pstmt.setInt(6, peca.getIdPeca());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void excluirPeca(Peca peca) {
        String sql = "DELETE FROM peca WHERE id_peca = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, peca.getIdPeca());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Peca vizualizarPeca(String nomePeca) {
        String sql = "SELECT id_peca AS id, nome, categoria, fabricante, preco, quantidade_estoque AS qtd FROM peca WHERE nome = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nomePeca);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String categoria = rs.getString("categoria");
                    String fabricante = rs.getString("fabricante");
                    double preco = rs.getDouble("preco");
                    int quantidadeEstoque = rs.getInt("qtd");

                    return new Peca(id, nome, categoria, fabricante, preco, quantidadeEstoque);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Peca buscarPecaById(int idPeca){
        String sql = "SELECT id_peca, nome, categoria, fabricante, preco, quantidade_estoque AS qtd FROM peca WHERE id_peca = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idPeca);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id_peca");
                    String nome = rs.getString("nome");
                    String categoria = rs.getString("categoria");
                    String fabricante = rs.getString("fabricante");
                    double preco = rs.getDouble("preco");
                    int quantidadeEstoque = rs.getInt("qtd");

                    return new Peca(id, nome, categoria, fabricante, preco, quantidadeEstoque);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static List<Peca> listaPecas() {
        List<Peca> pecas = new ArrayList<>();
        String sql = "SELECT id_peca AS id, nome, categoria, fabricante, preco, quantidade_estoque AS qtd FROM peca;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String categoria = rs.getString("categoria");
                    String fabricante = rs.getString("fabricante");
                    double preco = rs.getDouble("preco");
                    int quantidadeEstoque = rs.getInt("qtd");

                    pecas.add(new Peca(id, nome, categoria, fabricante, preco, quantidadeEstoque));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return pecas;
    }
}
