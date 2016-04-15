package controllerTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import model.Usuario;
import model.dados.Db;

import org.junit.Assert;
import org.junit.Test;

import controller.UsuarioController;
/**
 * Classe testa a Classe de UsuarioControll
 *
 */
public class UsuarioControllerTest {
	/**
	 * testa Adicionar o usuario valido;
	 */
	@Test
	public void adicionarUsuarioTest(){
		try{
			Usuario novoUsuario = new Usuario();
	        UsuarioController newUsuarioController = new UsuarioController();
	        novoUsuario.nome = "Metal";
	        novoUsuario.senha = "anubis";
	        novoUsuario.dicaSenha = "cachoro";
	        novoUsuario.email = "metal@gmail.com";
	      
	    
	        newUsuarioController.adicionarUsuario(novoUsuario);
	        Assert.assertEquals("Email invalido.","metal@gmail.com",novoUsuario.email);
	        Assert.assertEquals("Nome invalido.", "Metal",novoUsuario.nome);
	        Assert.assertEquals("Digite uma senha valida.","anubis",novoUsuario.senha);
	        Assert.assertEquals("A senha deve ter entre 6 e 8 caracteres.","anubis",novoUsuario.senha);
	        Assert.assertEquals("Dica de senha invalida", "cachoro" , novoUsuario.dicaSenha);
	        
	        
	        newUsuarioController.removerUsuario(novoUsuario);
	       }catch(Exception e){
	    	   System.out.println(e.getMessage());
	       }
	}
	/**
	 * Tenta criar um usu�rio com nome invalido (vazio ou nulo)
	 */
	@Test
	public void adicionarUsuarioTeste2(){
		try{
			Usuario novoUsuario = new Usuario();
			UsuarioController newUsuarioController = new UsuarioController();
			
			novoUsuario.senha = "anubis";
			novoUsuario.dicaSenha = "cachoro";
			novoUsuario.email = "metal@gmail.com";
        
        	newUsuarioController.adicionarUsuario(novoUsuario);
        	Assert.fail();
        }catch(Exception e){
        	Assert.assertEquals("Nome invalido.", e.getMessage());
        	
        }
		
	}
	/**
	 * Tenta criar um usu�rio com um e-mail inv�lido
	 */
	@Test
	public void adicionarUsuarioTeste3(){
		try{
			Usuario novoUsuario = new Usuario();
			UsuarioController newUsuarioController = new UsuarioController();
			novoUsuario.nome = "Metal";
			novoUsuario.senha = "anubis";
			novoUsuario.dicaSenha = "cachoro";
			novoUsuario.email = "xxx.gmail.com";
        
			newUsuarioController.adicionarUsuario(novoUsuario);
			Assert.fail();
        }catch(Exception e){
        	Assert.assertEquals("Email invalido.", e.getMessage());
        	}
        }
	
