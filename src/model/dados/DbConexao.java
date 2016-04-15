package model.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConexao {
	
	private final String PATH_BD = "db";
	protected Connection conn;
	protected Statement stm;
	
	DbConexao() throws SQLException{		
		 this.conn= DriverManager.getConnection("jdbc:sqlite:" + PATH_BD);
	     this.stm= this.conn.createStatement();
		
	}
}
