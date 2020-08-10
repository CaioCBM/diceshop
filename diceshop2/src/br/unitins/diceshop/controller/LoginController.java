package br.unitins.diceshop.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.diceshop.application.Session;
import br.unitins.diceshop.application.Util;
import br.unitins.diceshop.dao.UsuarioDAO;
import br.unitins.diceshop.model.Usuario;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;
	
	public String logar() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.verificarLoginSenha(getUsuario().getLogin(),
				Util.hashSHA256(getUsuario().getSenha()));
		
		if (usuario != null) {
			// adicionando um ussuario na sessao
			Session.getInstance().setAttribute("usuarioLogado", usuario);
			// redirecionando para o template
			return "index.xhtml?faces-redirect=true";
		}
		Util.addErrorMessage("Login ou Senha inválido.");
		return "";
	}
	
	public String register() {
		return "register.xhtml?faces-redirect=true";
	}
	
	public void limpar() {
		usuario = null;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
