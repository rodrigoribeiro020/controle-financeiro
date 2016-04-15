package controller;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.dados.*;
import model.*;
/**
 * Classe controla  fun�oes do Usuario
 */
public class UsuarioController {
	
	Db db;
	
	public UsuarioController() throws SQLException{
		db = new Db();
	}
	/**
	 * Classe adiciona um usuario ao banco de dados
	 * @param usuario usuario 
	 * @throws Exception caso o usuario seja invalido ou ja exista
	 */
	public void adicionarUsuario(Usuario usuario) throws Exception {
		validarEmail(usuario.email);
		verificaNome(usuario.nome);
		verificaSenha(usuario.senha);
		verificaDica(usuario.dicaSenha);
        Iterator it = db.usuarios.listarTodos().iterator();
        Usuario hs;
        while (it.hasNext()) {
            hs = (Usuario) it.next();
            if(hs.email.equals(usuario.email))
            	throw new Exception("Email de usuario já existente.");
            
        }              
        db.usuarios.adicionar(usuario);
    }
	/**
	 * verifica se o email � valido
	 * @param email
	 * @return true se for valido
	 * @throws Exception se nao for valido
	 */
    private boolean validarEmail(String email)throws Exception{
    	final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        if(!matcher.find())
            throw new Exception("Email invalido.");
        return matcher.find();
    }
    /**
     * testa se o nome do usuario � valodo
     * @param nome
     * @return true se o for valido
     * @throws Exception caso nao seja valido
     */
    private boolean verificaNome(String nome)throws Exception{
    	if(nome == null || nome.equals("")){
    		throw new Exception("Nome invalido.");
    	}
    	return true;
    }
    /**
     * verifica se a dica de senha � valida
     * @param dica dica de senha do Usuario
     * @return true se passa nos testes
     * @throws Exception caso nao passe nos testes
     */
    private boolean verificaDica(String dica)throws Exception{
    	if(dica == null || dica.equals("")){
    		throw new Exception("Dica de senha invalida");
    	}
    	return true;
    }
    /**
     * verifica se a senha � valida 
     * @param senha senha do usuario
     * @return true se a senha for valida
     * @throws Exception caso a senha nao seja valida
     */
    private boolean verificaSenha(String senha)throws Exception{
    	if(senha == null || senha == ""){
    		throw new Exception("Digite uma senha valida.");
    	}else if(senha.length() < 6 || senha.length() > 8){
    		throw new Exception("A senha deve ter entre 6 e 8 caracteres.");
    		}
    	return true;
    }
	/**
	 * Remove um usuario do banco de dados
	 * @param usuario usuario
	 * @throws Exception caso o usuario nao exista 
	 */
	public void removerUsuario(Usuario usuario) throws Exception {
        buscarUsuario(usuario.email);
        db.usuarios.remover(usuario.email);
    }
	/**
	 * Busca um usuario no banco de dados
	 * @param email do usuario
	 * @return Usuario o usuario buscado 
	 * @throws Exception caso o usuario nao esteja no banco de dados
	 */
	public Usuario buscarUsuario(String email) throws Exception {
        Iterator it = db.usuarios.listarTodos().iterator();
        Usuario hs;
        while (it.hasNext()) {
            hs = (Usuario) it.next();
            if(hs.email.equals(email))
            	return hs;
        }        
        throw new Exception("Usuario não registrado.");
    }
	/**
	 * atualiza um usuario do banco de dados
	 * @param usuario usuario
	 * @throws Exception caso o usuario nao exista
	 */
	public void atualizaUsuario(Usuario usuario) throws Exception{
         validarEmail(usuario.email);
	     verificaNome(usuario.nome);
         verificaSenha(usuario.senha);
         verificaDica(usuario.dicaSenha);
         
         db.usuarios.atualizar(usuario);            
        }
	/**
	 * gerencia outra conta de Usuario
	 * @param email  email do usuario logado
	 * @return lista de usuarios gerenciado pelo usuario 
	 * @throws Exception caso nao consiga gerar a lista
	 */
	 public List contasVinculadas(String email) throws Exception{
			return db.usuarios.listarContasVinculadas(email);
		}
	/**
	 * retorna uma lista dos usuarios no banco de dados
	 * @return lista lista de usuarios
	 * @throws Exception se nao puder lista todos os usuarios
	 */
	public List toList() throws Exception{
		return db.usuarios.listarTodos();
	}
	/**
	 * assessa uma conta no banco de dados
	 * @param email email do usuario 
	 * @param senha senha do usuario
	 * @return true se consegui logar 
	 * @throws Exception se nao consegui logar
	 */
	public boolean logar(String email, String senha) throws Exception{
		Iterator it = db.usuarios.listarTodos().iterator();
		Usuario hs;
		while (it.hasNext()){
			hs = (Usuario) it.next();
			if(hs.email.equals(email) && hs.senha.equals(senha))
				return true;
		}		
		   throw new Exception("Email ou senha não registrado");
	}
	
}