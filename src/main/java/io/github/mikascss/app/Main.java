package io.github.mikascss.app;

import io.github.mikascss.cardapio.ItemCardapio;
import io.github.mikascss.cardapio.Lanche;
import io.github.mikascss.cardapio.LancheFactory;
import io.github.mikascss.pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        MenuConsole menu = new MenuConsole();
        RestauranteFacade sistema = new RestauranteFacade();
        boolean executando = true;

        while (executando) {
            menu.exibirMenu();
            switch (menu.lerOpcao()) {
                case 1:
                    Pedido pedido = sistema.criarPedido(menu.lerNomeCliente());
                    do {
                        acrescentarItem(menu, sistema, pedido);
                    } while (menu.lerAdicionarMaisItem());
                    menu.exibirPedido(pedido);
                    break;
                case 2:
                    menu.exibirPedidos(sistema.getPedidos());
                    break;
                case 3:
                    double total = sistema.calcularTotalDoDia();
                    menu.exibirRelatorioGorjetas(sistema.getPedidos(), sistema.calcularTotalGorjetas(),
                            sistema.calcularTotalComGorjetas());
                    menu.exibirFechamentoDoDia(total, sistema.getCombinacoesDoDia());
                    executando = false;
                    break;
                case 4:
                    modificarPedido(menu, sistema);
                    break;
                case 5:
                    registrarGorjeta(menu, sistema);
                    break;
                case 0:
                    menu.exibirSaida();
                    executando = false;
                    break;
                default:
                    menu.exibirOpcaoInvalida();
            }
        }
    }

    private static void registrarGorjeta(MenuConsole menu, RestauranteFacade sistema) {
        int numero = menu.lerNumeroPedido();
        Pedido pedido = sistema.buscarPedido(numero);
        if (pedido == null) {
            menu.exibirMensagem("Pedido não encontrado.");
            return;
        }
        menu.exibirPedido(pedido);
        sistema.registrarGorjeta(numero, menu.lerGorjeta());
        menu.exibirMensagem("Gorjeta registrada.");
    }

    private static void acrescentarItem(MenuConsole menu, RestauranteFacade sistema, Pedido pedido) {
        String nomeLanche = menu.lerOpcaoLanche(sistema.getCardapio());
        Lanche lanche = sistema.criarLanche(nomeLanche);
        menu.personalizarLanche(lanche);
        ItemCardapio item = lanche;
        if (menu.lerAdicionarAdicional("cheddar", 4.00)) {
            item = LancheFactory.adicionarAdicional("cheddar", item);
        }
        if (menu.lerAdicionarAdicional("bacon extra", 6.00)) {
            item = LancheFactory.adicionarAdicional("bacon", item);
        }
        sistema.adicionarItem(pedido.getNrIdentificador(), item);
    }

    private static void modificarPedido(MenuConsole menu, RestauranteFacade sistema) {
        Pedido pedido = sistema.buscarPedido(menu.lerNumeroPedido());
        if (pedido == null) {
            menu.exibirMensagem("Pedido não encontrado.");
            return;
        }
        boolean modificando = true;
        while (modificando) {
            menu.exibirPedido(pedido);
            menu.exibirMenuAlteracao();
            switch (menu.lerOpcao()) {
                case 1:
                    acrescentarItem(menu, sistema, pedido);
                    break;
                case 2:
                    int numeroItem = menu.lerNumeroItem(pedido);
                    if (numeroItem != 0) {
                        sistema.removerItem(pedido.getNrIdentificador(), numeroItem);
                        menu.exibirMensagem("Item removido.");
                    }
                    break;
                case 3:
                    sistema.cancelarPedido(pedido.getNrIdentificador());
                    menu.exibirMensagem("Pedido cancelado.");
                    modificando = false;
                    break;
                case 0:
                    modificando = false;
                    break;
                default:
                    menu.exibirOpcaoInvalida();
            }
        }
    }
}
