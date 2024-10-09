package model.dao;

import model.NotaPedido;
import model.Peca;
import model.Pedido;
import model.db.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CREATE TABLE nota_pedido (
//        id_nota SERIAL PRIMARY KEY,
//        id_peca INT NOT NULL,
//        id_pedido INT NOT NULL,
//        quantidade_peca INT NOT NULL,
//        valor_total DOUBLE PRECISION NOT NULL,

public class NotaPedidoDAO {
    public static void criaNotaPedido(NotaPedido nota_pedido) {
        String sql = "INSERT INTO nota_pedido(id_peca, id_pedido, quantidade_peca, valor_total) VALUES (?,?,?,?);";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                pstmt.setInt(1, nota_pedido.getPeca().getIdPeca());
                pstmt.setInt(2, nota_pedido.getPedido().getIdPedido());
                pstmt.setInt(3, nota_pedido.getQntPeca());
                pstmt.setDouble(4, nota_pedido.getValorTotal());
                int insertedRow = pstmt.executeUpdate();
                if(insertedRow > 0){
                    ResultSet generetedKeys = pstmt.getGeneratedKeys();
                    if(generetedKeys.next()){
                        nota_pedido.setIdNotaPedido(generetedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void editaNotaPedido(NotaPedido nota_pedido) {
        String sql = "UPDATE nota_pedido SET quantidade_peca = ?, id_peca = ?, id_pedido = ?, valor_total = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, nota_pedido.getQntPeca());
                pstmt.setInt(2, nota_pedido.getPeca().getIdPeca());
                pstmt.setInt(4, nota_pedido.getPedido().getIdPedido());
                pstmt.setDouble(4, nota_pedido.getValorTotal());
                pstmt.executeUpdate();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void excluirNotaPedido(NotaPedido nota_pedido) {
        String sql = "DELETE FROM nota_pedido WHERE id_nota = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, nota_pedido.getIdNotaPedido());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static NotaPedido buscarNotaPedido(NotaPedido nota_pedido) {
        String sql = "SELECT * FROM nota_pedido WHERE id_nota = ?";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, nota_pedido.getIdNotaPedido());
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()){
                    int id = rs.getInt("id_nota");
                    int quantidadePeca = rs.getInt("quantidade_peca");
                    Peca peca = PecaDAO.buscarPecaById(rs.getInt("id_peca"));
                    Pedido pedido = PedidoDAO.buscarPedidoById(rs.getInt("id_pedido"));
                    return new NotaPedido(id, quantidadePeca, peca, pedido);
                }
            }
        } catch (SQLException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<NotaPedido> listarNotasPedidos() {
        List<NotaPedido> notaPedidos = new ArrayList<>();
        String sql = "SELECT id_nota AS id, quantidade_peca AS qntd, id_peca, id_pedido, valor_total AS valor FROM nota_pedido;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int quantidadePeca = rs.getInt("qntd");
                    Peca peca = PecaDAO.buscarPecaById(rs.getInt("id_peca"));
                    Pedido pedido = PedidoDAO.buscarPedidoById(rs.getInt("id_pedido"));
                    double valorTotal = rs.getDouble("valor");

                    notaPedidos.add(new NotaPedido(id, quantidadePeca, peca, pedido));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return notaPedidos;
    }
}
