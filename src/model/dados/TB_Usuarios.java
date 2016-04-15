package model.dados;

import java.sql.*;
import model.*;
import java.util.Vector;

/**
 * Estabelece conexão e inicia o banco de dados.
 */
public class TB_Usuarios extends DbConexao {

    TB_Usuarios() throws SQLException {
        super();
    }

    public void init() throws SQLException {
        super.stm.executeUpdate("DROP TABLE IF EXISTS usuarios");
        super.stm.executeUpdate("CREATE TABLE usuarios ("
                + "nome varchar(70) ,"
                + "senha varchar(10),"
                + "dicaSenha varchar(70),"
                + "emailVinculado varchar(70),"
                + "email varchar(70) PRIMARY KEY NOT NULL);");
    }

    public void adicionar(Usuario usuario) throws SQLException {
        super.stm = super.conn.createStatement();
        super.stm.executeUpdate("INSERT INTO usuarios VALUES ('"
                + usuario.nome + "','"
                + usuario.senha + "','"
                + usuario.dicaSenha + "','"
                + usuario.emailVinculado + "','"
                + usuario.email + "')");
    }

    public void remover(String email) throws SQLException {
        super.stm = super.conn.createStatement();
        super.stm.executeUpdate("DELETE FROM usuarios WHERE email = '" + email + "'");
    }

    public void atualizar(Usuario usuario) throws SQLException {
        super.stm = super.conn.createStatement();
        super.stm.executeUpdate("UPDATE usuarios SET "
                + "nome = '" + usuario.nome + "',"
                + "senha = '" + usuario.senha + "',"
                + "dicaSenha = '" + usuario.dicaSenha + "' WHERE email = '" + usuario.email + "' ;");
    }

    public Vector listarTodos() throws Exception {
        Vector lista = new Vector();
        ResultSet rs;
        rs = super.stm.executeQuery("SELECT * FROM usuarios ORDER BY nome");

        while (rs.next()) {
            Usuario usr = new Usuario();
            usr.nome = rs.getString("nome");
            usr.senha = rs.getString("senha");
            usr.dicaSenha = rs.getString("dicaSenha");
            usr.email = rs.getString("email");
            lista.add(usr);
        }
        rs.close();
        return lista;
    }

    public Vector listarContasVinculadas(String emailVinculado) throws Exception {
        Vector lista = new Vector();
        ResultSet rs;
        rs = super.stm.executeQuery("SELECT * FROM usuarios WHERE emailVinculado = '" + emailVinculado + "' ORDER BY nome");

        while (rs.next()) {
            Usuario usr = new Usuario();
            usr.nome = rs.getString("nome");
            usr.senha = rs.getString("senha");
            usr.dicaSenha = rs.getString("dicaSenha");
            usr.email = rs.getString("email");
            lista.add(usr);
        }
        rs.close();
        return lista;
    }
}
