package SistemaDePedidos;

import java.util.*;

class Pedido {
	private List<Item> listaPedidos = new ArrayList<>();

	public void adicionarItem(Item item) {
		listaPedidos.add(item);
	}

	public double calcularTotal() {
		double total = 0;
		for (Item item : listaPedidos) {
			total += item.getPreco();
		}
		return total;
	}

	public List<Item> getItens() {
		return listaPedidos;
	}
}