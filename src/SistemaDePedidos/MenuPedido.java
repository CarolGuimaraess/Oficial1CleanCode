package SistemaDePedidos;

import java.util.List;
import java.util.Scanner;

public class MenuPedido {

	private List<Pedido> pedidos;
	private Scanner scanner;

	public MenuPedido(List<Pedido> pedidos, Scanner scanner) {
		this.pedidos = pedidos;
		this.scanner = scanner;
	}

	// Método principal que inicia a interação do usuário com o sistema de pedidos.
	// Esse Método chama todas as funções criadas.
	public void executarMenuPedidos() {
		while (true) {
			exibirMenu();
			OpcaoMenu opcao = lerEscolhaDoUsuario();

			switch (opcao) {
			case CRIAR_PEDIDO:
				criarPedido();
				break;
			case ADICIONAR_ITEM:
				adicionarItemPedido();
				break;
			case CALCULAR_TOTAL:
				calcularTotalDoPedido();
				break;
			case LISTAR_ITENS:
				listarItensDoPedido();
				break;
			case SAIR:
				encerrarPrograma();
			}
		}
	}

	// Encerra o programa.
	public void encerrarPrograma() {
		System.out.println("Saindo do sistema.");
		System.exit(0);
	}

	/**
	 * Lê uma escolha válida do usuário a partir da entrada padrão e a retorna como
	 * uma opção de menu.
	 */
	private OpcaoMenu lerEscolhaDoUsuario() {
		while (true) {
			try {
				System.out.print("Escolha uma opção: ");
				int escolha = Integer.parseInt(scanner.nextLine());

				for (OpcaoMenu opcao : OpcaoMenu.values()) {
					if (opcao.getValor() == escolha) {
						return opcao;
					}
				}
				/*
				 * Exibe uma mensagem de erro quando o usuário fornece uma escolha que não
				 * corresponde a nenhuma opção do menu
				 */
				System.out.println("Opção inválida. Escolha uma opção do menu.");
			} catch (NumberFormatException e) {
				// Exibe uma mensagem de erro se a entrada do usuário for diferente de número
				System.out.println("Opção inválida. Só é permitido número. Escolha uma opção do menu.");
			}
		}
	}

	// Exibe as opções do menu para o usuário de acordo com o enum OpcaoMenu
	public void exibirMenu() {
		for (OpcaoMenu opcao : OpcaoMenu.values()) {
			System.out.println(opcao.getValor() + ". " + opcao.getDescricao());
		}
	}

	// Cria um novo pedido e adiciona à lista de pedidos.
	public void criarPedido() {
		pedidos.add(new Pedido());
		System.out.println("Pedido criado.");
	}

	// Adiciona um item ao pedido atual.
	public void adicionarItemPedido() {
		if (!temPedidoAtual()) {
			System.out.println("Crie um pedido primeiro.");
			return;
		}

		System.out.print("Digite o nome do item: ");
		String nomeItem = scanner.nextLine();

		double precoItem;
		System.out.print("Digite o preço do item: ");
		if (scanner.hasNextDouble() && (precoItem = scanner.nextDouble()) > 0) {
			Item item = new Item(nomeItem, precoItem);
			Pedido ultimoPedido = pedidos.get(pedidos.size() - 1);
			ultimoPedido.adicionarItem(item);
			System.out.println("Item adicionado ao pedido.");

			scanner.nextLine(); // Limpar entrada inválida.
		} else {
			// Exibe uma mensagem de erro se a entrada do preço do item for inválida
			System.out.println("Entrada inválida para o preço do item. "
					+ "Só é permitido número positivo e utilize vírgula (,) para valores decimais.");
			scanner.nextLine(); // Limpar entrada inválida.
		}

	}

	// Calcula e exibe o total do pedido atual.
	public void calcularTotalDoPedido() {
		if (!temPedidoAtual()) {
			System.out.println("Crie um pedido primeiro.");
			return;
		}

		Pedido pedidoAtual = pedidos.get(pedidos.size() - 1);
		double total = pedidoAtual.calcularTotal();
		System.out.println("Total do pedido: " + total);
	}

	// Lista os itens do pedido atual.
	public void listarItensDoPedido() {
		if (!temPedidoAtual()) {
			System.out.println("Crie um pedido primeiro.");
			return;
		}

		Pedido pedidoParaListar = pedidos.get(pedidos.size() - 1);
		List<Item> itensDoPedido = pedidoParaListar.getItens();
		System.out.println("Itens do pedido:");
		for (Item item : itensDoPedido) {
			System.out.println(item.getNome() + ": " + item.getPreco());
		}
	}

	// Verifica se há um pedido.
	private boolean temPedidoAtual() {
		return !pedidos.isEmpty();
	}
}
