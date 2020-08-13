package br.unitins.diceshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.diceshop.dao.DadoDAO;
import br.unitins.diceshop.dao.UsuarioDAO;
import br.unitins.diceshop.model.Dado;

@Named
@ViewScoped
public class ConsultaDadoController implements Serializable{
	
	private static final long serialVersionUID = 5971844866316069324L;
	
	private List<Dado> listaDado = null;
	private String filtro = null;
	
	public void pesquisarTipoDado(int tipoDado) {
		DadoDAO dao = new DadoDAO();
		listaDado = dao.findByTipoDado(tipoDado);
	}
	
	public void pesquisar() {
		DadoDAO dao = new DadoDAO();
		listaDado = dao.findByDescricao(getFiltro());
	}
	
	public String novoDado() {
		return "dado.xhtml?faces-redirect=true";
	}
	
	public String editar(Dado dado) {
		DadoDAO dao = new DadoDAO();
		dado = dao.findById(dado.getId());
		
		Flash flash = FacesContext.getCurrentInstance().
						getExternalContext().getFlash();

		flash.put("flashDado", dado);
		return "dado.xhtml?faces-redirect=true";
	}

	public List<Dado> getListaDado() {
		if (listaDado == null) {
			DadoDAO dao = new DadoDAO();
			listaDado = dao.findAll();
		}
		return listaDado;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
