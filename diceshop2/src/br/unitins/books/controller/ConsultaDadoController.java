package br.unitins.books.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.dao.DadoDAO;
import br.unitins.books.dao.UsuarioDAO;
import br.unitins.books.model.Dado;

@Named
@ViewScoped
public class ConsultaDadoController implements Serializable{
	
	private static final long serialVersionUID = 5971844866316069324L;
	
	private List<Dado> listaDado;
	
	private int tipoFiltro = 1;
	private String filtro = null;
	
	public void pesquisar() {
		DadoDAO dao = new DadoDAO();
		if (tipoFiltro == 1)
			listaDado = dao.findByDescricao(getFiltro());
		else 
			listaDado = dao.findByLado(getFiltro());
	}
	
	public String novoDado() {
		return "dado.xhtml?faces-redirect=true";
	}
	
	public String editar(Dado dado) {
		DadoDAO dao = new DadoDAO();
		dado = dao.findById(dado.getId());
		
		Flash flash = FacesContext.getCurrentInstance().
						getExternalContext().getFlash();
		//Oq é um Flash? Pesquisar
		flash.put("flashDado", dado);
		return "dado.xhtml?faces-redirect=true";
	}

	public List<Dado> getListaDado() {
		if (listaDado == null) {
			listaDado = new ArrayList<Dado>();
		}
		return listaDado;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public int getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

}
