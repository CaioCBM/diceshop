package br.unitins.diceshop.controller;

import java.io.Serializable;

import br.unitins.diceshop.application.Util;
import br.unitins.diceshop.dao.DAO;
import br.unitins.diceshop.model.Entity;

public abstract class Controller <T extends Entity<T>> implements Serializable {

	private static final long serialVersionUID = -2022582742025295921L;
	
	protected T entity = null;
	protected DAO<T> dao = null;
	
	public Controller(DAO<T> dao) {
		super();
		this.dao = dao;
	}
	
	public abstract T getEntity();
	
	public void setEntity(T entity) {
		this.entity = entity;
	}

	public void incluir() {
		if (validarDados()) {
			if (dao.create(getEntity())) {
				limpar();
				Util.addInfoMessage("Inclus�o realizada com sucesso.");
			} else {
				Util.addWarningMessage("Erro ao incluir no banco de dados.");
			}
		}
	}

	public void alterar() {
		if (validarDados()) {
			if (dao.update(getEntity())) {
				limpar();
				Util.addInfoMessage("Altera��o realizada com sucesso.");
			} else {
				Util.addWarningMessage("Erro ao alterar no banco de dados.");
			}
		}
	}

	public void remover() {
		if (dao.delete(getEntity().getId())) {
			limpar();
			Util.addInfoMessage("Remo��o realizada com sucesso.");
		} else {
			Util.addErrorMessage("Erro ao remover no banco de dados.");
		}
	}

	public void editar(T entity) {
		entity = dao.findById(entity.getId());
		setEntity(entity);
	}
	
	public boolean validarDados() {
		return true;
	}
	
	public void limpar() {
		entity = null;
	}
	

}