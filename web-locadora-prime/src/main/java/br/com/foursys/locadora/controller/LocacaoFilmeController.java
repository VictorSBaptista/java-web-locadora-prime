package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.dao.LocacaoFilmeDAO;

/**
 * Classe respons�vel por acessar o objeto DAO e efetuar altera��es e consulta
 * na base de dados
 * 
 * @author Victor Baptista
 * @since 05/05/2021
 * @version 1.0
 */
public class LocacaoFilmeController {

	public void salvar(LocacaoFilme locacaoFilme) {
		try {
			new LocacaoFilmeDAO().salvar(locacaoFilme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(LocacaoFilme locacaoFilme) {
		try {
			new LocacaoFilmeDAO().excluir(locacaoFilme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * M�todo para consultar os locações por nome
	 */
	public ArrayList<LocacaoFilme> buscarPorNome(String nome) {
		
		ArrayList<LocacaoFilme> retorno = new ArrayList<LocacaoFilme>();
		
		try {
			retorno = new LocacaoFilmeDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	/*
	 * M�todo para consultar os locações por locacao
	 */
	public ArrayList<LocacaoFilme> buscarPorLocacao(Locacao locacao){
		try {
			return new LocacaoFilmeDAO().buscarPorLocacao(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * M�todo para consultar os locações por locacao
	 */
	public ArrayList<LocacaoFilme> buscarTodos(){
		try {
			return new LocacaoFilmeDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
