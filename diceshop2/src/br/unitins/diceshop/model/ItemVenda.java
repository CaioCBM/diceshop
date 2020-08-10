package br.unitins.diceshop.model;

public class ItemVenda extends Entity<ItemVenda> {
	private static final long serialVersionUID = 4337294373727203428L;
	private Dado dado;
	private Float valor;
	private Venda venda;

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Dado getDado() {
		return dado;
	}

	public void setDado(Dado dado) {
		this.dado = dado;
	}


}
