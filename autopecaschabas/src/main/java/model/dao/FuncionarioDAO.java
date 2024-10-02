package model.dao;

import java.sql.SQLException;
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


        var sql = "INSET INTO funcionario(nome, login, senha, gerente) VALUES (?,?,?, ?);";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, login);
            pstmt.setString(3, senha);
            pstmt.setBoolean(4, gerente);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void editaFuncario(Funcionario funcionario) {
        String nome = funcionario.getNome();
        String login = funcionario.getLogin();
        String senha = funcionario.getSenha();
        Boolean gerente = funcionario.getGerente();

        var sql = "UPDATE funcionario SET nome = ?, login = ?, senha = ?, gerente = ?;";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, login);
            pstmt.setString(3, senha);
            pstmt.setBoolean(4, gerente);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void excluirFuncionario(Funcionario funcionario) {
        var sql = "DELETE FROM funcionario WHERE id_func = ?;";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, funcionario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Funcionario buscaFuncionario(String nome){
        var sql = "SELECT id_func AS id, nome, login, senha, gerente FROM funcionario WHERE nome = ?;";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nome);
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome_func = rs.getString("nome");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                Boolean gerente = rs.getBoolean("gerente");

                return new Funcionario(nome_func, login, senha, gerente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    
    public static List<Funcionario> listaFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        var sql = "SELECT id_func AS id, nome, login, senha, gerente FROM funcionario;";
        try (var conn = DB.getConnection(); var pstmt = conn.prepareStatement(sql)) {
            var rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                Boolean gerente = rs.getBoolean("gerente");

                funcionarios.add(new Funcionario(nome, login, senha, gerente));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return funcionarios;
    }
    
}
