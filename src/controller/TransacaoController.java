package controller;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import model.Transacao;
import model.dados.Db;
/**
 * classe que Controla Trasancoes
 */
public class TransacaoController {
	
	Db db;
   
	/**
	 * Cosntrutor da classe
	 * @throws SQLException caso aconteca algum erro no banco de dados
	 */
	public TransacaoController() throws SQLException{
		db = new Db();
	}
	/**
	 * adiciona uma Transacao ao banco de dados
	 * @param transacao transacao do usuario
	 * @throws Exception caso os testes falhem
	 */
	public void adicionar(Transacao transacao) throws Exception{
		testaTransacao(transacao);
		db.transacoes.adicionar(transacao);	
	}
	/**
	 * Remove uma transacao do banco de dados
	 * @param usuarioLogado  email do usuario logado
	 * @param id id da transacao
	 * @throws Exception  caso a transacao nao exista
	 */
	public void remover(int id, String usuarioLogado) throws Exception{
		buscarTransacao(id, usuarioLogado);
		db.transacoes.remover(id);
	}
	/**
	 * retorna uma lista pelo tipo de filtro
	 * @param filtro  filtro da lista
	 * @return lista çlista pelo filtro
	 * @throws Exception caso nao congiga gerar a lista
	 */
	 public List listComFiltro(String filtro) throws Exception {
	        return db.transacoes.listarPorFiltro(filtro);
	        
	    }
	 /**
	  * retorna uma lista de transacoes por data 
	  * @param dataInicial data de onde comeca inicial
	  * @param dataFinal data ate onde vai final
	  * @param usuarioLogado email do usuario logado
	  * @return lista lista de transacoes por data
	  * @throws Exception caso nao consiga gerar a lista
	  */
	 public List listTransacaoPorData(String dataInicial,String dataFinal, String usuarioLogado) throws Exception {
	        return db.transacoes.listarPorData(dataInicial, dataFinal, usuarioLogado);
	    }
	/**
	 * retorna uma lista de transacoes
	 * @param usuarioLogado  email do usuario logado
	 * @return List do tipo transacoes
	 * @throws Exception caso nao consiga criar a lista
	 */
	public List toList(String usuarioLogado) throws Exception{
		return db.transacoes.listarTodos(usuarioLogado);
		}
	/**
	 * Busca uma trasacao no banco de dados
	 * @param id id da transacao
	 * @param usuarioLogado  email do usuario logado
	 * @return Transacao retorna a transacao buscada;
	 * @throws Exception caso a nao for cadastrada
	 
	 */
	public Transacao buscarTransacao(int id, String usuarioLogado) throws Exception {
		Iterator it = db.transacoes.listarTodos(usuarioLogado).iterator();
        Transacao hs;
        while (it.hasNext()) {
            hs = (Transacao) it.next();
            if(hs.id == id){
            	return hs;
            }
        }        
        throw new Exception("Transacao não cadastrada");
     }
        /**
         * Atualixa uma Transacao
         * @param transacao uma transacao
         * @throws Exception caso a transacao nao exista
         */
        public void atualizaTransacao(Transacao transacao) throws Exception{
            testaTransacao(transacao);
            db.transacoes.atualizar(transacao);
        }
	/**
	 * testa se Transacao � valida
	 * @param transacao
	 * @throws Exception
	 */
	private void testaTransacao(Transacao transacao) throws Exception{
		testaTipoTransacao(transacao.tipoTransacao);
		testadata(transacao.dataInsercao);
		testaDescricao(transacao.descricao);
		testaValor(transacao.valor);
		testaRecorrencia(transacao.tipoRecorrencia);
	}
	/**
	 * testa se tipo de transacao for validajá
	 * @param tipoDeTransacao
	 * @throws Exception caso o tipo de transacao nao for  valida
	 */
	private void testaTipoTransacao(String tipoDeTransacao) throws Exception{
		if(tipoDeTransacao == null||!(tipoDeTransacao.equals("Despesa")|| tipoDeTransacao.equals("Provento"))){
			throw new Exception("Escolha uma opção: 'Provento' ou 'Despesa'");
		}

		
	}
    /**
     * testa se a data de insercao é valida
     * @param data string data
     * @throws Exception se a data nao for valida
     */
    private void testadata(String data) throws Exception{
        validaData(data);
	if(data == null || data.length() != "##/##/####".length()){
		throw new Exception("Data de insercao invalida");
	}
	}
    
