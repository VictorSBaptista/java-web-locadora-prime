/**
 * 
 */
package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.dao.FormaPagamentoDAO;

/**
 * Classe respons�vel por acessar o objeto DAO e efetuar altera��es e consulta
 * na base de dados
 * 
 * @author Victor Baptista
 * @since 03/05/2021
 * @version 1.0
 */
public class FormaPagamentoController {

	public void salvar(FormaPagamento formaPagamento) {
		try {
			new FormaPagamentoDAO().salvar(formaPagamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(FormaPagamento formaPagamento) {
		try {
			new FormaPagamentoDAO().excluir(formaPagamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * M�todo para consultar os formaPagamentos por nome
	 */
	public ArrayList<FormaPagamento> buscarPorNome(String nome) {
		
		ArrayList<FormaPagamento> retorno = new ArrayList<FormaPagamento>();
		
		try {
			retorno = new FormaPagamentoDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	/*
	 * M�todo para consultar os formaPagamentos por nome
	 */
	public ArrayList<FormaPagamento> buscarTodos() {
		
		ArrayList<FormaPagamento> retorno = new ArrayList<FormaPagamento>();
		
		try {
			retorno = new FormaPagamentoDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
}
