package br.com.foursys.locadora.controller;


import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.dao.EnderecoDAO;

/**
 * Classe responsável por armazenar os métodos de manutenção de base de dados
 * @author Victor Baptista
 * @since 01/04/2021
 * @version 1.0
 */
public class EnderecoController {

    /*
     * Método para incluir ou alterar um objeto no banco de dados
     */
    public void salvar(Endereco endereco) {
        try {
            new EnderecoDAO().salvar(endereco);
        } catch (Exception e) {
        }
    }
    
    /*
    * Método para excluir um objeto
    */
    public void excluir(Endereco endereco) {
        try {
            new EnderecoDAO().excluir(endereco);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//fim do método excluir
}
