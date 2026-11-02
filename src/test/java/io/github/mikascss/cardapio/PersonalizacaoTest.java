package io.github.mikascss.cardapio;

import io.github.mikascss.app.RestauranteFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalizacaoTest {
    @Test
    void novoLancheDeveVirComTodosOsIngredientes() {
        Lanche lanche = LancheFactory.xBacon();
        assertTrue(lanche.getQueijo());
        assertTrue(lanche.getAlface());
        assertTrue(lanche.getTomate());
        assertTrue(lanche.getCebola());
        assertEquals("X-Bacon", lanche.getDescricao());
    }

    @Test
    void retirarIngredientesDeveAlterarDescricaoSemAlterarPreco() {
        Lanche lanche = LancheFactory.xBacon();
        lanche.setQueijo(false);
        lanche.setAlface(false);
        lanche.setTomate(false);
        lanche.setCebola(false);
        assertEquals("X-Bacon sem queijo sem alface sem tomate sem cebola", lanche.getDescricao());
        assertEquals(22.0, lanche.getPreco(), 0.001);
    }

    @Test
    void adicionalDevePreservarDescricaoDoLanchePersonalizado() {
        Lanche lanche = LancheFactory.xBurger();
        lanche.setCebola(false);
        ItemCardapio item = new ComCheddar(lanche);
        assertEquals("X-Burger sem cebola + cheddar", item.getDescricao());
        assertEquals(20.0, item.getPreco(), 0.001);
    }
}
