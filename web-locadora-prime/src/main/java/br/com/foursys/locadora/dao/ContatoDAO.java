package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.util.HibernateUtil;

/**
 * Classe responsável por armazenar os métodos para acesso ao banco de dados
 * @author Victor Baptista
 * @since 24/03/2021
 * @version 1.0
 */
public class ContatoDAO extends GenericDAO{
    
    /*
    * Método para consultar os contatos na tabela
    */
    public ArrayList<Contato> buscarTodos() throws Exception{
        //lista auxiliar para retornar no método
        ArrayList<Contato> retorno = new ArrayList<Contato>();
        
        //classe auxiliar para armazenar a sessão com o banco de dados
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        
        //classe auxiliar para consultar o banco de dados
        Criteria criteria = sessao.createCriteria(Contato.class);
        
        //adicionando a ordenação da pesquisa
        criteria.addOrder(Order.asc("idContato"));
        
        //valorizando o objeto de retorno do método com os registros da tabela
        retorno = (ArrayList<Contato>) criteria.list();
        
        //encerrando a conexão com o banco de dados
        sessao.close();
        
        //retornando a lista preenchida
        return retorno;
    }//fim do método buscarTodos
}//fim da classe
