package model.dao;

import model.Pedido;
import model.db.DB;
import model.Funcionario;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* id_pedido SERIAL,
    data_pedido date NOT NULL,
    status character varying(10) COLLATE pg_catalog."default" NOT NULL,
    id_func integer NOT NULL,
    id_cliente integer NOT NULL,
*/

public class PedidoDAO {

    public static void criaPedido(Pedido pedido) {
        String sql = "INSERT INTO pedido(data_pedido, status, id_func, id_cliente) VALUES (?,?,?,?);";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setDate(1, new java.sql.Date(pedido.getData().getTime()));
                pstmt.setString(2, pedido.getStatus());
                pstmt.setInt(3, pedido.getFuncionario().getId());
                pstmt.setInt(4, pedido.getCliente().getId());

                int insertedRow = pstmt.executeUpdate();
                if (insertedRow > 0) {
                    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            pedido.setIdPedido(generatedKeys.getInt(1));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editaPedido(Pedido pedido) {
        String sql = "UPDATE pedido SET data_pedido = ?, status = ?, id_func = ?, id_cliente = ? WHERE id_pedido = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setDate(1, new java.sql.Date(pedido.getData().getTime()));
                pstmt.setString(2, pedido.getStatus());
                pstmt.setInt(3, pedido.getFuncionario().getId());
                pstmt.setInt(4, pedido.getCliente().getId());
                pstmt.setInt(5, pedido.getIdPedido());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void excluirPedido(Pedido pedido) {
        String sql = "DELETE FROM pedido WHERE id_pedido = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, pedido.getIdPedido());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Pedido> listarPedido() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT id_pedido, data_pedido, status, id_func, id_cliente FROM pedido;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id_pedido");
                    Date data = rs.getDate("data_pedido");
                    String status = rs.getString("status");
                    Funcionario funcionario = FuncionarioDAO.buscarFuncionarioById(rs.getInt("id_func"));
                    Cliente cliente = ClienteDAO.buscarClienteById(rs.getInt("id_cliente"));

                    pedidos.add(new Pedido(id, data, status, funcionario, cliente));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pedidos;
    }

    public static void editarStatus(int idPedido, String novoStatus) {
        String sql = "UPDATE pedido SET status = ? WHERE id_pedido = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, novoStatus);
                pstmt.setInt(2, idPedido);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Pedido buscarPedidoById(int idPedido) {
        String sql = "SELECT * FROM pedido WHERE id_pedido = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idPedido);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id_pedido");
                    Date data = rs.getDate("data_pedido");
                    String status = rs.getString("status");
                    Funcionario funcionario = FuncionarioDAO.buscarFuncionarioById(rs.getInt("id_func"));
                    Cliente cliente = ClienteDAO.buscarClienteById(rs.getInt("id_cliente"));

                    return new Pedido(id, data, status, funcionario, cliente);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
