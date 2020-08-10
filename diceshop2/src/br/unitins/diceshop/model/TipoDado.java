package br.unitins.diceshop.model;

public enum TipoDado {
	
	NAO_DEFINIDO(0, "Indefinido"),
	SETCOMPLETO (1, "Set Completo"),
	D4(2, "D4"),
	D6(3, "D6"),
	D8(4, "D8"),
	D10(5, "D10"),
	D12(6, "D12"),
	D20(7, "D20"),
	D30(8, "D30"),
	D100(9, "D100");
	
	
	private int id;
	private String label;
	
	private TipoDado(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static TipoDado valueOf(int valor) {
		for (TipoDado tipoDado : TipoDado.values()) {
			if (valor == tipoDado.getId())
				return tipoDado;
		} 
		return null;
	}
	
}
