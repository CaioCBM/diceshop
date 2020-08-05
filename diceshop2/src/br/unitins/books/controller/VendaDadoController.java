package br.unitins.books.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.application.Session;
import br.unitins.books.application.Util;
import br.unitins.books.dao.DadoDAO;
import br.unitins.books.model.ItemVenda;
import br.unitins.books.model.Dado;

@Named
@ViewScoped
public class VendaDadoController implements Serializable {

	private static final long serialVersionUID = -8413311224021825448L;

	private String descricao;
	private List<Dado> listaDado = null;
	
	public void pesquisar() {
		listaDado = null;
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
		
		Util.addInfoMessage("Dado adicionado no carrinho. "
				+ "Quantidade de Itens: " + carrinho.size());
		
	}

	public List<Dado> getListaDado() {
		if (listaDado == null) {
			DadoDAO dao = new DadoDAO();
			listaDado = dao.findAll();
			if (listaDado == null)
				listaDado = new ArrayList<Dado>();
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
