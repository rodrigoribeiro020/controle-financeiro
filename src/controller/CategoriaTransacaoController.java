package controller;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import model.CategoriaTransacao;
import model.Transacao;
import model.Usuario;
import model.dados.Db;

/**
 * Classe  Controla Categoria
 */
public class CategoriaTransacaoController {
	
	Db db;
	
	/**
	 * Cria um espa�o pata Categoria no banco de dados
	 * @throws SQLException caso falhe a cria espaco no banco de dados
	 */
	public CategoriaTransacaoController() throws SQLException{
		db = new Db();
	}
	/**
	 * Adiciona uma categoria ao banco de dados
	 * @param categoria categoria de transacoes
	 * @throws Exception caso nao consiga adicionar
	 */
	public void adicionar(CategoriaTransacao categoria) throws Exception{
		
		testaCategoria(categoria, categoria.emailUsuario);
		db.categoriaTransacoes.adicionar(categoria);		
	}
	/**
	 * Remove uma categoria do banco de dados
	 * @param id id da categoria
	 * @param usuarioLogado email do usuario
	 * @throws Exception caso nao consiga remover
	 */
	public void remover(int id,String usuarioLogado) throws Exception{
		buscaCategoria(id,usuarioLogado);
		db.categoriaTransacoes.remover(id);
	}
	 /**
     * Busca o nome da categoria 
     * @param nome nome da categoria
     * @param usuarioLogado  email do Usuario logado
     * @return int  o inteiro da cor 
     * @throws Exception caso a categoria nao esteja  cadastrada
     * 
     */
	public CategoriaTransacao buscaCategoriaPorNome(String nome, String usuarioLogado) throws Exception{
        List listaCategoria = toList(usuarioLogado);
        Iterator it = listaCategoria.iterator();
        CategoriaTransacao cat = null;
        while(it.hasNext()){
            cat = (CategoriaTransacao) it.next();
            if (cat.nome.equals(nome))
                return cat;
        }            
        return cat;
    }

    /**
     * Busca uma categoria
     * @param id id da categoria
     * @param usuarioLogado email do usuario logado
     * @return categoria tipo de categoria
     * @throws Exception caso a categora nao esteja cadastrada
     */
    public CategoriaTransacao buscaCategoria(int id, String usuarioLogado) throws Exception{
    	List listaCategoria = toList(usuarioLogado);
        Iterator it = listaCategoria.iterator();
        CategoriaTransacao cat;
        while(it.hasNext()){
        	cat = (CategoriaTransacao) it.next();
        	if (cat.id == id)
        		return cat;
        	}
        throw new Exception("categoria nao cadastrada");
    }
    /**
     * Retorna uma lista de categorias
     * @param usuarioLogado  email do usuario logado
     * @return List uma lista de categorias
     * @throws Exception caso falhe
     */
	public List toList(String usuarioLogado) throws Exception{
		return db.categoriaTransacoes.listarTodos(usuarioLogado);
	}
	/**
	 * Atualiza uma categoria resistrada
	 * @param categoria um tipo de categoria
	 * @param usuarioLogado email do usuario logado
	 * @throws Exception caso a categoria nao esteja resistrada
	 */
    public void atualizar(CategoriaTransacao categoria,String usuarioLogado) throws Exception{
    	
    	String categoriaAnterior = buscaCategoria(categoria.id, usuarioLogado).nome;
    	
    	if(categoria.nome.equals(categoriaAnterior)){
    	
    	}
    	else{
    		testaCategoria(categoria, usuarioLogado);	
    	}
    	
        db.categoriaTransacoes.atualizar(categoria);
    }
    /**
     * Testa se a categoria � valida
     * @param categoria uma categoria
     * @param usuarioLogado email do usuario logado 
     * @throws Exception casso a categoria nao seja valida
     */
    private void testaCategoria(CategoriaTransacao categoria, String usuarioLogado) throws Exception{
    	testaNome(categoria.nome);
		testaOrcamento(categoria.orcamento);
		
		List listaCategoria = toList(usuarioLogado);
		Iterator it = listaCategoria.iterator();
		CategoriaTransacao cat;
		while(it.hasNext()){
			cat = (CategoriaTransacao) it.next();
    	if (cat.nome.equals(categoria.nome)){
    		throw new Exception("Categoria ja registrada");
    		}
		}
    	
    }
    /**
     * testa se o nome da categoria � valida
     * @param nome  nome da categoria
     * @throws Exception caso no nome da categoria nao seja valida
     */
    private boolean testaNome(String nome) throws Exception{
    	if(nome == null || nome.equals("")){
    		throw new Exception("Nome invalido.");
    	}
    	return true;
    }
    /**
     * Testa se o orcamento � validp
     * @param orcamento or�amento da categoria
     * @throws Exception caso o orcamento nao seja valido
     */
    private void testaOrcamento(double orcamento ) throws Exception{
        if(orcamento < 0){
        	throw new Exception("Orcamento invalido");        
        }
    }
    
}