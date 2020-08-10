package br.unitins.diceshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.diceshop.application.Session;
import br.unitins.diceshop.dao.VendaDAO;
import br.unitins.diceshop.model.Usuario;
import br.unitins.diceshop.model.Venda;

@Named
@ViewScoped
public class PerfilUsuarioController implements Serializable {

	private static final long serialVersionUID = -8585030860902916284L;
	
	private List<Venda> listaVenda = null;
	private Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Venda> getListaVenda() {
		if (listaVenda == null) {
			VendaDAO dao = new VendaDAO();
			Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
			listaVenda = dao.findByUsuario(usuario.getId());
			if (listaVenda == null)
				listaVenda = new ArrayList<Venda>();
		}
		return listaVenda;
	}
	
	public String detalhes(Venda venda) {
		Flash flash = FacesContext.
				getCurrentInstance().
				getExternalContext().getFlash();
		flash.put("detalheVenda", venda);
		
		return "detalhesvenda.xhtml?faces-redirect=true";
	}
	
}
