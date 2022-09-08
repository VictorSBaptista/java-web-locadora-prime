package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.util.HibernateUtil;

/**
 * Classe responsável por armazenar os métodos para acesso ao banco de dados
 * @author Victor Baptista
 * @since 27/04/2021
 * @version 1.0
 */
public class FuncionarioDAO extends GenericDAO{
    
	@SuppressWarnings("unchecked")
    public ArrayList<Funcionario> buscarTodos() throws Exception {

        ArrayList<Funcionario> listaRetorno = new ArrayList<Funcionario>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Funcionario.class);
        criteria.addOrder(Order.asc("idFuncionario"));
        listaRetorno = (ArrayList<Funcionario>) criteria.list();
        sessao.close();
        return listaRetorno;
    }
	
    /*
    * Método para consultar os alunos na tabela
    */
    public ArrayList<Funcionario> buscarPorNome(String nome) throws Exception{
        //lista auxiliar para retornar no m�todo
        ArrayList<Funcionario> retorno = new ArrayList<Funcionario>();
        
        //classe auxiliar para armazenar a sess�o com o banco de dados
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        
        //classe auxiliar para consultar o banco de dados
        Criteria criteria = sessao.createCriteria(Funcionario.class);
        
        //adicionar crit�rio de pesquisa
        criteria.add(Restrictions.like("nome", nome + "%"));
        
        //adicionando a ordena��o da pesquisa
        criteria.addOrder(Order.asc("nome"));
        
        //valorizando o objeto de retorno do m�todo com os registros da tabela
        retorno = (ArrayList<Funcionario>) criteria.list();
        
        //encerrando a conex�o com o banco de dados
        sessao.close();
        
        //retornando a lista preenchida
        return retorno;
    }//fim do método buscarTodos
    
    /*
     * Método para consultar os alunos na tabela
     */
     public ArrayList<Funcionario> buscarPorLogin(String login) throws Exception{
         //lista auxiliar para retornar no m�todo
         ArrayList<Funcionario> retorno = new ArrayList<Funcionario>();
         
         //classe auxiliar para armazenar a sess�o com o banco de dados
         Session sessao = HibernateUtil.getSessionFactory().openSession();
         
         //classe auxiliar para consultar o banco de dados
         Criteria criteria = sessao.createCriteria(Funcionario.class);
         
         //adicionar crit�rio de pesquisa
         criteria.add(Restrictions.eq("login", login));
         
         //adicionando a ordena��o da pesquisa
         criteria.addOrder(Order.asc("nome"));
         
         //valorizando o objeto de retorno do m�todo com os registros da tabela
         retorno = (ArrayList<Funcionario>) criteria.list();
         
         //encerrando a conex�o com o banco de dados
         sessao.close();
         
         //retornando a lista preenchida
         return retorno;
     }//fim do método buscarTodos
}//fim da classe
