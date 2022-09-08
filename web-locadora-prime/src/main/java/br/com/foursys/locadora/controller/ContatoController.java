package br.com.foursys.locadora.controller;


import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.dao.ContatoDAO;

/**
 * Classe responsável por armazenar os métodos de manutenção de base de dados
 * @author Victor Baptista
 * @since 01/04/2021
 * @version 1.0
 */
public class ContatoController {

    /*
     * Método para incluir ou alterar um objeto no banco de dados
     */
    public void salvar(Contato contato) {
        try {
            new ContatoDAO().salvar(contato);
        } catch (Exception e) {
        }
    }
    
    /*
    * Método para excluir um objeto
    */
    public void excluir(Contato contato) {
        try {
            new ContatoDAO().excluir(contato);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//fim do método excluir
}
