package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.util.HibernateUtil;

/**
 * Classe responsável por armazenar os métodos para acesso ao banco de dados
 * @author Victor Baptista
 * @since 03/05/2021
 * @version 1.0
 */
public class FormaPagamentoDAO extends GenericDAO{
    
    /*
    * Método para consultar os alunos na tabela
    */
    public ArrayList<FormaPagamento> buscarPorNome(String nome) throws Exception{
        //lista auxiliar para retornar no m�todo
        ArrayList<FormaPagamento> retorno = new ArrayList<FormaPagamento>();
        
        //classe auxiliar para armazenar a sess�o com o banco de dados
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        
        //classe auxiliar para consultar o banco de dados
        Criteria criteria = sessao.createCriteria(FormaPagamento.class);
        
        //adicionar crit�rio de pesquisa
        criteria.add(Restrictions.like("nome", nome + "%"));
        
        //adicionando a ordena��o da pesquisa
        criteria.addOrder(Order.asc("nome"));
        
        //valorizando o objeto de retorno do m�todo com os registros da tabela
        retorno = (ArrayList<FormaPagamento>) criteria.list();
        
        //encerrando a conex�o com o banco de dados
        sessao.close();
        
        //retornando a lista preenchida
        return retorno;
    }//fim do método buscarTodos
    
    /*
     * Método para consultar os alunos na tabela
     */
    @SuppressWarnings("unchecked")
    public ArrayList<FormaPagamento> buscarTodos() throws Exception {
        ArrayList<FormaPagamento> listaRetorno = new ArrayList<FormaPagamento>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(FormaPagamento.class);
        criteria.addOrder(Order.asc("idFormaPagamento"));
        listaRetorno = (ArrayList<FormaPagamento>) criteria.list();
        sessao.close();
        return listaRetorno;
    }
    
}//fim da classe
