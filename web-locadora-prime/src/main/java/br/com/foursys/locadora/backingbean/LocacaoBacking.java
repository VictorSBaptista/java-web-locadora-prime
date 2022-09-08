package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.FormaPagamentoController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe respons�vel por controlar os componentes do front-end Locação
 * @author Victor Baptista
 * @since 05/05/2021
 * @version 1.0
 */

@ManagedBean(name = "locacaoBacking")
@ViewScoped
public class LocacaoBacking implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Atributos da tela de cadastro
	private String cpfCliente;
	private String dataLocacao;
	private Date dataDevolucao;
	private LocalDateTime dataLimite1 = LocalDateTime.now();
	private LocalDateTime dataLimite;
	private double valor;
	private double valorTotal;
	private int filme;
	private int formaPagamento;
	
	// Atributos auxiliares
	private Locacao locacao;
	private Cliente cliente;
	private Filme filmeSelecionado;
	private ArrayList<Filme> listaFilmesDisponiveis;
	private ArrayList<Filme> listaFilmeLocacao = new ArrayList<Filme>();
	private ArrayList<Cliente> listaClientes;
	private ArrayList<FormaPagamento> listaFormaPagamento;
	private boolean bloq = true;
	
	
	
	public LocacaoBacking() {
		carregarFormaPagamento();
		carregarListaClientes();
		carregarDataLocacao();
		dataLimite = dataLimite1.plusDays(30);
	}
	
	/*
	 * M�todos Getters e Setters
	 */
	
	
	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public LocalDateTime getDataLimite1() {
		return dataLimite1;
	}

	public void setDataLimite1(LocalDateTime dataLimite1) {
		this.dataLimite1 = dataLimite1;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public boolean isBloq() {
		return bloq;
	}

	public void setBloq(boolean bloq) {
		this.bloq = bloq;
	}

	public LocalDateTime getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(LocalDateTime dataLimite) {
		this.dataLimite = dataLimite;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public ArrayList<Filme> getListaFilmeLocacao() {
		return listaFilmeLocacao;
	}

	public void setListaFilmeLocacao(ArrayList<Filme> listaFilmeLocacao) {
		this.listaFilmeLocacao = listaFilmeLocacao;
	}

	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public int getFilme() {
		return filme;
	}

	public void setFilme(int filme) {
		this.filme = filme;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}


	public int getFormaPagamento() {
		return formaPagamento;
	}
	
	public ArrayList<FormaPagamento> getListaFormaPagamento() {
		return listaFormaPagamento;
	}

	public void setListaFormaPagamento(ArrayList<FormaPagamento> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}

	public ArrayList<Filme> getListaFilmesDisponiveis() {
		return listaFilmesDisponiveis;
	}

	public void setListaFilmesDisponiveis(ArrayList<Filme> listaFilmesDisponiveis) {
		this.listaFilmesDisponiveis = listaFilmesDisponiveis;
	}

	/*
	 * M�todo respons�vel por capturar a a��o do bot�o cadastrar na tela CAD-FILME.JSP
	 */
	public void cadastrar() {
		if (validar()) {
			try {
				getLocacao();
				new LocacaoController().salvar(locacao);
				salvar();
				cancelar();
				JSFUtil.addInfoMessage(Titulo.EFETUAR_LOCACAO, Mensagem.LOCACAO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.EFETUAR_LOCACAO, Mensagem.LOCACAO_ERRO_SALVO);
			}
		}
	}
	
	private boolean validar() {
		
		if (Valida.isIntZero(formaPagamento)) {
			JSFUtil.addErrorMessage(Titulo.EFETUAR_LOCACAO, Mensagem.FORMA_PAGAMENTO_VAZIO);
			return false;
		}
		
		if (Valida.isIntZero(listaFilmeLocacao.size())) {
			JSFUtil.addErrorMessage(Titulo.EFETUAR_LOCACAO, Mensagem.LISTA_FILME_LOCACAO_VAZIO);
			return false;
		}
		
		if (!Valida.isDouble(valorTotal + "")) {
			JSFUtil.addErrorMessage(Titulo.EFETUAR_LOCACAO, "");
			return false;
		} else if (Valida.isDoubleZero(valorTotal)) {
			JSFUtil.addErrorMessage(Titulo.EFETUAR_LOCACAO, "");
			return false;
		}
		
		
		return true;
	}
	
	/*
	 * M�todo respons�vel por capturar a a��o do bot�o cancelar na tela CAD-FILME.JSP
	 */
	public void cancelar() {
		limparCampos();
		bloq = true;
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
	 * M�todo para retornar objeto Locacao
	 */
	private void getLocacao() {
		locacao = new Locacao();
		
		locacao.setClienteIdCliente(cliente);
		locacao.setDataLocacao(dataLocacao);
		locacao.setDataDevolucao(carregaDataDevolucao());
		locacao.setValor(valorTotal);
		int indice = listaFormaPagamento.indexOf(new FormaPagamento(formaPagamento));
		locacao.setFormaPagamentoIdFormaPagamento(listaFormaPagamento.get(indice));
		locacao.setDevolvido("Não");
		locacao.setFuncionarioIdFuncionario(LoginBacking.funcionarioLogado);
	}
	
	
	private void limparCampos() {
		setCpfCliente(null);
		cliente = new Cliente();
		setFilme(0);
		setFormaPagamento(0);
		setDataDevolucao(null);
		setValorTotal(0.0);
		carregarComboFilmes();
		listaFilmeLocacao = new ArrayList<Filme>();
	}
	
	
	/*
	 * Método para carregar a lista de gêneros
	 */
	public void carregarFormaPagamento() {
		try {
			listaFormaPagamento = new FormaPagamentoController().buscarTodos();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/*
	 * Método para carregar a lista de filmes disponíveis
	 */
	public void carregarComboFilmes() {
		listaFilmesDisponiveis  = new ArrayList<Filme>();
		try {
			ArrayList<Filme> listaFilmes = new FilmeController().buscarTodos();
			for (Filme filme : listaFilmes) {
				if (filme.getDisponivel().equals("Sim") && !(cliente.getIdade() < filme.getFaixaEtaria())) {
					listaFilmesDisponiveis.add(filme);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/*
	 * Método para carregar os dados do cliente
	 */
	public void carregarListaClientes() {
		try {
			listaClientes = new ClienteController().buscarTodos();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void pesquisarCliente() {
		if (!Valida.formattedIsEmptyOrNull(cpfCliente)) {
			for (Cliente client : listaClientes) {
				if (client.getCpf().equals(cpfCliente)) {
					cliente = new Cliente();
					cliente = client;
					bloq = false;
					carregarComboFilmes();
				}
			}
		} else {
			JSFUtil.addErrorMessage(Titulo.EFETUAR_LOCACAO, Mensagem.CPF_VAZIO);
		}
		
	}
	
	public void escolheFilme() {
		if (filme > 0) {
			int indice = listaFilmesDisponiveis.indexOf(new Filme(filme));
			Filme filme = listaFilmesDisponiveis.get(indice);
			
			listaFilmeLocacao.add(filme);
			listaFilmesDisponiveis.remove(filme);
			
			if (filme.getValorPromocao() != null) {
				valor = filme.getValorPromocao();
			} else {
				valor = filme.getValor();
			}
			
			valorTotal += valor;
			
			// Sorting
			Collections.sort(listaFilmeLocacao, new Comparator<Filme>() {
				@Override
			    public int compare(Filme filme2, Filme filme1){
					return  filme2.getIdFilme().compareTo(filme1.getIdFilme());
			    }
			});
		} else {
			JSFUtil.addErrorMessage(Titulo.EFETUAR_LOCACAO, Mensagem.LISTA_FILME_LOCACAO_VAZIO);
		}
		
	}
	
	public void removeFilme() {
		listaFilmeLocacao.remove(filmeSelecionado);
		listaFilmesDisponiveis.add(filmeSelecionado);
		
		if (filmeSelecionado.getPromocao().equals("Sim")) {
			valor = filmeSelecionado.getValorPromocao();
			
		} else {
			valor = filmeSelecionado.getValor();
		}
		
		valorTotal -= valor;
		
		// Sorting
		Collections.sort(listaFilmesDisponiveis, new Comparator<Filme>() {
			@Override
			public int compare(Filme filme2, Filme filme1){
				return  filme2.getIdFilme().compareTo(filme1.getIdFilme());
			}
		});
	}
	
	private void carregarDataLocacao() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dataLocacao = dateFormat.format(date);
	}
	
	private String carregaDataDevolucao() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(dataDevolucao);
	}
	
	private void salvar() {
		for (Filme filme : listaFilmeLocacao) {
			try {
				LocacaoFilme locacaoFilme = new LocacaoFilme();
				locacaoFilme.setFilmeIdFilme(filme);
				locacaoFilme.setLocacaoIdLocacao(locacao);

				new LocacaoFilmeController().salvar(locacaoFilme);

				filme.setDisponivel("Não");
				new FilmeController().salvar(filme);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