	/**
	 * Testa digita uma senha nula
	 */
	@Test
	public void adicionarUsuarioTeste4(){
		try{
			Usuario novoUsuario = new Usuario();
			UsuarioController newUsuarioController = new UsuarioController();
			novoUsuario.nome = "Metal";
			novoUsuario.dicaSenha = "cachoro";
			novoUsuario.email = "metal@gmail.com";
        
        	newUsuarioController.adicionarUsuario(novoUsuario);
        	Assert.fail();
        }catch(Exception e){
        	Assert.assertEquals("Digite uma senha valida.", e.getMessage());
        	}
        }
	/**
	 * testa  uma senha que � maior que o limite permitido
	 */
	@Test
	public void adicionarUsuarioTeste5(){
		try{
			Usuario novoUsuario = new Usuario();
			UsuarioController newUsuarioController = new UsuarioController();
			novoUsuario.nome = "Metal";
			novoUsuario.senha = "anubesnaoemacaco";
			novoUsuario.dicaSenha = "cachoro";
			novoUsuario.email = "metal@gmail.com";
        	newUsuarioController.adicionarUsuario(novoUsuario);
        	Assert.fail();
        }catch(Exception e){
        	Assert.assertEquals("A senha deve ter entre 6 e 8 caracteres.", e.getMessage());
        	}
        }
	
	
	/**
	 * testa adicionar 2 usuarios iguais
	 * @throws Exception caso nao consiga cria as classes
	 */
	@Test public void AdicionarUsuariosIguais()throws Exception{
		Usuario novoUsuario = new Usuario();
			UsuarioController newUsuarioController = new UsuarioController();
			novoUsuario.nome = "Metal";
			novoUsuario.senha = "anubis";
		    novoUsuario.dicaSenha = "cachoro";
		    novoUsuario.email = "metal@gmail.com";
			
       
		try{
			newUsuarioController.adicionarUsuario(novoUsuario);
			newUsuarioController.adicionarUsuario(novoUsuario);
		
		Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Email de usuario já existente.", e.getMessage());
			newUsuarioController.removerUsuario(novoUsuario);
			}
		}
	/**
	 * testa removerUsuario removendo um usuario que nao existe;
	 */
	@Test public void removerUsuarioTest(){
		try{
			Usuario novoUsuario = new Usuario();
			UsuarioController newUsuarioController = new UsuarioController();
			novoUsuario.nome = "Metal";
			novoUsuario.senha = "anubis";
			novoUsuario.dicaSenha = "cachoro";
			novoUsuario.email = "metal@gmail.com";
			newUsuarioController.adicionarUsuario(novoUsuario);
       
        	newUsuarioController.removerUsuario(novoUsuario);
        	newUsuarioController.removerUsuario(novoUsuario);
        }catch(Exception e){
        	Assert.assertEquals("Usuario não registrado.", e.getMessage());
        	
        	}
        }
	/**
	 * Testa busca usuario pelo email
	 */
	@Test public void buscarUsuarioTest(){
		
		
		try {
			Usuario novoUsuario = new Usuario();
			UsuarioController newUsuarioController;
			newUsuarioController = new UsuarioController();
			
			novoUsuario.nome = "Metal";
			novoUsuario.senha = "anubis";
			novoUsuario.dicaSenha = "cachoro";
			novoUsuario.email = "metal@gmail.com";
			newUsuarioController.adicionarUsuario(novoUsuario);
			
			Assert.assertTrue(novoUsuario.email.equals(newUsuarioController.buscarUsuario("metal@gmail.com").email));
			
			newUsuarioController.removerUsuario(novoUsuario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
	}
	/**
	 * Testa  o toList
	 */
	@Test
	public void testaToList(){
		try{
			Usuario novoUsuario = new Usuario();
			UsuarioController newUsuarioController = new UsuarioController();
			novoUsuario.nome = "Metal";
			novoUsuario.senha = "anubis";
			novoUsuario.dicaSenha = "cachoro";
			novoUsuario.email = "metal@gmail.com";
			
			int numeroDeUsuario = newUsuarioController.toList().size();
			newUsuarioController.adicionarUsuario(novoUsuario);
			
			Assert.assertTrue(newUsuarioController.toList().size() == numeroDeUsuario + 1 );
			
			newUsuarioController.removerUsuario(novoUsuario);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Testa atualizaUsuario
	 */
	@Test
	public void atualizaUsuario(){
		try{
			Usuario novoUsuario = new Usuario();
			UsuarioController newUsuarioController = new UsuarioController();
			novoUsuario.nome = "Metal";
			novoUsuario.senha = "anubis";
			novoUsuario.dicaSenha = "cachoro";
			novoUsuario.email = "metal@gmail.com";
			newUsuarioController.adicionarUsuario(novoUsuario);
			
			Usuario novoUsuario2 = new Usuario();
			novoUsuario2.nome = "Macaco";
			novoUsuario2.senha = "123456";
			novoUsuario2.email = "metal@gmail.com";
			novoUsuario2.dicaSenha = "numeros";
			newUsuarioController.atualizaUsuario(novoUsuario2);
			
			List listaUsuario = newUsuarioController.toList();
	    	novoUsuario = (Usuario) listaUsuario.get(listaUsuario.size()-1);
	    	
	    	Assert.assertTrue(novoUsuario.nome.equals("Macaco"));
	    	Assert.assertTrue(novoUsuario.senha.equals("123456"));
	    	
			newUsuarioController.removerUsuario(novoUsuario);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
	/**
	 * testa o metodo logar da clase
	 */
	@Test
	public void LogarTest(){
		try{
			Usuario novoUsuario = new Usuario();
			UsuarioController newUsuarioController = new UsuarioController();
			novoUsuario.nome = "Metal";
			novoUsuario.senha = "anubis";
			novoUsuario.dicaSenha = "cachoro";
			novoUsuario.email = "metal@gmail.com";
			newUsuarioController.adicionarUsuario(novoUsuario);
	   
	    	Assert.assertTrue(newUsuarioController.logar("metal@gmail.com", "anubis"));
	    	
	    	newUsuarioController.removerUsuario(novoUsuario);
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    }
	   
	    
	}
	/**
	 * Testa logar com um e-mail nao registrado
	 * @throws Exception caso nao consiga cria as classes
	 */
	@Test
	public void LogarTest2()throws Exception{
		Usuario novoUsuario = new Usuario();
		UsuarioController newUsuarioController = new UsuarioController();
	    novoUsuario.nome = "Metal";
	    novoUsuario.senha = "anubis";
	    novoUsuario.dicaSenha = "cachoro";
	    novoUsuario.email = "metal@gmail.com";
	    newUsuarioController.adicionarUsuario(novoUsuario);
	    try{
	    	Assert.assertTrue(newUsuarioController.logar("macaco@gg.com", "anubis"));
	    	
	    }catch(Exception e){ 
	    	Assert.assertEquals("Email ou senha não registrado", e.getMessage());
	    	newUsuarioController.removerUsuario(novoUsuario);
	    }
	  
	}
	/**
	 * tenta loga com uma senha diferente da registrada 
	 * @throws Exception caso nao consiga cria as classes
	 */
	@Test
	public void LogarTest3()throws Exception{
		Usuario novoUsuario = new Usuario();
		UsuarioController newUsuarioController = new UsuarioController();
	    novoUsuario.nome = "Metal";
	    novoUsuario.senha = "anubis";
	    novoUsuario.dicaSenha = "cachoro";
	    novoUsuario.email = "metal@gmail.com";
	    newUsuarioController.adicionarUsuario(novoUsuario);
	    try{
	    	Assert.assertTrue(newUsuarioController.logar("macaco@gg.com", "123456"));
	    	
	    }catch(Exception e){ 
	    	Assert.assertEquals("Email ou senha não registrado", e.getMessage());
	    	newUsuarioController.removerUsuario(novoUsuario);
	    }
	}
}