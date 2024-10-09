package model.dao;

import model.db.DB;
import model.Cliente;
import model.Funcionario;
import model.Peca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {

    public static void criarCliente(Cliente cliente) {
        String nome = cliente.getNome();
        String telefone = cliente.getTelefone();
        String cpf = cliente.getCpf();

        String sql = "INSERT INTO cliente(nome, telefone, cpf) VALUES (?,?,?);";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, nome);
                pstmt.setString(2, telefone);
                pstmt.setString(3, cpf);
                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void editaCliente(Cliente cliente) {
        String nome = cliente.getNome();
        String telefone = cliente.getTelefone();
        String cpf = cliente.getCpf();

        String sql = "UPDATE cliente SET nome = ?, telefone = ?, cpf = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nome);
                pstmt.setString(2, telefone);
                pstmt.setString(3, cpf);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void excluirCliente(Cliente cliente){
        String sql = "DELETE FROM cliente WHERE id_cliente = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)){

                pstmt.setInt(1, cliente.getId());
                pstmt.executeUpdate();

            }
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
    }

/*
 * CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(11) NOT NULL
 */
    public static Cliente buscarClienteByCpf(String cpf){
        String sql = "SELECT id_cliente,nome,telefone,cpf from cliente WHERE cpf = ?;";

        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, cpf);
                ResultSet rs = pstmt.executeQuery();

                if(rs.next()){
                    int id = rs.getInt("id_cliente");
                    String nomeCliente = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    String cpfAchado = rs.getString("cpf");
                    Cliente c = new Cliente(nomeCliente, telefone, cpfAchado);
                    c.setId(id);
                    return c;

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Cliente buscarClienteById (int id){
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?;";
        try(Connection conn = DB.getConnection()) {
            assert conn != null;
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    int idc = rs.getInt("id_cliente");
                    String nomec = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    String cpf = rs.getString("cpf");

                    Cliente c = new Cliente(nomec, telefone, cpf);
                    c.setId(idc);
                    return c;
                }
            }
        } catch (SQLException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static List<Cliente> listaClientes(){
        List<Cliente> clientes = new ArrayList<Cliente>();

        String sql = "SELECT id_cliente AS id, nome, telefone, cpf from cliente;";

        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)){
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nomeCliente = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    String cpf = rs.getString("cpf");

                    Cliente c = new Cliente(nomeCliente, telefone, cpf);
                    c.setId(id);

                    clientes.add(c);
                }
                return clientes;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
