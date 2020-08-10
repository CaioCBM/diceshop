package br.unitins.diceshop.controller;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.diceshop.dao.DadoDAO;
import br.unitins.diceshop.model.Dado;
import br.unitins.diceshop.model.TipoDado;
import br.unitins.diceshop.model.TipoUsuario;
//ALTERADO PARA ATUALIZAR A TABELA A TODOS OS BUTÔS
@Named
@ViewScoped
public class DadoController extends Controller<Dado> {

	private static final long serialVersionUID = 1651642114811762868L;
	
	private List<Dado> listaDado;
	
	public DadoController() {
		super(new DadoDAO());
		Flash flash = FacesContext.getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("flashLivro");
		entity = (Dado) flash.get("flashLivro");
	}
	
	public List<Dado> getListaDado() {
		if (listaDado == null) {
			DadoDAO dao = new DadoDAO();
			listaDado = dao.findAll();
		}
		return listaDado;
	}
	
	@Override
	public Dado getEntity() {
		if (entity == null)
			entity = new Dado();
		return entity;
	}
	
	@Override
	public void limpar() {
		super.limpar();
		listaDado = null;
	}
	
	public TipoDado[] getListaTipoDado() {
		return TipoDado.values();
	}
	
}