    /**
     * testa se a data de insercao é valida
     * @param data string data
     * @throws Exception se a data nao for valida
     */
    private void validaData(String data) throws Exception{
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));        
        int ano = Integer.parseInt(data.substring(6, 10));        
        if (ano == 0 && dia == 0 && mes == 0)
            throw new Exception ("Data da criação da Terra não é válido!");
        if (dia < 1 || dia > 31)
            throw new Exception ("Dia inválido");
        if (mes < 1 || mes > 12)
            throw new Exception ("mes inválido");
        
    }
    /**
     * testa se a descrica eh valida
     * @param nome descri�ao
     * @throws Exception caso nao seja valida
     */
    private void testaDescricao(String nome) throws Exception{
		if(nome == null || nome.equals("")){
			throw new Exception("Descreva a transação");
		}
	}
    /**
     * Testa o tipo de recorencia
     * @param nome o tipo da recorencia
     * @throws Exception caso a reconrencia seja invalida
     */
    private void testaRecorrencia(String nome) throws Exception {
        if ((nome == null || nome.equals(""))||!(nome.equals("Nenhuma")|| nome.equals("Mensal")
        		|| nome.equals("Anual"))) {
            throw new Exception("Escolha uma recorrencia: 'Nenhuma', 'Mensal' ou 'Anual'");
        }
    }
    /**
     * Testa se o valor da transacao eh valido
     * @param valor valor da transacao
     * @throws Exception
     */
	private void testaValor(Object valor) throws Exception{
            if(!(valor instanceof Double)||(double)valor == 0)
                throw new Exception("Valor invalido");
   }
	/**
	 * Calcula o saldo Do Usuario pela data inicial ate onde o usuario deseja
	 * @param listaTransacao uma lista de transacoes
	 * @param dataInicial data de onde comeca a calcular o saldo
	 * @param dataFinal data de onde termina de calcular o saldo
	 * @return saldo saldo do Usuario
	 */
	public double calculaSaldo(List listaTransacao, String dataInicial, String dataFinal){
        Iterator it = listaTransacao.iterator();
        //dd/mm/yyyy
        int mesInicial = Integer.parseInt(dataInicial.substring(3,5));
        int mesFinal = Integer.parseInt(dataFinal.substring(3,5));
        int anoInicial = Integer.parseInt(dataFinal.substring(6,10));
        int anoFinal = Integer.parseInt(dataFinal.substring(6,10));
        
        int diferencaMes = (mesFinal - mesInicial) + 1;
        int diferencaAno = (anoFinal - anoInicial) + 1;
        double somador = 0;
        Transacao hs;
        while (it.hasNext()) {
            hs = (Transacao) it.next();
            if(hs.tipoTransacao.equals("Provento"))
                
                if(hs.tipoRecorrencia.equals("Mensal"))
                    somador += hs.valor * diferencaMes;
                else if (hs.tipoRecorrencia.equals("Anual"))
                    somador += hs.valor * diferencaAno;
                else
                    somador += hs.valor;
            else
                if(hs.tipoRecorrencia.equals("Mensal"))
                    somador -= hs.valor * diferencaMes;
                else if (hs.tipoRecorrencia.equals("Anual"))
                    somador -= hs.valor * diferencaAno;
                else
                    somador -= hs.valor;

        }
        
        
        return somador;
    }

}