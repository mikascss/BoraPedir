package io.github.mikascss.cardapio;

public abstract class AdicionalDecorator implements ItemCardapio {
    protected final ItemCardapio item;

    protected AdicionalDecorator(ItemCardapio item) {
        this.item = item;
    }
}
