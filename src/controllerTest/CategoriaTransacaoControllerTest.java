package controllerTest;



import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;
import model.CategoriaTransacao;

import org.junit.Test;

import controller.CategoriaTransacaoController;
import view.usuario.MeuEscritorio;

/**
 * Classe de Teste CategoriaTransacaoController
 *
 */
public class CategoriaTransacaoControllerTest {
	/**
	 * Testa adicionar Categoria valido
	 */
	@Test
	public void AdicionarTest(){
		try{
			CategoriaTransacao novaCategoria = new CategoriaTransacao();
			CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
			novaCategoria.nome = "feijoada";
			novaCategoria.orcamento = 100.0;
			novaCategoria.cor = 20;
			novaCategoria.emailUsuario ="macaco@gb.com";
			novaCategoriaController.adicionar(novaCategoria);
			
			Assert.assertEquals("Nome invalido.", "feijoada", novaCategoria.nome);
			Assert.assertEquals("Orcamento invalido", 100.0 , novaCategoria.orcamento);
	
			List listaDeCategorias = novaCategoriaController.toList("macaco@gb.com");
			novaCategoria = (CategoriaTransacao) listaDeCategorias.get(listaDeCategorias.size()-1);
			novaCategoriaController.remover(novaCategoria.id,"macaco@gb.com");
		
	}catch(Exception e){
		System.err.println(e.getMessage());
		}
	}
	/**
	 * Testa adicionar uma Categoria com nome invalido (nome nulo)
	 */
	@Test
	public void AdicionarSemNome(){
		try{
			CategoriaTransacao novaCategoria = new CategoriaTransacao();
			CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
			
			novaCategoria.orcamento = 100.0;
			novaCategoria.cor = 20;
			novaCategoria.emailUsuario ="macaco@gb.com";
			
			novaCategoriaController.adicionar(novaCategoria);
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Nome invalido.", e.getMessage());
		}
	}
	
