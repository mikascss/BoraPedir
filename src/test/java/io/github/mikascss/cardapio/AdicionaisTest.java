package io.github.mikascss.cardapio;

import io.github.mikascss.app.RestauranteFacade;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class AdicionaisTest {
    @Test
    void baconExtraDeveAcrescentarSeisReais() {
        ItemCardapio item = new ComBaconExtra(LancheFactory.xBurger());
        assertEquals("X-Burger + bacon extra", item.getDescricao());
        assertEquals(22, item.getPreco(), 0.001);
    }

    @Test
    void ambosAdicionaisDevemPreservarPersonalizacaoEEntrarNoFechamento() {
        var sistema = new RestauranteFacade();
        var pedido = sistema.criarPedido("Ana");
        Lanche base = sistema.criarLanche("X-Bacon");
        base.setCebola(false);
        ItemCardapio item = new ComBaconExtra(new ComCheddar(base));
        sistema.adicionarItem(pedido.getNrIdentificador(), item);
        assertEquals("X-Bacon sem cebola + cheddar + bacon extra", item.getDescricao());
        assertEquals(32, pedido.calcularTotal(), 0.001);
        assertEquals(32, sistema.calcularTotalDoDia(), 0.001);
        assertEquals(Set.of(item.getDescricao()), sistema.getCombinacoesDoDia());
        assertEquals(22, base.getPreco(), 0.001);
        assertEquals("X-Bacon", sistema.criarLanche("X-Bacon").getDescricao());
    }
}
