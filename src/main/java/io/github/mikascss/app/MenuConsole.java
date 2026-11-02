package io.github.mikascss.app;

import io.github.mikascss.cardapio.ItemCardapio;
import io.github.mikascss.cardapio.Lanche;
import io.github.mikascss.pedido.Pedido;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MenuConsole {
    private final Scanner sc = new Scanner(System.in);
    private final NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

    public void exibirMenu() {
        System.out.println("=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("4 - Modificar um pedido");
        System.out.println("5 - Registrar ou atualizar gorjeta de um pedido");
        System.out.println("0 - Sair");
    }

    public int lerOpcao() {
        while (true) {
            System.out.print("Escolha sua opção: ");
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("A opção deve ser um número, escolha novamente.");
            }
        }
    }

    public String lerNomeCliente() {
        while (true) {
            System.out.print("Nome do cliente: ");
            String nome = sc.nextLine().trim();
            if (!nome.isEmpty()) {
                return nome;
            }
            System.out.println("O nome do cliente não pode ser vazio.");
        }
    }

    public String lerOpcaoLanche(Map<String, Double> cardapio) {
        ArrayList<String> nomes = new ArrayList<>(cardapio.keySet());
        while (true) {
            System.out.println("=== CARDÁPIO ===");
            for (int i = 0; i < nomes.size(); i++) {
                String nome = nomes.get(i);
                System.out.println((i + 1) + " - " + nome + " — " + moeda.format(cardapio.get(nome)));
            }
            int opcao = lerOpcao();
            if (opcao >= 1 && opcao <= nomes.size()) {
                return nomes.get(opcao - 1);
            }
            exibirOpcaoInvalida();
        }
    }

    public void personalizarLanche(Lanche lanche) {
        lanche.setQueijo(!lerRemoverItem("queijo"));
        lanche.setAlface(!lerRemoverItem("alface"));
        lanche.setTomate(!lerRemoverItem("tomate"));
        lanche.setCebola(!lerRemoverItem("cebola"));
    }

    public boolean lerRemoverItem(String item){
        while(true) {
            System.out.print("Deseja remover " + item + " do lanche? (s/n): ");
            String resposta = sc.nextLine().trim();
            if (resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim")) {
                return true;
            }
            if (resposta.equalsIgnoreCase("n") || resposta.equalsIgnoreCase("não")
                    || resposta.equalsIgnoreCase("nao")) {
                return false;
            }
            System.out.println("Responda s para sim ou n para não.");
        }
    }

    public boolean lerAdicionarAdicional(String adicional, double preco) {
        while (true) {
            System.out.print("Deseja adicionar " + adicional + " (+ " + moeda.format(preco) + ")? (s/n): ");
            String resposta = sc.nextLine().trim();
            if (resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim")) {
                return true;
            }
            if (resposta.equalsIgnoreCase("n") || resposta.equalsIgnoreCase("não")
                    || resposta.equalsIgnoreCase("nao")) {
                return false;
            }
            System.out.println("Responda s para sim ou n para não.");
        }
    }

    public boolean lerAdicionarMaisItem() {
        while (true) {
            System.out.print("Deseja adicionar mais um item? (s/n): ");
            String resposta = sc.nextLine().trim();
            if (resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim")) {
                return true;
            }
            if (resposta.equalsIgnoreCase("n") || resposta.equalsIgnoreCase("não")
                    || resposta.equalsIgnoreCase("nao")) {
                return false;
            }
            System.out.println("Responda s para sim ou n para não.");
        }
    }

    public void exibirPedido(Pedido pedido) {
        System.out.println("Pedido #" + pedido.getNrIdentificador() + " — " + pedido.getNomeCliente());
        int numeroItem = 1;
        for (ItemCardapio lanche : pedido.getItens()) {
            System.out.println("  " + numeroItem++ + " - " + lanche.getDescricao() + " — " + moeda.format(lanche.getPreco()));
        }
        System.out.println("Total do pedido: " + moeda.format(pedido.calcularTotal()));
        System.out.println("Gorjeta: " + moeda.format(pedido.getGorjeta()));
    }

    public void exibirPedidos(Collection<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado no dia.");
            return;
        }
        for (Pedido pedido : pedidos) {
            exibirPedido(pedido);
        }
    }

    public int lerNumeroPedido() {
        System.out.println("Informe o número do pedido abaixo.");
        return lerOpcao();
    }

    public void exibirMenuAlteracao() {
        System.out.println("1 - Acrescentar item");
        System.out.println("2 - Remover item");
        System.out.println("3 - Cancelar pedido inteiro");
        System.out.println("0 - Voltar ao menu principal");
    }

    public int lerNumeroItem(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            exibirMensagem("O pedido não possui itens para remover.");
            return 0;
        }
        exibirPedido(pedido);
        while (true) {
            System.out.println("Informe o número do item para remover (0 para voltar):");
            int numero = lerOpcao();
            if (numero >= 0 && numero <= pedido.getItens().size()) {
                return numero;
            }
            exibirOpcaoInvalida();
        }
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public double lerGorjeta() {
        while (true) {
            System.out.print("Valor da gorjeta: ");
            try {
                double valor = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                if (Double.isFinite(valor) && valor >= 0) {
                    return valor;
                }
            } catch (NumberFormatException e) {}
            System.out.println("Informe um valor não negativo, como 5,50.");
        }
    }

    public void exibirRelatorioGorjetas(Collection<Pedido> pedidos, double totalGorjetas,
                                       double totalComGorjetas) {
        System.out.println("=== RELATÓRIO DE GORJETAS ===");
        for (Pedido pedido : pedidos) {
            if (pedido.getGorjeta() > 0) {
                System.out.println("Pedido #" + pedido.getNrIdentificador() + " — "
                        + pedido.getNomeCliente() + ": " + moeda.format(pedido.getGorjeta()));
            }
        }
        System.out.println("Total de gorjetas: " + moeda.format(totalGorjetas));
        System.out.println("Total geral (vendas + gorjetas): " + moeda.format(totalComGorjetas));
    }

    public void exibirFechamentoDoDia(double total, Set<String> combinacoes) {
        System.out.println("Total vendido no dia: " + moeda.format(total));
        System.out.println("Combinações diferentes: " + combinacoes.size());
        for (String descricao : new TreeSet<>(combinacoes)) {
            System.out.println("  - " + descricao);
        }
        System.out.println("Restaurante fechado.");
    }

    public void exibirSaida() {
        System.out.println("Sistema encerrado.");
    }

    public void exibirOpcaoInvalida() {
        System.out.println("Opção inválida, escolha novamente.");
    }
}
