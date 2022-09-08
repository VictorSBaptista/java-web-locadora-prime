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
import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.CidadeController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.EstadoController;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Logradouro;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe respons�vel por controlar os componentes do front-end Funcionario
 * @author Victor Baptista
 * @since 30/04/2021
 * @version 1.0
 */

@ManagedBean(name = "funcionarioBacking")
@SessionScoped
public class FuncionarioBacking implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Atributos da tela de cadastro
	private String nome;
	private String cpf;
	private String rg;
	private String dataNascimento;
	private String idade;
	private String sexo;
	private String login;
	private String senha;
	private String perfilAcesso;
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
	private Funcionario funcionarioSelecionado;
	
	// Atributos auxiliares
	private Funcionario funcionario;
	private ArrayList<Funcionario> listaFuncionarios;
	private ArrayList<String> listaPerfil;
	private ArrayList<String> listaLogradouros;
	private ArrayList<Estado> listaEstados;
    private ArrayList<Cidade> listaCidades;
    private ArrayList<String> listaE = carregarComboEstados();
    private ArrayList<String> listaC;
    
    public FuncionarioBacking() {
		carregarPerfil();
		carregarLogradouro();
		carregaListaFuncionarios();
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPerfilAcesso() {
		return perfilAcesso;
	}
	public void setPerfilAcesso(String perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}
	public ArrayList<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}
	public void setListaFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	public String getNomePesquisar() {
		return nomePesquisar;
	}
	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}
	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}
	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
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
				getFuncionario();
				new FuncionarioController().salvar(funcionario);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}
	}
	
	/*
	 * M�todo respons�vel por capturar a a��o do bot�o alterar na tela ALT-FILME.JSP
	 */
	public void alterarFuncionario() {
		if (validar()) {
			try {
				getContatoAlterar();
				new ContatoController().salvar(contato);
				getEnderecoAlterar();
				new EnderecoController().salvar(endereco);
				getFuncionarioAlterar();
				new FuncionarioController().salvar(funcionario);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}
	}
	
	private boolean validar() {
		
		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NOME_VAZIO);
			return false;
		}
		
		if (Valida.formattedIsEmptyOrNull(cpf)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CPF_VAZIO);
			return false;
		} else if (Valida.isCpfInvalido(cpf)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CPF_INVALIDO);
			return false;
		}
		
		if (Valida.formattedIsEmptyOrNull(rg)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.RG_VAZIO);
			return false;
		}
		
		if (Valida.formattedIsEmptyOrNull(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.DATA_NASCIMENTO_VAZIO);
			return false;
		} else if (Valida.isDataInvalida(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.DATA_NASCIMENTO_INVALIDO);
		}
		
		if (!Valida.isInteger(idade)) {
			JSFUtil.addErrorMessage("", "");
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(idade))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.IDADE_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(sexo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.SEXO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(login)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.LOGIN_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(senha)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.SENHA_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(perfilAcesso)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.PERFIL_ACESSO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(tipoLogradouro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.TIPO_LOGRADOURO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(enderec)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ENDERECO_VAZIO);
			return false;
		}
		
		if (!Valida.isInteger(numero)) {
			JSFUtil.addErrorMessage("", "");
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(numero))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NUMERO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(bairro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.BAIRRO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(cep)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CEP_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(estado)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ESTADO_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(cidade)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CIDADE_VAZIO);
			return false;
		}
		
		if (Valida.formattedIsEmptyOrNull(telefone)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.TELEFONE_VAZIO);
			return false;
		}
		
		if (Valida.formattedIsEmptyOrNull(celular)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CELULAR_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(email)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.EMAIL_VAZIO);
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
	public String cadastroFuncionario() {
		limparCampos();
		return "";
	}
	
	/*
	 * M�todo para abrir a tela de consulta
	 */
	public String consultarFuncionario() {
		nomePesquisar = null;
		listaFuncionarios = null;
		return "";
	}
	
	/*
	 * M�todo para retornar objeto Funcionario
	 */
	private void getFuncionario() {
		funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setRg(rg);
		funcionario.setDataNascimento(dataNascimento);
		funcionario.setIdade(Integer.parseInt(idade));
		funcionario.setSexo(sexo);
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		funcionario.setPerfilAcesso(perfilAcesso);
		funcionario.setContatoIdContato(contato);
		funcionario.setEnderecoIdEndereco(endereco);
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
	 * M�todo para retornar objeto Funcionario
	 */
	private void getFuncionarioAlterar() {
		funcionario = funcionarioSelecionado;
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setRg(rg);
		funcionario.setDataNascimento(dataNascimento);
		funcionario.setIdade(Integer.parseInt(idade));
		funcionario.setSexo(sexo);
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		funcionario.setPerfilAcesso(perfilAcesso);
		funcionario.setContatoIdContato(contato);
		funcionario.setEnderecoIdEndereco(endereco);
	}
	
	/*
	 * M�todo para retornar objeto Contato
	 */
	private void getContatoAlterar() {
		contato = funcionarioSelecionado.getContatoIdContato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}
	
	/*
	 * M�todo para retornar objeto Endereco
	 */
	private void getEnderecoAlterar() {
		endereco = funcionarioSelecionado.getEnderecoIdEndereco();
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
		setLogin(null);
		setSenha(null);
		setPerfilAcesso(null);
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
	 * M�todo respons�vel por pesquisar funcionarios
	 */
	public String pesquisar() {
		try {
			listaFuncionarios = new FuncionarioController().buscarPorNome(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_PESQUISA);
		}
		
		return "";
	}
	
	public void carregaListaFuncionarios() {
		try {
			listaFuncionarios = new FuncionarioController().buscarTodos();
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_PESQUISA);
		}
	}
	
	/*
	 * M�todo respons�vel por
	 */
	public void alterar() {
		String estadoA = "";
		
		nome = funcionarioSelecionado.getNome();
		cpf = funcionarioSelecionado.getCpf();
		rg = funcionarioSelecionado.getRg();
		dataNascimento = funcionarioSelecionado.getDataNascimento();
		idade = funcionarioSelecionado.getIdade() + "";
		sexo = funcionarioSelecionado.getSexo();
		login = funcionarioSelecionado.getLogin();
		senha = funcionarioSelecionado.getSenha();
		perfilAcesso = funcionarioSelecionado.getPerfilAcesso();
		tipoLogradouro = funcionarioSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderec = funcionarioSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = funcionarioSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = funcionarioSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = funcionarioSelecionado.getEnderecoIdEndereco().getBairro();
		cep = funcionarioSelecionado.getEnderecoIdEndereco().getCep();
		estadoA = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome();
		estado = listaE.get(listaE.indexOf(estadoA));
		carregarComboCidade();
		cidade = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getNome();
		telefone = funcionarioSelecionado.getContatoIdContato().getTelefone();
		celular = funcionarioSelecionado.getContatoIdContato().getCelular();
		email = funcionarioSelecionado.getContatoIdContato().getEmail();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-funcionario.xhtml");
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
		
		nome = funcionarioSelecionado.getNome();
		cpf = funcionarioSelecionado.getCpf();
		rg = funcionarioSelecionado.getRg();
		dataNascimento = funcionarioSelecionado.getDataNascimento();
		idade = funcionarioSelecionado.getIdade() + "";
		sexo = funcionarioSelecionado.getSexo();
		login = funcionarioSelecionado.getLogin();
		senha = funcionarioSelecionado.getSenha();
		perfilAcesso = funcionarioSelecionado.getPerfilAcesso();
		tipoLogradouro = funcionarioSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderec = funcionarioSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = funcionarioSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = funcionarioSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = funcionarioSelecionado.getEnderecoIdEndereco().getBairro();
		cep = funcionarioSelecionado.getEnderecoIdEndereco().getCep();
		estadoA = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome();
		estado = listaE.get(listaE.indexOf(estadoA));
		carregarComboCidade();
		cidade = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getNome();
		telefone = funcionarioSelecionado.getContatoIdContato().getTelefone();
		celular = funcionarioSelecionado.getContatoIdContato().getCelular();
		email = funcionarioSelecionado.getContatoIdContato().getEmail();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-funcionario.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		try {
			new FuncionarioController().excluir(funcionarioSelecionado);
			new EnderecoController().excluir(endereco);
			new ContatoController().excluir(contato);
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_EXCLUIDO);
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
