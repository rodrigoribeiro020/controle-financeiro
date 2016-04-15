package model.dados;

import java.sql.SQLException;

public class Db {
	
	public TB_Usuarios usuarios;
	public TB_Transacoes transacoes;
	public TB_CategoriaTransacoes categoriaTransacoes;
	
	public Db() throws SQLException{
		this.usuarios = new  TB_Usuarios();
		this.transacoes = new TB_Transacoes();
		this.categoriaTransacoes = new TB_CategoriaTransacoes();
	}
	
	public void resetDb() throws Exception{
		usuarios.init();
		transacoes.init();
		categoriaTransacoes.init();
	}

}
