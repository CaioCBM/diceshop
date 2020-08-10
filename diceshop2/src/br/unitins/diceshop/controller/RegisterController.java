package br.unitins.diceshop.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.diceshop.application.Session;
import br.unitins.diceshop.application.Util;
import br.unitins.diceshop.dao.UsuarioDAO;
import br.unitins.diceshop.model.Usuario;

@Named
@RequestScoped
public class RegisterController {
	
	public String register() {
		return "login.xhtml?faces-redirect=true";
	}
}
