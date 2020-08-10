package br.unitins.diceshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.diceshop.application.Session;
import br.unitins.diceshop.model.ItemVenda;
import br.unitins.diceshop.model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable {

	private static final long serialVersionUID = -925765300233216226L;

	private Usuario usuarioLogado = null;
	private List<ItemVenda> carrinho;			

	public int getCarrinho() {
		carrinho = (ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");			
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();
		
		return carrinho.size();
	}

	public void setCarrinho(List<ItemVenda> carrinho) {
		
		this.carrinho = carrinho;
	}

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) // buscando o usuario da sessao
			usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");			
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String encerrarSessao() {
		// encerrando a sessao
		Session.getInstance().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}

}
