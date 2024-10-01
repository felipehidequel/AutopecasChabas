package model.dao;

import model.db.DB;
import model.Cliente;
import model.Peca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(11) NOT NULL
 */

public class ClienteDAO {

    public static void criarCliente(Cliente cliente) {
        String nome = cliente.getNome();
        String telefone = cliente.getTelefone();
        String cpf = cliente.getCpf();

        var sql = "INSET INTO cliente(nome, telefone, cpf) VALUES (?,?,?);";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, telefone);
            pstmt.setString(3, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void editaCliente(Cliente cliente) {
        String nome = cliente.getNome();
        String telefone = cliente.getTelefone();
        String cpf = cliente.getCpf();

        var sql = "UPDATE cliente SET nome = ?, telefone = ?, cpf = ?;";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, telefone);
            pstmt.setString(3, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void excluirCliente(Cliente cliente){
        var sql = "DELETE FROM cliente WHERE id_cliente = ?;";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, cliente.getId());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
    }

        public static void excluirPeca(Peca peca) {
        var sql = "DELETE FROM peca WHERE id_peca = ?;";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, peca.getIdPeca());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Cliente buscarCliente(String nome){
        var sql = "SELECT id_cliente,nome,telefone,cpf from cliente WHERE nome = ?;";

        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nome);
            var rs = pstmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id_cliente");
                String nome_cliente = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String cpf = rs.getString("cpf");

                return new Cliente(id, nome_cliente, telefone, cpf);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    
    public static List<Cliente> listaClientes(){
        List<Cliente> clientes = new ArrayList<Cliente>();

        var sql = "SELECT id_peca AS id, nome, telefone, cpf from cliente;";

        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)){
            var rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nome_cliente = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String cpf = rs.getString("cpf");

                clientes.add(new Cliente(id, nome_cliente, telefone, cpf));
            }
            return clientes;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}