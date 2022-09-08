package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

@ManagedBean(name = "loginBacking")
@SessionScoped
public class LoginBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;
	
	public boolean user;
	public boolean admin;
	
	public static Funcionario funcionarioLogado;

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
	
	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public static void setFuncionarioLogado(Funcionario funcionarioLogado) {
		LoginBacking.funcionarioLogado = funcionarioLogado;
	}

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void efetuarLogin() {
		if (validar()) {
			try {
				
				ArrayList<Funcionario> funcionarios = new FuncionarioController().buscarPorLogin(login);
				//verifica e logo após abre o index
				if (validaDados(funcionarios)) {
					
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.LOGIN, Mensagem.CREDENCIAIS_INVALIDAS);
			}
		}
		
	}
		
	private boolean validar() {
		if (Valida.isEmptyOrNull(login)) {
			JSFUtil.addErrorMessage(Titulo.LOGIN, Mensagem.CREDENCIAIS_INVALIDAS);
			return false;
		}
		
		if (Valida.isEmptyOrNull(senha)) {
			JSFUtil.addErrorMessage(Titulo.LOGIN, Mensagem.CREDENCIAIS_INVALIDAS);
			return false;
		}
	
		return true;
	}
	
	private boolean validaDados(ArrayList<Funcionario> func) {
		for (Funcionario funcionario : func) {
			if (funcionario.getSenha().equals(senha)) {
				funcionarioLogado = funcionario;
				if (funcionario.getPerfilAcesso().equals(Perfil.USER.getDescricao())) {
					user = true;
				} else {
					user = false;
				}
				
				if (funcionario.getPerfilAcesso().equals(Perfil.ADMIN.getDescricao())) {
					admin = true;
				} else {
					admin = false;
				}
				
				return true;
			} else {
				JSFUtil.addErrorMessage(Titulo.LOGIN, Mensagem.CREDENCIAIS_INVALIDAS);
			}
		}
		
		return false;
	}

}
