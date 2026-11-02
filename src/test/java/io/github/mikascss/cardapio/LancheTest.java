package io.github.mikascss.cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LancheTest {

    @Test
    void quandoColocarPrecoNegativoLancarExcecao() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Lanche("Eggs Bacon", -13));

        assertEquals("O preço do lanche não deve ser negativo.", exception.getMessage());
    }
}