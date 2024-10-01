package model.dao;

import model.NotaPedido;
import model.Peca;
import model.Pedido;
import model.db.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaPedidoDAO {
    public static void criaNotaPedido(NotaPedido nota_pedido) {
        var sql = "INSERT INT nota_pedido(quantidade_peca, peca, pedido, valor_total) VALUES (?,?,?,?);";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1, nota_pedido.getQntPeca());
            pstmt.setInt(2, nota_pedido.getPeca().getIdPeca());
            pstmt.setInt(4, nota_pedido.getPedido().getIdPedido());
            pstmt.setDouble(5, nota_pedido.getValorTotal());

            int insertedRow = pstmt.executeUpdate();
            if(insertedRow > 0){
                ResultSet generetedKeys = pstmt.getGeneratedKeys();
                if(generetedKeys.next()){
                    nota_pedido.setIdNotaPedido(generetedKeys.getInt(1));
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void editaNotaPedido(NotaPedido nota_pedido) {
        var sql = "UPDATE nota_pedido SET quantidade_peca = ?, peca = ?, pedido = ?, valor_total = ?;";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, nota_pedido.getQntPeca());
            pstmt.setInt(2, nota_pedido.getPeca().getIdPeca());
            pstmt.setInt(4, nota_pedido.getPedido().getIdPedido());
            pstmt.setDouble(4, nota_pedido.getValorTotal());
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void excluirNotaPedido(NotaPedido nota_pedido) {
        var sql = "DELETE FROM nota_pedido WHERE id_nota_pedido = ?;";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, nota_pedido.getIdNotaPedido());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static NotaPedido visualizarNotaPedido(NotaPedido nota_pedido) {
        var sql = "SELECT id_nota_pedido AS id, quantidade_peca AS qntd, peca, pedido, valor_total AS valor FROM nota_pedido WHERE pedido = ?";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(4, nota_pedido.getPedido().getIdPedido());
            var rs = pstmt.executeQuery();

            if (rs.next()){
                int id = rs.getInt("id");
                int quantidadePeca = rs.getInt("qntd");
                Peca peca = PecaDAO.vizualizarPeca("peca");
                Pedido pedido = PedidoDAO.vizualizarPedido("pedido");
                Double valorTotal = rs.getDouble("valor");

                return new NotaPedido(id, quantidadePeca, peca, pedido, valorTotal);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<NotaPedido> listaNotasPedidos() {
        List<NotaPedido> notaPedidos = new ArrayList<>();
        var sql = "SELECT id_nota_pedido AS id, quantidade_peca AS qntd, peca, pedido, valor_total AS valor FROM nota_pedido;";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)) {
            var rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int quantidadePeca = rs.getInt("qntd");
                Peca peca = PecaDAO.vizualizarPeca("peca");
                Pedido pedido = PedidoDAO.vizualizarPedido("pedido");
                double valorTotal = rs.getDouble("valor");

                notaPedidos.add(new NotaPedido(id, quantidadePeca, peca, pedido, valorTotal));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return notaPedidos;
    }
}
