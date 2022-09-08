package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.dao.LocacaoDAO;

/**
 * Classe respons�vel por acessar o objeto DAO e efetuar altera��es e consulta
 * na base de dados
 * 
 * @author Victor Baptista
 * @since 05/05/2021
 * @version 1.0
 */
public class LocacaoController {

	public void salvar(Locacao locacao) {
		try {
			new LocacaoDAO().salvar(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Locacao locacao) {
		try {
			new LocacaoDAO().excluir(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * M�todo para consultar os locações por nome
	 */
	public ArrayList<Locacao> buscarPorNome(String nome) {
		
		ArrayList<Locacao> retorno = new ArrayList<Locacao>();
		
		try {
			retorno = new LocacaoDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	/*
	 * M�todo para consultar os locações por nome
	 */
	public ArrayList<Locacao> buscarTodos() {
		
		ArrayList<Locacao> retorno = new ArrayList<Locacao>();
		
		try {
			retorno = new LocacaoDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
}
