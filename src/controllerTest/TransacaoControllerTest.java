package controllerTest;

import java.util.List;

import junit.framework.Assert;
import model.Transacao;

import org.junit.Test;

import controller.TransacaoController;
/**
 * Classe de testes de TransacaoController
 */
public class TransacaoControllerTest {
	/**
	 * testa adicionar uma Transacao valida
	 */
	@Test
	public void testaAdicionar(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			novaTransacao.dataInsercao ="10/05/2020";
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 100.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
	
			Assert.assertEquals("Data de insercao invalida", "2020-05-10",novaTransacao.dataInsercao);
			Assert.assertEquals("Descreva a transação","dinheiro para pagar livros",novaTransacao.descricao );
			Assert.assertEquals("Escolha uma opção: 'Provento' ou 'Despesa'",
					"Despesa", novaTransacao.tipoTransacao);
			Assert.assertEquals("Valor invalido", 100.00, novaTransacao.valor);
			Assert.assertEquals("Escolha uma recorrencia: 'Nenhuma', 'Mensal' ou 'Anual'","Mensal",novaTransacao.tipoRecorrencia);
			
			List listaTransacoes =  novoTransacaoController.toList("metal@gmail.com");
			novaTransacao = (Transacao) listaTransacoes.get(listaTransacoes.size()-1);
			novoTransacaoController.remover(novaTransacao.id ,"metal@gmail.com" );;
			
	
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * adicionar uma transacao com dataInsercao nula
	 */
	@Test
	public void testaAdicionar2(){
		
		try{
			TransacaoController novoTransacaoController;
		    novoTransacaoController = new TransacaoController();
		    Transacao novaTransacao = new Transacao();
		
		    novaTransacao.descricao = "dinheiro para pagar livros";
		    novaTransacao.tipoTransacao = "Despesa";
		    novaTransacao.tipoRecorrencia = "Mensal";
		    novaTransacao.valor = 0.0;
		    novaTransacao.emailUsuario = "metal@gmail.com";
		    novoTransacaoController.adicionar(novaTransacao);
		
		}catch(Exception e){
		Assert.assertEquals("Data de insercao invalida",e.getMessage());
		
		}
	}
	/**
	 * adicionar uma transacao com valor nulo
	 */
	@Test
	public void testaAdicionar3(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="10/05/2020";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.tipoTransacao = "Despesa";
			novaTransacao.emailUsuario = "metal@gmail.com";
		
			novoTransacaoController.adicionar(novaTransacao);
			
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Valor invalido", e.getMessage());
		}
	}
	/**
	 * testa adicionar uma transacao com a descricao nula
	 */
	@Test
	public void testaAdicionar4(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			
			novaTransacao.tipoRecorrencia = "mensal";
			novaTransacao.dataInsercao ="10/05/2020";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 100.0;
			novaTransacao.emailUsuario = "metal@gmail.com";
		
			novoTransacaoController.adicionar(novaTransacao);
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Descreva a transação", e.getMessage());
		}
	}
	/**
	 * testa adicionar uma transacao com o tipoTransacao nulo
	 */
	@Test
	public void testaAdicionar5(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="10/05/2020";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.valor = 100.0;
			novaTransacao.emailUsuario = "metal@gmail.com";
		
			novoTransacaoController.adicionar(novaTransacao);
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Escolha uma opção: 'Provento' ou 'Despesa'", e.getMessage());
		}
	}
	/**
	 * testa adicionar uma transacao com o tipoTransacao invalida
	 */
	@Test
	public void testaAdicionar6(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			novaTransacao.tipoTransacao = "dividas";
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="10/05/2020";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.valor = 100.0;
			novaTransacao.emailUsuario = "metal@gmail.com";
		
			novoTransacaoController.adicionar(novaTransacao);
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Escolha uma opção: 'Provento' ou 'Despesa'", e.getMessage());
		}
	}
	/**
	 * Testa adicionar com um tipo de recorencia nulo
	 */
	@Test
	public void testaAdicionar7(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			
			novaTransacao.dataInsercao ="10/05/2020";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 100.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
		}catch(Exception e){
			Assert.assertEquals("Escolha uma recorrencia: 'Nenhuma', 'Mensal' ou 'Anual'", e.getMessage());
			}
	}
	/**
	 * testa remover uma transacao valida
	 */
	@Test
	public void testaRemover(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="10/05/2020";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 100.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			
			List listaTransacoes =  novoTransacaoController.toList("metal@gmail.com");
			novaTransacao = (Transacao) listaTransacoes.get(listaTransacoes.size()-1);
			
			novoTransacaoController.remover(novaTransacao.id,"metal@gmail.com");
			Assert.assertTrue( novoTransacaoController.toList("metal@gmail.com").size() == listaTransacoes.size()-1);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * teste remover uma transacao que nao existe
	 */
	@Test
	public void testaRemover2(){
	
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="10/05/2020";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 100.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			
			novoTransacaoController.remover(-1,"metal@gmail.com");
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Transacao não cadastrada",e.getMessage());
		}
	}
	/**
	 * Testa  ListTransacaoPorData
	 */
	@Test
	public void testaListTransacaoPorData(){
		try {
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="06/01/2015";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 100.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="08/03/2014";
			novaTransacao.descricao = "dinheiro para comida";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 100.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="07/02/2014";
			novaTransacao.descricao = "dinheiro para pagar passagem";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 100.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			
			List listaTransacoesPorData =  novoTransacaoController.listTransacaoPorData("01/01/2014","09/03/2014","metal@gmail.com");
			Assert.assertEquals(2, listaTransacoesPorData.size());
			
			novaTransacao =(Transacao) listaTransacoesPorData.get(listaTransacoesPorData.size()-1);
			Assert.assertEquals("07/02/2014",novaTransacao.dataInsercao);
			
			novaTransacao =(Transacao) listaTransacoesPorData.get(listaTransacoesPorData.size()-2);
			Assert.assertEquals("08/03/2014",novaTransacao.dataInsercao);
			
			// revome as transacoes
			novaTransacao =(Transacao) listaTransacoesPorData.get(listaTransacoesPorData.size()-1);
			novoTransacaoController.remover(novaTransacao.id,"metal@gmail.com");
			novoTransacaoController.remover(novaTransacao.id-1,"metal@gmail.com");
			novoTransacaoController.remover(novaTransacao.id-2,"metal@gmail.com");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	/**
	 * testa  o metodo toList
	 */
	@Test
	public void testaToList(){
		try{
			
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="10/05/2020";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 100.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			List listAntes =  novoTransacaoController.toList("metal@gmail.com");
			
			novoTransacaoController.adicionar(novaTransacao);
			novoTransacaoController.adicionar(novaTransacao);
			novoTransacaoController.adicionar(novaTransacao);
			List listaTransacoes =  novoTransacaoController.toList("metal@gmail.com");
			
			
			Assert.assertTrue(listaTransacoes.size() == listAntes.size()+3);
			
			novaTransacao = (Transacao) listaTransacoes.get(listaTransacoes.size()-1);
			novoTransacaoController.remover(novaTransacao.id,"metal@gmail.com");
			novoTransacaoController.remover(novaTransacao.id-1,"metal@gmail.com");
			novoTransacaoController.remover(novaTransacao.id-2,"metal@gmail.com");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			
			 
		}
	}
	/**
	 * testa busca uma transacao valida
	 */
	@Test
	public void testaBuscaTransacoes(){
		try{
			TransacaoController novoTransacaoController;
		   novoTransacaoController = new TransacaoController();
		   Transacao novaTransacao = new Transacao();
		   
		   novaTransacao.tipoRecorrencia = "Mensal";
		   novaTransacao.dataInsercao ="10/05/2020";
		   novaTransacao.descricao = "dinheiro para pagar livros";
		   novaTransacao.tipoTransacao ="Despesa";
		   novaTransacao.valor = 100.00;
		   novaTransacao.emailUsuario = "metal@gmail.com";
		   
		   novoTransacaoController.adicionar(novaTransacao);
		   List listaTransacoes = (List) novoTransacaoController.toList("metal@gmail.com");
		   novaTransacao = (Transacao) listaTransacoes.get(listaTransacoes.size()-1);
		   
		   Assert.assertEquals("Transacao não cadastrada",novaTransacao.id , novoTransacaoController.buscarTransacao(novaTransacao.id,"metal@gmail.com").id);
		   
		   novoTransacaoController.remover(novaTransacao.id,"metal@gmail.com");
		}catch(Exception e){
			System.out.println(e.getMessage());
			}
		}
	
	/**
	 * testa busca uma transacao que nao existe
	 */
	@Test
	public void testaBuscaTransacoes2(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			
			novoTransacaoController.buscarTransacao(-1,"metal@gmail.com");
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Transacao não cadastrada", e.getMessage());
			}
		}
	/**
	 * testa atualizarTransacao
	 */
	@Test
	public void testaAtualizaTransacao(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			
			novaTransacao.tipoTransacao = "Despesa";
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="10/05/2020";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.valor = 100.0;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			List listaTransacoes =  novoTransacaoController.toList("metal@gmail.com");
			novaTransacao = (Transacao) listaTransacoes.get(listaTransacoes.size()-1);
			
			int id = novaTransacao.id;
			novaTransacao.tipoTransacao = "Provento";
			novaTransacao.tipoRecorrencia = "Nenhuma";;
			novaTransacao.dataInsercao ="11/05/2020";
			novaTransacao.descricao = "dinheiro para passagem de onibus";
			novaTransacao.valor = 30.00;
			novaTransacao.id = id;
			 
			novoTransacaoController.atualizaTransacao(novaTransacao);
			
			List listaTransacoes2 =  novoTransacaoController.toList("metal@gmail.com");
			novaTransacao = (Transacao) listaTransacoes.get(listaTransacoes.size()-1);
			
			Assert.assertTrue(novaTransacao.tipoTransacao.equals("Provento"));
			Assert.assertTrue(novaTransacao.tipoRecorrencia.equals("Nenhuma"));
			Assert.assertTrue(novaTransacao.dataInsercao.equals("2020-05-11"));
			Assert.assertTrue(novaTransacao.descricao.equals("dinheiro para passagem de onibus"));
			Assert.assertTrue(novaTransacao.valor == 30.00);
			
			novoTransacaoController.remover(id,"metal@gmail.com");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			}
	}
	/**
	 *Testa calcular Saldo com tipo de recorencia Mensal
	 */
	@Test
	public void calculaSaldoMensal(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="07/07/2014";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 50.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="07/07/2014";
			novaTransacao.descricao = "viagens";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 50.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			
			novaTransacao.tipoRecorrencia = "Mensal";
			novaTransacao.dataInsercao ="07/07/2014";
			novaTransacao.descricao = "salario";
			novaTransacao.tipoTransacao ="Provento";
			novaTransacao.valor = 200.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			
			List listaTransacoes = novoTransacaoController.toList("metal@gmail.com");
			
			double saldo = novoTransacaoController.calculaSaldo(novoTransacaoController.listTransacaoPorData(
					"07/07/2014","07/08/2014",novaTransacao.emailUsuario)
					,"07/07/2014","07/08/2014");
			
			//Assert.assertEquals(saldo, 200.0);
			
			novaTransacao = (Transacao) listaTransacoes.get(listaTransacoes.size()-1);
			novoTransacaoController.remover(novaTransacao.id,"metal@gmail.com");
			novoTransacaoController.remover(novaTransacao.id-1,"metal@gmail.com");
			novoTransacaoController.remover(novaTransacao.id-2,"metal@gmail.com");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * Verifica calcular sauldo com tipo de recorrencia Anual e Nenhuma
	 */
	@Test
	public void calculaSaldoAnualTest(){
		try{
			TransacaoController novoTransacaoController;
			novoTransacaoController = new TransacaoController();
			Transacao novaTransacao = new Transacao();
			
			novaTransacao.tipoRecorrencia = "Anual";
			novaTransacao.dataInsercao ="07/07/2014";
			novaTransacao.descricao = "dinheiro para pagar livros";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 50.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			
			novaTransacao.tipoRecorrencia = "Anual";
			novaTransacao.dataInsercao ="07/07/2014";
			novaTransacao.descricao = "viagens";
			novaTransacao.tipoTransacao ="Despesa";
			novaTransacao.valor = 50.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			
			novaTransacao.tipoRecorrencia = "Nenhuma";
			novaTransacao.dataInsercao ="07/07/2014";
			novaTransacao.descricao = "Jogo";
			novaTransacao.tipoTransacao ="Provento";
			novaTransacao.valor = 400.00;
			novaTransacao.emailUsuario = "metal@gmail.com";
			novoTransacaoController.adicionar(novaTransacao);
			List listaTransacoes = novoTransacaoController.toList("metal@gmail.com");
			
			double saldo = novoTransacaoController.calculaSaldo(listaTransacoes
					,"07/07/2014","07/07/2014");
			//Assert.assertEquals(saldo, 200.0);
			System.out.println(saldo);
			
			novaTransacao = (Transacao) listaTransacoes.get(listaTransacoes.size()-1);
			novoTransacaoController.remover(novaTransacao.id,"metal@gmail.com");
			novoTransacaoController.remover(novaTransacao.id-1,"metal@gmail.com");
			novoTransacaoController.remover(novaTransacao.id-2,"metal@gmail.com");

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

}

