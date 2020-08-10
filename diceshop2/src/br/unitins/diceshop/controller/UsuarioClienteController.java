package br.unitins.diceshop.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.diceshop.application.Session;
import br.unitins.diceshop.application.Util;
import br.unitins.diceshop.dao.UsuarioDAO;
import br.unitins.diceshop.model.Usuario;

@Named
@ViewScoped
public class UsuarioClienteController implements Serializable{

	private static final long serialVersionUID = 8156330847829473874L;
	private UsuarioDAO dao = new UsuarioDAO();

	private Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
	private Usuario usuarioAux = usuario.getClone();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioAux() {
		return usuarioAux;
	}

	public void setUsuarioAux(Usuario usuarioAux) {
		this.usuarioAux = usuarioAux;
	}
	
	public void alterar() {
		if (validarSenha()) {
			if (dao.update(getUsuarioAux())) {
				usuario = dao.findById(usuario.getId());
				Session.getInstance().setAttribute("usuarioLogado", usuarioAux);
				Util.addInfoMessage("Alteração realizada com sucesso.");
			} else {
				Util.addInfoMessage("Erro ao alterar no banco de dados.");
			}
		}  else {
			Util.addInfoMessage("Confirme se sua senha está certa.");
		}
	}
	
	public void limpar() {
		usuario = null;
	}
	
	public boolean validarSenha() {
		if (dao.verificarSenhaAlterar(usuarioAux.getId(), Util.hashSHA256(usuarioAux.getSenha())) != null)
			return true;
		else
			return false;
	}
	
	public Usuario getClone() {
		try {
			return (Usuario) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
