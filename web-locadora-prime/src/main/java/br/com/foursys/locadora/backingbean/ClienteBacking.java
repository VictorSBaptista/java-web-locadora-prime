package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.controller.CidadeController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.EstadoController;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Logradouro;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe respons�vel por controlar os componentes do front-end Cliente
 * @author Victor Baptista
 * @since 03/05/2021
 * @version 1.0
 */

@ManagedBean(name = "clienteBacking")
@SessionScoped
public class ClienteBacking implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Atributos da tela de cadastro
	private String nome;
	private String cpf;
	private String rg;
	private String dataNascimento;
	private String idade;
	private String sexo;
	private String telefone;
	private String celular;
	private String email;
	private String tipoLogradouro;
	private String enderec;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	
	private Contato contato;
	private Endereco endereco;
	
	// Atributos da tela de Consulta
	private String nomePesquisar;
	private Cliente clienteSelecionado;
	
	// Atributos auxiliares
	private Cliente cliente;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<String> listaPerfil;
	private ArrayList<String> listaLogradouros;
	private ArrayList<Estado> listaEstados;
    private ArrayList<Cidade> listaCidades;
    private ArrayList<String> listaE = carregarComboEstados();
    private ArrayList<String> listaC;
    
    public ClienteBacking() {
		carregarPerfil();
		carregarLogradouro();
		carregarClientesConsulta();
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public String getNomePesquisar() {
		return nomePesquisar;
	}
	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}
	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	public ArrayList<Estado> getListaEstados() {
		return listaEstados;
	}
	public void setListaEstados(ArrayList<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}
	public ArrayList<Cidade> getListaCidades() {
		return listaCidades;
	}
	public void setListaCidades(ArrayList<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}
	public ArrayList<String> getListaE() {
		return listaE;
	}
	public void setListaE(ArrayList<String> listaE) {
		this.listaE = listaE;
	}
	public ArrayList<String> getListaC() {
		return listaC;
	}
	public void setListaC(ArrayList<String> listaC) {
		this.listaC = listaC;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getEnderec() {
		return enderec;
	}
	public void setEnderec(String enderec) {
		this.enderec = enderec;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public ArrayList<String> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(ArrayList<String> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}
	public ArrayList<String> getListaLogradouros() {
		return listaLogradouros;
	}

	public void setListaLogradouros(ArrayList<String> listaLogradouros) {
		this.listaLogradouros = listaLogradouros;
	}

	/*
	 * M�todo respons�vel por capturar a a��o do bot�o cadastrar na tela CAD-FILME.JSP
	 */
	public void cadastrar() {
		
		if (validar()) {
			try {
				getContato();
				new ContatoController().salvar(contato);
				getEndereco();
				new EnderecoController().salvar(endereco);
				getCliente();
				new ClienteController().salvar(cliente);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_SALVO);
			}
		}
	}
	
	/*
	 * M�todo respons�vel por capturar a a��o do bot�o alterar na tela ALT-FILME.JSP
	 */
	public void alterarCliente() {
		if (validar()) {
			try {
				getContatoAlterar();
				new ContatoController().salvar(contato);
				getEnderecoAlterar();
				new EnderecoController().salvar(endereco);
				getClienteAlterar();
				new ClienteController().salvar(cliente);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_SALVO);
			}
		}
	}
	
	private boolean validar() {
		
		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.NOME_VAZIO);
			return false;
		}
		
		if (Valida.formattedIsEmptyOrNull(cpf)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CPF_VAZIO);
			return false;
		}else if (Valida.isCpfInvalido(cpf)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CPF_INVALIDO);
			return false;
		}
		
		if (Valida.formattedIsEmptyOrNull(rg)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.RG_VAZIO);
			return false;
		}
		
		if (Valida.formattedIsEmptyOrNull(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.DATA_NASCIMENTO_VAZIO);
			return false;
		} else if (Valida.isDataInvalida(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.DATA_NASCIMENTO_INVALIDO);
			return false;
		}
		
		if (!Valida.isInteger(idade)) {
			JSFUtil.addErrorMessage("", "");
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(idade))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.IDADE_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(sexo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.SEXO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(tipoLogradouro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.TIPO_LOGRADOURO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(enderec)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.ENDERECO_VAZIO);
			return false;
		}
		
		if (!Valida.isInteger(numero)) {
			JSFUtil.addErrorMessage("", "");
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(numero))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.NUMERO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(bairro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.BAIRRO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(cep)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CEP_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(estado)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.ESTADO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(cidade)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CIDADE_VAZIO);
			return false;
		}
		
		if (Valida.formattedIsEmptyOrNull(telefone)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.TELEFONE_VAZIO);
			return false;
		}
		
		if (Valida.formattedIsEmptyOrNull(celular)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CELULAR_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(email)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.EMAIL_VAZIO);
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
	public String cadastroCliente() {
		limparCampos();
		return "";
	}
	
	/*
	 * M�todo para abrir a tela de consulta
	 */
	public String consultarCliente() {
		nomePesquisar = null;
		listaClientes = null;
		return "";
	}
	
	/*
	 * M�todo para retornar objeto Cliente
	 */
	private void getCliente() {
		cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setRg(rg);
		cliente.setDataNascimento(dataNascimento);
		cliente.setIdade(Integer.parseInt(idade));
		cliente.setSexo(sexo);
	
		cliente.setContatoIdContato(contato);
		cliente.setEnderecoIdEndereco(endereco);
	}
	
	/*
	 * M�todo para retornar objeto Contato
	 */
	private void getContato() {
		contato = new Contato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}
	
	/*
	 * M�todo para retornar objeto Endereco
	 */
	private void getEndereco() {
		endereco = new Endereco();
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setEndereco(enderec);
		endereco.setNumero(Integer.parseInt(numero));
		endereco.setBairro(bairro);
		endereco.setComplemento(complemento);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(listaCidades.get(listaC.indexOf(cidade)));
	}
	/*
	 * M�todo para retornar objeto Cliente
	 */
	private void getClienteAlterar() {
		cliente = clienteSelecionado;
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setRg(rg);
		cliente.setDataNascimento(dataNascimento);
		cliente.setIdade(Integer.parseInt(idade));
		cliente.setSexo(sexo);
		
		cliente.setContatoIdContato(contato);
		cliente.setEnderecoIdEndereco(endereco);
	}
	
	/*
	 * M�todo para retornar objeto Contato
	 */
	private void getContatoAlterar() {
		contato = clienteSelecionado.getContatoIdContato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}
	
	/*
	 * M�todo para retornar objeto Endereco
	 */
	private void getEnderecoAlterar() {
		endereco = clienteSelecionado.getEnderecoIdEndereco();
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setEndereco(enderec);
		endereco.setNumero(Integer.parseInt(numero));
		endereco.setBairro(bairro);
		endereco.setComplemento(complemento);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(listaCidades.get(listaC.indexOf(cidade)));
	}
	
	private void limparCampos() {
		setNome(null);
		setCpf(null);
		setRg(null);
		setDataNascimento(null);
		setIdade(null);
		setSexo(null);
		setTelefone(null);
		setCelular(null);
		setEmail(null);
		setTipoLogradouro(null);
		setEnderec(null);
		setNumero(null);
		setComplemento(null);
		setBairro(null);
		setCep(null);
		setEstado(null);
		setCidade(null);
	}
	
	/*
	 * M�todo respons�vel por pesquisar clientes
	 */
	public String pesquisar() {
		try {
			listaClientes = new ClienteController().buscarPorNome(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_PESQUISA);
		}
		
		return "";
	}
	
	public void carregarClientesConsulta() {
		try {
			listaClientes = new ClienteController().buscarTodos();
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_PESQUISA);
		}
	}
	
	/*
	 * M�todo respons�vel por
	 */
	public void alterar() {
		String estadoA = "";
		
		nome = clienteSelecionado.getNome();
		cpf = clienteSelecionado.getCpf();
		rg = clienteSelecionado.getRg();
		dataNascimento = clienteSelecionado.getDataNascimento();
		idade = clienteSelecionado.getIdade() + "";
		sexo = clienteSelecionado.getSexo();

		tipoLogradouro = clienteSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderec = clienteSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = clienteSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = clienteSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = clienteSelecionado.getEnderecoIdEndereco().getBairro();
		cep = clienteSelecionado.getEnderecoIdEndereco().getCep();
		estadoA = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome();
		estado = listaE.get(listaE.indexOf(estadoA));
		carregarComboCidade();
		cidade = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getNome();
		telefone = clienteSelecionado.getContatoIdContato().getTelefone();
		celular = clienteSelecionado.getContatoIdContato().getCelular();
		email = clienteSelecionado.getContatoIdContato().getEmail();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-cliente.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * M�todo respons�vel por
	 */
	public void detalhar() {
		String estadoA = "";
		
		nome = clienteSelecionado.getNome();
		cpf = clienteSelecionado.getCpf();
		rg = clienteSelecionado.getRg();
		dataNascimento = clienteSelecionado.getDataNascimento();
		idade = clienteSelecionado.getIdade() + "";
		sexo = clienteSelecionado.getSexo();

		tipoLogradouro = clienteSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderec = clienteSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = clienteSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = clienteSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = clienteSelecionado.getEnderecoIdEndereco().getBairro();
		cep = clienteSelecionado.getEnderecoIdEndereco().getCep();
		estadoA = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome();
		estado = listaE.get(listaE.indexOf(estadoA));
		carregarComboCidade();
		cidade = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getNome();
		telefone = clienteSelecionado.getContatoIdContato().getTelefone();
		celular = clienteSelecionado.getContatoIdContato().getCelular();
		email = clienteSelecionado.getContatoIdContato().getEmail();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-cliente.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		try {
			new ClienteController().excluir(clienteSelecionado);
			new EnderecoController().excluir(endereco);
			new ContatoController().excluir(contato);
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_EXCLUIDO);
		}
	}
	
	/*
     * M�todo para carregar a combo de estados
     */
    public ArrayList<String> carregarComboEstados() {
    	ArrayList<String> list = new ArrayList<String>();
    	listaEstados = new EstadoController().buscarTodos();
    	for (Estado estado : listaEstados) {
			list.add(estado.getNome());
		}
    	return list;
    }
    
    /*
     * M�todo para carregar a combo de cidades
     */
    public void carregarComboCidade() {
    	int indice = listaE.indexOf(estado);
    	listaC = new ArrayList<String>();
    	if (indice >= 0) {
        	listaCidades = new CidadeController().buscarPorEstado(listaEstados.get(indice));	
        	for (Cidade cidade : listaCidades) {
    			listaC.add(cidade.getNome());
    		}
    	}
    }
    
    
    /*
	 * Método para carregar a lista de perfil
	 */
	public void carregarPerfil() {
		listaPerfil = new ArrayList<String>();
		
		for (Perfil p : Perfil.values()) {
			listaPerfil.add(p.getDescricao());
		}
	}
	
	/*
	 * Método para carregar a lista de logradouros
	 */
	public void carregarLogradouro() {
		listaLogradouros = new ArrayList<String>();
		
		for (Logradouro l : Logradouro.values()) {
			listaLogradouros.add(l.getDescricao());
		}
	}
}
