package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;
import model.db.DB;

// CREATE TABLE funcionario (
// id_func SERIAL PRIMARY KEY,
// nome VARCHAR(50) NOT NULL,
// login VARCHAR(20) NOT NULL,
// senha VARCHAR(20) NOT NULL,
// gerente BOOLEAN NOT NULL
// );

public class FuncionarioDAO {
    public static void criarFuncionario(Funcionario funcionario) {
        String nome = funcionario.getNome();
        String login = funcionario.getLogin();
        String senha = funcionario.getSenha();
        Boolean gerente = funcionario.getGerente();


        String sql = "INSERT INTO funcionario(nome, login, senha, gerente) VALUES (?,?,?, ?);";
        try (Connection conn = DB.getConnection();) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, nome);
                pstmt.setString(2, login);
                pstmt.setString(3, senha);
                pstmt.setBoolean(4, gerente);
                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();

                if (rs.next()) {
                    funcionario.setId(rs.getInt("id_func"));
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void editaFuncario(Funcionario funcionario) {
        String nome = funcionario.getNome();
        String login = funcionario.getLogin();
        String senha = funcionario.getSenha();
        Boolean gerente = funcionario.getGerente();

        String sql = "UPDATE funcionario SET nome = ?, login = ?, senha = ?, gerente = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nome);
                pstmt.setString(2, login);
                pstmt.setString(3, senha);
                pstmt.setBoolean(4, gerente);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void excluirFuncionario(Funcionario funcionario) {
        String sql = "DELETE FROM funcionario WHERE id_func = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, funcionario.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Funcionario buscaFuncionario(String nome) {
        String sql = "SELECT id_func AS id, nome, login, senha, gerente FROM funcionario WHERE nome = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nome);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nome_func = rs.getString("nome");
                    String login = rs.getString("login");
                    String senha = rs.getString("senha");
                    Boolean gerente = rs.getBoolean("gerente");
                    Funcionario f = new Funcionario(nome_func, login, senha, gerente);
                    f.setId(id);
                    return f;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Funcionario buscaFuncionarioByLogin(String login) {
        String sql = "SELECT id_func AS id, nome, login, senha, gerente FROM funcionario WHERE login = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, login);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nome_func = rs.getString("nome");
                    String loginF = rs.getString("login");
                    String senha = rs.getString("senha");
                    Boolean gerente = rs.getBoolean("gerente");

                    Funcionario f = new Funcionario(nome_func, loginF, senha, gerente);
                    f.setId(id);
                    return f;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Funcionario buscarFuncionarioById(int id) {
        String sql = "SELECT * FROM funcionario WHERE id_func = ?;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int idf = rs.getInt("id_func");
                    String nomeF = rs.getString("nome");
                    String login = rs.getString("login");
                    String senha = rs.getString("senha");
                    Boolean gerente = rs.getBoolean("gerente");
                    Funcionario f = new Funcionario(nomeF, login, senha, gerente);
                    f.setId(idf);
                    return f;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static List<Funcionario> listaFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT id_func AS id, nome, login, senha, gerente FROM funcionario;";
        try (Connection conn = DB.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String login = rs.getString("login");
                    String senha = rs.getString("senha");
                    Boolean gerente = rs.getBoolean("gerente");
                    Funcionario f = new Funcionario(nome, login, senha, gerente);
                    f.setId(id);
                    funcionarios.add(f);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return funcionarios;
    }

}
