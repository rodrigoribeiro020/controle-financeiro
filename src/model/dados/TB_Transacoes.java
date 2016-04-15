package model.dados;

import java.sql.*;
import java.util.Vector;

import model.Transacao;

public class TB_Transacoes extends DbConexao {

    TB_Transacoes() throws SQLException {
        super();
    }

    public void init() {
        try {

            super.stm.executeUpdate("DROP TABLE IF EXISTS transacoes");
            super.stm.executeUpdate("CREATE TABLE transacoes ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "categoria varchar(70) ,"
                    + "dataInsercao date,"
                    + "tipoRecorrencia varchar(70),"
                    + "tipoTransacao varchar(70),"
                    + "descricao varchar(70),"
                    + "valor real,"
                    + "emailUsuario varchar(70),"
                    + "FOREIGN KEY(emailUsuario) REFERENCES usuarios(email));");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void adicionar(Transacao transacao) throws SQLException {
        transacao.dataInsercao = dataPadraotoSQL(transacao.dataInsercao);
        super.stm = super.conn.createStatement();
        super.stm.executeUpdate("INSERT INTO transacoes (categoria,dataInsercao,tipoRecorrencia,tipoTransacao,descricao, valor, emailUsuario) VALUES ('"
                + transacao.categoria + "','"
                + transacao.dataInsercao + "','"
                + transacao.tipoRecorrencia + "','"
                + transacao.tipoTransacao + "','"
                + transacao.descricao + "','"
                + transacao.valor + "','"
                + transacao.emailUsuario + "')");
    }

    public void remover(int id) throws SQLException {
        super.stm = super.conn.createStatement();
        super.stm.executeUpdate("DELETE FROM transacoes WHERE id = " + id);
    }

    public void atualizar(Transacao transacao) throws SQLException {
        transacao.dataInsercao = dataPadraotoSQL(transacao.dataInsercao);
        super.stm = super.conn.createStatement();
        super.stm.executeUpdate("UPDATE transacoes SET "
                + "categoria = '" + transacao.categoria + "',"
                + "dataInsercao = '" + transacao.dataInsercao + "',"
                + "tipoRecorrencia = '" + transacao.tipoRecorrencia + "',"
                + "tipoTransacao = '" + transacao.tipoTransacao + "',"
                + "descricao = '" + transacao.descricao + "',"
                + "valor = '" + transacao.valor + "' WHERE id = '" + transacao.id + "' ;");
    }

    public Vector listarPorData(String datainicial, String datafinal, String usuario) throws Exception {
        Vector lista = new Vector();
        ResultSet rs;
        datainicial = dataPadraotoSQL(datainicial);
        datafinal = dataPadraotoSQL(datafinal);
        rs = super.stm.executeQuery("SELECT * FROM transacoes WHERE emailUsuario = '" + usuario + "' AND dataInsercao BETWEEN '" + datainicial + "' AND '" + datafinal + "'  ORDER BY id");

        while (rs.next()) {
            Transacao transacao = new Transacao();
            transacao.categoria = rs.getString("categoria");
            transacao.id = Integer.parseInt(rs.getString("id"));
            
            transacao.dataInsercao = rs.getString("dataInsercao");
            transacao.dataInsercao = dataSQLtoPadrao(transacao.dataInsercao);
            
            transacao.tipoRecorrencia = rs.getString("tipoRecorrencia");
            transacao.tipoTransacao = rs.getString("tipoTransacao");
            transacao.valor = Double.parseDouble(rs.getString("valor"));
            transacao.descricao = rs.getString("descricao");
            transacao.emailUsuario = rs.getString("emailUsuario");
            lista.add(transacao);
        }
        rs.close();
        return lista;
    }

    private String dataSQLtoPadrao(String data) {
        // yyyy-mm-dd
        String nova = null, dia, mes, ano;

        ano = data.substring(0, 4);
        mes = data.substring(5, 7);
        dia = data.substring(8, 10);

        nova = nova.format("%s/%s/%s", dia, mes, ano);
        return nova;
    }

    private String dataPadraotoSQL(String data) {
        // dd/mm/yyyy
        String nova = null, dia, mes, ano;
        dia = data.substring(0, 2);
        mes = data.substring(3, 5);
        ano = data.substring(6, 10);

        nova = nova.format("%s-%s-%s", ano, mes, dia);
        return nova;
    }

    public Vector listarTodos(String usuario) throws Exception {
        Vector lista = new Vector();
        ResultSet rs;
        rs = super.stm.executeQuery("SELECT * FROM transacoes WHERE emailUsuario = '" + usuario + "' ORDER BY categoria");

        while (rs.next()) {
            Transacao transacao = new Transacao();
            transacao.categoria = rs.getString("categoria");
            transacao.id = Integer.parseInt(rs.getString("id"));
            
            transacao.dataInsercao = rs.getString("dataInsercao");
            transacao.dataInsercao = dataSQLtoPadrao(transacao.dataInsercao);
            
            transacao.tipoRecorrencia = rs.getString("tipoRecorrencia");
            transacao.tipoTransacao = rs.getString("tipoTransacao");
            transacao.valor = Double.parseDouble(rs.getString("valor"));
            transacao.descricao = rs.getString("descricao");
            transacao.emailUsuario = rs.getString("emailUsuario");
            lista.add(transacao);
        }
        rs.close();
        return lista;
    }
    
    public Vector listarPorFiltro(String filtro) throws Exception {
        Vector lista = new Vector();
        ResultSet rs;
        rs = super.stm.executeQuery("SELECT * FROM transacoes WHERE " + filtro + " ORDER BY dataInsercao");

        while (rs.next()) {
            Transacao transacao = new Transacao();
            transacao.categoria = rs.getString("categoria");
            transacao.id = Integer.parseInt(rs.getString("id"));
            
            transacao.dataInsercao = rs.getString("dataInsercao");
            transacao.dataInsercao = dataSQLtoPadrao(transacao.dataInsercao);
            
            transacao.tipoRecorrencia = rs.getString("tipoRecorrencia");
            transacao.tipoTransacao = rs.getString("tipoTransacao");
            transacao.valor = Double.parseDouble(rs.getString("valor"));
            transacao.descricao = rs.getString("descricao");
            transacao.emailUsuario = rs.getString("emailUsuario");
            lista.add(transacao);
        }
        rs.close();
        return lista;
    }
}
