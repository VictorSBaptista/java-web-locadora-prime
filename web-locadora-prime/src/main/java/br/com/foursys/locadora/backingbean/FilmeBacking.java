package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.util.Genero;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe respons�vel por controlar os componentes do front-end Filme
 * @author vicbaptista
 * @since 27/04/2021
 * @version 1.0
 */

@ManagedBean(name = "filmeBacking")
@SessionScoped
public class FilmeBacking implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Atributos da tela de cadastro
	private String nome;
	private String valor;
	private String disponivel;
	private String promocao;
	private String valorPromocao;
	private String diretor;
	private String anoLancamento;
	private String faixaEtaria;
	private String genero;
	
	// Atributos da tela de Consulta
	private String nomePesquisar;
	private Filme filmeSelecionado;
	
	// Atributos auxiliares
	private Filme filme;
	private ArrayList<Filme> listaFilmes;
	private ArrayList<String> listaGeneros;
	
	public FilmeBacking() {
		carregarGeneros();
		carregarFilmesConsulta(); 
	}
	
	/*
	 * M�todos Getters e Setters
	 */
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}
	public String getPromocao() {
		return promocao;
	}
	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}
	public String getValorPromocao() {
		return valorPromocao;
	}
	public void setValorPromocao(String valorPromocao) {
		this.valorPromocao = valorPromocao;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public String getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public String getFaixaEtaria() {
		return faixaEtaria;
	}
	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}
	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}
	public String getNomePesquisar() {
		return nomePesquisar;
	}
	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}
	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}
	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}
	public ArrayList<String> getListaGeneros() {
		return listaGeneros;
	}
	public void setListaGeneros(ArrayList<String> listaGeneros) {
		this.listaGeneros = listaGeneros;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	/*
	 * M�todo respons�vel por capturar a a��o do bot�o cadastrar na tela CAD-FILME.JSP
	 */
	public void cadastrar() {
		if (validar()) {
			try {
				getFilme();
				new FilmeController().salvar(filme);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_SALVO);
			}
		}
	}
	
	/*
	 * M�todo respons�vel por capturar a a��o do bot�o alterar na tela ALT-FILME.JSP
	 */
	public void alterarFilme() {
		if (validar()) {
			try {
				getFilmeAlterar();
				new FilmeController().salvar(filme);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_SALVO);
			}
		}
	}
	
	private boolean validar() {
		
		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.NOME_VAZIO);
			return false;
		}
		
		if (!Valida.isDouble(valor)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_INVALIDO);
			return false;
		} else if (Valida.isDoubleZero(Double.parseDouble(valor))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(disponivel)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.DISPONIVEL_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(promocao)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.PROMOCAO_VAZIO);
			return false;
		}
		
		if (promocao.equals("Sim")) {
			if (!Valida.isDouble(valorPromocao)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_PROMOCAO_INVALIDO);
				return false;
			} else if (Valida.isDoubleZero(Double.parseDouble(valorPromocao))) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_PROMOCAO_VAZIO);
				return false;
			}
		}
		
		if (Valida.isEmptyOrNull(diretor)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.DIRETOR_VAZIO);
			return false;
		}
		
		if (!Valida.isInteger(anoLancamento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.ANO_LANCAMENTO_INVALIDO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(anoLancamento))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.ANO_LANCAMENTO_VAZIO);
			return false;
		}
		
		if (!Valida.isInteger(faixaEtaria)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FAIXA_ETARIA_INVALIDO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(faixaEtaria))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FAIXA_ETARIA_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(genero)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.GENERO_VAZIO);
			return false;
		}
		
		return true;
	}
	
	/*
	 * M�todo respons�vel por capturar a a��o do bot�o cancelar na tela CAD-FILME.JSP
	 */
	public void cancelar() {
		limparCampos();
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
	
	/*
	 * M�todo para abrir a tela de cadastro
	 */
	public String cadastroFilme() {
		limparCampos();
		return "";
	}
	
	/*
	 * M�todo para abrir a tela de consulta
	 */
	public String consultarFilme() {
		nomePesquisar = null;
		listaFilmes = null;
		return "";
	}
	
	/*
	 * M�todo para retornar objeto Filme
	 */
	private void getFilme() {
		filme = new Filme();
		filme.setNome(nome);
		filme.setValor(Double.parseDouble(valor));
		filme.setDisponivel(disponivel);
		filme.setPromocao(promocao);
		
		if (promocao.equals("Sim")) {
			filme.setValorPromocao(Double.parseDouble(valorPromocao));
		} else {
			filme.setValorPromocao(Double.parseDouble(valor));
		}
		
		filme.setDiretor(diretor);
		filme.setAnoLancamento(anoLancamento);
		filme.setFaixaEtaria(Integer.parseInt(faixaEtaria));
		filme.setGenero(genero);
	}
	
	/*
	 * M�todo para retornar objeto Filme
	 */
	private void getFilmeAlterar() {
		filme = filmeSelecionado;
		filme.setNome(nome);
		filme.setValor(Double.parseDouble(valor));
		filme.setDisponivel(disponivel);
		filme.setPromocao(promocao);
		
		if (promocao.equals("Sim")) {
			filme.setValorPromocao(Double.parseDouble(valorPromocao));
		} else {
			filme.setValorPromocao(Double.parseDouble(valor));
		}
		
		filme.setDiretor(diretor);
		filme.setAnoLancamento(anoLancamento);
		filme.setFaixaEtaria(Integer.parseInt(faixaEtaria));
		filme.setGenero(genero);
	}
	
	private void limparCampos() {
		setNome(null);
		setValor(null);
		setDisponivel(null);
		setPromocao(null);
		setValorPromocao(null);
		setDiretor(null);
		setAnoLancamento(null);
		setFaixaEtaria(null);
		setGenero(null);
	}
	
	/*
	 * M�todo respons�vel por pesquisar filmes
	 */
	public String pesquisar() {
		try {
			listaFilmes = new FilmeController().buscarPorNome(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_PESQUISA);
		}
		
		return "";
	}
	
	public void carregarFilmesConsulta() {
		try {
			listaFilmes = new FilmeController().buscarTodos();
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_PESQUISA);
		}
	}
	
	/*
	 * M�todo respons�vel por
	 */
	public void alterar() {
		nome = filmeSelecionado.getNome();
		valor = filmeSelecionado.getValor() + "";
		disponivel = filmeSelecionado.getDisponivel();
		promocao = filmeSelecionado.getPromocao();
		if (filmeSelecionado.getValorPromocao() != null) {
			valorPromocao = filmeSelecionado.getValorPromocao() + "";
		} else {
			valorPromocao = filmeSelecionado.getValor() + "";
		}
		
		diretor = filmeSelecionado.getDiretor();
		anoLancamento = filmeSelecionado.getAnoLancamento() + "";
		faixaEtaria = filmeSelecionado.getFaixaEtaria() + "";
		genero = filmeSelecionado.getGenero();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-filme.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * M�todo respons�vel por
	 */
	public void detalhar() {
		nome = filmeSelecionado.getNome();
		valor = filmeSelecionado.getValor() + "";
		disponivel = filmeSelecionado.getDisponivel();
		promocao = filmeSelecionado.getPromocao();
		if (filmeSelecionado.getValorPromocao() != null) {
			valorPromocao = filmeSelecionado.getValorPromocao() + "";
		} else {
			valorPromocao = filmeSelecionado.getValor() + "";
		}
		
		diretor = filmeSelecionado.getDiretor();
		anoLancamento = filmeSelecionado.getAnoLancamento() + "";
		faixaEtaria = filmeSelecionado.getFaixaEtaria() + "";
		genero = filmeSelecionado.getGenero();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-filme.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		try {
			new FilmeController().excluir(filmeSelecionado);
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_EXCLUIDO);
		}
	}
	
	/*
	 * Método para carregar a lista de gêneros
	 */
	public void carregarGeneros() {
		listaGeneros = new ArrayList<String>();
		
		for (Genero g : Genero.values()) {
			listaGeneros.add(g.getDescricao());
		}
	}
}
