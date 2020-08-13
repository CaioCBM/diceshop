package br.unitins.diceshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.diceshop.application.Session;
import br.unitins.diceshop.application.Util;
import br.unitins.diceshop.dao.DadoDAO;
import br.unitins.diceshop.model.Dado;
import br.unitins.diceshop.model.ItemVenda;

@Named
@ViewScoped
public class VendaDadoController implements Serializable {

	private static final long serialVersionUID = -8413311224021825448L;

	private String descricao = null;
	private List<Dado> listaDado = null;
	private String filtro = null;

	public void pesquisarTipoDado(int tipoDado) {
		DadoDAO dao = new DadoDAO();
		listaDado = dao.findByTipoDado(tipoDado);
	}
	
	public void pesquisar() {
		DadoDAO dao = new DadoDAO();
		listaDado = dao.findByDescricao(getDescricao());
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	public void adicionar(int idDado) {
		DadoDAO dao = new DadoDAO();
		Dado Dado = dao.findById(idDado);
		// verifica se existe um carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona um carrinho (de itens de venda) na sessao
			Session.getInstance().setAttribute("carrinho", 
					new ArrayList<ItemVenda>());
		}
		
		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = 
				(ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		
		// criando um item de venda para adicionar no carrinho
		ItemVenda item = new ItemVenda();
		item.setDado(Dado);
		item.setValor(Dado.getPreco());
		
		// adicionando o item no carrinho (variavel local) 
		carrinho.add(item);
		
		// atualizando o carrinho na sessao
		Session.getInstance().setAttribute("carrinho", carrinho);
		
		Util.addInfoMessage("Adicionados " + carrinho.size() + " itens ao Carrinho");
		
	}

	public List<Dado> getListaDado() {
		if (listaDado == null) {
			DadoDAO dao = new DadoDAO();
			listaDado = dao.findAll();
		}
		return listaDado;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
