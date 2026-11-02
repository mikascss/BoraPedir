package io.github.mikascss.cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComBaconExtraTest {

    @Test
    void xBaconComCheddarDeveCustar26() {
        ItemCardapio item = new ComCheddar(LancheFactory.xBacon());
        assertEquals(26.0, item.getPreco(), 0.001);
    }

    @Test
    void xSaladaComCheddarDeveVirDescricaoCorreta(){
        ItemCardapio item = new ComCheddar(LancheFactory.xSalada());

        assertEquals("X-Salada + cheddar", item.getDescricao());
    }

}