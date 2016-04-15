package model.dados;

import java.sql.*;
import model.*;
import java.util.Vector;

/**
 * Estabelece conexão e inicia o banco de dados.
 */
public class TB_CategoriaTransacoes extends DbConexao {

    TB_CategoriaTransacoes() throws SQLException {
        super();
    }

    public void init() {
        try {

            super.stm.executeUpdate("DROP TABLE IF EXISTS categoriaTransacoes");
            super.stm.executeUpdate("CREATE TABLE categoriaTransacoes ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome varchar(70),"
                    + "cor integer,"
                    + "emailUsuario varchar(70),"
                    + "orcamento real,"
                    + "FOREIGN KEY(emailUsuario) REFERENCES usuarios(email));");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void adicionar(CategoriaTransacao categoria) throws SQLException {
        super.stm = super.conn.createStatement();
        super.stm.executeUpdate("INSERT INTO categoriaTransacoes (nome,cor,orcamento,emailUsuario) VALUES ('"
                + categoria.nome + "','"
                + categoria.cor + "','"
                + categoria.orcamento + "','"
                + categoria.emailUsuario + "')");
    }

    public void remover(int id) throws SQLException {
        super.stm = super.conn.createStatement();
        super.stm.executeUpdate("DELETE FROM categoriaTransacoes WHERE id = '" + id + "'");
    }

    public Vector listarTodos(String usuario) throws Exception {
        Vector lista = new Vector();
        ResultSet rs;

        rs = super.stm.executeQuery("SELECT * FROM categoriaTransacoes WHERE emailUsuario = '" + usuario + "' ORDER BY id");

        while (rs.next()) {
            CategoriaTransacao categoria = new CategoriaTransacao();
            categoria.nome = rs.getString("nome");
            categoria.cor = Integer.parseInt(rs.getString("cor"));
            categoria.emailUsuario = rs.getString("emailUsuario");
            categoria.id = Integer.parseInt(rs.getString("id"));
            categoria.orcamento = Double.parseDouble(rs.getString("orcamento"));
            lista.add(categoria);
        }

        rs.close();
        return lista;
    }

    public void atualizar(CategoriaTransacao categoria) throws SQLException {
        super.stm = super.conn.createStatement();
        super.stm.executeUpdate("UPDATE categoriaTransacoes SET "
                + "nome = '" + categoria.nome + "',"
                + "cor = '" + categoria.cor + "',"
                + "orcamento = '" + categoria.orcamento + "' WHERE id = " + categoria.id + " ;");
    }
}