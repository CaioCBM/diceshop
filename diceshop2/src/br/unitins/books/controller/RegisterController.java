package br.unitins.books.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.books.application.Session;
import br.unitins.books.application.Util;
import br.unitins.books.dao.UsuarioDAO;
import br.unitins.books.model.Usuario;

@Named
@RequestScoped
public class RegisterController {
	
	public String register() {
		return "login.xhtml?faces-redirect=true";
	}
}
