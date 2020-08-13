package br.unitins.diceshop.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.diceshop.application.Session;
import br.unitins.diceshop.application.Util;
import br.unitins.diceshop.dao.VendaDAO;
import br.unitins.diceshop.model.ItemVenda;
import br.unitins.diceshop.model.Usuario;
import br.unitins.diceshop.model.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = 780477496476930858L;

	private Venda venda;

	public Venda getVenda() {
		if (venda == null)
			venda = new Venda();

		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = (ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		// adicionando os itens do carrinho na venda
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();
		venda.setListaItemVenda(carrinho);

		return venda;
	}

	public void finalizar() {
		Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		List<ItemVenda> carrinho = (ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		if (carrinho.size() == 0) {
			Util.addErrorMessage("Carrinho vazio!");
			return;
		}
		
		// montar a venda
		Venda venda = new Venda();
		venda.setData(LocalDate.now());
		venda.setUsuario(usuario);
		venda.setListaItemVenda(carrinho);
		// salvar no banco
		VendaDAO dao = new VendaDAO();
		if (dao.create(venda)) {
			Util.addInfoMessage("Venda realizada com sucesso.");
			// limpando o carrinho
			Session.getInstance().setAttribute("carrinho", null);
		} else {
			Util.addErrorMessage("Erro ao finalizar a Venda.");
		}

	}

	public void remover(int idProduto) {

		// Obtendo o carrinho da sessão.

		List<ItemVenda> carrinho = (ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		int cont = 0;
		for (ItemVenda itemVenda : carrinho) {
			if (itemVenda.getDado().getId() == idProduto) {
				carrinho.remove(cont);
				break;
			}
			cont++;
		}
	}

	public void setVenda(Venda venda) {

		this.venda = venda;
	}

}
