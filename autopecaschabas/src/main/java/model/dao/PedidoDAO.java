package model.dao;

import model.Pedido;
import model.db.DB;

import java.sql.*;
import java.util.Calendar;

//CREATE TABLE pedido (
//        id_pedido SERIAL PRIMARY KEY,
//        data_pedido DATE NOT NULL,
//        status VARCHAR(10) NOT NULL,
//id_func INT NOT NULL,
//id_cliente INT NOT NULL,

public class PedidoDAO {

    public static Pedido buscarPedidoById(int idPedido){
        Pedido pedido = null;
        var sql = "SELECT * FROM pedido WHERE id = ?";
        try(var conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPedido);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_pedido");
                int idCliente = rs.getInt("id_cliente");
                int idFuncionario = rs.getInt("id_func");
                Date data = rs.getDate("data_pedido");

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


        return  null;
    }
    
}