	/**
	 * Testa adicionar uma categoria sem Orcamento
	 */
	@Test
	public void AdicionarSemOrcamento(){
		try{
			CategoriaTransacao novaCategoria = new CategoriaTransacao();
			CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
			
			novaCategoria.nome = "feijoada";
			novaCategoria.cor = 20;
			novaCategoria.emailUsuario ="macaco@gb.com";
			
			novaCategoriaController.adicionar(novaCategoria);
			Assert.fail();
		}catch(Exception e){
		Assert.assertEquals("Orcamento invalido", e.getMessage());
		}
	}
	/**
	 * Testa adicionar Duas categorias iguais
	 * @throws Exception caso nao consiga cria as classes
	 */
	@Test
	public void AdicionarCategoriasIguais() throws Exception{
		
			CategoriaTransacao novaCategoria = new CategoriaTransacao();
			CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
			try{
				novaCategoria.nome = "feijoada";
				novaCategoria.cor = 20;
				novaCategoria.orcamento = 100.0;
				novaCategoria.emailUsuario ="macaco@gb.com";
			
				novaCategoriaController.adicionar(novaCategoria);
				novaCategoriaController.adicionar(novaCategoria);
				Assert.fail();
			}catch(Exception e){
				Assert.assertEquals("Categoria ja registrada", e.getMessage());
		}
			List listaDeCategorias = novaCategoriaController.toList("macaco@gb.com");
			novaCategoria = (CategoriaTransacao) listaDeCategorias.get(listaDeCategorias.size()-1);
			novaCategoriaController.remover(novaCategoria.id, "macaco@gb.com");
			
	}
	/**
	 * Testa remover uma Categoria cadastrada 
	 */
	@Test
	public void RemoverTest(){
		try{
			CategoriaTransacao novaCategoria = new CategoriaTransacao();
			CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
			novaCategoria.nome = "feijoada";
			novaCategoria.orcamento = 100.0;
			novaCategoria.cor = 20;
			novaCategoria.emailUsuario ="macaco@gb.com";
			
			novaCategoriaController.adicionar(novaCategoria);
			List listaDeCategorias = novaCategoriaController.toList("macaco@gb.com");
			novaCategoria = (CategoriaTransacao) listaDeCategorias.get(listaDeCategorias.size()-1);
			
			novaCategoriaController.remover(novaCategoria.id,"macaco@gb.com");
			Assert.assertTrue(novaCategoriaController.toList("macaco@gb.com").size() ==
					listaDeCategorias.size()-1);
	
		}catch(Exception e){
			System.err.println(e.getMessage());
			
		}
		
	}
	/**
	 * Testa Remover uma categoria nao cadastrada
	 */
	@Test
	public void RemoverInexistente(){
		try{
			CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
			novaCategoriaController.remover(-2,"macaco@gb.com");
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("categoria nao cadastrada", e.getMessage());
		}
		
	}
	/**
	 * Testa busca uma cor com nome de categoria valido
	 */
	@Test
	public void buscaCategoriaPorNome(){
		try{
			CategoriaTransacao novaCategoria = new CategoriaTransacao();
			CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
			
			novaCategoria.nome = "feijoada";
			novaCategoria.orcamento = 100.0;
			novaCategoria.cor = 20;
			novaCategoria.emailUsuario ="macaco@gb.com";
			novaCategoriaController.adicionar(novaCategoria);
			
			
			Assert.assertTrue(
					novaCategoriaController.buscaCategoriaPorNome(novaCategoria.nome,"macaco@gb.com").nome.equals("feijoada"));
			
			List listaDeCategorias = novaCategoriaController.toList("macaco@gb.com");
			novaCategoria = (CategoriaTransacao) listaDeCategorias.get(listaDeCategorias.size()-1);
			novaCategoriaController.remover(novaCategoria.id,"macaco@gb.com");
			
		}catch(Exception e){
			System.err.println("BucaCategoriaPorNome/n"+ e.getMessage());
		}
		
	}
	/**
	 * testa Busca uma categoria cadastrada
	 */
	@Test
	public void buscaCategoria(){
		try{
			CategoriaTransacao novaCategoria = new CategoriaTransacao();
			CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
			novaCategoria.nome = "feijoada";
			novaCategoria.orcamento = 100.0;
			novaCategoria.cor = 20;
			novaCategoria.emailUsuario ="macaco@gb.com";
			
			novaCategoriaController.adicionar(novaCategoria);
			List listaDeCategorias = novaCategoriaController.toList("macaco@gb.com");
			novaCategoria = (CategoriaTransacao) listaDeCategorias.get(listaDeCategorias.size()-1);
			
			Assert.assertEquals("categoria nao cadastrada", "feijoada"
					,novaCategoriaController.buscaCategoria(novaCategoria.id, "macaco@gb.com").nome);
		
			novaCategoria = (CategoriaTransacao) listaDeCategorias.get(listaDeCategorias.size()-1);
			novaCategoriaController.remover(novaCategoria.id,"macaco@gb.com");
			
		}catch(Exception e){
			System.err.println(e.getMessage());
			
			}
		}
	/**
	 * Testa busca uma categoria nao cadastrada
	 */
	@Test
	public void buscaCategoriaInvalida(){
			try{
				
				CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
				novaCategoriaController.buscaCategoria(-1, "macaco@gb.com");
				Assert.fail();
			}catch(Exception e){
				Assert.assertEquals("categoria nao cadastrada", e.getMessage());
				
			}

	}
	/**
	 * Testa o metodo ToList
	 */
	@Test
	public void testaToList(){
		try {
			CategoriaTransacao novaCategoria = new CategoriaTransacao();
			CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
			List listAntes =  novaCategoriaController.toList("metal@gmail.com");
			novaCategoria.nome = "feijoada";
			novaCategoria.orcamento = 100.0;
			novaCategoria.cor = 20;
			novaCategoria.emailUsuario ="macaco@gb.com";
			novaCategoriaController.adicionar(novaCategoria);
			
			novaCategoria.nome = "carros";
			novaCategoria.orcamento = 500.0;
			novaCategoria.cor = 80;
			novaCategoria.emailUsuario ="macaco@gb.com";
			novaCategoriaController.adicionar(novaCategoria);
			List listaDeCategorias = novaCategoriaController.toList("macaco@gb.com");
			Assert.assertTrue( listaDeCategorias.size() == listAntes.size()+2);
			
			//remover categoria do banco de dados
			novaCategoria = (CategoriaTransacao) listaDeCategorias.get(listaDeCategorias.size()-1);
			novaCategoriaController.remover(novaCategoria.id,"macaco@gb.com");
			novaCategoriaController.remover(novaCategoria.id-1,"macaco@gb.com");
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			}
		}
	/**
	 * Testa o atualizar uma categoria
	 */
	@Test
	public void testaAtualizar(){
		try{
			CategoriaTransacao novaCategoria = new CategoriaTransacao();
			CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
				
			novaCategoria.nome = "feijoada";
			novaCategoria.orcamento = 100.0;
			novaCategoria.cor = 20;
			novaCategoria.emailUsuario ="macaco@gb.com";
			novaCategoriaController.adicionar(novaCategoria);
				
			List listaDeCategorias = novaCategoriaController.toList("macaco@gb.com");
				
			novaCategoria = (CategoriaTransacao) listaDeCategorias.get(listaDeCategorias.size()-1);
			int id = novaCategoria.id;
				
			novaCategoria.nome = "carros";
			novaCategoria.orcamento = 500.0;
			novaCategoria.cor = 80;
			novaCategoria.emailUsuario ="macaco@gb.com";
			novaCategoria.id = id;
			novaCategoriaController.atualizar(novaCategoria, novaCategoria.emailUsuario);
				
			List listaDeCategorias2 = novaCategoriaController.toList("macaco@gb.com");
			novaCategoria = (CategoriaTransacao) listaDeCategorias2.get(listaDeCategorias.size()-1);

			Assert.assertTrue(novaCategoria.nome.equals("carros"));
			Assert.assertTrue(novaCategoria.orcamento == 500.0);
			Assert.assertTrue(novaCategoria.cor == 80);
					
			novaCategoriaController.remover(novaCategoria.id,"macaco@gb.com");	
			} catch (Exception e) {
				System.err.println(e.getMessage());
				}
	}
	/**
	 * Testa atualizar uma categoria que nao existe
	 * @throws SQLException caso nao consiga cria as classes
	 */
	@Test
	public void testaAtualizar2() throws SQLException{
		CategoriaTransacao novaCategoria = new CategoriaTransacao();
		CategoriaTransacaoController novaCategoriaController = new CategoriaTransacaoController();
		novaCategoria.nome = "feijoada";
		novaCategoria.orcamento = 100.0;
		novaCategoria.cor = 20;
		novaCategoria.emailUsuario ="macaco@gb.com";
		try {
			novaCategoriaController.atualizar(novaCategoria,novaCategoria.emailUsuario);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(("categoria nao cadastrada"), e.getMessage());
			}
	}
}
