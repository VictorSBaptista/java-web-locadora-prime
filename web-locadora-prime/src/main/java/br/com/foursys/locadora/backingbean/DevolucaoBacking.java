package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.FilterMeta;

import com.mysql.fabric.xmlrpc.base.Array;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;

/**
 * Classe respons�vel por controlar os componentes do front-end Devolução e lista locações
 * @author Victor Baptista
 * @since 06/05/2021
 * @version 1.0
 */

@ManagedBean(name = "devolucaoBacking")
@ViewScoped
public class DevolucaoBacking implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Atributos da tela de cadastro
	private int locacaoIndice;
	private ArrayList<Locacao> listaLocacaos;
	private ArrayList<Locacao> listaTodasLocacaos;
	private Locacao locacao;
	private Locacao locacaoSelec;
	private String dataAtual;
	private String nomePesquisar;
	
	// Atributos auxiliares
	private boolean bloq = true;
	private ArrayList<Filme> listaFilme;
	private ArrayList<Filme> listaFilmeCons;
	
	public DevolucaoBacking() {
		carregarListaLocacao();
		carregaDataAtual();
		carregarListaTodasLocacao();
	}
	
	/*
	 * M�todos Getters e Setters
	 */
	
	
	public ArrayList<Filme> getListaFilme() {
		return listaFilme;
	}

	public Locacao getLocacaoSelec() {
		return locacaoSelec;
	}

	public void setLocacaoSelec(Locacao locacaoSelec) {
		this.locacaoSelec = locacaoSelec;
	}

	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public ArrayList<Locacao> getListaTodasLocacaos() {
		return listaTodasLocacaos;
	}

	public void setListaTodasLocacaos(ArrayList<Locacao> listaTodasLocacaos) {
		this.listaTodasLocacaos = listaTodasLocacaos;
	}

	public ArrayList<Filme> getListaFilmeCons() {
		return listaFilmeCons;
	}

	public void setListaFilmeCons(ArrayList<Filme> listaFilmeCons) {
		this.listaFilmeCons = listaFilmeCons;
	}

	public void setListaFilme(ArrayList<Filme> listaFilme) {
		this.listaFilme = listaFilme;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public boolean isBloq() {
		return bloq;
	}

	public void setBloq(boolean bloq) {
		this.bloq = bloq;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public int getLocacaoIndice() {
		return locacaoIndice;
	}

	public void setLocacaoIndice(int locacaoIndice) {
		this.locacaoIndice = locacaoIndice;
	}
	
	public ArrayList<Locacao> getListaLocacaos() {
		return listaLocacaos;
	}

	public void setListaLocacaos(ArrayList<Locacao> listaLocacaos) {
		this.listaLocacaos = listaLocacaos;
	}

	public void efetuaDevolucao() {
		try {
			locacao.setDevolvido("Sim");
			if (!dataAtual.equals(locacao.getDataDevolucao())) {
				locacao.setDataDevolucao(dataAtual);
			}
			new LocacaoController().salvar(locacao);
			devolveFilme();
			
			cancelar();
			carregarListaLocacao();
			JSFUtil.addInfoMessage(Titulo.EFETUAR_DEVOLUCAO, Mensagem.DEVOLUCAO_SALVO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.EFETUAR_DEVOLUCAO, Mensagem.DEVOLUCAO_ERRO_SALVO);
		}
	}
	
	/*
	 * Método para carregar os dados da locação
	 */
	public void carregarListaLocacao() {
		listaLocacaos = new ArrayList<Locacao>();
		ArrayList<Locacao> listaLoc = new ArrayList<Locacao>();
		try {
			listaLoc = new LocacaoController().buscarTodos();
			for (Locacao loc : listaLoc) {
				if (loc.getDevolvido().equals("Não") && !listaLocacaos.contains(loc)) {
					listaLocacaos.add(loc);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*
	 * Método para carregar os dados da locação
	 */
	public void carregarListaTodasLocacao() {
		listaTodasLocacaos = new ArrayList<Locacao>();
		ArrayList<Locacao> listaLoc = new ArrayList<Locacao>();
		try {
			listaLoc = new LocacaoController().buscarTodos();
			for (Locacao loc : listaLoc) {
				if (!listaTodasLocacaos.contains(loc)) {
					listaTodasLocacaos.add(loc);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void pesquisarPorNomeCliente() {
		listaTodasLocacaos = new ArrayList<Locacao>();
		ArrayList<Locacao> listaLoc = new ArrayList<Locacao>();
		try {
			listaLoc = new LocacaoController().buscarTodos();
			if (!nomePesquisar.equals("")) {
				for (Locacao loc : listaLoc) {
					if (!listaTodasLocacaos.contains(loc) && loc.getClienteIdCliente().getNome().equals(nomePesquisar)) {
						listaTodasLocacaos.add(loc);
					} else {
						JSFUtil.addErrorMessage(Titulo.CONSULTA_LOCACOES, Mensagem.CLIENTE_NAO_CADASTRADO);
					}
					
				}
			}else {
				carregarListaTodasLocacao();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void carregaDadosLocacao() {
		if (locacaoIndice > 0) {
			locacao = new Locacao();
			int index = listaLocacaos.indexOf(new Locacao(locacaoIndice));
			locacao = listaLocacaos.get(index);
			carregaFilmes();
			bloq = false;
		} else {
			JSFUtil.addErrorMessage(Titulo.EFETUAR_DEVOLUCAO, Mensagem.LISTA_DEVOLUCAO_VAZIO);
		}
		
	}
	
	public void cancelar() {
		limparCampos();
		bloq = true;
	}
	
	private void limparCampos() {
		setLocacaoIndice(0);
		carregarListaLocacao();
		locacao = new Locacao();
	}
	
	private void carregaFilmes() {
		listaFilme = new ArrayList<Filme>();
		for (LocacaoFilme locacaoFilme : new LocacaoFilmeController().buscarPorLocacao(locacao)) {
			listaFilme.add(locacaoFilme.getFilmeIdFilme());
		}
	}
	
	
	private void devolveFilme() {
		for (Filme filme : listaFilme) {
			filme.setDisponivel("Sim");
			try {
				new FilmeController().salvar(filme);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private void carregaDataAtual() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dataAtual = dateFormat.format(date);
	}
	
	/*
	 * M�todo respons�vel por capturar a a��o do bot�o sair na tela CAD-FILME.JSP
	 */
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
