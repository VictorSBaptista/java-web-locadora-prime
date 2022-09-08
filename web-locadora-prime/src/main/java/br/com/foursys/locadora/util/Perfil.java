package br.com.foursys.locadora.util;

/**
 * Classe responsável por armazenar tipos de perfil de funcionários 
 * @author Victor Baptista
 * @since 30/04/2021
 * @version 1.0
 */
public enum Perfil {

	ADMIN("Administrador"),
	DEV("Desenvolvedor"),
	USER("Usuario");
	
	private String descricao;
	
	Perfil(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
